package it.unisa.tirocinio.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String gender;
    private String password;
    private LocalDate dateOfBirth;
}
