package GUI.StudentScenes;

import DB.EnrollmentRepo;
import Domain.*;
import GUI.*;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class NewEnrollmentStudentScene {

    public Scene getScene(Student student){
        Label studentName = new Label(student.getName()+" courses left to enroll");

        TableView<Course> table = new TableView<>();
        // StudentRepo repo = new StudentRepo();
        ObservableList<Course> allCourses = GUI.courseRepo.getAllCourses();
        table.setItems(allCourses);
 
        TableColumn<Course,String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        TableColumn<Course,String> subjectCol = new TableColumn<>("Subject");
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        TableColumn<Course,String> introCol = new TableColumn<>("Introduction");
        introCol.setCellValueFactory(new PropertyValueFactory<>("IntroductionText"));
        TableColumn<Course,LevelIndication> levelCol = new TableColumn<>("Level");
        levelCol.setCellValueFactory(new PropertyValueFactory<>("LevelIndication"));

        table.getColumns().add(nameCol);
        table.getColumns().add(subjectCol);
        table.getColumns().add(introCol);
        table.getColumns().add(levelCol);
        table.sort();
    
        
        VBox vBox = new VBox();
        vBox.getChildren().add(studentName);
        vBox.getChildren().add(table);

        //java.time.LocalDate.now()

        Scene newEnrollment = new Scene(vBox, 600, 400);
        return newEnrollment;
    }
    
}