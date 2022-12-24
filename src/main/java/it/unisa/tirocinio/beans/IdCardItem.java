package it.unisa.tirocinio.beans;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IdCardItem {

    @JsonProperty(value = "key")
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String surname;

    @JsonProperty
    private Integer age;

    @JsonProperty
    private String ownerId;

}
