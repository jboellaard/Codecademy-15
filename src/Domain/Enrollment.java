package Domain;
/*Ads data to enrollment class */ 
public class Enrollment {
    private int enrollmentID;
    private Student student;
    private Course course;
    private String signUpDate;
    private int finishedCourse;
    private Certificate certificate;

    public Enrollment(int ID, Student student, Course course, String signUpDate, int finishedCourse){
        this.finishedCourse = finishedCourse;
        this.enrollmentID = ID;
        this.student = student;
        this.course = course;
        this.signUpDate = signUpDate;
    }
    /*Returns requested data*/ 
    public void addCertificate(Certificate certificate){
        this.certificate = certificate;
    }

    public String getCompleted(){
        if (finishedCourse==1) return "Certificate received";
        return "No certificate";
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
