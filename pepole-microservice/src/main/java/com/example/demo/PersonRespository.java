package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRespository
        extends MongoRepository<Person, String> {

    @Query("{name:'?0'}")
    List<Person> findByName(@Param("name") String name);
}
