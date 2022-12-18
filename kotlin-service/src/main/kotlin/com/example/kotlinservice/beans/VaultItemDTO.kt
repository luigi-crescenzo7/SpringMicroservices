package com.example.kotlinservice.beans

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate

@Data
@NoArgsConstructor
class VaultItemDTO
    (
    @field:JsonProperty var id: String?,
    @field:JsonProperty var idCardNumber: String,
    @field:JsonProperty var resourceURI: String,
    @field:JsonProperty var itemName: String,
    @field:JsonProperty var creationDate: LocalDate,
    @field:JsonProperty var ownerId: String
)