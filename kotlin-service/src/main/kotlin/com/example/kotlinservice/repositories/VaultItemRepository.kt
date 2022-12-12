package com.example.kotlinservice.repositories

import com.example.kotlinservice.beans.VaultItem
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface VaultItemRepository : MongoRepository<VaultItem, String>