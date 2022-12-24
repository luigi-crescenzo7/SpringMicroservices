package it.unisa.tirocinio.beans;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDate;

public class VaultItemDeserializer extends JsonDeserializer<VaultItem> {
    @Override
    public VaultItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String itemId = node.get("id").asText();
        String itemCardNumber = node.get("idCardNumber").asText();
        String itemResourceURI = node.get("resourceURI").asText();
        String itemOwnerId = node.get("ownerId").get("id").asText();
        String itemName = node.get("itemName").asText();
        LocalDate date = LocalDate.parse(node.get("creationDate").asText());

        return new VaultItem(itemId, itemCardNumber, itemResourceURI,
                itemOwnerId, itemName, date);
    }
}
