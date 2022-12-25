package it.unisa.tirocinio.beans;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = IdCardItemDeserializer.class)
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
