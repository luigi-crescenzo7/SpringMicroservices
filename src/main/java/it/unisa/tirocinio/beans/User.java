package it.unisa.tirocinio.beans;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate dateOfBirth;
}
