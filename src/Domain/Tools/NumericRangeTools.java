package Domain.Tools;

public class NumericRangeTools {
/*Checks if percentage is valid, meaning it has to be between 0 and 100 */
    public static boolean isValidPercentage(int percentage) {
        return 0 <= percentage && percentage <= 100;
    }
    
}
