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
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
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
        final Car car = new Car();
        init(car);
    }
    
    public EditCarPage(final PageParameters parameters) {
        final Car car = prepareCar(parameters);
        init(car);
    }
    
    private void init(Car car) {
        addForm(car);
    }
    
    private void addForm(final Car car) {
        
        final TextField<String> modelField = new TextField<>("model");
        modelField.setRequired(true);
        modelField.add(StringValidator.maximumLength(50));
        
        
        final TextField<String> regNumberField = new TextField<>("regNumber");
        regNumberField.setRequired(true);
        regNumberField.add(StringValidator.maximumLength(7));
        
        final TextField<Double> dailyFeeField = new TextField<>("dailyFee");
        dailyFeeField.setRequired(true);
        
        FeedbackPanel feed = new FeedbackPanel("feed");
        
        
        Form<Car> form = new Form("editCar",new CompoundPropertyModel<>(car)) {
            @Override
            protected void onSubmit(){
                if (car.getId() == null) {
                    manager.saveCar(car);
                    //TODO
                    getSession().info(getString("cars.added"));
                } else {
                    manager.updateCar(car);
                    getSession().info(getString("cars.edited"));
                }
                setResponsePage(CarsListPage.class);
            }
        };
        
        
        add(form);
                
        form.add(modelField);
        form.add(regNumberField);
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