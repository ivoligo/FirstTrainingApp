package ru.systematic.firstTrainingApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private Role role;
    private Address address;

    public Person(String name, String surname, String email, Role role, Address address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.address = address;
    }
}
