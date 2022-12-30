package it.unisa.tirocinio.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.resolver.DefaultAddressResolverGroup;
import it.unisa.tirocinio.beans.IdCardItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class FabricServiceImpl implements FabricService {

    private final ObjectMapper mapper;

    public FabricServiceImpl(final ObjectMapper beanMapper) {
        this.mapper = beanMapper;
    }

    @Value("${app.fabric-rest-url}")
    private String FABRIC_ENDPOINT;
    private static final String ALL_URI = "/api/contract/all";
    private static final String SAVE_URI = "/api/contract/save";
    private static final String ASSETS_BY_OWNER_ID = "/api/contract/ownerId";

    @Override
    public List<IdCardItem> findAllAssets() {
        log.info("Querying fabric service...");

        WebClient client = httpsWebClient();
        Optional<ResponseEntity<List<IdCardItem>>> opt = client.get()
                .uri(ALL_URI).retrieve()
                .toEntityList(IdCardItem.class).blockOptional();

        ResponseEntity<List<IdCardItem>> response = opt.orElseThrow();
        log.info("status code : " + response.getStatusCode());
        if (!response.hasBody() || !response.getStatusCode().is2xxSuccessful())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error code: " + response.getStatusCode());

        return response.getBody();
    }

    @Override
    public List<IdCardItem> findAssetsByOwnerId(String ownerId) {
        log.info("find assets by owner id");
        JsonNode node = mapper.createObjectNode().put("ownerId", ownerId);

        WebClient client = httpsWebClient();

        Optional<ResponseEntity<List<IdCardItem>>> optResult =
                client.post().uri(ASSETS_BY_OWNER_ID)
                        .bodyValue(node).retrieve()
                        .toEntityList(IdCardItem.class).blockOptional();

        ResponseEntity<List<IdCardItem>> response = optResult.orElseThrow();
        log.info("status code : " + response.getStatusCode());

        if (!response.hasBody() || !response.getStatusCode().is2xxSuccessful())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status code: " + response.getStatusCode());

        return response.getBody();
    }

    @Override
    public String saveItem(IdCardItem item) {
        JsonNode node = mapper.createObjectNode().set("asset", mapper.valueToTree(item));
        WebClient client = httpsWebClient();
        Optional<String> opt = client.post()
                .uri(SAVE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(node)
                .retrieve().bodyToMono(String.class).blockOptional();
        return opt.orElse(null);
    }

    private WebClient httpWebClient() {
        return WebClient.builder().baseUrl(FABRIC_ENDPOINT).build();
    }

    private WebClient httpsWebClient() {
        SslContext sslContext;
        try {
            sslContext = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }

        HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE)
                .secure(provider -> provider.sslContext(sslContext));

        String local = "https://localhost:8447/";

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(FABRIC_ENDPOINT.contains("https") ? FABRIC_ENDPOINT : local).build();
    }
}

