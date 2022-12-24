package it.unisa.tirocinio.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisa.tirocinio.beans.VaultItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VaultItemServiceImpl implements VaultItemService {
    private final static ObjectMapper mapper = new ObjectMapper();
    private final static String CREATE_URI = "/save-item";
    private final static String ALL_URI = "/items";
    private final static String ENDPOINT = "http://localhost:8082/kotlin";

    @Override
    public boolean saveItem(VaultItem item) {
        WebClient client = httpWebClient();

        Optional<String> optional = client.post().uri(CREATE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(item)
                .retrieve()
                .bodyToMono(String.class).blockOptional();
        String restResult = optional.orElseThrow();
        return restResult.equals("true");
    }

    @Override
    public List<VaultItem> findAll() {
        WebClient client = httpWebClient();

        Optional<String> opt = client.get()
                .uri(ALL_URI).retrieve()
                .bodyToMono(String.class).blockOptional();

        String result = opt.orElseThrow();
        try {
            return mapper.readerForListOf(VaultItem.class).readValue(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    private WebClient httpWebClient() {
        return WebClient.builder().baseUrl(ENDPOINT).build();
    }
}
