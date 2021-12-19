package Domain;

public class Email {

    private String emailAddress;

    public Email(String email){
        this.emailAddress=email;
    }

    public boolean isValidEmail(){
        return true;
    }

    @Override
    public String toString(){
        return this.emailAddress;
    }
    
}
