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
package cz.muni.rentalservice.web.pages;

import cz.muni.rentalservice.db.managers.CustomerManager;
import cz.muni.rentalservice.models.Customer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.LocalDate;


/**
 *
 * @author paynes
 */
public class EditCustomerPage extends BasePage {
    
    @SpringBean
    private CustomerManager manager;
    
    String name;
    String surname;
    String year;
    String month;
    String day;
    
    public EditCustomerPage() {
        addForm();
    }

    private void addForm() {
        Form<EditCustomerPage> editCustomer = new Form<>("editCustomer", 
                new CompoundPropertyModel<>(this));
        
        add(editCustomer);
        
        Label nameLabel = new Label("nameLabel","Customers name");
        editCustomer.add(nameLabel);
        
        RequiredTextField<String> nameField = new RequiredTextField<>("name");
        editCustomer.add(nameField);
        
        Label surnameLabel = new Label("surnameLabel","Customers surname");
        editCustomer.add(surnameLabel);
        
        RequiredTextField<String> surnameField = new RequiredTextField("surname");
        editCustomer.add(surnameField);
        
        Button submitButton = new Button("submitButton") {
            
            @Override
            public void onSubmit() {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setSurname(surname);
                LocalDate dt = new LocalDate(1991,3,9);
                customer.setBorn(dt);
                manager.saveCustomer(customer);
                
                getSession().info("Customer added successfully");
                setResponsePage(CustomersListPage.class);
            }
        };
        
        editCustomer.add(submitButton);
    }
}