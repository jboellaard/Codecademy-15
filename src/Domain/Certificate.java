package Domain;

public class Certificate {
    private int certificateID;
    private double grade;
    private nameOfStaffMember nameOfStaffMember;


    public Certificate(int certificateID, double grade, nameOfStaffMember nameOfStaffMember){
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

    public nameOfStaffMember getNameOfStaffMember(){
        return this.nameOfStaffMember;
    }
}
