package ru.systematic.firstTrainingApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.systematic.firstTrainingApp.dto.PersonDto;
import ru.systematic.firstTrainingApp.model.Address;
import ru.systematic.firstTrainingApp.service.PersonService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    private PersonDto person2;
    PersonDto personForSomeActions;
    private final List<PersonDto> personDtoList = new ArrayList<>();
    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @BeforeEach
    void setUp() {

        Address address1 = new Address();
        address1.setCountry("Russia");
        address1.setCity("city1");
        address1.setStreet("street1");
        address1.setHouse(1);
        address1.setApartment(191);
        PersonDto person1 = new PersonDto("name1", "surname1", "test1@test.ru", "Администратор", address1);


        Address address2 = new Address();
        address2.setCountry("Russia");
        address2.setCity("city2");
        address2.setStreet("street2");
        address2.setHouse(2);
        person2 = new PersonDto("name2", "surname2", "test2@test.ru", "Тестовый пользователь", address2);

        personDtoList.add(person1);
        personDtoList.add(person2);

        Address addressForSomeActions = new Address();
        addressForSomeActions.setCountry("Russia");
        addressForSomeActions.setCity("cityForSomeActions");
        addressForSomeActions.setStreet("streetForSomeActions");
        addressForSomeActions.setHouse(2);
        personForSomeActions = new PersonDto("nameForSomeActions", "surnameForSomeActions", "testForSomeActions@test.ru", "Пользователь", addressForSomeActions);

    }

    @Test
    void getPerson() throws Exception {

        personDtoList.add(personForSomeActions);
        long id = 2L;
        when(personService.getPerson(id)).thenReturn(personForSomeActions);
        mockMvc.perform(get("/person/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        mockMvc.perform(get("/person/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(personForSomeActions.getName())))
                .andExpect(jsonPath("$.surname", is(personForSomeActions.getSurname())))
                .andExpect(jsonPath("$.email", is(personForSomeActions.getEmail())))
                .andExpect(jsonPath("$.role", is(personForSomeActions.getRole())))
                .andExpect(jsonPath("$.address.country", is(personForSomeActions.getAddress().getCountry())))
                .andExpect(jsonPath("$.address.city", is(personForSomeActions.getAddress().getCity())))
                .andExpect(jsonPath("$.address.country", is(personForSomeActions.getAddress().getCountry())))
                .andExpect(jsonPath("$.address.house", is(personForSomeActions.getAddress().getHouse())))
                .andExpect(jsonPath("$.address.street", is(personForSomeActions.getAddress().getStreet())))
                .andExpect(jsonPath("$.address.apartment", is(personForSomeActions.getAddress().getApartment())));
    }

    @Test
    void create() throws Exception {

        when(personService.create(personForSomeActions)).thenReturn(personForSomeActions);
        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(personForSomeActions)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(personForSomeActions.getName())))
                .andExpect(jsonPath("$.surname", is(personForSomeActions.getSurname())))
                .andExpect(jsonPath("$.email", is(personForSomeActions.getEmail())))
                .andExpect(jsonPath("$.role", is(personForSomeActions.getRole())))
                .andExpect(jsonPath("$.address.country", is(personForSomeActions.getAddress().getCountry())))
                .andExpect(jsonPath("$.address.city", is(personForSomeActions.getAddress().getCity())))
                .andExpect(jsonPath("$.address.country", is(personForSomeActions.getAddress().getCountry())))
                .andExpect(jsonPath("$.address.house", is(personForSomeActions.getAddress().getHouse())))
                .andExpect(jsonPath("$.address.street", is(personForSomeActions.getAddress().getStreet())))
                .andExpect(jsonPath("$.address.apartment", is(personForSomeActions.getAddress().getApartment())));

    }

    @Test
    void update() throws Exception {
        long id = 1;
        given(personService.update(id, personForSomeActions)).willReturn(personForSomeActions);
        mockMvc.perform(put("/person/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(personForSomeActions)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(personForSomeActions.getName())))
                .andExpect(jsonPath("$.surname", is(personForSomeActions.getSurname())))
                .andExpect(jsonPath("$.email", is(personForSomeActions.getEmail())))
                .andExpect(jsonPath("$.role", is(personForSomeActions.getRole())))
                .andExpect(jsonPath("$.address.country", is(personForSomeActions.getAddress().getCountry())))
                .andExpect(jsonPath("$.address.city", is(personForSomeActions.getAddress().getCity())))
                .andExpect(jsonPath("$.address.country", is(personForSomeActions.getAddress().getCountry())))
                .andExpect(jsonPath("$.address.house", is(personForSomeActions.getAddress().getHouse())))
                .andExpect(jsonPath("$.address.street", is(personForSomeActions.getAddress().getStreet())))
                .andExpect(jsonPath("$.address.apartment", is(personForSomeActions.getAddress().getApartment())));


    }

    @Test
    void deletePerson() throws Exception {
        long id = 1L;
        given(personService.delete(id)).willReturn(personForSomeActions);
        mockMvc.perform(delete("/person/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(personForSomeActions)))
                .andExpect(status().isOk());
    }
}