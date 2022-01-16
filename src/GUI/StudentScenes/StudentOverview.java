package GUI.StudentScenes;

import DB.DBConnection;
// import DB.StudentRepo;
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

public class StudentOverview {

    public Scene getScene(){
        TableView<Student> table = new TableView<>();
        ObservableList<Student> allStudents = DBConnection.studentRepo.getAllStudents();
        table.setItems(allStudents);
 
        TableColumn<Student,String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Student,String> emailCol = new TableColumn<>("Email Address");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("EmailAddress"));
        TableColumn<Student,String> dobCol = new TableColumn<>("Date of birth");
        dobCol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        TableColumn<Student,Gender> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        TableColumn<Student,Integer> addressIDCol = new TableColumn<>("Address ID");
        addressIDCol.setCellValueFactory(new PropertyValueFactory<>("addressID"));

        table.getColumns().add(nameCol);
        table.getColumns().add(emailCol);
        table.getColumns().add(dobCol);
        table.getColumns().add(genderCol);
        table.getColumns().add(addressIDCol);
        table.sort();
        
        VBox vBox = new VBox(10);
        vBox.getChildren().add(table);

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(15,15,5,15));
        buttons.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttons);
        Button createStudent = new Button("Add new student");
        createStudent.setOnAction((event -> {
            NewStudentScene newStudent = new NewStudentScene();
            GUI.getStage().setScene(newStudent.getCreateScene());
        }));
        buttons.getChildren().add(createStudent);

        Label noStudentSelected = new Label("");

        Button updateStudent = new Button("Update information");
        updateStudent.setOnAction((event -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();
            if (selectedStudent!=null){
                NewStudentScene changeInfoStudent = new NewStudentScene();
                GUI.getStage().setScene(changeInfoStudent.getUpdateScene(selectedStudent));
            } else {
                noStudentSelected.setText("Please select a student first");
            }
        }));
        buttons.getChildren().add(updateStudent);

        Button delete = new Button("Delete student");
        delete.setOnAction((event -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();
            if (selectedStudent!=null){
                if (DBConnection.studentRepo.delete(selectedStudent)){
                    noStudentSelected.setText("Student succesfully deleted");
                } else {
                    noStudentSelected.setText("Unfortunately the student has not been deleted");
                }
            } else {
                noStudentSelected.setText("Please select a student first");
            }
        }));
        buttons.getChildren().add(delete);

        Button back = new Button("Go back");
        back.setOnAction((event -> {
            GUI.GUIStage.setScene(GUI.getHomeScene());
        }));
        buttons.getChildren().add(back);

        HBox buttonsSecondRow = new HBox(15);
        buttonsSecondRow.setPadding(new Insets(5,15,5,15));
        buttonsSecondRow.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttonsSecondRow);

        Button enrollments = new Button("Show enrollments");
        enrollments.setOnAction((event) -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();
            if (selectedStudent!=null){
                EnrollmentOverviewStudentScene showEnrollments = new EnrollmentOverviewStudentScene();
                GUI.getStage().setScene(showEnrollments.getScene(selectedStudent));
            } else {
                noStudentSelected.setText("Please select a student first");
            }
        });
        buttonsSecondRow.getChildren().add(enrollments);

        Button certificates = new Button("Show certificates");
        certificates.setOnAction((event) -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();
            if (selectedStudent!=null){
                CertificatesPerStudentScene showCertificates = new CertificatesPerStudentScene();
                GUI.getStage().setScene(showCertificates.getScene(selectedStudent));
            } else {
                noStudentSelected.setText("Please select a student first");
            }
        });
        buttonsSecondRow.getChildren().add(certificates);

        Button progressWebcasts = new Button("Show progress webcasts");
        progressWebcasts.setOnAction((event) -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();
            if (selectedStudent!=null){
                WebcastsViewedOverview showWebcasts = new WebcastsViewedOverview();
                GUI.getStage().setScene(showWebcasts.getScene(selectedStudent));
            } else {
                noStudentSelected.setText("Please select a student first");
            }
        });
        buttonsSecondRow.getChildren().add(progressWebcasts);

        HBox error = new HBox(15);
        error.setPadding(new Insets(5,25,15,25));
        error.setAlignment(Pos.CENTER);
        error.getChildren().add(noStudentSelected);
        vBox.getChildren().add(error);

        Scene scene = new Scene(vBox, 700, 400);
        return scene;
    }
    
}
