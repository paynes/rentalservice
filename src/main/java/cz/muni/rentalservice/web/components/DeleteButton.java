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
package cz.muni.rentalservice.web.components;

import cz.muni.rentalservice.db.managers.CarManager;
import cz.muni.rentalservice.models.Car;
import cz.muni.rentalservice.web.pages.CarsListPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author paynes
 */
public class DeleteButton extends Button {
    
    private Car car;
    
    @SpringBean
    CarManager manager;
    public DeleteButton(String id, IModel model) {
        super(id,model);
        car = (Car) model.getObject();
    }
    
    @Override
    public void onSubmit() {
        //Car car = (Car)getParent().getDefaultModelObject();
        manager.removeCar(car);
        setResponsePage(new CarsListPage());
    }
}
