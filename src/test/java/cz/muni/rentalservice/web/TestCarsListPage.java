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

import cz.muni.rentalservice.web.pages.CarsListPage;
import cz.muni.rentalservice.web.pages.EditCarPage;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

/**
 *
 * @author paynes
 */
public class TestCarsListPage extends AbstractRentalServiceWebTest{
    
    private WicketTester tester;
    
    @Before
    public void setup() {
        tester = getWicketTester();
        tester.startPage(CarsListPage.class);
    }
    
    @Test
    @DirtiesContext
    public void testCarsListPageRendersSuccessfully() {        
        tester.assertRenderedPage(CarsListPage.class);
        tester.assertNoErrorMessage();
    }
    
    @Test
    @DirtiesContext
    public void testCarsListPageLinkOnEditPage() {
        tester.clickLink("EditCarPage");
        tester.assertRenderedPage(EditCarPage.class);
    }
    
    @Test
    @DirtiesContext
    public void testCarsListPageComponents() {
        tester.assertComponent("feed", FeedbackPanel.class);
        tester.assertComponent("cars", ListView.class);
    }
}
