package Domain.Tools.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import Domain.Tools.ZipCodeTools;

public class ZipCodeToolsTest {


    @Test(expected = NullPointerException.class)
    public void testFormatPostalCodeRequiresNullSignalsNullPointerException(){
        //arrange
        String postalCode = null;

        //act
        ZipCodeTools.formatZipCode(postalCode);

        //assert (does not apply)
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequiresNumberLargerThan9999SignalsIllegalArgumentException(){
        //arrange
        String postalCode = "10000AA";

        //act
        ZipCodeTools.formatZipCode(postalCode);

        //assert (does not apply)
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequiresNumberSmallerThan1000SignalsIllegalArgumentException(){
        //arrange
        String postalCode = "999AA";

        //act
        ZipCodeTools.formatZipCode(postalCode);

        //assert (does not apply)
    }

    @Test()
    public void testFormatPostalCodeRequires1000AAEnsuresFormattedPostalCode(){
        //arrange
        String postalCode = "1000AA";

        //act
        String result = ZipCodeTools.formatZipCode(postalCode);

        //assert 
        assertEquals("1000 AA", result);
    }

    @Test()
    public void testFormatPostalCodeRequires1000ZZEnsuresFormattedPostalCode(){
        //arrange
        String postalCode = "1000ZZ";

        //act
        String result = ZipCodeTools.formatZipCode(postalCode);

        //assert
        assertEquals("1000 ZZ", result);
    }

    @Test()
    public void testFormatPostalCodeRequires1000AZEnsuresFormattedPostalCode(){
        //arrange
        String postalCode = "1000AZ";

        //act
        String result = ZipCodeTools.formatZipCode(postalCode);

        //assert
        assertEquals("1000 AZ", result);
    }

    @Test()
    public void testFormatPostalCodeRequires9999AAEnsuresFormattedPostalCode(){
        //arrange
        String postalCode = "9999AA";

        //act
        String result = ZipCodeTools.formatZipCode(postalCode);

        //assert
        assertEquals("9999 AA", result);
    }

    @Test()
    public void testFormatPostalCodeRequiresPostalCodeWithExtraSpacesEnsuresFormattedPostalCode(){
        //arrange
        String postalCode = " 1000   AA   ";

        //act
        String result = ZipCodeTools.formatZipCode(postalCode);

        //assert
        assertEquals("1000 AA", result);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequiresOneLetterSignalsIllegalArgumentException(){
        //arrange
        String postalCode = "1000A";

        //act
        ZipCodeTools.formatZipCode(postalCode);

        //assert (does not apply)
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequiresMoreThanTwoLettersSignalsIllegalArgumentException(){
        //arrange
        String postalCode = "1000AAA";

        //act
        ZipCodeTools.formatZipCode(postalCode);

        //assert (does not apply)
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequiresOtherSymbolThanLetterSignalsIllegalArgumentException(){
        //arrange
        String postalCode = "1000@Z";

        //act
        ZipCodeTools.formatZipCode(postalCode);

        //assert (does not apply)
    }
    
}
