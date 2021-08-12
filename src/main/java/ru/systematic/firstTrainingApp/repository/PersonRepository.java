package ru.systematic.firstTrainingApp.repository;

import org.springframework.stereotype.Component;
import ru.systematic.firstTrainingApp.model.Person;

import java.util.Map;

@Component
public class PersonRepository {

    Map<Long, Person> personDb;
    private Long idCount = 0L;

    public Person save(Person person){
        idCount++;
        person.setId(idCount);
        return personDb.put(person.getId(), person);
    }

    public Person update(Person person){
        return personDb.put(person.getId(), person);
    }

    public Person find(Long id){
        return personDb.get(id);
    }

    public Map<Long, Person> getAll(){
        return personDb;
    }

    public Person delete(long id) {
        return personDb.remove(id);
    }
}
