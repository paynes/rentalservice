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
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
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
    
    public EditCarPage() {
        Car car = new Car();
        init(car);
    }
    
    public EditCarPage(final PageParameters parameters) {
        Car car = prepareCar(parameters);
        init(car);
    }
    
    private void init(Car car) {
        add(new Label("title","Edit car"));
        addForm(car);
    }
    

    private void addForm(final Car car) {
        Label modelLabel = new Label("modelLabel","Model");
        
        final TextField<String> modelField = new TextField<>("model");
        modelField.setRequired(true);
        modelField.add(StringValidator.maximumLength(50));
        
        Label regNumberLabel = new Label("regNumberLabel", "Registration Number");
        
        final TextField<String> regNumberField = new TextField<>("regNumber");
        regNumberField.setRequired(true);
        regNumberField.add(StringValidator.maximumLength(7));
        
        Label dailyFeeLabel = new Label("dailyFeeLabel","Daily fee");
        
        final TextField<Double> dailyFeeField = new TextField<>("dailyFee");
        dailyFeeField.setRequired(true);
        
        FeedbackPanel feed = new FeedbackPanel("feed");
        
        
        Form<Car> form = new Form("editCar",new CompoundPropertyModel<>(car)) {
            @Override
            protected void onSubmit(){
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
        
        
        add(form);
                
        form.add(modelLabel);
        form.add(modelField);
        form.add(regNumberLabel);
        form.add(regNumberField);        
        form.add(dailyFeeLabel);
        form.add(dailyFeeField);       
        form.add(feed);
    }
    
    private Car prepareCar(final PageParameters parameters) {
        Car c = manager.getCar(parameters.get("id").toLong());
        if (c == null) {
            return new Car();
        }
        return c;
    }   
}