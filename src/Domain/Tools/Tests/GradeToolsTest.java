package Domain.Tools.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import Domain.Tools.GradeTools;

public class GradeToolsTest {

    @Test
    public void testIsValidGradeRequiresZeroDotNinetyEnsuresFalse() {
        // arrange
        double grade = 0.90;

        // act
        boolean result = GradeTools.isValidGrade(grade);

        // assert
        assertEquals(false, result);
    }

    @Test
    public void testIsValidGradeRequiresOneEnsuresTrue() {
        // arrange
        double grade = 1.00;

        // act
        boolean result = GradeTools.isValidGrade(grade);

        // assert
        assertEquals(true, result);
    }

    @Test
    public void testIsValidGradeRequiresTenDotTenEnsuresFalse() {
        // arrange
        double grade = 10.10;

        // act
        boolean result = GradeTools.isValidGrade(grade);

        // assert
        assertEquals(false, result);
    }

    @Test
    public void testIsValidGradeRequiresTenEnsuresTrue() {
        // arrange
        double grade = 10.00;

        // act
        boolean result = GradeTools.isValidGrade(grade);

        // assert
        assertEquals(true, result);
    }

    @Test
    public void testIsValidGradeRequiresNumberInRangeEnsuresTrue() {
        // arrange
        double grade = 5.50;

        // act
        boolean result = GradeTools.isValidGrade(grade);

        // assert
        assertEquals(true, result);
    }

}
