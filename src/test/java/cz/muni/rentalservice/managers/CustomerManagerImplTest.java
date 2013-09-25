/*
 * Copyright 2013 paynes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.muni.rentalservice.managers;

import java.util.List;
import org.joda.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.rentalservice.db.managers.CustomerManager;
import cz.muni.rentalservice.models.Customer;
import cz.muni.rentalservice.test.AbstractRentalServiceTest;

/**
 *
 * @author paynes
 */
public class CustomerManagerImplTest extends AbstractRentalServiceTest {

    @Autowired
    private CustomerManager manager;
    
    @Test
    public void testGetCustomers() {
        List<Customer> customers = manager.getCustomers();
        assertEquals(this.countRowsInTable("customer"),customers.size());
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetCustomerWithNullId() {
        manager.getCustomer(null);
    }
    
    @Test
    public void testGetCustomer() {
        final Customer expected = createExistingCustomerMock();
        
        Customer actual = manager.getCustomer(expected.getId());
        
        assertNotNull(actual);
        assertEquals(expected, actual);
        
        compare(expected,actual);
    }
    
    @Test(expected = NullPointerException.class)
    public void testSaveNullCustomer() {
        manager.saveCustomer(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSaveCustomerWithIncorrectId() {
        Customer customer = new Customer();
        customer.setId(Long.MAX_VALUE);
        
        manager.saveCustomer(customer);
    }
    
    @Test
    public void tesSaveCustomer() {
        final Customer expected = new Customer();
        expected.setName("Peter");
        expected.setSurname("Marcin");
        expected.setBorn(new LocalDate(1995,8,8));
        
        manager.saveCustomer(expected);
        assertNotNull(expected.getId());
        
        Customer actual = manager.getCustomer(expected.getId());
        assertNotNull(actual);
        assertEquals(expected,actual);
        
        compare(expected,actual);
    }
    
    @Test(expected = NullPointerException.class)
    public void testUpdateNullCustomer() {
        manager.updateCustomer(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testUpdateWithIncorrectId() {
        manager.updateCustomer(new Customer());
    }
    
    @Test
    public void testUpdateCustomer() {
        final Customer expected = createExistingCustomerMock();
        expected.setName("Honza");
        expected.setSurname("Novak");
        expected.setBorn(new LocalDate(1991,8,8));
        
        manager.updateCustomer(expected);
        
        Customer actual = manager.getCustomer(expected.getId());
        assertNotNull(actual);
        assertEquals(expected,actual);
        
        compare(expected,actual);
    }
    
    @Test(expected = NullPointerException.class)
    public void testDeleteNullCustomer() {
        manager.removeCustomer(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testDeleteCustomerWithIncorrectId() {
        manager.removeCustomer(new Customer());
    }
    
    @Test
    public void testDeleteCustomer() {
        final int rowCount = this.countRowsInTable("customer");
        
        final Customer customer = createExistingCustomerMock();
        
        manager.removeCustomer(customer);
        
        final int newRowCount = this.countRowsInTable("customer");
        
        assertEquals(rowCount -1 , newRowCount);
    }
    
    
    public Customer createExistingCustomerMock() {
        Customer customer = new Customer();
        customer.setId(Long.valueOf(1));
        customer.setName("Peter");
        customer.setSurname("Marcin");
        customer.setBorn(new LocalDate(2008,8,8));
        
        return customer;
    }
    
    public void compare(Customer expected, Customer actual) {
        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getName(),actual.getName());
        assertEquals(expected.getSurname(),actual.getSurname());
        assertEquals(expected.getBorn(),actual.getBorn());
    }    
}
