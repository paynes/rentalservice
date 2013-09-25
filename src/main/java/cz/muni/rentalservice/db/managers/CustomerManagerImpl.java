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
import cz.muni.rentalservice.mappers.CustomerMapper;
import cz.muni.rentalservice.models.Customer;

/**
 *
 * @author paynes
 */
@Service
public class CustomerManagerImpl implements CustomerManager {
    
    private final CustomerMapper mapper;
    
    @Autowired
    public CustomerManagerImpl(CustomerMapper mapper) {
        Preconditions.checkNotNull(mapper);
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        Preconditions.checkNotNull(customer != null, "Customer should not be null.");
        Preconditions.checkArgument(customer.getId() == null, "Customer should have id.");

        mapper.insert(customer);
    }

    @Override
    @Transactional
    public void removeCustomer(Customer customer) {
        Preconditions.checkNotNull(customer != null, "Customer should not be null.");
        Preconditions.checkNotNull(customer.getId(), "Customers id should not be null.");
        mapper.delete(customer);
    }

    @Override
    @Transactional
    public void updateCustomer(Customer customer) {
        Preconditions.checkNotNull(customer != null, "Customer should not be null.");
        Preconditions.checkNotNull(customer.getId(), "Customers id should not be null.");
        mapper.update(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = mapper.selectCustomers();
        return ImmutableList.copyOf(customers);
    }

    @Override
    public Customer getCustomer(Long id) {
        Preconditions.checkNotNull(id, "Id should not be null.");
        Customer customer = mapper.selectCustomer(id);
        return customer;
    }    
}
