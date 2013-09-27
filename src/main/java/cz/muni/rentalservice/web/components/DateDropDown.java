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
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.PropertyModel;
import org.joda.time.LocalDateTime;

/**
 *
 * @author paynes
 */
public class DateDropDown extends FormComponentPanel<LocalDateTime> {
    
    private static final long serialVersionUID = 1L;

    private Integer month;
    private Integer day;
    private Integer year;
    
    private DropDownChoice<Integer> monthField;
    private DropDownChoice<Integer> dayField;
    private DropDownChoice<Integer> yearField;
    
    public DateDropDown(String id) {
        super(id);
        
        List<Integer> a = new ArrayList<>();
        a.add(1955);
        a.add(1888);
        monthField = new DropDownChoice<>("month", new PropertyModel<Integer>(this,"month"),a);
        dayField = new DropDownChoice<>("day", new PropertyModel<Integer>(this,"day"),a);
        yearField = new DropDownChoice<>("year", new PropertyModel<Integer>(this,"year"),a);
        add(monthField);
        add(dayField);
        add(yearField);
    }
    
    public Integer getMonth() {
        return month;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public Integer getDay() {
        return day;
    }
    
    public void setMonth(Integer month) {
        this.month = month;
    }
    
    public void setDay(Integer day) {
        this.day = day;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    @Override
    protected void convertInput() {
        setMonth(monthField.getConvertedInput());
        setDay(dayField.getConvertedInput());
        setYear(yearField.getConvertedInput());
        setConvertedInput(new LocalDateTime(getYear(),getMonth(),getDay(),0,0));
    }
}
