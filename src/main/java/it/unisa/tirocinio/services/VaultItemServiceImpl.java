package it.unisa.tirocinio.services;

import it.unisa.tirocinio.beans.VaultItem;
import it.unisa.tirocinio.beans.VaultItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@Slf4j
public class VaultItemServiceImpl implements VaultItemService {

    @Override
    public boolean saveItem(VaultItem item) {
        WebClient client = httpWebClient();

        Optional<String> optional = client.post().uri("/save-item")
                .bodyValue(new VaultItemDTO(item, item.getOwnerId()))
                .retrieve()
                .bodyToMono(String.class).blockOptional();
        String restResult = optional.orElseThrow();
        return restResult.equals("true");
    }


    private WebClient httpWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8082/kotlin").build();
    }
}
