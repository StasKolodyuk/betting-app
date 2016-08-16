
package by.bsu.kolodyuk.bettingapp.validation;

import by.bsu.kolodyuk.bettingapp.model.entity.SportEvent;
import by.bsu.kolodyuk.bettingapp.model.entity.SportType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author StasKolodyuk
 */
public class AddSportEventsValidatorIT {
    
    private AddSportEventsValidator validator;
    
    public AddSportEventsValidatorIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<SportEvent> sportEvents = new ArrayList<>();
        SportEvent sportEvent = new SportEvent(10, "Description", SportType.FOOTBALL, Calendar.getInstance(), Calendar.getInstance());
        sportEvents.add(sportEvent);
        validator = new AddSportEventsValidator(sportEvents);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkValidity method, of class AddSportEventsValidator.
     * @throws java.lang.Exception
     */
    @Test
    public void testCheckValidity() throws Exception {
        boolean expResult = false;
        boolean result = validator.checkValidity();
        assertEquals(expResult, result);
    }
    
}
