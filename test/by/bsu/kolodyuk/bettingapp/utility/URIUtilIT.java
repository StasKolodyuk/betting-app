
package by.bsu.kolodyuk.bettingapp.utility;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class URIUtilIT {
    
    public URIUtilIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPageFromURI method, of class URIUtil.
     */
    @Test
    public void testGetPageFromURI() {
        System.out.println("getPageFromURI");
        String requestURI = "http://localhost:8084/BettingApp/pages/common/about.jsp";
        String expResult = "about";
        String result = URIUtil.getPageFromURI(requestURI);
        assertEquals(expResult, result);
    }
    
}
