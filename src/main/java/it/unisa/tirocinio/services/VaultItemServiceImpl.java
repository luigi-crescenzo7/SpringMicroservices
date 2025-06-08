package it.unisa.tirocinio.services;


import it.unisa.tirocinio.beans.VaultItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class VaultItemServiceImpl implements VaultItemService {

    private final Logger logger = LoggerFactory.getLogger(VaultItemServiceImpl.class);
    @Value("${app.vaultitems-service-url}")
    private String valutItemServiceUrl;
    private final static String CREATE_URI = "/save-item";
    private final static String FIND_BY_ID_URI = "/ownerId";
    private final static String UPDATE_URI = "/update-item";
    private final static String DELETE_URI = "/delete-item";

    @Override
    public boolean saveItem(VaultItem item) {
        return createUpdateDelete(item, CREATE_URI);
    }

    @Override
    public boolean updateItem(VaultItem item) {
        return createUpdateDelete(item, UPDATE_URI);
    }

    private boolean createUpdateDelete(VaultItem item, String uri) {
        WebClient client = httpWebClient();

        Optional<ResponseEntity<String>> optional = client.post().uri(uri)
                .contentType(MediaType.APPLICATION_JSON).bodyValue(item)
                .retrieve().onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.toEntity(String.class).map(CustomResponseException::new))
                .toEntity(String.class).blockOptional();

        return optional.map(entity -> Objects.equals(entity.getBody(), "true")).orElseThrow();
    }

    @Override
    public boolean deleteItem(VaultItem item) {
        return createUpdateDelete(item, DELETE_URI);
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
        return WebClient.builder().baseUrl(valutItemServiceUrl + "vault").build();
    }
}
