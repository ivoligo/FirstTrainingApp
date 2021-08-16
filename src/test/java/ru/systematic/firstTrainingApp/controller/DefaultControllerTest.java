package ru.systematic.firstTrainingApp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.systematic.firstTrainingApp.dto.PersonDto;
import ru.systematic.firstTrainingApp.model.Address;
import ru.systematic.firstTrainingApp.service.PersonService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DefaultController.class)
class DefaultControllerTest {

    private final List<PersonDto> personDtoList = new ArrayList<>();

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
        PersonDto person2 = new PersonDto("name2", "surname2", "test2@test.ru", "Тестовый пользователь", address2);

        personDtoList.add(person1);
        personDtoList.add(person2);
    }

    @Test
    void list() throws Exception {
        when(personService.getAll()).thenReturn(personDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{},{}]"));
    }
}