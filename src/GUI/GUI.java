package GUI;

import DB.StudentRepo;
import Domain.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
    private Map<Button,Boolean> buttons = new HashMap<>();


    @Override
    public void start(Stage stage) throws Exception {

        BorderPane layout = new BorderPane();
        GridPane gridPane = new GridPane();

        Label studentName = new Label("Student name:");
        TextField studentNameInput = new TextField();
        Label studentEmail = new Label("Student email:");
        TextField studentEmailInput = new TextField();
        Label studentDOB = new Label("Student date of birth:");
        TextField studentDOBInput = new TextField();
        Label studentGender = new Label("Student gender:");
        TextField studentGenderInput = new TextField();
        Label studentAddress = new Label("Student address:");
        TextField studentAddressInput = new TextField();

        Button newStudent = new Button("submit form");
        newStudent.setOnAction((event) -> {
            String name = studentNameInput.getText();
            String email = studentEmailInput.getText();
            String dOB = studentDOBInput.getText();
            String gender = studentGenderInput.getText();
            String address = studentAddressInput.getText();
            StudentRepo repo = new StudentRepo();
            Student hans = new Student(name,email,dOB,gender,address);
            repo.create(hans);
            gridPane.add(new Label(""+repo.getAllStudents()),1,7);
        });

        // 3.2. Create the buttons
        
        Button createStudent = new Button("Create student");
        gridPane.add(createStudent,0,0);
        createStudent.setFont(Font.font("Monospaced", 25));
        createStudent.setOnAction((event) -> {
            gridPane.add(studentName,1,1);
            gridPane.add(studentNameInput,2,1);
            gridPane.add(studentEmail,1,2);
            gridPane.add(studentEmailInput,2,2);
            gridPane.add(studentDOB,1,3);
            gridPane.add(studentDOBInput,2,3);
            gridPane.add(studentGender,1,4);
            gridPane.add(studentGenderInput,2,4);
            gridPane.add(studentAddress,1,5);
            gridPane.add(studentAddressInput,2,5);
            gridPane.add(newStudent,1,6);

        });
        // Button getAllStudents = new Button("Get all students");
        // getAllStudents.setFont(Font.font("Monospaced", 25));
        // getAllStudents.setOnAction((event) -> {

        // 
        // }); 
        // layout.setCenter(createStudent);
        layout.setCenter(gridPane);

        Scene view = new Scene(layout, 600, 600);
        stage.setTitle("GUI");
        stage.setScene(view);
        stage.show();
    }

    


    
}


