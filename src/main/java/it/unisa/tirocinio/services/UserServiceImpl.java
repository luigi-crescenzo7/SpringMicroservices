package it.unisa.tirocinio.services;


import it.unisa.tirocinio.beans.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
// SpringBoot REST MongoDB Endpoint
public class UserServiceImpl implements UserService {


    @Value("${app.users-rest-url}")
    private String URL;
    private final String ALL_URI = "/all";
    private final String SAVE_URI = "/register";

    private final String LOGIN_URI = "/login";

    @Override
    public List<User> findAll() {
        log.info("Querying microservice endpoint...");
        final WebClient client = buildWebClient();

        Optional<ResponseEntity<List<User>>> opt = client.get()
                .uri(ALL_URI)
                .retrieve().onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.toEntity(String.class).map(CustomResponseException::new))
                .toEntityList(User.class).blockOptional();

        return opt.map(HttpEntity::getBody).orElseThrow();
    }

    @Override
    public boolean saveUser(User user) {
        Optional<String> optResult;
        WebClient client = buildWebClient();
        optResult = client.post().uri(SAVE_URI)
                .bodyValue(user).retrieve().onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.toEntity(String.class).map(CustomResponseException::new))
                .bodyToMono(String.class)
                .blockOptional();

        return optResult.map(saveResponse -> saveResponse.contains("UserId")).orElseThrow();
    }

    @Override
    public boolean login(String email, String password) {
        log.info("Querying microservice endpoint...");
        WebClient client = buildWebClient();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("email", email);
        map.add("password", password);

        Optional<String> opt = client.post().uri(LOGIN_URI)
                .body(BodyInserters.fromFormData(map))
                .retrieve().onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.toEntity(String.class).map(CustomResponseException::new))
                .bodyToMono(String.class)
                .blockOptional();

        return opt.map(loginResult -> loginResult.equalsIgnoreCase("authorized"))
                .orElseThrow(() -> new RuntimeException("user not authorized"));
    }


    private WebClient buildWebClient() {
        return WebClient.builder().baseUrl(URL + "user-service").build();
    }
}
