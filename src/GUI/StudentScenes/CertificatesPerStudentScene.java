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

public class CertificatesPerStudentScene {

    public Scene getScene(Student student){
        Label studentName = new Label(student.getName()+" certificates");

        TableView<Certificate> table = new TableView<>();
        ObservableList<Certificate> allCertificatesStudent = EnrollmentRepo.getAllCertificatesStudent(student);

        table.setItems(allCertificatesStudent);
 
        // TableColumn<Certificate,String> courseCol = new TableColumn<>("Course name");
        // courseCol.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        TableColumn<Certificate,Double> gradeCol = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("Grade"));
        TableColumn<Certificate,String> staffCol = new TableColumn<>("Name staff");
        staffCol.setCellValueFactory(new PropertyValueFactory<>("NameOfStaffCodecademy"));
        

        // table.getColumns().add(courseCol);
        table.getColumns().add(gradeCol);
        table.getColumns().add(staffCol);
        table.sort();
        
        VBox vBox = new VBox();
        vBox.getChildren().add(studentName);
        vBox.getChildren().add(table);

        Label noEnrollmentSelected = new Label("");

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(15,15,5,15));
        buttons.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttons);

        Button back = new Button("Go back");
        back.setOnAction((event -> {
            StudentOverview overview = new StudentOverview();
            GUI.GUIStage.setScene(overview.getScene());
        }));
        buttons.getChildren().add(back);
        

        Scene enrollments = new Scene(vBox, 700, 400);
        return enrollments;
    }
    
}
