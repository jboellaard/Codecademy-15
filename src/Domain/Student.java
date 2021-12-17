package Domain;

public class Student {
    // private Email emailAddress;
    private String name;
    // private Date dateOfBirth;
    // private Address address;
    // private Gender gender;
    private String email;
    private String dateOfBirth;
    private String address;
    private String gender;

    // public Student(String name, Email emailAddress, Date dateOfBirth, Gender gender, Address address){
    //     this.name = name;
    //     this.emailAddress = emailAddress;
    //     this.dateOfBirth = dateOfBirth;
    //     this.gender = gender;
    //     this.address = address;
    // }

    public Student(String name, String email, String dateOfBirth, String gender, String address){
        this.name = name;
        this.email=email;
        this.dateOfBirth=dateOfBirth;
        this.gender=gender;
        this.address=address;
    }

    @Override 
    public String toString(){
        System.out.println("name");
        return this.name;
    }
    
}
