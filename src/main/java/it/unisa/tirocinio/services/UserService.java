package it.unisa.tirocinio.services;

import it.unisa.tirocinio.beans.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    boolean saveUser(User user);

    boolean login(String email, String password);
}
