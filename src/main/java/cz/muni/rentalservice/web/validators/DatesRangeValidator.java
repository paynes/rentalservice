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
package cz.muni.rentalservice.web.validators;

import cz.muni.rentalservice.web.components.DateDropDown;
import java.util.HashMap;
import java.util.Map;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.model.ResourceModel;

/**
 *
 * @author paynes
 */
public class DatesRangeValidator extends AbstractFormValidator {
    
    private final FormComponent<?>[] components;
    
    public DatesRangeValidator(FormComponent d1, FormComponent d2) {
        if (d1 == null) {
            throw new IllegalArgumentException("argument d1 cannot be null.");
        }
        
        if (d2 == null) {
            throw new IllegalArgumentException("argument d1 cannot be null.");
        }
        components = new FormComponent[] {d1,d2};
    }

    @Override
    public FormComponent<?>[] getDependentFormComponents() {
        return components;
    }

    @Override
    public void validate(Form<?> form) {
        final DateDropDown d1 = (DateDropDown) components[0];
        final DateDropDown d2 = (DateDropDown) components[1];

        if (d1.getConvertedInput().isAfter(d2.getConvertedInput())) {
            Map var = new HashMap();
            var.put("from",d1.getConvertedInput());
            var.put("to",d2.getConvertedInput());
            form.error(new ResourceModel("validator.error").getObject(),var);
        }
    }
}
