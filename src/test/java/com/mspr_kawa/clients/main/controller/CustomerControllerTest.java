package com.mspr_kawa.clients.main.controller;

import java.util.Set;

import com.mspr_kawa.clients.main.model.Customer;

import com.mspr_kawa.clients.main.service.CustomerService;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
@Test
    void getAllCustomersTest() {
    Customer customer1 =new Customer();
    Customer customer2 =new Customer();
    List<Customer> customerList = Arrays.asList(customer1,customer2);
    when(customerService.getAllCustomers()).thenReturn(customerList);
    ResponseEntity<List<Customer>> response = customerController.getAllCustomers();
    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertEquals(customerList,response.getBody());
    assertEquals(customer1,response.getBody().get(0));
    assertEquals(customer2,response.getBody().get(1));


}


    @Test
    void testCreateCustomer_MissingFirstName() {
        Customer customer = new Customer();
        customer.setLastName("Doe");
        customer.setUsername("johndoe");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            customerController.createCustomer(customer);
        });

        assertEquals("firstName is required", exception.getMessage());
    }

    @Test
    void testCreateCustomer_MissingLastName() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setUsername("johndoe");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            customerController.createCustomer(customer);
        });

        assertEquals("lastName is required", exception.getMessage());
    }

    @Test
    void testCreateCustomer_MissingUsername() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            customerController.createCustomer(customer);
        });

        assertEquals("username is required", exception.getMessage());
    }

@Test
    void getCustomerByIdTest() {
        UUID customerId = UUID.randomUUID();
        Customer  customer1=new Customer();
        when(customerService.getCustomerById(customerId)).thenReturn(customer1);
        ResponseEntity<Customer> response = customerController.getCustomerById(customerId);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(customer1,response.getBody());

}

@Test
    void testGetCustomerByIdNotFound() {
        UUID customerId = UUID.randomUUID();
        when(customerService.getCustomerById(customerId)).thenReturn(null);
        ResponseEntity<Customer> response = customerController.getCustomerById(customerId);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

}
@Test
void testCreateCustomer() {
    Customer createdCustomer = new Customer();
    createdCustomer.setUsername("doe");
    createdCustomer.setName("jhon");
    createdCustomer.setFirstName("jhon");
    createdCustomer.setLastName("jhon");

    when(customerService.createCustomer(createdCustomer)).thenReturn(createdCustomer);

    ResponseEntity<Customer> response = customerController.createCustomer(createdCustomer);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(createdCustomer, response.getBody());
}
@Test
    void testUpdateCustomer() {
        UUID customerId = UUID.randomUUID();
        String newUserName="jhonDo";
        Customer customer1=new Customer();
        customer1.setUsername("jhon");
        Customer updatedCustomer =new Customer();
        updatedCustomer.setUsername(newUserName);
        when(customerService.updateCustomer(customerId,updatedCustomer)).thenReturn(customer1);
        when(customerService.updateCustomer(customerId,updatedCustomer)).thenReturn(updatedCustomer);
        ResponseEntity<Customer> response = customerController.updateCustomer(customerId,updatedCustomer);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(newUserName,response.getBody().getUsername());
}
@Test
    void testUpdateCustomerNotFound() {
        UUID customerId = UUID.randomUUID();
        Customer customer1=new Customer();
        when(customerService.updateCustomer(customerId,customer1)).thenReturn(null);
        ResponseEntity<Customer> response = customerController.updateCustomer(customerId,customer1);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
}
@Test
    void testDeleteCustomerNotFound(){
        UUID customerId = UUID.randomUUID();
    when(customerService.deleteCustomer(customerId)).thenReturn(false);
    ResponseEntity<Void> response = customerController.deleteCustomer(customerId);
    assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
}
@Test
    void testDeleteCustomer() {
        UUID customerId = UUID.randomUUID();
        when(customerService.deleteCustomer(customerId)).thenReturn(true);
        ResponseEntity<Void> response = customerController.deleteCustomer(customerId);
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
}
}