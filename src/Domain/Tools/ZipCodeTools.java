package Domain.Tools;

public class ZipCodeTools {

    public static String formatZipCode(String zipCode){
        if (zipCode == null){
            throw new NullPointerException("The input cannot be null");
        } else if (zipCode.length()<6) {
            throw new IllegalArgumentException("The input is too short");
        } else {
            if (zipCode.trim().matches("^[1-9][0-9]{3} *(?!sa|sd|ss|SA|SD|SS)[A-Za-z]{2}$")){
                return zipCode.trim().substring(0, 4) + " " + zipCode.trim().substring(4).trim().toUpperCase();
            } else {
                throw new IllegalArgumentException("The input is not a valid postalCode");
            }
        }
    }
    
}
