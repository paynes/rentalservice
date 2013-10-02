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
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.LocalDate;

/**
 *
 * @author paynes
 */
public class EditRentalPage extends BasePage {
    
    @SpringBean
    private CarManager carMngr;
    
    @SpringBean
    private CustomerManager customerMngr;
    
    @SpringBean
    private RentalManager rentalMngr;
    
    Boolean payement;
    
    Integer expectedDays;
    
    Car car;
    
    Customer customer;
    
    LocalDate dateFrom;
    
    LocalDate dateTo;
    
    public EditRentalPage() {
        addForm();
        add(new Label("title","Edit rental"));
    }

    private void addForm() {
        Form<EditRentalPage> editForm = new Form<>("editRental",
                new CompoundPropertyModel<>(this));
        
        add(editForm);
        
        Label dateFromLabel = new Label("dateFromLabel", "Date from");
        editForm.add(dateFromLabel);
        
        DateDropDown dateFromField = new DateDropDown("dateFrom");
        dateFromField.setRequired(true);
        editForm.add(dateFromField);
        
        Label dateToLabel = new Label("dateToLabel", "Date to");
        editForm.add(dateToLabel);
        
        DateDropDown dateToField = new DateDropDown("dateTo");
        dateToField.setRequired(true);
        editForm.add(dateToField);
        
        Label expectedDaysLabel = new Label("expectedDaysLabel", "Expected days");
        editForm.add(expectedDaysLabel);
        
        TextField<Integer> expectedDaysField = new TextField<>("expectedDays");
        RangeValidator<Integer> validator = new RangeValidator<>(1,Integer.MAX_VALUE);
        expectedDaysField.add(validator);
        editForm.add(expectedDaysField);
        
        Label payementLabel = new Label("payementLabel", "Paid");
        editForm.add(payementLabel);
        
        CheckBox box = new CheckBox("payement");
        editForm.add(box);
        
        Label carLabel = new Label("carLabel", "Car");
        editForm.add(carLabel);
        
        ChoiceRenderer<Car> carRenderer = new ChoiceRenderer<>("model");
        DropDownChoice<Car> carField = new DropDownChoice<>("car",carMngr.getCars(),carRenderer);
        carField.setRequired(true);
        editForm.add(carField);
        
        
        Label customerLabel = new Label("customerLabel", "Customer");
        editForm.add(customerLabel);
        
        ChoiceRenderer<Customer> customerRenderer = new ChoiceRenderer<>("surname");
        DropDownChoice<Customer> customerField = new DropDownChoice<>("customer", customerMngr.getCustomers(),customerRenderer);
        customerField.setRequired(true);
        editForm.add(customerField);
        
        editForm.add(new DatesRangeValidator(dateFromField,dateToField));
        
        
        Button submitButton = new Button("submitButton") {
            
            @Override
            public void onSubmit() {
                Rental r = new Rental();
                r.setDateFrom(dateFrom);
                r.setDateTo(dateTo);
                r.setDays(expectedDays);
                r.setPaid(payement);
                r.setCar(car);
                r.setCustomer(customer);
                
                rentalMngr.saveRental(r);
                
                getSession().info("Rental added successfully.");
                setResponsePage(RentalsListPage.class);
            }
        };
        
        editForm.add(submitButton);
        
        FeedbackPanel feed = new FeedbackPanel("feed");
        editForm.add(feed);
    }
    
}