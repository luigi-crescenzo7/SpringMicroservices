package it.unisa.tirocinio.services;

import it.unisa.tirocinio.beans.User;

public interface UserService {
    String getUsers();

    boolean saveUser(User user);

    boolean login(String email, String password);
}
