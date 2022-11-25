package it.unisa.tirocinio.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@Slf4j
// SpringBoot REST MongoDB Endpoint
public class UserServiceImpl implements UserService {

    private final String URL = "http://localhost:8081/persons";
    private final String URI = "/all";

    @Override
    public String getPersons() {
        log.info("Querying microservice endpoint...");
        final WebClient client = WebClient.builder().baseUrl(URL).build();

        Optional<String> opt = client.get()
                .uri(URI)
                .retrieve()
                .bodyToMono(String.class).blockOptional();

        return opt.orElse(null);
    }
}
