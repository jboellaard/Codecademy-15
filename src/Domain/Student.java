package Domain;

public class Student {
    private Email emailAddress;
    private String name;
    private Date dateOfBirth;
    private Address address;
    private Gender gender;

    public Student(String name, Email emailAddress, Date dateOfBirth, Gender gender, Address address){
        this.name = name;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
    }

    public Email getEmail(){
        return this.emailAddress;
    }

    public String getName(){
        return this.name;
    }

    public Date getDOB(){
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
