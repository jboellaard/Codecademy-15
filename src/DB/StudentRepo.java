package DB;

import java.util.*;
import Domain.Student;

public class StudentRepo {
    private List<Student> allStudents;

    public StudentRepo(){
        allStudents = new ArrayList<>();
    }

    public void create(Student student){
        // StudentRepo repo = new StudentRepo();
        // repo.create(new Student());
        System.out.println("create");
        allStudents.add(student);
    }

    public String getAllStudents(){
        String students = "";
        for (Student student: allStudents){
            students+=student.toString() + " ";
        }
        return students;
    }

    public void update(Student student){
        if (allStudents.contains(student)){
            //update
        }
    }
    
    public void delete(){

    }
}
