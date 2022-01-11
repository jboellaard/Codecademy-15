package Domain;

public class Student {
    private String emailAddress;
    private String name;
    private String dateOfBirth;
    private Gender gender;
    // private Address address;
    private int addressID;

    public Student(String name, String emailAddress, String dateOfBirth, Gender gender, int addressID){
        //do we save address or addressid, considering we need to get the info from the database
        this.name = name;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.addressID = addressID;
    }

    public String getEmailAddress(){
        return this.emailAddress;
    }

    public String getName(){
        return this.name;
    }

    public String getDateOfBirth(){
        return this.dateOfBirth;
    }

    public Gender getGender(){
        return this.gender;
    }

    public int getAddressID(){
        return this.addressID;
    }

    @Override 
    public String toString(){
        return this.name+ ": " + this.emailAddress;
    }
    
}
