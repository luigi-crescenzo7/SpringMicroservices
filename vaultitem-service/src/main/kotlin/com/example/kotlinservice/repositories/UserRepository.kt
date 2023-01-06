package com.example.kotlinservice.repositories

import com.example.kotlinservice.beans.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : MongoRepository<User, String>