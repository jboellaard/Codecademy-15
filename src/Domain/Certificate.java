package Domain;

public class Certificate {
    // private int certificateID;
    private double grade;
    private String nameOfStaffMember;

    public Certificate(double grade, String nameOfStaffMember){
        this.grade = grade;
        this.nameOfStaffMember = nameOfStaffMember;
        //create certificate in db
    }
    
}
