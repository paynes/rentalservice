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
import cz.muni.rentalservice.web.components.DateDropDown;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.joda.time.LocalDate;



/**
 *
 * @author paynes
 */
public final class EditCustomerPage extends BasePage {
    
    @SpringBean
    private CustomerManager manager;
    
    
    //TODO odstranit
    private Form form;

    private Customer customer;
    
    //TODO Opravit konstruktory
    public EditCustomerPage() {
        this.customer = new Customer();
        addForm();
        add(new Label("title", "Edit Customer"));
    }
    
    public EditCustomerPage(final PageParameters parameters) {
        initCustomer(parameters);
        addForm();
        add(new Label("title", "Edit Customer"));
    }

    private void addForm() {
        //TODO odstranit property modely
        form = new Form<>("editCustomer", new CompoundPropertyModel<>(customer));
        
        add(form);
        
        
        Label dropLabel = new Label("dropLabel","Choose date of birth");
        form.add(dropLabel);
        
        final FormComponentPanel<LocalDate> bornField = new DateDropDown("born");
        bornField.setRequired(true);
        form.add(bornField);
        
        Label nameLabel = new Label("nameLabel","Customers name");
        form.add(nameLabel);
        
        final TextField<String> nameField = new TextField<>("name", new PropertyModel(customer, "name"));
        nameField.setRequired(true);
        nameField.add(StringValidator.maximumLength(20));
        form.add(nameField);
        
        Label surnameLabel = new Label("surnameLabel","Customers surname");
        form.add(surnameLabel);
        
        final TextField<String> surnameField = new TextField("surname", new PropertyModel(customer, "surname"));
        surnameField.setRequired(true);
        surnameField.add(StringValidator.maximumLength(20));
        form.add(surnameField);
        
        
        //TODO odstranit a submit vo forme spravit
        Button submitButton = new Button("submitButton") {
            
            @Override
            public void onSubmit() {
                customer.setName(nameField.getModelObject());
                customer.setSurname(surnameField.getModelObject());
                //customer.setBorn(bornField.getModelObject());
                
                if (customer.getId() == null) {
                    manager.saveCustomer(customer);
                    getSession().info("Customer added successfully");
                } else {
                    manager.updateCustomer(customer);
                    getSession().info("Customer edited successfully");
                }
                
                setResponsePage(CustomersListPage.class);
            }
        };
        
        System.out.println(getDefaultModelObject());
        form.add(submitButton);
        System.out.println(getDefaultModelObject());
        FeedbackPanel feed = new FeedbackPanel("feed");
        form.add(feed);
        
    }
    
    public void initCustomer(final PageParameters parameters) {
        //TODO prepareCustomer a tahat z databazy
        this.customer = new Customer();
        this.customer.setId(parameters.get("id").toLong());
        this.customer.setName(parameters.get("name").toString());
        this.customer.setSurname(parameters.get("surname").toString());
        LocalDate dt = new LocalDate(parameters.get("year").toInt(),parameters.get("month").toInt(),parameters.get("day").toInt());
        //this.customer.setBorn(dt);
    }
}
