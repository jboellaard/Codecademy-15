package Domain.Tools;

public class EmailTools {

    public EmailTools(){

    }

    public static boolean isValidEmail(String mailAddress){
        mailAddress = mailAddress.trim();
        if (!mailAddress.contains("@")) {
            return false; 
        } else if (mailAddress.split("@")[0].length() < 1){
            return false;
        } else if (mailAddress.split("@")[1].split("\\.").length > 2) {
            return false;
        } else if (mailAddress.split("@")[1].split("\\.")[0].length() < 1) {
            return false; 
        } else if (mailAddress.split("@")[1].split("\\.")[1].length() < 1) {
            return false; 
        }
        return true;
    }
    
}
