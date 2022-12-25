package it.unisa.tirocinio.services;


import it.unisa.tirocinio.beans.IdCardItem;

import java.util.List;

public interface FabricService {
    String findAllAssets();

    List<IdCardItem> findAssetsByOwnerId(String ownerId);

    String saveItem(IdCardItem item);
}
