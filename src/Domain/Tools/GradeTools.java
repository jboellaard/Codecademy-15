package Domain.Tools;

public class GradeTools {
    /*Checks if grade is valid, meaning it has to be between 1 and 10 */
    public static boolean isValidGrade(double grade) {
        return 1.0 <= grade && grade <= 10.0;
    }

}
