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
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.muni.rentalservice.mappers.CarMapper;
import cz.muni.rentalservice.models.Car;

/**
 *
 * @author paynes
 */
@Service
public class CarManagerImpl implements CarManager{
    
    private final CarMapper mapper;

    @Autowired
    public CarManagerImpl(CarMapper mapper) {
        Preconditions.checkNotNull(mapper);
        this.mapper = mapper;
    }
    
    @Override
    @Transactional
    public void saveCar(Car car) {
        Preconditions.checkNotNull(car != null, "Car should not be null.");
        Preconditions.checkArgument(car.getId() == null, "Car should have id.");
        mapper.insert(car);
    }
    
    @Override
    @Transactional
    public void removeCar(Car car){
        Preconditions.checkNotNull(car != null, "Car should not be null.");
        Preconditions.checkNotNull(car.getId(), "Car id should not be null.");
        mapper.delete(car);
    }

    @Override
    @Transactional
    public void updateCar(Car car) {
        Preconditions.checkNotNull(car != null, "Car should not be null.");
        Preconditions.checkNotNull(car.getId(), "Car id should not be null.");
        mapper.update(car);
    }
    

    @Override
    public Car getCar(Long id) {
        Preconditions.checkNotNull(id, "Id should not be null.");
        Car car = mapper.selectCar(id);
        return car;
    }
    

    @Override
    public List<Car> getCars() {
        List<Car> cars = mapper.selectCars();
        return ImmutableList.copyOf(cars);
    }
}
