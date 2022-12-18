package it.unisa.tirocinio.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaultItemDTO {

    @JsonProperty("item")
    private VaultItem item;
    @JsonProperty("ownerId")
    private String ownerId;
}
