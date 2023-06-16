package com.example.classicmodels;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.classicmodels.controller.CustomerController;

@SpringBootTest
public class CustomerControllerTests {
    @Autowired
	private CustomerController customerController;

    @Test
	public void contextLoads() throws Exception {
		assertNotNull(customerController);
	}
}
