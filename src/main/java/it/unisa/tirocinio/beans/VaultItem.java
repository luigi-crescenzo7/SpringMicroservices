package it.unisa.tirocinio.beans;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


//@JsonDeserialize(using = VaultItemDeserializer.class)
public class VaultItem {
    private String id;
    private String idCardNumber;
    private String resourceURI;
    private String ownerId;
    private String itemName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;


    public VaultItem() {
    }

    public VaultItem(String itemId, String itemCardNumber, String itemResourceURI, String itemOwnerId, String itemName, LocalDate date) {
        this.id = itemId;
        this.idCardNumber = itemCardNumber;
        this.resourceURI = itemResourceURI;
        this.ownerId = itemOwnerId;
        this.itemName = itemName;
        this.creationDate = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
