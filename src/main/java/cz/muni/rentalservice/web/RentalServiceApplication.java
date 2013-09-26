package cz.muni.rentalservice.web;

import cz.muni.rentalservice.web.pages.CarsListPage;
import cz.muni.rentalservice.web.pages.CustomersListPage;
import cz.muni.rentalservice.web.pages.RentalsListPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.stereotype.Component;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see cz.muni.Start#main(String[])
 */
@Component
public class RentalServiceApplication extends WebApplication {    	
    
            
    @Override
    public Class getHomePage() {
	return CarsListPage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
	super.init();

	this.getComponentInstantiationListeners().add(new SpringComponentInjector(this));
                
        this.mountPage("cars", CarsListPage.class);
        this.mountPage("customers", CustomersListPage.class);
        this.mountPage("rentals",RentalsListPage.class);
    }
}
