package ru.systematic.firstTrainingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.systematic.firstTrainingApp.model.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private String name;
    private String surname;
    private String email;
    private String role;
    private Address address;

}
