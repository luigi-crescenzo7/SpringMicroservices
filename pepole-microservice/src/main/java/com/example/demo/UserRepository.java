package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{name: ?0}")
    <S extends User> List<S> findAllByName(@Param("name") String name);

    @Override
    <S extends User> S insert(S entity);

    @Query("{email:  '?0', password:  '?1'}")
    <S extends User> Optional<S> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    //change query to fetch only the password field
    @Query("{}{_id: 0, email:1, password: 1}")
    <S extends User> List<S> findAllUsersWithEmailAndPassword();
}
