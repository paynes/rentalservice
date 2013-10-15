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

import cz.muni.rentalservice.web.pages.EditRentalPage;
import cz.muni.rentalservice.web.pages.RentalsListPage;
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
public class TestRentalsListPage extends AbstractRentalServiceWebTest {
    
    private WicketTester tester;
    
    @Before
    public void setup() {
        tester = getWicketTester();
        tester.startPage(RentalsListPage.class);
    }
    
    @Test
    @DirtiesContext
    public void testRentalsListPageRendersScuccessfully() {
        tester.assertRenderedPage(RentalsListPage.class);
        tester.assertNoErrorMessage();
    }
    
    
    @Test
    @DirtiesContext
    public void testRentalsListPageLinkOnEditPage() {
        tester.clickLink("EditRentalPage");
        tester.assertRenderedPage(EditRentalPage.class);
    }
    
    @Test
    @DirtiesContext
    public void testRentalsListPageComponents() {
        tester.assertComponent("feed", FeedbackPanel.class);
        tester.assertComponent("rentals", ListView.class);
    }
}
