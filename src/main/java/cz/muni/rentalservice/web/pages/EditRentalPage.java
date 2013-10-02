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
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.LocalDate;

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

    Form form;
    
    Rental rental;
    
    public EditRentalPage() {
        this.rental = new Rental();
        addForm();
        add(new Label("title","Edit rental"));
    }
    
    public EditRentalPage(PageParameters parameters) {
        initRental(parameters);
        addForm();
        add(new Label("title","Edit rental"));
    }

    private void addForm() {
        form = new Form<>("editRental", new CompoundPropertyModel<>(rental));
        
        add(form);
        
        Label dateFromLabel = new Label("dateFromLabel", "Date from");
        form.add(dateFromLabel);
        
        final DateDropDown dateFromField = new DateDropDown("dateFrom");
        if (rental.getDateFrom() != null) {
            dateFromField.setDay(rental.getDateFrom().getDayOfMonth());
            dateFromField.setMonth(rental.getDateFrom().getMonthOfYear());
            dateFromField.setYear(rental.getDateFrom().getYear());
            dateFromField.setRequired(true);
        }
        form.add(dateFromField);
        
        Label dateToLabel = new Label("dateToLabel", "Date to");
        form.add(dateToLabel);
        
        final DateDropDown dateToField = new DateDropDown("dateTo");
        if (rental.getDateTo() != null) {
            dateToField.setDay(rental.getDateTo().getDayOfMonth());
            dateToField.setMonth(rental.getDateTo().getMonthOfYear());
            dateToField.setYear(rental.getDateTo().getYear());
            dateToField.setRequired(true);
        }
        dateToField.setRequired(true);
        form.add(dateToField);
        
        Label expectedDaysLabel = new Label("expectedDaysLabel", "Expected days");
        form.add(expectedDaysLabel);
        
        final TextField<Integer> expectedDaysField = new TextField<>("expectedDays");
        RangeValidator<Integer> validator = new RangeValidator<>(1,Integer.MAX_VALUE);
        expectedDaysField.add(validator);
        form.add(expectedDaysField);
        
        Label payementLabel = new Label("payementLabel", "Paid");
        form.add(payementLabel);
        
        final CheckBox box = new CheckBox("payement");

        form.add(box);
        
        Label carLabel = new Label("carLabel", "Car");
        form.add(carLabel);
        
        ChoiceRenderer<Car> carRenderer = new ChoiceRenderer<>("model");
        final DropDownChoice<Car> carField = new DropDownChoice<>("car",carMngr.getCars(),carRenderer);
        carField.setRequired(true);
        form.add(carField);
        
        
        Label customerLabel = new Label("customerLabel", "Customer");
        form.add(customerLabel);
        
        ChoiceRenderer<Customer> customerRenderer = new ChoiceRenderer<>("surname");
        final DropDownChoice<Customer> customerField = new DropDownChoice<>("customer", customerMngr.getCustomers(),customerRenderer);
        customerField.setRequired(true);
        form.add(customerField);
        
        form.add(new DatesRangeValidator(dateFromField,dateToField));
        
        
        Button submitButton = new Button("submitButton") {
            
            @Override
            public void onSubmit() {
                rental.setDateFrom(dateFromField.getModelObject());
                rental.setDateTo(dateToField.getModelObject());
                rental.setDays(expectedDaysField.getModelObject());
                rental.setPaid(box.getModelObject());
                rental.setCar(carField.getModelObject());
                rental.setCustomer(customerField.getModelObject());
                
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
        
        form.add(submitButton);
        
        FeedbackPanel feed = new FeedbackPanel("feed");
        form.add(feed);
    }
    
    public void initRental(final PageParameters parameters) {
        this.rental = new Rental();
        this.rental.setId(parameters.get("id").toLong());
        this.rental.setCar(carMngr.getCar(parameters.get("car").toLong()));
        this.rental.setCustomer(customerMngr.getCustomer(parameters.get("customer").toLong()));
        this.rental.setPaid(parameters.get("payement").toBoolean());
        this.rental.setDays(parameters.get("expected").toInt());
        LocalDate from = new LocalDate(parameters.get("yearFrom").toInt(),parameters.get("monthFrom").toInt(),parameters.get("dayFrom").toInt());
        LocalDate to = new LocalDate(parameters.get("yearTo").toInt(),parameters.get("monthTo").toInt(),parameters.get("dayTo").toInt());
        this.rental.setDateFrom(from);
        this.rental.setDateTo(to);
    }
    
}