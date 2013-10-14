package cz.muni.rentalservice.web;

import cz.muni.rentalservice.web.pages.BasePage;
import cz.muni.rentalservice.web.pages.CarsListPage;
import cz.muni.rentalservice.web.pages.CustomersListPage;
import cz.muni.rentalservice.web.pages.RentalsListPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage extends AbstractRentalServiceWebTest {

    @Test
    @DirtiesContext
    public void basePageRendersSuccessfully() {
        //start and render the test page
        WicketTester tester = this.getWicketTester();
        tester.startPage(BasePage.class);

        //assert rendered page class
        tester.assertRenderedPage(BasePage.class);
    }
    
    @Test
    @DirtiesContext
    public void basePageClickLink() {
        WicketTester tester = getWicketTester();
        tester.startPage(BasePage.class);
        tester.assertBookmarkablePageLink("CarsListPage", CarsListPage.class, new PageParameters());
        tester.assertBookmarkablePageLink("CustomersListPage", CustomersListPage.class, new PageParameters());
        tester.assertBookmarkablePageLink("RentalsListPage", RentalsListPage.class, new PageParameters());
    }
}
