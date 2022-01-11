package Domain;

public class Student {
    private String emailAddress;
    private String name;
    private String dateOfBirth;
    private Address address;
    private Gender gender;

    public Student(String name, String emailAddress, String dateOfBirth, Gender gender, Address address){
        this.name = name;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
    }

    public String getEmail(){
        return this.emailAddress;
    }

    public String getName(){
        return this.name;
    }

    public String getDOB(){
        return this.dateOfBirth;
    }

    public int getAddressID(){
        return this.address.getAddressID();
    }

    public Gender getGender(){
        return this.gender;
    }

    @Override 
    public String toString(){
        return this.name+ ": " + this.emailAddress;
    }
    
}
