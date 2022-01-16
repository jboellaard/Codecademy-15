package GUI.StudentScenes;

import DB.DBConnection;
import DB.EnrollmentRepo;
import Domain.*;
import Domain.Tools.GradeTools;
import GUI.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//This scene show the progress students have in specific courses

public class ProgressPerCoursePerStudentScene {

    public Scene getScene(Student student, Enrollment enrollment){
        Label studentName = new Label("Progress of modules " + enrollment.getCourse().getCourseName() +"; " + student.getName());

        TableView<ProgressModule> table = new TableView<>();
        table.setItems(DBConnection.courseModuleRepo.getModulesAndProgressFromDB(enrollment.getCourse()));
 
        TableColumn<ProgressModule,String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        TableColumn<ProgressModule,String> versionCol = new TableColumn<>("Version");
        versionCol.setCellValueFactory(new PropertyValueFactory<>("Version"));
        TableColumn<ProgressModule,LevelIndication> folCol = new TableColumn<>("FollowNumber");
        folCol.setCellValueFactory(new PropertyValueFactory<>("FollowNumber"));
        TableColumn<ProgressModule,Integer> progCol = new TableColumn<>("Progress");
        progCol.setCellValueFactory(new PropertyValueFactory<>("Progress"));

        table.getColumns().add(titleCol);
        table.getColumns().add(versionCol);
        table.getColumns().add(folCol);
        table.getColumns().add(progCol);
        table.sort();
    
        VBox vBox = new VBox();
        vBox.getChildren().add(studentName);
        vBox.getChildren().add(table);

        Label noCourseSelected = new Label("");

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(15,15,5,15));
        buttons.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttons);

        Label grade = new Label("Grade: ");
        TextField gradeInput = new TextField();

        Label nameStaff = new Label("Name staff: ");
        TextField nameStaffInput = new TextField();
        

        Button addCertificate = new Button("Add certificate for this course");

        Button submitCertificate = new Button("Submit certificate");
        submitCertificate.setOnAction((event -> {
            double givenGrade = Double.valueOf(gradeInput.getText());
            String staff = nameStaffInput.getText();
            if (GradeTools.isValidGrade(givenGrade)){
                Certificate cert = new Certificate(enrollment.getEnrollmentID(),givenGrade,staff);
                if (EnrollmentRepo.addCertificate(enrollment,cert)){
                    noCourseSelected.setText("Certificate added succesfully");
                } else {
                    noCourseSelected.setText("The certificate could not be added");
                }
            } else {
                noCourseSelected.setText("This is not a valid grade");
            }

        }));

        HBox certificate = new HBox(15);
        certificate.setPadding(new Insets(5,25,15,25));
        certificate.setAlignment(Pos.CENTER);
        vBox.getChildren().add(certificate);


        addCertificate.setOnAction((event -> {
            certificate.getChildren().add(grade);
            certificate.getChildren().add(gradeInput);
            certificate.getChildren().add(nameStaff);
            certificate.getChildren().add(nameStaffInput);
            certificate.getChildren().add(submitCertificate);
            
        }));
        buttons.getChildren().add(addCertificate);


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

        Scene newEnrollment = new Scene(vBox, 700, 400);
        return newEnrollment;
    }
    
}
