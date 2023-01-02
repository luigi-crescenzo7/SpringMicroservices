package it.unisa.tirocinio.beans;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = VaultItemDeserializer.class)
public class VaultItem {
    private String id;
    private String idCardNumber;
    private String resourceURI;
    private String ownerId;
    private String itemName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate creationDate;
}
