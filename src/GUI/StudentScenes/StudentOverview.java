package GUI.StudentScenes;

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
        // StudentRepo repo = new StudentRepo();
        ObservableList<Student> allStudents = GUI.studentRepo.getAllStudents();
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

        // nameCol.setSortType(TableColumn.SortType.DESCENDING);
        // table.getColumns().setAll(nameCol,emailCol,dobCol,genderCol,addressIDCol);
        table.getColumns().add(nameCol);
        table.getColumns().add(emailCol);
        table.getColumns().add(dobCol);
        table.getColumns().add(genderCol);
        table.getColumns().add(addressIDCol);
        table.sort();
        
        VBox vBox = new VBox(10);
        vBox.getChildren().add(table);

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(40,40,40,40));
        buttons.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttons);
        Button createStudent = new Button("Add new student");
        createStudent.setOnAction((event -> {
            NewStudentScene newStudent = new NewStudentScene();
            GUI.getStage().setScene(newStudent.getCreateScene());
            GUI.getStage().setTitle("Add student");
        }));
        Button back = new Button("Go back");
        back.setOnAction((event -> {
            // HomeScene home = new HomeScene();
            GUI.GUIStage.setScene(GUI.getHomeScene());
            GUI.GUIStage.setTitle("Home");
        }));
        buttons.getChildren().add(back);
        buttons.getChildren().add(createStudent);

        Label noStudentSelected = new Label("");
        vBox.getChildren().add(noStudentSelected);

        Button updateStudent = new Button("Change info student");
        updateStudent.setOnAction((event -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();
            if (selectedStudent!=null){
                NewStudentScene changeInfoStudent = new NewStudentScene();
                GUI.getStage().setScene(changeInfoStudent.getUpdateScene(selectedStudent));
                GUI.getStage().setTitle("Add student");
            } else {
                noStudentSelected.setText("Please select a student");
            }
            
        }));
        buttons.getChildren().add(updateStudent);

        Button enrollments = new Button("Show enrollments");
        enrollments.setOnAction((event) -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();
            if (selectedStudent!=null){
                EnrollmentOverviewStudentScene showEnrollments = new EnrollmentOverviewStudentScene();
                GUI.getStage().setScene(showEnrollments.getScene(selectedStudent));
            } else {
                noStudentSelected.setText("Please select a student");
            }
            //new scene tableview with courses selected student
            //in that scene a button to enroll to a new course
        });
        buttons.getChildren().add(enrollments);

        Button certificates = new Button("Show certificates for this student");
        certificates.setOnAction((event) -> {
            //new scene tableview with certificates selected student
        });

        //delete button


        Scene scene = new Scene(vBox, 600, 400);
        return scene;
    }
    
}
