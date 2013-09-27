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

import cz.muni.rentalservice.db.managers.CarManager;
import cz.muni.rentalservice.db.managers.CustomerManager;
import cz.muni.rentalservice.models.Customer;
import cz.muni.rentalservice.web.components.DateDropDown;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.joda.time.LocalDateTime;



/**
 *
 * @author paynes
 */
public class EditCustomerPage extends BasePage {
    
    @SpringBean
    private CustomerManager manager;
    
    @SpringBean
    private CarManager cManager;
    
    
    String name;
    String surname;
    LocalDateTime born;
    //String month;
    
    public EditCustomerPage() {
        addForm();
        add(new Label("title", "Edit Customer"));
    }

    private void addForm() {
        Form<EditCustomerPage> editCustomer = new Form<>("editCustomer", 
                new CompoundPropertyModel<>(this));
        
        add(editCustomer);
        
        
        Label dropLabel = new Label("dropLabel","Choose date of birth");
        editCustomer.add(dropLabel);
        
        DateDropDown bornField = new DateDropDown("born");
        editCustomer.add(bornField);
        
        Label nameLabel = new Label("nameLabel","Customers name");
        editCustomer.add(nameLabel);
        
        TextField<String> nameField = new TextField<>("name");
        nameField.setRequired(true);
        nameField.add(StringValidator.maximumLength(20));
        editCustomer.add(nameField);
        
        Label surnameLabel = new Label("surnameLabel","Customers surname");
        editCustomer.add(surnameLabel);
        
        TextField<String> surnameField = new TextField("surname");
        surnameField.setRequired(true);
        surnameField.add(StringValidator.maximumLength(20));
        editCustomer.add(surnameField);
        
        Button submitButton = new Button("submitButton") {
            
            @Override
            public void onSubmit() {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setSurname(surname);
                //customer.setBorn(born);
                LocalDateTime dt = new LocalDateTime(2013,9,27,0,0);
                customer.setBorn(born);
                
                manager.saveCustomer(customer);
                
                getSession().info("Customer added successfully");
                setResponsePage(CustomersListPage.class);
            }
        };
        
        editCustomer.add(submitButton);
    }
}
