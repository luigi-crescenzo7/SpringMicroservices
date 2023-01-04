package it.unisa.tirocinio.services;


import it.unisa.tirocinio.beans.VaultItem;

import java.util.List;

public interface VaultItemService {
    boolean saveItem(VaultItem item);
    List<VaultItem> findAll();
    List<VaultItem> findAllById(String ownerId);
}
