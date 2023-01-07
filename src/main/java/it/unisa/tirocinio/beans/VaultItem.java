package it.unisa.tirocinio.beans;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonDeserialize(using = VaultItemDeserializer.class)
public class VaultItem {
    private String id;
    private String idCardNumber;
    private String resourceURI;
    private String ownerId;
    private String itemName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
}
