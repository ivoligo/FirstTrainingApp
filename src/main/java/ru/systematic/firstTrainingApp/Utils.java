package ru.systematic.firstTrainingApp;

import ru.systematic.firstTrainingApp.model.Person;
import ru.systematic.firstTrainingApp.dto.PersonDto;
import ru.systematic.firstTrainingApp.model.Role;

public class Utils {

    public static PersonDto convert(Person person){
        PersonDto personDto = new PersonDto();
        personDto.setName(person.getName());
        personDto.setSurname(person.getSurname());
        personDto.setEmail(person.getEmail());
        personDto.setRole(person.getRole().toString());
        personDto.setAddress(person.getAddress());
        return personDto;
    }

    public static Person convert(PersonDto personDto){
        Person person = new Person();
        person.setName(personDto.getName());
        person.setSurname(personDto.getSurname());
        person.setEmail(personDto.getEmail());
        person.setAddress(personDto.getAddress());
        person.setRole(convertRoleToPerson(personDto.getRole()));
        return person;
    }

    private static Role convertRoleToPerson(String role){
        switch (role){
            case "Администратор":
                return Role.ADMIN;
            case"Пользователь":
                return Role.USER;
            case "Тестовый пользователь":
                return Role.TEST_USER;
            default:
                throw new IllegalArgumentException("Неверный тип роли " + role );
        }
    }
}
