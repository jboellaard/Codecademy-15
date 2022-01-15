package GUI.CourseScenes;

import DB.DBConnection;
import Domain.*;
import GUI.*;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class CourseOverview {

    public Scene getScene(){
        TableView<Course> table = new TableView<>();
        // StudentRepo repo = new StudentRepo();
        ObservableList<Course> allCourses = DBConnection.courseRepo.getAllCourses();
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
        vBox.getChildren().add(table);
        Button createCourse = new Button("Add new course");
        createCourse.setOnAction((event -> {
            // NewCourseScene newStudent = new NewCourseScene();
            // GUI.getStage().setScene(newStudent.getCreateScene());
        }));
        Button back = new Button("Go back");
        back.setOnAction((event -> {
            // HomeScene home = new HomeScene();
            GUI.GUIStage.setScene(GUI.getHomeScene());
        }));
        vBox.getChildren().add(back);
        vBox.getChildren().add(createCourse);
        

        Button updateStudent = new Button("Change info student");
        updateStudent.setOnAction((event -> {
            Course selectedCourse = table.getSelectionModel().getSelectedItem();
            if (selectedCourse!=null){
                // NewCourseScene changeInfoCourse = new NewCourseScene();
                // GUI.getStage().setScene(changeInfoCourse.getUpdateScene(selectedCourse));
            } else {
                //message no student selected
            }
            
        }));
        vBox.getChildren().add(updateStudent);
        Button courses = new Button("Show enrollments");
        courses.setOnAction((event) -> {
            //new scene tableview with courses selected student
            //in that scene a button to enroll to a new course
        });
        Button certificates = new Button("Show certificates for this student");
        certificates.setOnAction((event) -> {
            //new scene tableview with certificates selected student
        });

        //delete button


        Scene scene = new Scene(vBox, 600, 400);
        return scene;
    }
    
}
