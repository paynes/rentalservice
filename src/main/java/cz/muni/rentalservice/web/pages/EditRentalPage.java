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
import cz.muni.rentalservice.db.managers.RentalManager;
import cz.muni.rentalservice.models.Car;
import cz.muni.rentalservice.models.Customer;
import cz.muni.rentalservice.models.Rental;
import cz.muni.rentalservice.web.components.DateDropDown;
import cz.muni.rentalservice.web.validators.DatesRangeValidator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author paynes
 */
public final class EditRentalPage extends BasePage {
    
    @SpringBean
    private CarManager carMngr;
    
    @SpringBean
    private CustomerManager customerMngr;
    
    @SpringBean
    private RentalManager rentalMngr;
    
    public EditRentalPage() {
        final Rental rental = new Rental();
        init(rental);
    }
    
    public EditRentalPage(PageParameters parameters) {
        final Rental rental = prepareRental(parameters);
        init(rental);
    }
    
    private void init(Rental rental) {
        add(new Label("title", "Edit rental"));
        addForm(rental);
    }

    private void addForm(final Rental rental) {
        Label dateFromLabel = new Label("dateFromLabel", "Date from");
        
        final DateDropDown dateFromField = new DateDropDown("dateFrom");
        dateFromField.setRequired(true);
        
        Label dateToLabel = new Label("dateToLabel", "Date to");
        
        final DateDropDown dateToField = new DateDropDown("dateTo");
        dateToField.setRequired(true);
        
        Label expectedDaysLabel = new Label("expectedDaysLabel", "Expected days");
        
        final TextField<Integer> expectedDaysField = new TextField<>("expectedDays");
        RangeValidator<Integer> validator = new RangeValidator<>(1,Integer.MAX_VALUE);
        expectedDaysField.add(validator);
        
        Label payementLabel = new Label("payementLabel", "Paid");
        
        final CheckBox box = new CheckBox("payement");
        
        Label carLabel = new Label("carLabel", "Car");
        
        ChoiceRenderer<Car> carRenderer = new ChoiceRenderer<>("model");
        final DropDownChoice<Car> carField = new DropDownChoice<>("car",carMngr.getCars(),carRenderer);
        carField.setRequired(true);
        
        Label customerLabel = new Label("customerLabel", "Customer");
        
        ChoiceRenderer<Customer> customerRenderer = new ChoiceRenderer<>("surname");
        final DropDownChoice<Customer> customerField = new DropDownChoice<>("customer", customerMngr.getCustomers(),customerRenderer);
        customerField.setRequired(true);
        
        FeedbackPanel feed = new FeedbackPanel("feed");
        
        Form<Rental> form = new Form("editRental", new CompoundPropertyModel<>(rental)) {
            @Override
            public void onSubmit() {                
                if (rental.getId() == null) {
                    rentalMngr.saveRental(rental);
                    getSession().info("Rental added successfully.");
                } else {
                    rentalMngr.updateRental(rental);
                    getSession().info("Rental edited successfully.");
                }
                setResponsePage(RentalsListPage.class);
            }
        };
        
        add(form);
        
        form.add(dateFromLabel);
        form.add(dateFromField);
        form.add(dateToLabel);
        form.add(dateToField);
        form.add(expectedDaysLabel);
        form.add(expectedDaysField);
        form.add(payementLabel);
        form.add(box);
        form.add(carLabel);
        form.add(carField);
        form.add(customerLabel);
        form.add(customerField);
        form.add(new DatesRangeValidator(dateFromField,dateToField));
        form.add(feed);
    }
    
    private Rental prepareRental(final PageParameters parameters) {
        Rental rental = rentalMngr.getRental(parameters.get("id").toLong());
        if (rental == null) {
            return new Rental();
        }
        return rental;
    }
    
}