package it.unisa.tirocinio.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@Slf4j
// SpringBoot REST MongoDB Endpoint
public class UserServiceImpl implements UserService {


    @Value("${app.users-rest-url}")
    private String URL;
    private String URI = "/all";

    private String LOGIN = "/login";

    @Override
    public String getUsers() {
        log.info("Querying microservice endpoint...");
        final WebClient client = buildWebClient();

        Optional<String> opt = client.get()
                .uri(URI)
                .retrieve()
                .bodyToMono(String.class).blockOptional();

        return opt.orElse(null);
    }

    @Override
    public boolean login(String email, String password) {
        log.info("Querying microservice endpoint...");
        final WebClient client = buildWebClient();

        Optional<String> opt = client.post().uri(LOGIN)
                .body(BodyInserters.fromFormData("email", email).with("passwordHash", password))
                .retrieve()
                .bodyToMono(String.class).blockOptional();

        return opt.map(s -> s.equalsIgnoreCase("authorized")).orElse(false);
    }


    private WebClient buildWebClient() {
        return WebClient.builder().baseUrl(URL + "users/").build();
    }
}
