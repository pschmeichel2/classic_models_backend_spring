package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.*;
import org.springframework.beans.factory.annotation.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.web.servlet.*;

@SpringBootTest 
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DemoApplicationTests {

	@Value("${myproperty}")
	private String myProperty;

	@Test
	void dataSource() {
		assertThat(myProperty).isEqualTo("huhu");
		System.out.println(myProperty);
	}
}
