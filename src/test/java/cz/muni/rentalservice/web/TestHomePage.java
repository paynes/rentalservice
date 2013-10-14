package cz.muni.rentalservice.web;

import cz.muni.rentalservice.web.pages.BasePage;
import cz.muni.rentalservice.web.pages.CarsListPage;
import cz.muni.rentalservice.web.pages.CustomersListPage;
import cz.muni.rentalservice.web.pages.RentalsListPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage extends AbstractRentalServiceWebTest {
    
    private WicketTester tester;
    
    @Before
    public void setup() {
        tester = getWicketTester();
        tester.startPage(BasePage.class);
    }

    @Test
    @DirtiesContext
    public void basePageRendersSuccessfully() {
        //assert rendered page class
        tester.assertRenderedPage(BasePage.class);
        tester.assertNoErrorMessage();
    }
    
    @Test
    @DirtiesContext
    public void basePageClickLink() {
        tester.assertBookmarkablePageLink("CarsListPage", CarsListPage.class, new PageParameters());
        tester.assertBookmarkablePageLink("CustomersListPage", CustomersListPage.class, new PageParameters());
        tester.assertBookmarkablePageLink("RentalsListPage", RentalsListPage.class, new PageParameters());
    }
}
