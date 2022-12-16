package it.unisa.tirocinio.beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;



@Data
public class VaultItem {
    private String id;
    private String idCardNumber;
    private String resourceURI;
    @JsonIgnore
    private String ownerId;
    private String itemName;
    private LocalDate creationDate;
}
