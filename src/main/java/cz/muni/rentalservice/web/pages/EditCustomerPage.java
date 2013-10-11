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
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
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
    
    public EditCustomerPage() {
        final Customer customer = new Customer();
        init(customer);
    }
    
    public EditCustomerPage(final PageParameters parameters) {
        final Customer customer = prepareCustomer(parameters);
        init(customer);
    }
    
    private void init(Customer customer) {
        addForm(customer);
    }

    private void addForm(final Customer customer) {        
        final FormComponentPanel<LocalDate> bornField = new DateDropDown("born");
        bornField.setRequired(true);
        
        final TextField<String> nameField = new TextField<>("name");
        nameField.setRequired(true);
        nameField.add(StringValidator.maximumLength(20));
        
        final TextField<String> surnameField = new TextField("surname");
        surnameField.setRequired(true);
        surnameField.add(StringValidator.maximumLength(20));
        
        FeedbackPanel feed = new FeedbackPanel("feed");
        
        Form<Customer> form = new Form("editCustomer", new CompoundPropertyModel<>(customer)) {
            @Override
            public void onSubmit() {                
                if (customer.getId() == null) {
                    manager.saveCustomer(customer);
                    //TODO
                    getSession().info(getString("customers.added"));
                } else {
                    manager.updateCustomer(customer);
                    getSession().info(getString("customers.edited"));
                }
                setResponsePage(CustomersListPage.class);
            }
        };
        
        add(form);
        
        form.add(bornField);
        form.add(nameField);
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
