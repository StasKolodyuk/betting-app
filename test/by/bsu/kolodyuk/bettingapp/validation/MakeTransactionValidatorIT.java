
package by.bsu.kolodyuk.bettingapp.validation;

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
public class MakeTransactionValidatorIT {
    
    private MakeTransactionValidator validator;
    
    public MakeTransactionValidatorIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        validator = new MakeTransactionValidator("Visa", "1234567891011112", "012", "100", "deposit");
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of checkValidity method, of class MakeTransactionValidator.
     * @throws java.lang.Exception
     */
    @Test
    public void testCheckValidity() throws Exception {
        boolean expResult = true;
        boolean result = validator.checkValidity();
        assertEquals(expResult, result);
    }
    
}
