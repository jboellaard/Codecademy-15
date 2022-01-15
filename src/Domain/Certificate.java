package Domain;

public class Certificate {
    private int certificateID;
    private double grade;
    private String nameOfStaffMember;


    public Certificate(int certificateID, double grade, String nameOfStaffMember){
        this.certificateID = certificateID;
        this.grade = grade;
        this.nameOfStaffMember = nameOfStaffMember;
    }

    public int certificateID(){
        return this.certificateID;
    }

    public double getGrade(){
        return this.grade;
    }

    public String getNameOfStaffMember(){
        return this.nameOfStaffMember;
    }
}
