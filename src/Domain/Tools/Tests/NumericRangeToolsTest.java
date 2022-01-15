package Domain.Tools.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import Domain.Tools.NumericRangeTools;

public class NumericRangeToolsTest {

    @Test
    public void testIsValidPercentageRequiresZeroEnsuresTrue(){
        //arrange
        int percentage = 0;

        //act
        boolean result = NumericRangeTools.isValidPercentage(percentage);

        //assert
        assertEquals(true, result);
    }

    @Test
    public void testIsValidPercentageRequiresNumberSmallerThanZeroEnsuresFalse(){
        //arrange
        int percentage = -1; 

        //act
        boolean result = NumericRangeTools.isValidPercentage(percentage);

        //assert
        assertEquals(false, result);
    }

    @Test
    public void testIsValidPercentageRequire100EnsuresTrue(){
        //arrange
        int percentage = 100; 

        //act
        boolean result = NumericRangeTools.isValidPercentage(percentage);

        //assert
        assertEquals(true, result);
    }

    @Test
    public void testIsValidPercentageRequiresNumberLargerThan100EnsuresFalse(){
        //arrange
        int percentage = 101; 

        //act
        boolean result = NumericRangeTools.isValidPercentage(percentage);

        //assert
        assertEquals(false, result);
    }

    @Test
    public void testIsValidPercentageRequiresNumberInRangeEnsuresTrue(){
        //arrange
        int percentage = 50; 

        //act
        boolean result = NumericRangeTools.isValidPercentage(percentage);

        //assert
        assertEquals(true, result);
    }

}