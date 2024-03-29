package com.example.demo.repository;

import com.example.demo.beans.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{email: '?0'}{id: 0, email: 1, password: 1}")
    <S extends User> Optional<S> findUserByEmail(@Param("email") String email);
}
