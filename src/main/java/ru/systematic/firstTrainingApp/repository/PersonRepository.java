package ru.systematic.firstTrainingApp.repository;

import org.springframework.stereotype.Component;
import ru.systematic.firstTrainingApp.model.Person;

import java.util.Map;

@Component
public class PersonRepository {

    Map<Long, Person> personDb;

    public Person save(Person person){
        return personDb.put(person.getId(), person);
    }

    public Person find(Long id){
        return personDb.get(id);
    }

    public Map<Long, Person> getAll(){
        return personDb;
    }

    public void delete(long id) {
        personDb.remove(id);
    }
}
