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

import cz.muni.rentalservice.db.managers.RentalManager;
import cz.muni.rentalservice.models.Rental;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author paynes
 */
public class RentalsListPage extends BasePage {
 
    @SpringBean
    private RentalManager manager;
    
    public RentalsListPage() {
        add(new Label("message", "Rentals"));
        addRentalsModule();
        
        add(new Link<BasePage>("EditRentalPage") {

            @Override
            public void onClick() {
                setResponsePage(new EditRentalPage());
            }
            
        });
        
        add(new FeedbackPanel("feed"));
        
    }

    private void addRentalsModule() {
        ListView rentals = new ListView<Rental>("rentals", createModelForRental()) {

            @Override
            protected void populateItem(ListItem item) {
                Rental rental = (Rental) item.getModelObject();
                item.add(new Label("id",rental.getId()));
                item.add(new Label("customer",rental.getCustomer().getSurname()));
                item.add(new Label("car",rental.getCar().getModel()));
                item.add(new Label("payement",rental.isPaid()));
                item.add(new Label("dateFrom",rental.getDateFrom()));
                item.add(new Label("dateTo",rental.getDateTo()));
            }          
        };
        
        rentals.setVisible(!rentals.getList().isEmpty());
        
        add(rentals);
        
        
        Label noRentals = new Label("noRentals", "V databaze nie su ziadne zaznamy.");
        noRentals.setVisible(!rentals.isVisible());
        add(noRentals);
    }

    private LoadableDetachableModel<List<Rental>> createModelForRental() {
        return new LoadableDetachableModel<List<Rental>>() {

            @Override
            protected List<Rental> load() {
                return manager.getRentals();
            }
            
        };
    }
}
