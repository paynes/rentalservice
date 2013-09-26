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
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author paynes
 */
public class EditRentalPage extends BasePage {
    
    private CarManager carMngr;
    
    private CustomerManager customerMngr;
    
    private RentalManager rentalMngr;
    
    Boolean payement;
    
    Integer expectedDays;
    
    public EditRentalPage() {
        addForm();
        add(new Label("title","Edit rental"));
    }

    private void addForm() {
        Form<EditRentalPage> editForm = new Form<>("editRental",
                new CompoundPropertyModel<EditRentalPage>(this));
        
        add(editForm);
                     
    }
    
}
