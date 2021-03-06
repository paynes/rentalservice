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
package cz.muni.rentalservice.db.managers;

import java.util.List;
import cz.muni.rentalservice.models.Car;

/**
 *
 * @author paynes
 */
public interface CarManager {
    
    void saveCar(Car car);
    
    public void removeCar(Car car);
    
    void updateCar(Car car);
    
    //public List<Car> findCarByModel(String model) throws ServiceFailureException;
    
    public List<Car> getCars();
    
    public Car getCar(Long id);
    
}
