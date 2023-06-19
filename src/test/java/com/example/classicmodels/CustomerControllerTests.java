package com.example.classicmodels;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.example.classicmodels.controller.CustomerController;
import com.example.classicmodels.model.Customer;
import com.example.classicmodels.repository.CustomerRepository;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomerControllerTests {
    @Autowired
	private CustomerController customerController;
	
	@Autowired
    private CustomerRepository customerRepository;

	@Autowired
    private MockMvc mockMvc;

	final static Long customerNumber1 = 1225L;
	final static String customerName1 = "customer Name 1";

    @Test
	public void contextLoads() throws Exception {
		assertNotNull(customerController);
	}

	 @Test
    public void testGetSingleCustomerEndpoint() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerNumber(customerNumber1);
		customer.setCustomerName(customerName1);
        customerRepository.save(customer);

        mockMvc.perform(get("/api/customers/"+Long.toString(customerNumber1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerNumber").value(Long.toString(customerNumber1)))
                .andExpect(jsonPath("$.customerName").value(customerName1));
    }

}
