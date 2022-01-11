package Domain;

public class DateTools {

    // private String day;
    // private String month;
    // private String year;

    public DateTools(int day, int month, int year){
        
    }

    public static String formatDate(int day, int month, int year){
        String date = "";
        if (day<10) date += "0";
        date += day + "-";
        if (month<10) date += "0";
        date += month + "-" + year;
        return date;
    }

    public static boolean isValidDate(int day, int month, int year){
        if ( (month == 1 || month == 3 || month == 5 || month == 7 || 
            month == 8 || month == 10 || month == 12) && 1 <= day && day <= 31){
            return true;
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && 1 <= day && day <= 30) {
            return true;
        } else if (month == 2 && 1 <= day && day <= 29 &&
                    (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ) {
            return true;
        } else if (month == 2 && 1 <= day && day <= 28 &&
                (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0))){
            return true;
        }
        return false;
    }
    
}
