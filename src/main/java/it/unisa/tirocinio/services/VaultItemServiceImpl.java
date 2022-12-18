package it.unisa.tirocinio.services;

import it.unisa.tirocinio.beans.VaultItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@Slf4j
public class VaultItemServiceImpl implements VaultItemService {

    private final static String URI = "/save-item";
    private final static String ENDPOINT = "http://localhost:8082/kotlin";

    @Override
    public boolean saveItem(VaultItem item) {
        WebClient client = httpWebClient();

        Optional<String> optional = client.post().uri(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(item)
                .retrieve()
                .bodyToMono(String.class).blockOptional();
        String restResult = optional.orElseThrow();
        return restResult.equals("true");
    }


    private WebClient httpWebClient() {
        return WebClient.builder().baseUrl(ENDPOINT).build();
    }
}
