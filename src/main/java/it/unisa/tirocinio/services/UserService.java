package it.unisa.tirocinio.services;

public interface UserService {
    String getUsers();

    boolean login(String email, String password);
}
