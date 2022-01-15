package Domain.Tools.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import Domain.Tools.DateTools;

public class DateToolsTest {

    @Test
    public void testValidateDateRequiresDay0EnsuresFalse(){
        //arrange
        int day = 0;
        int month = 1;
        int year = 1;

        //act
        boolean result = DateTools.isValidDate(day, month, year);

        //arrange
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresMonth0EnsuresFalse(){
        //arrange
        int day = 1;
        int month = 0;
        int year = 1;

        //act
        boolean result = DateTools.isValidDate(day,month,year);

        //arrange
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresDay32EnsuresFalse(){
        //arrange
        int day = 32;
        int month = 1;
        int year = 1;

        //act
        boolean result = DateTools.isValidDate(day, month, year);

        //arrange
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresMonth13EnsuresFalse(){
        //arrange
        int day = 1;
        int month = 13;
        int year = 1;

        //act
        boolean result = DateTools.isValidDate(day,month,year);

        //arrange
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresMonth12EnsuresTrue(){
        //arrange
        int day = 1;
        int month = 12;
        int year = 1;

        //act
        boolean result = DateTools.isValidDate(day,month,year);

        //arrange
        assertEquals(true, result);
    }

    @Test
    public void testValidateDateRequiresd31m01y2022EnsuresTrue(){
        //arrange

        //act
        boolean result = DateTools.isValidDate(31,1,2022);

        //arrange
        assertEquals(true, result);
    }

    @Test
    public void testValidateDateRequiresd31m04y2022EnsuresFalse(){
        //arrange

        //act
        boolean result = DateTools.isValidDate(31,4,2022);

        //arrange
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresd30m04y2022EnsuresTrue(){
        //arrange

        //act
        boolean result = DateTools.isValidDate(30,4,2022);

        //arrange
        assertEquals(true, result);
    }

    @Test
    public void testValidateDateRequiresd30m02y2022EnsuresFalse(){
        //arrange

        //act
        boolean result = DateTools.isValidDate(30,2,2022);

        //arrange
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresd29m02y2020EnsuresTrue(){
        //arrange

        //act
        boolean result = DateTools.isValidDate(29,2,2020);

        //arrange
        assertEquals(true, result);
    }

    @Test
    public void testValidateDateRequiresd29m02y2100EnsuresFalse(){
        //arrange

        //act
        boolean result = DateTools.isValidDate(29,2,2100);

        //arrange
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresd28m02y2100EnsuresTrue(){
        //arrange

        //act
        boolean result = DateTools.isValidDate(28,2,2100);

        //arrange
        assertEquals(true, result);
    }

    @Test
    public void testValidateDateRequiresd29m02y2000EnsuresTrue(){
        //arrange
        
        //act
        boolean result = DateTools.isValidDate(29,2,2000);

        //arrange
        assertEquals(true, result);
    } 

    @Test
    public void testValidateDateRequiresd29m02y2001EnsuresFalse(){
        //arrange

        //act
        boolean result = DateTools.isValidDate(29,2,2001);

        //arrange
        assertEquals(false, result);
    }

    @Test
    public void testValidateDateRequiresd28m02y2001EnsuresTrue(){
        //arrange

        //act
        boolean result = DateTools.isValidDate(28,2,2001);

        //arrange
        assertEquals(true, result);
    }
    
}
