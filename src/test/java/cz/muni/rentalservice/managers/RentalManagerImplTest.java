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
import org.joda.time.LocalDateTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.rentalservice.db.managers.RentalManager;
import cz.muni.rentalservice.models.Car;
import cz.muni.rentalservice.models.Customer;
import cz.muni.rentalservice.models.Rental;
import cz.muni.rentalservice.test.AbstractRentalServiceTest;

/**
 *
 * @author paynes
 */
public class RentalManagerImplTest extends AbstractRentalServiceTest {
    
    @Autowired
    private RentalManager manager;
    
    @Test
    public void testGetRentals() {
        List<Rental> rentals = manager.getRentals();
        assertEquals(this.countRowsInTable("rental"),rentals.size());
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetRentalWithNullId() {
        manager.getRental(null);
    }
    
    @Test
    public void testGetRental() {
        Rental expected = createExistingRentalMock();
        
        Rental actual = manager.getRental(expected.getId());
        
        assertNotNull(actual);
        assertEquals(expected, actual);
        
        compare(expected,actual);
    }
    
    @Test(expected = NullPointerException.class)
    public void testSaveNullRental() {
        manager.saveRental(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSaveWithIncorrectId() {
        final Rental rental = new Rental();
        rental.setId(Long.MAX_VALUE);
        
        manager.saveRental(rental);
    }
    
    @Test
    public void testSaveRental() {
        final Rental expected = new Rental();
        
        expected.setPaid(true);
        expected.setDateFrom(new LocalDate(1991,9,23));
        expected.setDateTo(new LocalDate(1991,9,25));
        expected.setDays(2);
        
        Car car = new Car();
        car.setId(Long.valueOf(1));
        car.setModel("renault");
        car.setDailyFee(1.5);
        car.setRegNumber("123456");
        
        Customer customer = new Customer();
        customer.setId(Long.valueOf(1));
        customer.setName("Peter");
        customer.setSurname("Marcin");
        customer.setBorn(new LocalDate(1991,9,23));
        
        expected.setCar(car);
        expected.setCustomer(customer);
        
        manager.saveRental(expected);
        assertNotNull(expected.getId());
        
        Rental actual = manager.getRental(expected.getId());
        assertNotNull(actual);
        assertEquals(expected,actual);
        
        compare(expected, actual);
    }
    
    @Test(expected = NullPointerException.class)
    public void testUpdateNullRental() {
        manager.updateRental(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testUpdateRentalWithNullId() {
        manager.updateRental(new Rental());
    }
    
    @Test
    public void testUpdateRental() {
        final Rental expected = createExistingRentalMock();
        expected.setDays(3);
        expected.setPaid(false);
        
        Car car = new Car();
        car.setId(Long.valueOf(2));
        car.setModel("ford");
        car.setDailyFee(1.5);
        car.setRegNumber("234567");
        
        expected.setCar(car);
        
        Customer customer = new Customer();
        customer.setId(Long.valueOf(2));
        customer.setName("Honza");
        customer.setSurname("Novak");
        customer.setBorn(new LocalDate(1990,6,3));
        
        expected.setCustomer(customer);
        
        manager.updateRental(expected);
        
        Rental actual = manager.getRental(expected.getId());
        assertNotNull(actual);
        assertEquals(expected,actual);
        
        compare(expected,actual);
    }
    
    @Test(expected = NullPointerException.class)
    public void testDeleteNullRental() {
        manager.deleteRental(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testDeleteRentalWithNullId() {
        manager.deleteRental(new Rental());
    }
    
    @Test
    public void testDeleteRental() {
        final int rowCount = this.countRowsInTable("rental");
        
        final Rental rental = createExistingRentalMock();
        
        manager.deleteRental(rental);
        
        final int newRowCount = this.countRowsInTable("rental");
        
        assertEquals(rowCount - 1, newRowCount);
    }
    
    public Rental createExistingRentalMock() {
        Rental rental = new Rental();
        rental.setId(Long.valueOf(1));
        rental.setPaid(true);
        rental.setDateFrom(new LocalDate(1991,9,23));
        rental.setDateTo(new LocalDate(1991,9,25));
        rental.setDays(2);
        
        Car car = new Car();
        car.setId(Long.valueOf(1));
        car.setModel("renault");
        car.setDailyFee(1.5);
        car.setRegNumber("123456");
        
        Customer customer = new Customer();
        customer.setId(Long.valueOf(1));
        customer.setName("Peter");
        customer.setSurname("Marcin");
        customer.setBorn(new LocalDate(1991,9,23));
        
        rental.setCar(car);
        rental.setCustomer(customer);
        
        return rental;
    }
    
    public void compare(Rental expected, Rental actual) {
        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getCustomer(),actual.getCustomer());
        assertEquals(expected.getDateFrom(),actual.getDateFrom());
        assertEquals(expected.getDateTo(),actual.getDateTo());
        assertEquals(expected.getDays(),actual.getDays());
        assertEquals(expected.getCar(),actual.getCar());
    }    
}
