package Domain;

public class Enrollment {
    private Student student;
    private Course course;
    private String signUpDate;
    private Certificate certificate;

    public Enrollment(Student student, Course course, String signUpDate){
        this.student = student;
        this.course = course;
        this.signUpDate = signUpDate;
        //add enrollment to db
    }
    
    public void addCertificate(Certificate certificate){
        this.certificate = certificate;
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
