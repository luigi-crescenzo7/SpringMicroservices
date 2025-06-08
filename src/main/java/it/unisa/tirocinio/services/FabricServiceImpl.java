package it.unisa.tirocinio.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.resolver.DefaultAddressResolverGroup;
import it.unisa.tirocinio.beans.IdCardItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.List;
import java.util.Optional;


@Service
public class FabricServiceImpl implements FabricService {

    private final Logger logger = LoggerFactory.getLogger(FabricServiceImpl.class);
    private final ObjectMapper mapper;
    @Value("${app.fabric-rest-url}")
    private String fabricRestServiceUrl;
    private static final String SAVE_URI = "/api/private/save";
    private static final String ASSETS_BY_OWNER_ID = "/api/private/ownerId";

    public FabricServiceImpl(final ObjectMapper beanMapper) {
        this.mapper = beanMapper;
    }

    @Override
    public List<IdCardItem> findAssetsByOwnerId(String ownerId) {
        logger.info("find assets by owner id");
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
        return WebClient.builder().baseUrl(fabricRestServiceUrl).build();
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
                .baseUrl(fabricRestServiceUrl.contains("https") ? fabricRestServiceUrl : local).build();
    }
}

