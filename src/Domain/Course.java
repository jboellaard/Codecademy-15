package Domain;

import java.util.ArrayList;

public class Course {
    private String courseName;
    private String subject;
    private String introductionText;
    private LevelIndication levelIndication;
    private ArrayList<Module> modules;
    private ArrayList<Course> recommendedCourses;

    public Course(String courseName, String subject, String introductionText, LevelIndication level){
        this.courseName = courseName;
        this.subject = subject;
        this.introductionText = introductionText;
        this.levelIndication = level;
    }

    public String getCourseName(){
        return this.courseName;
    }

    public String getSubject(){
        return this.subject;
    }

    public String getIntroductionText(){
        return this.introductionText;
    }

    public LevelIndication getLevelIndication(){
        return this.levelIndication;
    }

    public void addModule(Module module){
        this.modules.add(module);
    }

    public void addRecommendedCourse(Course course){
        this.recommendedCourses.add(course);
    }
    
}
