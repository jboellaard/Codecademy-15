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
        TableColumn<Enrollment,String> certCol = new TableColumn<>("Certificate");
        certCol.setCellValueFactory(new PropertyValueFactory<>("Completed"));

        table.getColumns().add(nameCol);
        table.getColumns().add(emailCol);
        table.getColumns().add(courseCol);
        table.getColumns().add(dateCol);
        table.getColumns().add(certCol);
        table.sort();
        
        VBox vBox = new VBox();
        vBox.getChildren().add(studentName);
        vBox.getChildren().add(table);

        Label noEnrollmentSelected = new Label("");

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(15,15,5,15));
        buttons.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttons);

        Button createEnrollment = new Button("Enroll in new course");
        createEnrollment.setOnAction((event -> {
            NewEnrollmentStudentScene newEnrollment = new NewEnrollmentStudentScene();
            GUI.getStage().setScene(newEnrollment.getScene(student));
        }));
        buttons.getChildren().add(createEnrollment);

        Button progress = new Button("Show progress");
        progress.setOnAction((event) -> {
            Enrollment selectedEnrollment = table.getSelectionModel().getSelectedItem();
            if (selectedEnrollment!=null){
                ProgressPerCoursePerStudentScene showProgress = new ProgressPerCoursePerStudentScene();
                GUI.getStage().setScene(showProgress.getScene(student, selectedEnrollment));
            } else {
                noEnrollmentSelected.setText("Please select an enrollment");
            }
        });
        buttons.getChildren().add(progress);

        Button back = new Button("Go back");
        back.setOnAction((event -> {
            StudentOverview overview = new StudentOverview();
            GUI.GUIStage.setScene(overview.getScene());
        }));
        buttons.getChildren().add(back);
        
        HBox error = new HBox(15);
        error.setPadding(new Insets(5,25,15,25));
        error.setAlignment(Pos.CENTER);
        error.getChildren().add(noEnrollmentSelected);
        vBox.getChildren().add(error);

        Scene enrollments = new Scene(vBox, 700, 400);
        return enrollments;
    }
    
}
