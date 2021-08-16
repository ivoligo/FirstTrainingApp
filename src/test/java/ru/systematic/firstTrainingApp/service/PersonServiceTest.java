package ru.systematic.firstTrainingApp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.systematic.firstTrainingApp.Utils;
import ru.systematic.firstTrainingApp.dto.PersonDto;
import ru.systematic.firstTrainingApp.model.Address;
import ru.systematic.firstTrainingApp.model.Person;
import ru.systematic.firstTrainingApp.model.Role;
import ru.systematic.firstTrainingApp.repository.PersonRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private Person person1;
    private Person person2;
    private Map<Long, Person> testDb;
    private long id = 0;

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {

        Address address1 = new Address();
        address1.setCountry("Russia");
        address1.setCity("city1");
        address1.setStreet("street1");
        address1.setHouse(1);
        address1.setApartment(191);
        person1 = new Person("name1", "surname1", "test1@test.ru", Role.ADMIN, address1);
        id++;
        person1.setId(id);

        Address address2 = new Address();
        address2.setCountry("Russia");
        address2.setCity("city2");
        address2.setStreet("street2");
        address2.setHouse(2);
        person2 = new Person("name2", "surname2", "test2@test.ru", Role.TEST_USER, address2);
        id++;
        person2.setId(id);

        testDb = new HashMap<>();
        testDb.put(person1.getId(), person1);
        testDb.put(person2.getId(), person2);
    }

    @Test
    void getAll() {

        List<PersonDto> actual = new ArrayList<>();
        actual.add(Utils.convert(person1));
        actual.add(Utils.convert(person2));

        when(personRepository.getAll()).thenReturn(testDb);
        List<PersonDto> exceptTestDb = personService.getAll();
        Assertions.assertEquals(exceptTestDb, actual);
    }

    @Test
    void getPerson() {

        when(personRepository.find(1L)).thenReturn(person1);
        assertThat(personService.getPerson(person1.getId())).isEqualTo(Utils.convert(person1));
    }

    @Test
    void create() {

        Address address = new Address();
        address.setCountry("testCountry");
        address.setCity("testCity");
        address.setStreet("testStreet");
        address.setHouse(1);
        address.setApartment(1);

        PersonDto personDto = new PersonDto();
        personDto.setEmail("test@test.ru");
        personDto.setName("testName");
        personDto.setSurname("testSurname");
        personDto.setAddress(address);
        personDto.setRole("Администратор");

        when(personRepository.save(any())).thenReturn(Utils.convert(personDto));
        Person person = personService.create(personDto);
        verify(personRepository, times(1)).save(any());
        Assertions.assertEquals(person, Utils.convert(personDto));
    }

    @Test
    void update() {
        when(personRepository.find(1L)).thenReturn(person1);
        Person person = personRepository.find(1L);
        person.setName("updatedName");
        person.setSurname("updatedSurname");
        person.setAddress(person2.getAddress());
        person.setEmail(person2.getEmail());
        PersonDto personDto = Utils.convert(person);

        when(personRepository.update(person1)).thenReturn(person1);
        Person updatedPerson = personService.update(person1.getId(), personDto);
        Assertions.assertEquals(updatedPerson.getId(), person1.getId());
        Assertions.assertEquals(updatedPerson.getAddress(), person2.getAddress());
        Assertions.assertEquals(updatedPerson.getRole(), Role.ADMIN);
        Assertions.assertEquals(updatedPerson.getName(), "updatedName");
        Assertions.assertEquals(updatedPerson.getSurname(), "updatedSurname");
        Assertions.assertEquals(updatedPerson.getEmail(), person2.getEmail());
    }

    @Test
    void delete() {
        when(personRepository.delete(person1.getId())).thenReturn(person1);
        PersonDto personDto = personService.delete(person1.getId());
        Assertions.assertEquals(personDto, Utils.convert(person1));
        verify(personRepository, times(1)).delete(person1.getId());
    }
}