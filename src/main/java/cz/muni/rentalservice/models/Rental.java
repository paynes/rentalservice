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
package cz.muni.rentalservice.models;

import java.io.Serializable;
import java.util.List;
import org.joda.time.LocalDate;

/**
 *
 * @author paynes
 */
public class Rental implements Serializable{
    
    private Long id;
    private Car car;
    private Customer customer;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int expectedDays;
    private boolean payement;
    private List<LocalDate> date;

    public Long getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }
    
    public void setDate(List<LocalDate> list) {
        this.date = list;
    }
    
    public List<LocalDate> getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public int getDays() {
        return expectedDays;
    }

    public boolean isPaid() {
        return payement;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDateFrom(LocalDate dt) {
        this.dateFrom = dt;
    }

    public void setDateTo(LocalDate dt) {
        this.dateTo = dt;
    }

    public void setDays(int expectedDays) {
        this.expectedDays = expectedDays;
    }

    public void setPaid(boolean paid) {
        this.payement = paid;
    }

    @Override
    public String toString() {
        return "Rental{" + "id=" + id + ", car=" + car + ", customer=" + 
                customer + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + 
                    ", expectedDays=" + expectedDays + ", paid=" + payement + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rental other = (Rental) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }    
}
