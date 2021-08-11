package ru.systematic.firstTrainingApp.dto;

import lombok.Data;
import ru.systematic.firstTrainingApp.model.Address;

@Data
public class PersonDto {

    private String name;
    private String surname;
    private String email;
    private String role;
    private Address address;

}
