package ru.systematic.firstTrainingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.systematic.firstTrainingApp.dto.PersonDto;
import ru.systematic.firstTrainingApp.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/")
public class DefaultController {

    private final PersonService personService;

    @Autowired
    public DefaultController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> list() {
        return ResponseEntity.ok(personService.getAll());
    }
}
