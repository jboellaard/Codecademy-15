package GUI.StudentScenes;

import DB.StudentRepo;
import Domain.*;
import GUI.*;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
        table.getColumns().setAll(nameCol,emailCol,dobCol,genderCol,addressIDCol);
        table.sort();
        
        VBox vBox = new VBox();
        vBox.getChildren().add(table);
        Button createStudent = new Button("Add new student");
        createStudent.setOnAction((event -> {
            NewStudentScene newStudent = new NewStudentScene();
            GUI.getStage().setScene(newStudent.getScene());
            GUI.getStage().setTitle("Add student");
        }));
        Button back = new Button("Go back");
        back.setOnAction((event -> {
            // HomeScene home = new HomeScene();
            GUI.GUIStage.setScene(GUI.getHomeScene());
            GUI.GUIStage.setTitle("Home");
        }));
        vBox.getChildren().add(back);
        vBox.getChildren().add(createStudent);

        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        Button courses = new Button("Show courses for this student");
        courses.setOnAction((event) -> {
            //new scene tableview with courses selected student
        });
        Button certificates = new Button("Show certificates for this student");
        certificates.setOnAction((event) -> {
            //new scene tableview with certificates selected student
        });
        //crud buttons below table


        Scene scene = new Scene(vBox, 600, 400);
        return scene;
    }
    
}
