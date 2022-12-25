package it.unisa.tirocinio.beans;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class IdCardItemDeserializer extends JsonDeserializer<IdCardItem> {

    @Override
    public IdCardItem deserialize(JsonParser jsonParser,
                                  DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String id = node.get("id").asText();
        String name = node.get("name").asText();
        String surname = node.get("surname").asText();
        Integer age = node.get("age").asInt();
        String ownerId = node.get("ownerId").asText();

        return new IdCardItem(id, name, surname, age, ownerId);
    }
}
