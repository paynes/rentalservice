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
import org.apache.wicket.model.IModel;
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
    
    //TODO Opravit konstruktory
    public EditCustomerPage() {
        Customer customer = new Customer();
        init(customer);
    }
    
    public EditCustomerPage(final PageParameters parameters) {
        Customer customer = prepareCustomer(parameters);
        init(customer);
    }
    
    private void init(Customer customer) {
        add(new Label("title", "Edit Customer"));
        addForm(customer);
    }

    private void addForm(final Customer customer) {
        Label dropLabel = new Label("dropLabel","Choose date of birth");
        
        final FormComponentPanel<LocalDate> bornField = new DateDropDown("born");
        bornField.setRequired(true);
        
        Label nameLabel = new Label("nameLabel","Customers name");
        
        final TextField<String> nameField = new TextField<>("name");
        nameField.setRequired(true);
        nameField.add(StringValidator.maximumLength(20));
        
        Label surnameLabel = new Label("surnameLabel","Customers surname");
        
        final TextField<String> surnameField = new TextField("surname");
        surnameField.setRequired(true);
        surnameField.add(StringValidator.maximumLength(20));
        
        FeedbackPanel feed = new FeedbackPanel("feed");
        
        Form<Customer> form = new Form("editCustomer", new CompoundPropertyModel<>(customer)) {
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
        
        add(form);
        
        form.add(dropLabel);
        form.add(bornField);
        form.add(nameLabel);
        form.add(nameField);
        form.add(surnameLabel);
        form.add(surnameField);
        form.add(feed);
        
    }
    
    private Customer prepareCustomer(final PageParameters parameters) {
        Customer c = manager.getCustomer(parameters.get("id").toLong());
        if (c == null) {
            return new Customer();
        }
        return c;
    }
}
