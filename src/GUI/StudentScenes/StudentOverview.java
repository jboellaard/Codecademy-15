package GUI.StudentScenes;

import java.util.*;

import DB.StudentRepo;
import Domain.Gender;
import Domain.Student;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class StudentOverview {

    public Scene getScene(){
        TableView<Student> table = new TableView<>();
        StudentRepo repo = new StudentRepo();
        ObservableList<Student> allStudents = repo.getAllStudents();
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

 
        table.getColumns().setAll(nameCol,emailCol,dobCol,genderCol,addressIDCol);
        
        VBox vBox = new VBox();
        // // adding table to the vBox
        vBox.getChildren().add(table);
        // // creating a scene for adding vBox
        Scene scene = new Scene(vBox, 600, 600);

        return scene;
    }
    
}
