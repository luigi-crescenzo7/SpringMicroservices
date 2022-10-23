package it.unisa.tirocinio.Main;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.ConnectException;
import java.util.Optional;

@Service
@Slf4j
public class SimpleService {

    private final static String URL = "http://localhost:8181";
    private final static String ENDPOINT = "/events";

    public String getJSON() {

        log.info("Feching microservice endpoint...");
        final WebClient client = WebClient.builder().baseUrl(URL).build();
        Optional<String> opt = client.get()
                .uri(ENDPOINT)
                .retrieve().bodyToMono(String.class)
                .onErrorMap(ConnectException.class, t -> {
                    log.info(t.getMessage());
                    throw new RuntimeException(t.getCause());
                }).blockOptional();

        return opt.orElse(null);
    }
}
