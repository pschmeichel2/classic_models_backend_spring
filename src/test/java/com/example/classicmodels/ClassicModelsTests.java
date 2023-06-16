package com.example.classicmodels;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClassicModelsTests {

	@Value("${myproperty}")
	private String myProperty;

	@Test
	void propertyTest() {
		assertThat(myProperty).isEqualTo("huhu");
		System.out.println(myProperty);
	}
}
