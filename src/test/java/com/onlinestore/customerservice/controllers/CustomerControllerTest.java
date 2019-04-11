package com.onlinestore.customerservice.controllers;

import com.onlinestore.customerservice.domain.Customer;
import com.onlinestore.customerservice.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	private static final String CUSTOMER_ID = "25";

	@MockBean
	private CustomerService customerService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldGetCustomerById() throws Exception {
		mockMvc.perform(get("/v1/customers/"+CUSTOMER_ID)).andExpect(status().isOk());
		verify(customerService).getCustomerById(CUSTOMER_ID);
	}

	@Test
	public void shouldAddNewCustomer() throws Exception {
		mockMvc.perform(post("/v1/customers/")).andExpect(status().isOk());
		verify(customerService).saveCustomer(any(Customer.class));
	}
}
