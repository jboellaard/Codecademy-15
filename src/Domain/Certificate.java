package Domain;

public class Certificate {
    private int certificateID;
    private int enrollmentID;
    private double grade;
    private String nameOfStaffMember;
    private String courseName;

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

    public String getNameOfStaffCodecademy(){
        return this.nameOfStaffMember;
    }

    public void addCourseName(String name){
        this.courseName = name;
    }

    public String getCourseName(){
        return this.courseName;
    }
}
