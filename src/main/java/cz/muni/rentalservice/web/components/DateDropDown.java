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


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;
import org.joda.time.LocalDate;


/**
 *
 * @author paynes
 */
public class DateDropDown extends FormComponentPanel<LocalDate> {
    
    private static final long serialVersionUID = 1L;
    
    private final TextField<Integer> dayField;
    private final TextField<Integer> monthField;
    private final TextField<Integer> yearField;

    public DateDropDown(String id) {
        super(id);
        
        add(new Label("dayLabel","Day"));
        dayField = new TextField<>("day",new Model<Integer>());
        
        add(new Label("monthLabel","Month"));
        monthField = new TextField<>("month",new Model<Integer>());
        
        add(new Label("yearLabel","Year"));
        yearField = new TextField<>("year",new Model<Integer>());
    }
    
    @Override
    protected void onInitialize() {
        super.onInitialize();

        dayField.setRequired(this.isRequired());
        //RangeValidator<Integer> validator = new RangeValidator<>(1,31);
        //dayField.add(validator);
        add(dayField);
        
        monthField.setRequired(this.isRequired());
        //monthField.add(new RangeValidator<>(1,12));
        add(monthField);
        
        yearField.setRequired(this.isRequired());
        add(yearField);
        
    }
   
    
    @Override
    protected void convertInput() {
        try {
            setConvertedInput(new LocalDate(yearField.getConvertedInput(),monthField.getConvertedInput(),dayField.getConvertedInput()));
        } catch (IllegalArgumentException ex) {
            this.warn("Zadany datum neexistuje.");
        }
    }
}
