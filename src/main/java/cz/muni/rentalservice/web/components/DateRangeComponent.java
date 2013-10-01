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

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.joda.time.LocalDate;

/**
 *
 * @author paynes
 */
public class DateRangeComponent extends FormComponentPanel<List<LocalDate>> {
    
    private DateDropDown dateFromField;
    
    private DateDropDown dateToField;
    
    private LocalDate dateTo;
    
    private LocalDate dateFrom;
    
    public DateRangeComponent(String id) {
        super(id);
    }
    
    public void setDateFromField(DateDropDown dateFromField) {
        this.dateFromField = dateFromField;
    }
    
    public DateDropDown getGetDateFromField() {
        return this.dateFromField;
    }
    
    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        Label dateFromLabel = new Label("dateFromLabel", "Date from");
        add(dateFromLabel);
        
        dateFromField = new DateDropDown("from");
        dateFromField.setRequired(this.isRequired());
        //add(dateFromField);
        
        Label dateToLabel = new Label("dateToLabel", "Date to");
        add(dateToLabel);
        
        dateToField = new DateDropDown("dateTo");
        dateToField.setRequired(this.isRequired());
        add(dateToField);
    }
    
    @Override
    protected void convertInput() {
        List<LocalDate> list = new ArrayList<>();
        dateFromField.getConvertedInput();
        list.add(dateTo);
        setConvertedInput(list);
    }
}
