package Domain;

public class Certificate {
    private int certificateID;
    private double grade;
    private String nameOfStaffMember;

    public Certificate( double grade, String nameOfStaffMember){
        this.grade = grade;
        this.nameOfStaffMember = nameOfStaffMember;
    }

    public void addCertificateID(int certificateID){
        this.certificateID=certificateID;
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
