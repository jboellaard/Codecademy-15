package Domain.Tools.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import Domain.Tools.EmailTools;

public class EmailToolsTest {
    
    @Test
    public void testValidateMailAddressRequiresNoAtEnsuresFalse(){
        //arrange
        String email = "email.com";
        
        //act
        boolean result = EmailTools.isValidEmail(email);

        //assert
        assertEquals(false, result);
    }

    @Test
    public void testValidateMailAddressRequiresLengthBeforeAtSmallerThanOneEnsuresFalse(){
        //arrange
        String email = "@gmail.com";
        
        //act
        boolean result = EmailTools.isValidEmail(email);

        //assert
        assertEquals(false, result);
    }

    @Test
    public void testValidateMailAddressRequiresMoreThanOneDotAfterAtEnsuresFalse(){
        //arrange
        String email = "hans@gmail.com.co";
        
        //act
        boolean result = EmailTools.isValidEmail(email);

        //assert
        assertEquals(false, result);
    }

    @Test
    public void testValidateMailAddressRequiresNoCharactersBeforeDotEnsuresFalse(){
        //arrange
        String email = "hans@.com";
        
        //act
        boolean result = EmailTools.isValidEmail(email);

        //assert
        assertEquals(false, result);
    }

    @Test
    public void testValidateMailAddressRequiresNoCharactersAfterDotEnsuresFalse(){
        //arrange
        String email = "helloWorld@.com";
        
        //act
        boolean result = EmailTools.isValidEmail(email);

        //assert
        assertEquals(false, result);
    }

    @Test
    public void testValidateMailAddressRequiresNoneOfTheAboveEnsuresTrue(){
        //arrange
        String email = "helloWorld@gmail.com";
        
        //act
        boolean result = EmailTools.isValidEmail(email);

        //assert
        assertEquals(true, result);
    }

    
}
