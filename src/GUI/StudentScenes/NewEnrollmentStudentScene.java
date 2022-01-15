package GUI.StudentScenes;

import DB.EnrollmentRepo;
import Domain.*;
import GUI.*;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NewEnrollmentStudentScene {

    public Scene getScene(Student student){
        Label studentName = new Label("What course would you like to enroll "+ student.getName()+ " in?");

        TableView<Course> table = new TableView<>();
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

        Label noCourseSelected = new Label("");

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(15,15,5,15));
        buttons.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttons);
        Button createEnrollment = new Button("Add new enrollment");
        createEnrollment.setOnAction((event -> {
            Course selectedCourse = table.getSelectionModel().getSelectedItem();
            if (selectedCourse!=null){
                if (EnrollmentRepo.create(student, selectedCourse, String.valueOf(java.time.LocalDate.now()))){
                    noCourseSelected.setText("Succesfully enrolled!");
                } else {
                    noCourseSelected.setText("Unfortunately the enrollment was not succesful"); 
                }
            } else {
                noCourseSelected.setText("Please select a student first");
            }
        }));
        buttons.getChildren().add(createEnrollment);

        Button certificates = new Button("Show progress");
        certificates.setOnAction((event) -> {
            //new scene tableview with certificates selected student
        });
        buttons.getChildren().add(certificates);


        Button back = new Button("Go back");
        back.setOnAction((event -> {
            EnrollmentOverviewStudentScene overview = new EnrollmentOverviewStudentScene();
            GUI.getStage().setScene(overview.getScene(student));
        }));
        buttons.getChildren().add(back);

        HBox error = new HBox(15);
        error.setPadding(new Insets(5,25,15,25));
        error.setAlignment(Pos.CENTER);
        error.getChildren().add(noCourseSelected);
        vBox.getChildren().add(error);

        //java.time.LocalDate.now()

        Scene newEnrollment = new Scene(vBox, 600, 400);
        return newEnrollment;
    }
    
}
