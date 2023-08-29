package it.unisa.tirocinio.services;

import it.unisa.tirocinio.beans.User;


public interface UserService {

    boolean saveUser(User user);

    String login(String email, String password);
}
