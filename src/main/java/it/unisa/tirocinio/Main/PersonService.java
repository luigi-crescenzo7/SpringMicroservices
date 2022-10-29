package it.unisa.tirocinio.Main;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@Slf4j
public class PersonService {

    private final String URL = "http://localhost:8081/persons";
    private final String URI = "/all";


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
