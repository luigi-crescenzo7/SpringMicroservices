package it.unisa.vaultservice.vaultitemservice.beans

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.*
import java.time.LocalDate


@Data
@NoArgsConstructor
class VaultItemDTO
    (
    @JsonProperty("id") var id: String?,
    @JsonProperty("idCardNumber") var idCardNumber: String,
    @JsonProperty("resourceURI") var resourceURI: String,
    @JsonProperty("itemName") var itemName: String,
    @JsonProperty("creationDate") var creationDate: LocalDate,
    @JsonProperty("ownerId") var ownerId: String
)