package ru.systematic.firstTrainingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.systematic.firstTrainingApp.Utils;
import ru.systematic.firstTrainingApp.dto.PersonDto;
import ru.systematic.firstTrainingApp.exception.NotFoundException;
import ru.systematic.firstTrainingApp.model.Person;
import ru.systematic.firstTrainingApp.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {


    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDto> getAll() {
        return personRepository.getAll().values().stream().map(Utils::convert).collect(Collectors.toList());
    }

    public PersonDto getPerson(long id) {
        /*
         * Это здесь не нужно. Сделал просто для того,чтобы было видно, что пользователь не найден.
         * Отстуствие пользователя это нен ошибка.
         */
        if (personRepository.find(id) != null) {
            return Utils.convert(personRepository.find(id));
        }
        throw new NotFoundException();
    }

    public Person create(PersonDto personDto) {
        return personRepository.save(Utils.convert(personDto));
    }

    public Person update(long id, PersonDto personDto) {
        Person person = personRepository.find(id);
        person.setName(personDto.getName());
        person.setSurname(personDto.getSurname());
        person.setAddress(personDto.getAddress());
        person.setEmail(personDto.getEmail());

        return personRepository.update(person);
    }

    public PersonDto delete(long id) {
        return Utils.convert(personRepository.delete(id));
    }
}
