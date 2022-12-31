package it.unisa.tirocinio.services;


import it.unisa.tirocinio.beans.IdCardItem;

import java.util.List;

public interface FabricService {
    List<IdCardItem> findAllAssets();

    List<IdCardItem> findAssetsByOwnerId(String ownerId);

    IdCardItem saveItem(IdCardItem item);
}
