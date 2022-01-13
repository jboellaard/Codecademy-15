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
}
