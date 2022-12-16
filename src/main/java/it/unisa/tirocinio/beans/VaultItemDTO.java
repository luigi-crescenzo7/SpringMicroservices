package it.unisa.tirocinio.beans;

import lombok.Data;

@Data
public class VaultItemDTO {
    private VaultItem item;
    private String ownerId;

    public VaultItemDTO(VaultItem item, String s) {
        this.item = item;
        this.ownerId = s;
    }
}
