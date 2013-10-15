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
package cz.muni.rentalservice.web;

import cz.muni.rentalservice.web.pages.EditCarPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

/**
 *
 * @author paynes
 */

public class TestEditCarPage extends AbstractRentalServiceWebTest {
    
    private WicketTester tester;
    
    @Before
    public void setup() {
        tester = getWicketTester();
        tester.startPage(EditCarPage.class);
    }
    
    
    @Test
    @DirtiesContext
    public void testEditCarPageRendersSuccessfully() {
        tester.assertRenderedPage(EditCarPage.class);
        tester.assertNoErrorMessage();
    }
    
    @Test
    @DirtiesContext
    public void testEditCarPageForm() {
        editCarPageForm();
        tester.assertFeedback("feed", "Car added successfully");
    }
    
    @Test
    @DirtiesContext
    public void testEditCarPageComponents() {
        tester.assertComponent("editCar", Form.class);
    }
    
    private void editCarPageForm() {
        FormTester fTester = tester.newFormTester("editCar");
        fTester.setValue("model", "Renault");
        fTester.setValue("regNumber", "LM-157");
        fTester.setValue("dailyFee", "58");
        fTester.submit();
   }
}
