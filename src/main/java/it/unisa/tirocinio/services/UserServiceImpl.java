package it.unisa.tirocinio.services;


import it.unisa.tirocinio.beans.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Value("${app.user-service-url}")
    private String userServiceUrl;

    @Override
    public boolean saveUser(User user) {

        WebClient client = buildWebClient();
        String SAVE_URI = "/register";

        Optional<String> optResult = client.post().uri(SAVE_URI)
                .bodyValue(user).retrieve().onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.toEntity(String.class).map(CustomResponseException::new))
                .bodyToMono(String.class)
                .blockOptional();

        return optResult.map(saveResponse -> saveResponse.contains("UserId")).orElseThrow();
    }

    @Override
    public String login(String email, String password) {
        WebClient client = buildWebClient();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("email", email);
        map.add("password", password);

        String LOGIN_URI = "/login";
        Optional<ResponseEntity<String>> opt = client.post().uri(LOGIN_URI)
                .body(BodyInserters.fromFormData(map))
                .retrieve().onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.toEntity(String.class).map(CustomResponseException::new))
                .toEntity(String.class).blockOptional();

        String result = opt.map(HttpEntity::getBody).orElse("");

        if (result.contains("not authorized"))
            throw new CustomResponseException(new ResponseEntity<>("unauthorized", HttpStatus.UNAUTHORIZED));

        return result.substring(result.indexOf(":") + 1);
    }

    private WebClient buildWebClient() {
        return WebClient.builder().baseUrl(userServiceUrl + "user").build();
    }
}
