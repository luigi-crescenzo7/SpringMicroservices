package it.unisa.tirocinio.services;


import it.unisa.tirocinio.beans.IdCardItem;

public interface FabricService {
    String findAllAssets();

    String saveItem(IdCardItem item);
}
