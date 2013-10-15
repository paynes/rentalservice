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

import cz.muni.rentalservice.web.components.DateDropDown;
import cz.muni.rentalservice.web.pages.EditRentalPage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

/**
 *
 * @author paynes
 */
public class TestEditRentalPage extends AbstractRentalServiceWebTest {
    
    private WicketTester tester;
    
    @Before
    public void setup() {
        tester = getWicketTester();
        tester.startPage(EditRentalPage.class);
    }
    
    @Test
    @DirtiesContext
    public void editRentalPageRendersSuccessfully() {
        tester.assertRenderedPage(EditRentalPage.class);
        tester.assertNoErrorMessage();
    }
    
    @Test
    @DirtiesContext
    public void testEditRantalPageComponents() {
        tester.assertComponent("editRental", Form.class);
        tester.assertComponent("editRental:dateFrom", DateDropDown.class);
        tester.assertComponent("editRental:dateFrom:day", TextField.class);
        tester.assertComponent("editRental:dateFrom:month", TextField.class);
        tester.assertComponent("editRental:dateFrom:year", TextField.class);
        tester.assertComponent("editRental:dateTo", DateDropDown.class);
        tester.assertComponent("editRental:dateFrom:day", TextField.class);
        tester.assertComponent("editRental:dateFrom:month", TextField.class);
        tester.assertComponent("editRental:dateFrom:year", TextField.class);
        tester.assertComponent("editRental:expectedDays", TextField.class);
        tester.assertComponent("editRental:payement", CheckBox.class);
        tester.assertComponent("editRental:car", DropDownChoice.class);
        tester.assertComponent("editRental:customer", DropDownChoice.class);
    }
    
    @Test
    @DirtiesContext
    public void testEditRentalPageForm() {
        editRentalPageForm();
        tester.assertFeedback("feed", "Rental added successfully.");
    }
    
    private void editRentalPageForm() {
        FormTester fTester = tester.newFormTester("editRental");
        fTester.setValue("dateFrom:day", "25");
        fTester.setValue("dateFrom:month", "10");
        fTester.setValue("dateFrom:year", "2013");
        fTester.setValue("dateTo:day", "27");
        fTester.setValue("dateTo:month", "10");
        fTester.setValue("dateTo:year", "2013");
        fTester.setValue("payement", true);
        fTester.setValue("expectedDays", "5");
        fTester.select("car", 1);
        fTester.select("customer", 1);
        fTester.submit();
    }
}
