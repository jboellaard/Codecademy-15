package Domain;

public class Student {
    private String emailAddress;
    private String name;
    private String dateOfBirth;
    private Gender gender;
    private Address address;

    public Student(String name, String emailAddress, String dateOfBirth, Gender gender, Address address){
        this.name = name;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
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
        return this.address.getAddressID();
    }

    public Address getAddress(){
        return this.address;
    }

    @Override 
    public String toString(){
        return this.name+ ": " + this.emailAddress;
    }
    
}
