package cz.muni.rentalservice.web.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class BasePage extends WebPage {
    
    public BasePage() {
        super();
        this.init();
    }

    public BasePage(final PageParameters parameters) {
        super(parameters);
    }
    
    public final void init() {
        
        this.add(new BookmarkablePageLink<Void>("CarsListPage",CarsListPage.class));
        this.add(new BookmarkablePageLink<Void>("CustomersListPage",CustomersListPage.class));
        this.add(new BookmarkablePageLink<Void>("RentalsListPage",RentalsListPage.class));
    }
}
