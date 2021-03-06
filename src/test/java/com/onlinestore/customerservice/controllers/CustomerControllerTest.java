package com.onlinestore.customerservice.controllers;

import com.onlinestore.customerservice.domain.Customer;
import com.onlinestore.customerservice.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	private static final String MAIN_URI = "/v1/customers/";
	private static final String CUSTOMER_ID = "25";

	@MockBean
	private CustomerService customerService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldGetCustomerById() throws Exception {
		mockMvc.perform(get(MAIN_URI + CUSTOMER_ID)).andExpect(status().isOk());
		verify(customerService).getCustomerById(CUSTOMER_ID);
	}

	@Test
	public void shouldAddNewCustomer() throws Exception {
		mockMvc.perform(post(MAIN_URI)).andExpect(status().isOk());
		verify(customerService).saveCustomer(any(Customer.class));
	}

	@Test
	public void shouldDeleteCustomer() throws Exception {
		mockMvc.perform(delete(MAIN_URI + CUSTOMER_ID)).andExpect(status().isNoContent());
		verify(customerService).deleteCustomer(CUSTOMER_ID);
	}
}
