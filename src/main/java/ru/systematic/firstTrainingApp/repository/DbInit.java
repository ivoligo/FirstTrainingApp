package ru.systematic.firstTrainingApp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.systematic.firstTrainingApp.model.Address;
import ru.systematic.firstTrainingApp.model.Person;
import ru.systematic.firstTrainingApp.model.Role;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class DbInit {

    private final PersonRepository personRepository;

    @Autowired
    public DbInit(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @PostConstruct
    private void postConstruct(){

        Address address1 = new Address();
        address1.setCountry("Russia");
        address1.setCity("city1");
        address1.setStreet("street1");
        address1.setHouse(1);
        address1.setApartment(191);
        Person person1 = new Person("name1", "surname1", "test1@test.ru", Role.ADMIN, address1);

        Address address2 = new Address();
        address2.setCountry("Russia");
        address2.setCity("city2");
        address2.setStreet("street2");
        address2.setHouse(2);
        Person person2 = new Person("name2", "surname2", "test2@test.ru", Role.TEST_USER, address2);

        personRepository.personDb = new HashMap<>();
        personRepository.save(person1);
        personRepository.save(person2);
    }
}
