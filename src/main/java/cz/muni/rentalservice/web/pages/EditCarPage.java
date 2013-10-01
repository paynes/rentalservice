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
import cz.muni.rentalservice.models.Car;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

/**
 *
 * @author paynes
 */
public class EditCarPage extends BasePage {
    
    @SpringBean
    private CarManager manager;
    
    String model;
    String regNumber;
    Double dailyFee;
    
    public EditCarPage() {
        addForm();
        add(new Label("title","Edit car"));
    }
    

    private void addForm() {
        Form<EditCarPage> editForm = new Form<>("editCar",
                new CompoundPropertyModel<>(this));
        
        add(editForm);
        
        
        Label modelLabel  = new Label("modelLabel","Model");
        editForm.add(modelLabel);
        
        TextField<String> modelField = new TextField<>("model");
        modelField.setRequired(true);
        modelField.add(StringValidator.maximumLength(50));
        editForm.add(modelField);
        
        Label regNumberLabel = new Label("regNumberLabel", "Registration Number");
        editForm.add(regNumberLabel);
        
        TextField<String> regNumberField = new TextField<>("regNumber");
        regNumberField.setRequired(true);
        regNumberField.add(StringValidator.maximumLength(7));
        editForm.add(regNumberField);
        
        Label dailyFeeLabel = new Label("dailyFeeLabel","Daily fee");
        editForm.add(dailyFeeLabel);
        
        TextField<Double> dailyFeeField = new TextField<>("dailyFee");
        dailyFeeField.setRequired(true);
        editForm.add(dailyFeeField);
        
        Button submitButton = new Button("submitButton") {
            @Override
            public void onSubmit() {
                Car car = new Car();
                car.setModel(model);
                car.setDailyFee(dailyFee);
                car.setRegNumber(regNumber);
                manager.saveCar(car);
 
                getSession().info("Car added successfully");
                setResponsePage(CarsListPage.class);
            }
        };
        
        editForm.add(submitButton);
    }
    
}
