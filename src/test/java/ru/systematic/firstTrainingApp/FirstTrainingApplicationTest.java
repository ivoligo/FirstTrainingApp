package ru.systematic.firstTrainingApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.systematic.firstTrainingApp.service.PersonService;

@SpringBootTest
class FirstTrainingApplicationTest {

	@Autowired
	private PersonService personService;

	@Test
	void contextLoads() {
	}

}
