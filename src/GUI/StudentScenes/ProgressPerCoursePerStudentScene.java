package GUI.StudentScenes;

import DB.DBConnection;
import Domain.*;
import GUI.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

        //add button getcontactperson for module!

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(15,15,5,15));
        buttons.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttons);
        

        Button addCertificate = new Button("Add certificate for this course");
        addCertificate.setOnAction((event -> {
            //creates field with grade and name staff member and submit button
            
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
