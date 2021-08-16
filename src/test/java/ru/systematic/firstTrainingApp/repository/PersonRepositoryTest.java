package ru.systematic.firstTrainingApp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.systematic.firstTrainingApp.model.Address;
import ru.systematic.firstTrainingApp.model.Person;
import ru.systematic.firstTrainingApp.model.Role;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PersonRepositoryTest {

    private Person personToSave;
    private Person personToUpdate;
    private Person personToFindDelete;
    private long id = 0;
    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {

        Address addressToSave = new Address();
        addressToSave.setCountry("Russia");
        addressToSave.setCity("cityToSavePerson");
        addressToSave.setStreet("streetToSavePerson");
        addressToSave.setHouse(1);
        addressToSave.setApartment(191);
        personToSave = new Person("nameToSavePerson", "surnameToSavePerson", "testToSavePerson@test.ru", Role.ADMIN, addressToSave);
        id++;
        personToSave.setId(id);

        Address addressToUpdate = new Address();
        addressToUpdate.setCountry("Russia");
        addressToUpdate.setCity("cityToUpdatePerson");
        addressToUpdate.setStreet("streetToUpdatePerson");
        addressToUpdate.setHouse(2);
        personToUpdate = new Person("nameToUpdatePerson", "surnameToUpdatePerson", "testToUpdatePerson@test.ru", Role.TEST_USER, addressToUpdate);
        id++;
        personToUpdate.setId(id);

        Address addressToFindDelete = new Address();
        addressToFindDelete.setCountry("Russia");
        addressToFindDelete.setCity("cityToFindPerson");
        addressToFindDelete.setStreet("streetToFindPerson");
        addressToFindDelete.setHouse(3);
        personToFindDelete = new Person("nameToFindPerson", "surnameToFindPerson", "testToFindPerson@test.ru", Role.USER, addressToFindDelete);
        id++;
        personToFindDelete.setId(id);
        personRepository.save(personToFindDelete);
    }

    @Test
    void save() {

        int dbActualSize = PersonRepository.personDb.size();
        personRepository.save(personToSave);
        Integer dbExceptedSize = PersonRepository.personDb.size();
        assertEquals(dbExceptedSize, dbActualSize + 1);
    }

    @Test
    void update() {
        personToUpdate.setId(2L);
        Person updatedPerson = personRepository.update(personToUpdate);
        assertEquals(updatedPerson.getId(), personToUpdate.getId());
        Assertions.assertEquals(updatedPerson.getAddress(), personToUpdate.getAddress());
        Assertions.assertEquals(updatedPerson.getRole(), personToUpdate.getRole());
        Assertions.assertEquals(updatedPerson.getName(), personToUpdate.getName());
        Assertions.assertEquals(updatedPerson.getSurname(), personToUpdate.getSurname());
        Assertions.assertEquals(updatedPerson.getEmail(), personToUpdate.getEmail());
    }

    @Test
    void find() {

        Person person = personRepository.find(personToFindDelete.getId());
        assertEquals(person, personToFindDelete);
    }

    @Test
    void getAll() {

        Map<Long, Person> dbToActual = PersonRepository.personDb;
        Map<Long, Person> dbForExpect = personRepository.getAll();
        assertEquals(dbForExpect, dbToActual);
    }

    @Test
    void delete() {

        int dbActualSize = PersonRepository.personDb.size();
        personRepository.delete(personToFindDelete.getId());
        Integer dbExceptedSize = PersonRepository.personDb.size();
        assertEquals(dbExceptedSize, dbActualSize - 1);
    }
}