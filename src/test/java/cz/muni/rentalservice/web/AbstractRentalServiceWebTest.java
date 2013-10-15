
package cz.muni.rentalservice.web;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import org.apache.wicket.protocol.http.mock.MockServletContext;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import cz.muni.rentalservice.test.AbstractRentalServiceTest;

/**
 *
 * @author paynes
 */
public abstract class AbstractRentalServiceWebTest extends AbstractRentalServiceTest {
    
    @Inject
    RentalServiceApplication application;
    
    private WicketTester tester;
    
    @Before
    public void setUp() {
        String pathUrl = AbstractRentalServiceTest.class.getResource("/").getPath();
        
        final MockServletContext servletContext = new MockServletContext(application, pathUrl);
        servletContext.addInitParameter(ContextLoaderListener.CONFIG_LOCATION_PARAM, "/WEB-INF/applicationContext.xml");
        
        
        final ContextLoaderListener cll = new TestContextListener();
        cll.initWebApplicationContext(servletContext);
        
        tester = new WicketTester(application, servletContext);
    }
    
    @After
    public void tearDown() {
        if (tester != null) {
            tester.destroy();
        }        
    }
    
    protected WicketTester getWicketTester() {
        return this.tester;
    }

    private class TestContextListener extends ContextLoaderListener {
        @Override
        protected ApplicationContext loadParentContext(ServletContext servletContext) {
            return applicationContext;
        }                        
    }
}
