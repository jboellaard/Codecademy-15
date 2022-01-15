package GUI.StudentScenes;

import DB.EnrollmentRepo;
// import DB.*;
import Domain.*;
// import Domain.Tools.*;
import GUI.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.util.*;

public class EnrollmentOverviewStudentScene {

    public Scene getScene(Student student){
        Label studentName = new Label(student.getName()+" enrollments");

        TableView<Enrollment> table = new TableView<>();
        // StudentRepo repo = new StudentRepo();
        ObservableList<Enrollment> allEnrollmentsStudent = EnrollmentRepo.getEnrollmentsFromDB(student);
        table.setItems(allEnrollmentsStudent);
 
        TableColumn<Enrollment,String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        TableColumn<Enrollment,String> emailCol = new TableColumn<>("Email Address");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("StudentEmailAddress"));
        TableColumn<Enrollment,String> courseCol = new TableColumn<>("Course name");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        TableColumn<Enrollment,String> dateCol = new TableColumn<>("Sign up date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("SignUpDate"));

        table.getColumns().add(nameCol);
        table.getColumns().add(emailCol);
        table.getColumns().add(courseCol);
        table.getColumns().add(dateCol);
        table.sort();
        
        VBox vBox = new VBox();
        vBox.getChildren().add(studentName);
        vBox.getChildren().add(table);

        Button createEnrollment = new Button("Enroll in new course");
        createEnrollment.setOnAction((event -> {
            NewEnrollmentStudentScene newEnrollment = new NewEnrollmentStudentScene();
            GUI.getStage().setScene(newEnrollment.getScene(student));
        }));
        vBox.getChildren().add(createEnrollment);

        Button back = new Button("Go back");
        back.setOnAction((event -> {
            StudentOverview overview = new StudentOverview();
            GUI.GUIStage.setScene(overview.getScene());
        }));
        vBox.getChildren().add(back);
        

        Label noEnrollmentSelected = new Label("Please select an enrollment");
        //if all modules of a course are at 100%, add a button to add a certificate for the enrollment

        Button addCertificate = new Button("Add certificate");
        addCertificate.setOnAction((event -> {
            Enrollment selectedEnrollment = table.getSelectionModel().getSelectedItem();
            if (selectedEnrollment!=null){
                // NewStudentScene changeInfoStudent = new NewStudentScene();
                // GUI.getStage().setScene(changeInfoStudent.getUpdateScene(selectedStudent));
            } else {
                //message no student selected
                vBox.getChildren().add(noEnrollmentSelected);
            }
            
        }));
        vBox.getChildren().add(updateStudent);
        Button courses = new Button("Show enrollments");
        courses.setOnAction((event) -> {
            Enrollment selectedStudent = table.getSelectionModel().getSelectedItem();
            if (selectedStudent!=null){
                // EnrollmentOverviewStudent showEnrollments = new EnrollmentOverviewStudent();
                // GUI.getStage().setScene(showEnrollments.getScene(selectedStudent));
            } else {
                //message no student selected
                vBox.getChildren().add(noStudentSelected);
            }
            //new scene tableview with courses selected student
            //in that scene a button to enroll to a new course
        });

        Button certificates = new Button("Show certificates for this student");
        certificates.setOnAction((event) -> {
            //new scene tableview with certificates selected student
        });

        //delete button

        Scene enrollments = new Scene(vBox, 600, 400);
        return enrollments;
    }
    
}
