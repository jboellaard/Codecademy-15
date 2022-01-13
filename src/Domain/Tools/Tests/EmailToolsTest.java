package Domain.Tools.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import Domain.Tools.EmailTools;

public class EmailToolsTest {
    
    @Test
    public void testValidateMailAddressRequiresNoAtEnsuresFalse(){
        String email = "email.com";
        
        boolean result = EmailTools.isValidEmail(email);
        assertEquals(false, result);
    }

    @Test
    public void testValidateMailAddressRequiresLengthBeforeAtSmallerThanOneEnsuresFalse(){
        String email = "@gmail.com";
        
        boolean result = EmailTools.isValidEmail(email);
        assertEquals(false, result);
    }

    @Test
    public void testValidateMailAddressRequiresMoreThanOneDotAfterAtEnsuresFalse(){
        String email = "hans@gmail.com.co";
        
        boolean result = EmailTools.isValidEmail(email);
        assertEquals(false, result);
    }

    @Test
    public void testValidateMailAddressRequiresNoCharactersBeforeDotEnsuresFalse(){
        String email = "hans@.com";
        
        boolean result = EmailTools.isValidEmail(email);
        assertEquals(false, result);
    }

    @Test
    public void testValidateMailAddressRequiresNoCharactersAfterDotEnsuresFalse(){
        String email = "helloWorld@.com";
        
        boolean result = EmailTools.isValidEmail(email);
        assertEquals(false, result);
    }

    @Test
    public void testValidateMailAddressRequiresNoneOfTheAboveEnsuresTrue(){
        String email = "helloWorld@gmail.com";
        
        boolean result = EmailTools.isValidEmail(email);
        assertEquals(true, result);
    }

    
}
