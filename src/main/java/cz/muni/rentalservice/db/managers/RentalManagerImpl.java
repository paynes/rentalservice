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
import cz.muni.rentalservice.mappers.RentalMapper;
import cz.muni.rentalservice.models.Rental;

/**
 *
 * @author paynes
 */
@Service
public class RentalManagerImpl implements RentalManager {
    
    private final RentalMapper mapper;
    
    @Autowired
    public RentalManagerImpl(RentalMapper mapper) {
        Preconditions.checkNotNull(mapper);
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void saveRental(Rental rental) {
        Preconditions.checkNotNull(rental != null, "Rental should not be null.");
        Preconditions.checkArgument(rental.getId() == null, "Rental should have id.");
        mapper.insert(rental);
    }

    @Override
    @Transactional
    public void updateRental(Rental rental) {
        Preconditions.checkNotNull(rental != null, "Rental should not be null.");
        Preconditions.checkNotNull(rental.getId(), "Rentals id should not be null.");
        mapper.update(rental);
    }

    @Override
    @Transactional
    public void deleteRental(Rental rental) {
        Preconditions.checkNotNull(rental != null, "Rental should not be null.");
        Preconditions.checkNotNull(rental.getId(), "Rentals id should not be null.");
        mapper.delete(rental);
    }

    @Override
    public Rental getRental(Long id) {
        Preconditions.checkNotNull(id, "Id should not be null.");
        Rental rental = mapper.selectRental(id);
        return rental;
    }

    @Override
    public List<Rental> getRentals() {
        List<Rental> rentals = mapper.selectRentals();
        return ImmutableList.copyOf(rentals);
    }    
}
