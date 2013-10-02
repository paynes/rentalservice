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
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author paynes
 */
public class CustomersListPage extends BasePage {
    
    @SpringBean
    private CustomerManager manager;
    
    public CustomersListPage() {
        initComponents();
    }
    
    private void initComponents() {
        add(new Label("message", "Customers"));
        addCustomersModule();
        
        add(new Link<BasePage>("EditCustomerPage") {

            @Override
            public void onClick() {
                setResponsePage(new EditCustomerPage());
            }
        }); 
        
        add(new FeedbackPanel("feed"));
    }
    
    private void addCustomersModule() {
        ListView<Customer> customers = new ListView<Customer>("customers",createModelForCustomers()) {

            @Override
            protected void populateItem(ListItem item) {
                final Customer customer = (Customer) item.getModelObject();
                item.add(new Label("id",customer.getId()));
                item.add(new Label("name", customer.getName()));
                item.add(new Label("surname", customer.getSurname()));
                item.add(new Label("born",customer.getBorn()));
                item.add(new Link("delete") {

                    @Override
                    public void onClick() {
                       manager.removeCustomer(customer);
                       setResponsePage(new CustomersListPage());
                    }
                    
                });
                item.add(new Link("edit") {

                    @Override
                    public void onClick() {
                        PageParameters parameters = new PageParameters();
                        parameters.add("id", customer.getId());
                        parameters.add("name", customer.getName());
                        parameters.add("surname", customer.getSurname());
                        parameters.add("day",customer.getBorn().getDayOfMonth());
                        parameters.add("month", customer.getBorn().getMonthOfYear());
                        parameters.add("year", customer.getBorn().getYear());
                        setResponsePage(new EditCustomerPage(parameters));
                    }
                    
                });
            }          
        };
        customers.setVisible(!customers.getList().isEmpty());
        
        add(customers);
        
        Label noCustomers = new Label("noCustomers","V databaze nie su ziadny uzivatelia");
        noCustomers.setVisible(!customers.isVisible());
        add(noCustomers);
    }

    private LoadableDetachableModel<List<Customer>> createModelForCustomers() {
        return new LoadableDetachableModel<List<Customer>>() {

            @Override
            protected List<Customer> load() {
                return manager.getCustomers();
            }            
        };
    }
}
