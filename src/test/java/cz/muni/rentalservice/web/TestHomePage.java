package cz.muni.rentalservice.web;

import cz.muni.rentalservice.web.pages.BasePage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage extends AbstractRentalServiceWebTest {            

    @Test
    public void homepageRendersSuccessfully() {
        //start and render the test page
        WicketTester tester = this.getWicketTester();
        tester.startPage(BasePage.class);

        //assert rendered page class
        tester.assertRenderedPage(BasePage.class);
    }
    
    
}
