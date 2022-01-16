package Domain;

import DB.DBConnection;

public class Certificate {
    private int certificateID;
    private int enrollmentID;
    private double grade;
    private String nameOfStaffMember;

    public Certificate(int enrollmentID, double grade, String nameOfStaffMember){
        this.enrollmentID = enrollmentID;
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

    public String getNameStaffCodecademy(){
        return this.nameOfStaffMember;
    }

    public String getCourseName(){
        return "";
    }
}
