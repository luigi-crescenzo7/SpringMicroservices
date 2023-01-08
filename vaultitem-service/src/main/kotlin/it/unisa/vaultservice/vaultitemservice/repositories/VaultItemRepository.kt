package it.unisa.vaultservice.vaultitemservice.repositories

import it.unisa.vaultservice.vaultitemservice.beans.VaultItem
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface VaultItemRepository : MongoRepository<VaultItem, ObjectId> {

    @Query("{ownerId:  ?0}")
    fun findAllByOwnerId(ownerId: ObjectId): List<VaultItem>
}