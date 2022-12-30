package it.unisa.tirocinio.beans;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class IdCardItemDeserializer extends JsonDeserializer<IdCardItem> {

    @Override
    public IdCardItem deserialize(JsonParser jsonParser,
                                  DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String id = node.get("key").asText();
        String name = node.get("name").asText();
        String surname = node.get("surname").asText();
        String cardNumber = node.get("cardNumber").asText();
        String sex = node.get("sex").asText();
        LocalDate dateOfBirth = LocalDate.parse(node.get("dateOfBirth").asText(), DateTimeFormatter.ISO_LOCAL_DATE);
        String placeOfBirth = node.get("placeOfBirth").asText();
        String nationality = node.get("nationality").asText();
        LocalDate expiryDate = LocalDate.parse(node.get("expiryDate").asText(), DateTimeFormatter.ISO_LOCAL_DATE);
        String fiscalCode = node.get("fiscalCode").asText();
        String ownerId = node.get("ownerId").asText();

        return new IdCardItem(id, name, surname,
                cardNumber, sex, dateOfBirth,
                placeOfBirth, nationality, expiryDate,
                fiscalCode, ownerId);
    }
}
