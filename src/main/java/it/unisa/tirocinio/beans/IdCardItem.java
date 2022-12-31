package it.unisa.tirocinio.beans;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = IdCardItemDeserializer.class)
public class IdCardItem {

    @JsonProperty(value = "key", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String surname;

    @JsonProperty
    private String cardNumber;

    @JsonProperty
    private String sex;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate dateOfBirth;

    @JsonProperty
    private String placeOfBirth;

    @JsonProperty
    private String nationality;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate expiryDate;

    @JsonProperty
    private String fiscalCode;

    @JsonProperty
    private String ownerId;

}
