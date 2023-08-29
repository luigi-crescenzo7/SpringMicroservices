package it.unisa.tirocinio.services;


import it.unisa.tirocinio.beans.VaultItem;

import java.util.List;

public interface VaultItemService {
    boolean saveItem(VaultItem item);

    boolean updateItem(VaultItem item);

    boolean deleteItem(VaultItem item);

    List<VaultItem> findAllById(String ownerId);
}
