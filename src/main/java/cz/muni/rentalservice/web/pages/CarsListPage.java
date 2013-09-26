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
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;


/**
 *
 * @author paynes
 */
public class CarsListPage extends BasePage{
    
    @SpringBean
    private CarManager manager;
    
    public CarsListPage() {
        add(new Label("message", "Cars"));
        addCarsModule();
        
    }
    
    private void addCarsModule() {
        ListView<Car> cars = new ListView<Car>("cars",createModelForCars()) {
            @Override
            protected void populateItem(ListItem item) {
                Car car = (Car) item.getModelObject();
                item.add(new Label("id", car.getId()));  
                item.add(new Label("model", car.getModel()));
            }
        };
        cars.setVisible(!cars.getList().isEmpty());
        
        add(cars);
    }
    
    private LoadableDetachableModel<List<Car>> createModelForCars() {
        return new LoadableDetachableModel<List<Car>>() {

                        @Override
                        protected List<Car> load() {
                                return manager.getCars();
                        }
                };
    }
}

