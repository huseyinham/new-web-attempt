package hhami.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testingCustomer {

    @Test
    public void testingOneCustomerIsCreated(){
        Customer customer = new Customer(001, "John", "Smith", 0);
        assertEquals(0001, customer.getId());
        assertEquals("John", customer.getFirstName());
        assertEquals("Smith", customer.getSurname());
        assertEquals(0, customer.getBalance());
    }
}
