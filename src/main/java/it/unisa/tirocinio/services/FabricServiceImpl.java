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
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class FabricServiceImpl implements FabricService {

    private final ObjectMapper mapper;
    @Value("${app.fabric-rest-url}")
    private String FABRIC_ENDPOINT;
    private static final String ALL_URI = "/api/private/all";
    private static final String SAVE_URI = "/api/private/save";
    private static final String ASSETS_BY_OWNER_ID = "/api/private/ownerId";
    public FabricServiceImpl(final ObjectMapper beanMapper) {
        this.mapper = beanMapper;
    }

    @Override
    public List<IdCardItem> findAllAssets() {
        log.info("Querying fabric service...");

        WebClient client = httpsWebClient();
        Optional<ResponseEntity<List<IdCardItem>>> opt = client.get()
                .uri(ALL_URI).retrieve().onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.toEntity(String.class).map(CustomResponseException::new))
                .toEntityList(IdCardItem.class).blockOptional();

        return opt.map(HttpEntity::getBody).orElseThrow();
    }

    @Override
    public List<IdCardItem> findAssetsByOwnerId(String ownerId) {
        log.info("find assets by owner id");
        JsonNode node = mapper.createObjectNode().put("ownerId", ownerId);

        WebClient client = httpsWebClient();

        Optional<ResponseEntity<List<IdCardItem>>> optResult =
                client.post().uri(ASSETS_BY_OWNER_ID)
                        .bodyValue(node).retrieve().onStatus(HttpStatusCode::isError, clientResponse ->
                                clientResponse.toEntity(String.class).map(CustomResponseException::new))
                        .toEntityList(IdCardItem.class).blockOptional();

        return optResult.map(HttpEntity::getBody).orElseThrow();
    }

    @Override
    public IdCardItem saveItem(IdCardItem item) {
        JsonNode node = mapper.createObjectNode().set("asset", mapper.valueToTree(item));
        WebClient client = httpsWebClient();
        Optional<ResponseEntity<IdCardItem>> opt = client.post().uri(SAVE_URI)
                .contentType(MediaType.APPLICATION_JSON).bodyValue(node)
                .retrieve().onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.toEntity(String.class).map(CustomResponseException::new))
                .toEntity(IdCardItem.class).blockOptional();

        return opt.map(HttpEntity::getBody).orElseThrow();
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

