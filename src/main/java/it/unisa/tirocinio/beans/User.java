package it.unisa.tirocinio.beans;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String sex;
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate dateOfBirth;


    public User() {
    }

    public User(String id,
                String name,
                String surname,
                String email,
                String sex,
                String password,
                LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.sex = sex;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
