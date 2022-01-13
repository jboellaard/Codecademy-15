package Domain.Tools.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import Domain.Tools.ZipCodeTools;

public class ZipCodeToolsTest {


    @Test(expected = NullPointerException.class)
    public void testFormatPostalCodeRequiresNullSignalsNullPointerException(){
        String postalCode = null;

        ZipCodeTools.formatZipCode(postalCode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequiresNumberLargerThan9999SignalsIllegalArgumentException(){
        String postalCode = "10000AA";

        ZipCodeTools.formatZipCode(postalCode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequiresNumberSmallerThan1000SignalsIllegalArgumentException(){
        String postalCode = "999AA";

        ZipCodeTools.formatZipCode(postalCode);
    }

    @Test()
    public void testFormatPostalCodeRequires1000AAEnsuresFormattedPostalCode(){
        String postalCode = "1000AA";

        String result = ZipCodeTools.formatZipCode(postalCode);
        assertEquals("1000 AA", result);
    }

    @Test()
    public void testFormatPostalCodeRequires1000ZZEnsuresFormattedPostalCode(){
        String postalCode = "1000ZZ";

        String result = ZipCodeTools.formatZipCode(postalCode);
        assertEquals("1000 ZZ", result);
    }

    @Test()
    public void testFormatPostalCodeRequires1000AZEnsuresFormattedPostalCode(){
        String postalCode = "1000AZ";

        String result = ZipCodeTools.formatZipCode(postalCode);
        assertEquals("1000 AZ", result);
    }

    @Test()
    public void testFormatPostalCodeRequires9999AAEnsuresFormattedPostalCode(){
        String postalCode = "9999AA";

        String result = ZipCodeTools.formatZipCode(postalCode);
        assertEquals("9999 AA", result);
    }

    @Test()
    public void testFormatPostalCodeRequiresPostalCodeWithExtraSpacesEnsuresFormattedPostalCode(){
        String postalCode = " 1000   AA   ";

        String result = ZipCodeTools.formatZipCode(postalCode);
        assertEquals("1000 AA", result);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequiresOneLetterSignalsIllegalArgumentException(){
        String postalCode = "1000A";

        ZipCodeTools.formatZipCode(postalCode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequiresMoreThanTwoLettersSignalsIllegalArgumentException(){
        String postalCode = "1000AAA";

        ZipCodeTools.formatZipCode(postalCode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequiresOtherSymbolThanLetterSignalsIllegalArgumentException(){
        String postalCode = "1000@Z";

        ZipCodeTools.formatZipCode(postalCode);
    }
    
}
