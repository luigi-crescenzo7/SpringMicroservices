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
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.Optional;


@Service
@Slf4j
public class FabricServiceImpl implements FabricService {
    private final static ObjectMapper mapper = new ObjectMapper();

    @Value("${app.fabric-rest-url}")
    private String FABRIC_URL;

    @Override
    public String findAllAssets() {
        log.info("Querying fabric service...");

        WebClient client = httpsWebClient();
        Optional<String> opt = client.get()
                .uri("/api/contract/all").retrieve()
                .bodyToMono(String.class).blockOptional();

        return opt.orElse(null);
    }

    @Override
    public String saveItem(IdCardItem item) {
        JsonNode node = mapper.createObjectNode().set("asset", mapper.valueToTree(item));
        WebClient client = httpsWebClient();
        Optional<String> opt = client.post()
                .uri("/api/contract/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(node)
                //.bodyValue(new IdCardItemDTO(item))
                .retrieve().bodyToMono(String.class).blockOptional();
        return opt.orElse(null);
    }

    private WebClient httpWebClient() {
        return WebClient.builder().baseUrl(FABRIC_URL).build();
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

        log.info(FABRIC_URL);
        String local = "https://localhost:8447/";

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(FABRIC_URL.contains("https") ? FABRIC_URL : local).build();
    }
}

