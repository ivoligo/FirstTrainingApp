package ru.systematic.firstTrainingApp.model;

import lombok.Data;
import ru.systematic.firstTrainingApp.Utils;

@Data
public class Person {

    private long id;
    private String name;
    private String surname;
    private String email;
    private Role role;
    private Address address;

    public Person(String name, String surname, String email, Role role, Address address) {
        Utils.idCount++;
        this.id = Utils.idCount;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.address = address;
    }

    public Person() {
        Utils.idCount++;
        this.id = Utils.idCount;
    }

}
