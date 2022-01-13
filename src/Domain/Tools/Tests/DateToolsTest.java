package Domain.Tools.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
// import tools.DateTools;

import Domain.Tools.DateTools;

public class DateToolsTest {

    // public DateToolsTest(){

    // }

    // @BeforeClass
    // public static void setUpClass(){

    // }

    // @AfterClass
    // public static void tearDownClass(){

    // }

    // @Before
    // public void setUp(){

    // }

    // @After 
    // public void tearDown(){

    // }

    @Test
    public void testValidateDateRequiresDay0EnsuresFalse(){
        int day = 0;
        int month = 1;
        int year = 1;
        boolean result = DateTools.isValidDate(day, month, year);
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresMonth0EnsuresFalse(){
        int day = 1;
        int month = 0;
        int year = 1;
        boolean result = DateTools.isValidDate(day,month,year);
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresDay32EnsuresFalse(){
        int day = 32;
        int month = 1;
        int year = 1;
        boolean result = DateTools.isValidDate(day, month, year);
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresMonth13EnsuresFalse(){
        int day = 1;
        int month = 13;
        int year = 1;
        boolean result = DateTools.isValidDate(day,month,year);
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresMonth12EnsuresTrue(){
        int day = 1;
        int month = 12;
        int year = 1;
        boolean result = DateTools.isValidDate(day,month,year);
        assertEquals(true, result);
    }

    @Test
    public void testValidateDateRequiresd31m01y2022EnsuresTrue(){
        boolean result = DateTools.isValidDate(31,1,2022);
        assertEquals(true, result);
    }

    @Test
    public void testValidateDateRequiresd31m04y2022EnsuresFalse(){
        boolean result = DateTools.isValidDate(31,4,2022);
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresd30m04y2022EnsuresTrue(){
        boolean result = DateTools.isValidDate(30,4,2022);
        assertEquals(true, result);
    }

    @Test
    public void testValidateDateRequiresd30m02y2022EnsuresFalse(){
        boolean result = DateTools.isValidDate(30,2,2022);
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresd29m02y2020EnsuresTrue(){
        boolean result = DateTools.isValidDate(29,2,2020);
        assertEquals(true, result);
    }

    @Test
    public void testValidateDateRequiresd29m02y2100EnsuresFalse(){
        boolean result = DateTools.isValidDate(29,2,2100);
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresd28m02y2100EnsuresTrue(){
        boolean result = DateTools.isValidDate(28,2,2100);
        assertEquals(true, result);
    }

    @Test
    public void testValidateDateRequiresd29m02y2000EnsuresTrue(){
        boolean result = DateTools.isValidDate(29,2,2000);
        assertEquals(true, result);
    } 

    @Test
    public void testValidateDateRequiresd29m02y2001EnsuresFalse(){
        boolean result = DateTools.isValidDate(29,2,2001);
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresd28m02y2001EnsuresTrue(){
        boolean result = DateTools.isValidDate(28,2,2001);
        assertEquals(true, result);
    }
    
}
