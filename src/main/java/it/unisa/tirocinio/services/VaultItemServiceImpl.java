package it.unisa.tirocinio.services;


import it.unisa.tirocinio.beans.VaultItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class VaultItemServiceImpl implements VaultItemService {
    @Value("${app.vaultitems-service-url}")
    private String ENDPOINT;
    private final static String CREATE_URI = "/save-item";
    private final static String ALL_URI = "/items";
    private final static String FIND_BY_ID_URI = "/ownerId";

    @Override
    public boolean saveItem(VaultItem item) {
        WebClient client = httpWebClient();

        Optional<ResponseEntity<String>> optional = client.post().uri(CREATE_URI)
                .contentType(MediaType.APPLICATION_JSON).bodyValue(item)
                .retrieve().onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.toEntity(String.class).map(CustomResponseException::new))
                .toEntity(String.class).blockOptional();

        return optional.map(entity -> Objects.equals(entity.getBody(), "true")).orElseThrow();
    }

    @Override
    public List<VaultItem> findAll() {
        WebClient client = httpWebClient();

        Optional<ResponseEntity<List<VaultItem>>> opt = client.get().uri(ALL_URI)
                .retrieve().onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.toEntity(String.class).map(CustomResponseException::new))
                .toEntityList(VaultItem.class).blockOptional();

        return opt.map(HttpEntity::getBody).orElseThrow();
    }

    @Override
    public List<VaultItem> findAllById(String ownerId) {
        WebClient client = httpWebClient();

        Optional<ResponseEntity<List<VaultItem>>> opt = client.post()
                .uri(FIND_BY_ID_URI).bodyValue(ownerId)
                .retrieve().onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.toEntity(String.class).map(CustomResponseException::new))
                .toEntityList(VaultItem.class).blockOptional();

        return opt.map(HttpEntity::getBody).orElseThrow();
    }


    private WebClient httpWebClient() {
        return WebClient.builder().baseUrl(ENDPOINT + "kotlin").build();
    }
}
