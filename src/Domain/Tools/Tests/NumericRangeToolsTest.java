package Domain.Tools.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import Domain.Tools.NumericRangeTools;

public class NumericRangeToolsTest {

    @Test
    public void testIsValidPercentageRequiresZeroEnsuresTrue(){
        int percentage = 0;

        boolean result = NumericRangeTools.isValidPercentage(percentage);
        assertEquals(true, result);
    }

    @Test
    public void testIsValidPercentageRequiresNumberSmallerThanZeroEnsuresFalse(){
        int percentage = -1; 

        boolean result = NumericRangeTools.isValidPercentage(percentage);
        assertEquals(false, result);
    }

    @Test
    public void testIsValidPercentageRequire100EnsuresTrue(){
        int percentage = 100; 

        boolean result = NumericRangeTools.isValidPercentage(percentage);
        assertEquals(true, result);
    }

    @Test
    public void testIsValidPercentageRequiresNumberLargerThan100EnsuresFalse(){
        int percentage = 101; 

        boolean result = NumericRangeTools.isValidPercentage(percentage);
        assertEquals(false, result);
    }

}