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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.rentalservice.db.managers.CarManager;
import cz.muni.rentalservice.models.Car;
import cz.muni.rentalservice.test.AbstractRentalServiceTest;

/**
 *
 * @author paynes
 */
public class CarManagerImplTest extends AbstractRentalServiceTest {
    
    @Autowired
    private CarManager manager;
    
    @Test
    public void testGetCars() {
        List<Car> cars = manager.getCars();
        assertEquals(this.countRowsInTable("car"),cars.size());
    }
    
    @Test(expected=NullPointerException.class)
    public void testGetCarWithNullId() {
        manager.getCar(null);
    }
       
    @Test
    public void testGetCar() {
        Car expected = createExistingCarMock();
        
        Car actual = manager.getCar(expected.getId());
        
        assertNotNull(actual);
        assertEquals(expected,actual);
        compare(expected,actual);
    }
    
    @Test(expected=NullPointerException.class)
    public void testSaveNullCar() {
        manager.saveCar(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testSaveCarWithIncorrectId() {
        Car car = new Car();
        car.setId(Long.MAX_VALUE);
        
        manager.saveCar(car);
    }
    
    @Test
    public void testSaveCar() {
        final Car expected = new Car();
        expected.setModel("renault");
        expected.setRegNumber("123456");
        expected.setDailyFee(1.5);
        
        manager.saveCar(expected);
        assertNotNull(expected.getId());
        
        Car actual = manager.getCar(expected.getId());
        assertNotNull(actual);
        assertEquals(expected,actual);
        
        compare(expected,actual);
        
    }
    
    @Test(expected = NullPointerException.class)
    public void testUpdateNullCar() {
        manager.updateCar(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testUpdateCarWithNullId() {
        manager.updateCar(new Car());
    }
    
    @Test
    public void testUpdateCar() {
        final Car expected = createExistingCarMock();
        expected.setModel("seat");
        expected.setDailyFee(2.0);
        expected.setRegNumber("123");
        
        manager.updateCar(expected);
        
        Car actual = manager.getCar(expected.getId());
        assertNotNull(actual);
        assertEquals(expected,actual);
        compare(expected,actual);
    }
    
    @Test(expected = NullPointerException.class)
    public void testDeleteNullCar() {
        manager.removeCar(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testDeleteCarWithIncorrectId() {
        manager.removeCar(new Car());
    }
    
    @Test
    public void testDeleteCar() {
        final int rowCount = this.countRowsInTable("car");
        
        final Car car = createExistingCarMock();
        
        manager.removeCar(car);
        
        final int newRowCount = this.countRowsInTable("car");
        
        assertEquals(rowCount - 1,newRowCount);
    }
    
    public Car createExistingCarMock() {
        Car car = new Car();
        car.setId(Long.valueOf(1));
        car.setModel("renault");
        car.setDailyFee(1.5);
        car.setRegNumber("123456");
        
        return car;
    }
    
    public void compare(Car expected, Car actual) {
        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getModel(),actual.getModel());
        assertEquals(expected.getRegNumber(),actual.getRegNumber());
    }    
}
