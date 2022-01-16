package Domain;

public class Enrollment {
    private int enrollmentID;
    private Student student;
    private Course course;
    private String signUpDate;
    private Certificate certificate;

    public Enrollment(int ID, Student student, Course course, String signUpDate){
        this.enrollmentID = ID;
        this.student = student;
        this.course = course;
        this.signUpDate = signUpDate;
    }
    
    public void addCertificate(Certificate certificate){
        this.certificate = certificate;
    }

    public int getEnrollmentID(){
        return this.enrollmentID;
    }

    public Student getStudent(){
        return this.student;
    }

    public String getStudentName(){
        return this.student.getName();
    }

    public String getStudentEmailAddress(){
        return this.student.getEmailAddress();
    }

    public Course getCourse(){
        return this.course;
    }

    public String getCourseName(){
        return this.course.getCourseName();
    }

    public String getSignUpDate(){
        return this.signUpDate;
    }
    
}
