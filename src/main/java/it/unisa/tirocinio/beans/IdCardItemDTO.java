package it.unisa.tirocinio.beans;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IdCardItemDTO {

    @JsonProperty(value = "asset")
    private IdCardItem item;
}
