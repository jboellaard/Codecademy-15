package Domain;

public class Date {

    private String day;
    private String month;
    private String year;

    public Date(int day, int month, int year){
        if (day<10){
            this.day = "0" + day;
        }
        if (month<10){
            this.month = "0" + month;
        }
        this.year = "" + year;
    }

    public static int[] stringToIntList(String stringDate){
        if (stringDate!=null){
            String[] split = new String[3];
            if (stringDate.contains("-")){
                if (stringDate.split("-").length==3){
                    split = stringDate.split("-");
                } else {
                    throw new IllegalArgumentException("Please enter three values");
                }
            } else if (stringDate.contains("/")){
                if (stringDate.split("/").length==3){
                    split = stringDate.split("/");
                } else {
                    throw new IllegalArgumentException("Please enter three values");
                }
            } else {
                throw new IllegalArgumentException("This is not a valid format for the date");
            }
            return new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])};
        } else {
            throw new NullPointerException("The date cannot be null");
        }
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

    @Override
    public String toString(){
        return this.day+"-"+this.month+"-"+this.year;
    }
    
}
