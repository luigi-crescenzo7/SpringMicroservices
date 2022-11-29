package it.unisa.tirocinio.services;

public interface UserService {
    String getPersons();

    boolean login(String email, String password);
}
