/*
* Copyright 2013 paynes.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
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
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

/**
*
* @author paynes
*/
public final class EditCarPage extends BasePage {
    
    @SpringBean
    private CarManager manager;
    
    private Form form;
    
    private Car car;
    
    public EditCarPage() {
        this.car = new Car();
        add(new Label("title","Edit car"));
        addForm();
    }
    
    public EditCarPage(final PageParameters parameters) {
        initCar(parameters);
        add(new Label("title","Edit car"));
        addForm();
    }
    

    private void addForm() {        
        form = new Form("editCar",new CompoundPropertyModel<>(car));
        
        add(form);
                
        Label modelLabel = new Label("modelLabel","Model");
        form.add(modelLabel);
        
        final TextField<String> modelField = new TextField<>("model",new PropertyModel(car, "model"));
        modelField.setRequired(true);
        modelField.add(StringValidator.maximumLength(50));
        form.add(modelField);
        
        Label regNumberLabel = new Label("regNumberLabel", "Registration Number");
        form.add(regNumberLabel);
        
        final TextField<String> regNumberField = new TextField<>("regNumber",new PropertyModel(car, "regNumber"));
        regNumberField.setRequired(true);
        regNumberField.add(StringValidator.maximumLength(7));
        form.add(regNumberField);
        
        Label dailyFeeLabel = new Label("dailyFeeLabel","Daily fee");
        form.add(dailyFeeLabel);
        
        final TextField<Double> dailyFeeField = new TextField<>("dailyFee",new PropertyModel(car,"dailyFee"));
        dailyFeeField.setRequired(true);
        form.add(dailyFeeField);
        
        Button submitButton = new Button("submitButton") {
            @Override
            public void onSubmit() {
                car.setModel(modelField.getModelObject());
                car.setDailyFee(dailyFeeField.getModelObject());
                car.setRegNumber(regNumberField.getModelObject());
                if (car.getId() == null) {
                    manager.saveCar(car);
                    getSession().info("Car added successfully");
                } else {
                    manager.updateCar(car);
                    getSession().info("Car edited successfully");
                }
 
                setResponsePage(CarsListPage.class);
            }
        };
        
        FeedbackPanel feed = new FeedbackPanel("feed");
        form.add(feed);
        
        form.add(submitButton);
    }
    
    public void initCar(final PageParameters parameters) {
        this.car = new Car();
        this.car.setId(parameters.get("id").toLong());
        this.car.setModel(parameters.get("model").toString());
        this.car.setRegNumber(parameters.get("regNumber").toString());
        this.car.setDailyFee(parameters.get("dailyFee").toDouble());
    }
    
}