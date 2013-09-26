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
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author paynes
 */
public class EditCarPage extends BasePage {
    
    @SpringBean
    private CarManager manager;
    
    String model;
    String regNumber;
    String dailyFee;
    
    public EditCarPage() {
        addForm();
        add(new Label("title","Edit car"));
    }
    

    private void addForm() {
        Form<EditCarPage> editForm = new Form<EditCarPage>("editCar",
                new CompoundPropertyModel<EditCarPage>(this));
        
        add(editForm);
        
        Label modelLabel  = new Label("modelLabel","Model");
        editForm.add(modelLabel);
        
        RequiredTextField<String> modelField = new RequiredTextField<String>("model");
        editForm.add(modelField);
        
        Label regNumberLabel = new Label("regNumberLabel", "Registration Number");
        editForm.add(regNumberLabel);
        
        RequiredTextField<String> regNumberField = new RequiredTextField<String>("regNumber");
        editForm.add(regNumberField);
        
        Label dailyFeeLabel = new Label("dailyFeeLabel","Daily fee");
        editForm.add(dailyFeeLabel);
        
        RequiredTextField<String> dailyFeeField = new RequiredTextField<String>("dailyFee");
        editForm.add(dailyFeeField);
        
        Button submitButton = new Button("submitButton") {
            @Override
            public void onSubmit() {
                Car car = new Car();
                car.setModel(model);
                car.setDailyFee(Double.valueOf(dailyFee));
                car.setRegNumber(regNumber);
                manager.saveCar(car);
 
                getSession().info("Car added successfully");
                setResponsePage(CarsListPage.class);
            }
        };
        
        editForm.add(submitButton);
    }
    
}
