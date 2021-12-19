package GUI;

import DB.*;
import Domain.*;

import javafx.application.Application;
// import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane layout = new BorderPane();
        GridPane gridPane = new GridPane();

        Label studentName = new Label("Student name:");
        TextField studentNameInput = new TextField();
        Label studentEmail = new Label("Student email:");
        TextField studentEmailInput = new TextField();
        Label studentDOB = new Label("Student date of birth:");
        DatePicker studentDOBInput = new DatePicker();
        Label studentGender = new Label("Student gender:");
        CheckBox GM = new CheckBox("M");
        CheckBox GF = new CheckBox("F");
        CheckBox GO = new CheckBox("O");
        GridPane studentGenderInput = new GridPane();
        studentGenderInput.add(GM,0,0);
        studentGenderInput.add(GF,1,0);
        studentGenderInput.add(GO,2,0);
        Label studentAddress = new Label("Student address:");
        TextField studentAddressInput = new TextField();

        Button newStudent = new Button("submit form");
        newStudent.setOnAction((event) -> {
            String name = studentNameInput.getText();
            Email email = new Email(studentEmailInput.getText());
            Date dOB = new Date(studentDOBInput.toString());
            Gender gender = Gender.M;
            if (GF.isSelected()){
                gender = Gender.F;
            } else if (GO.isSelected()){
                gender = Gender.O;
            } 
            Address address = new Address(studentAddressInput.getText());
            StudentRepo repo = new StudentRepo();
            Student hans = new Student(name,email,dOB,gender,address);
            repo.create(hans);
            gridPane.add(new Label(""+repo.getAllStudents()),0,7);
        });
        
        Button createStudent = new Button("Create student");
        gridPane.add(createStudent,0,0);
        createStudent.setOnAction((event) -> {
            gridPane.add(studentName,0,1);
            gridPane.add(studentNameInput,1,1);
            gridPane.add(studentEmail,0,2);
            gridPane.add(studentEmailInput,1,2);
            gridPane.add(studentDOB,0,3);
            gridPane.add(studentDOBInput,1,3);
            gridPane.add(studentGender,0,4);
            gridPane.add(studentGenderInput,1,4);
            gridPane.add(studentAddress,0,5);
            gridPane.add(studentAddressInput,1,5);
            gridPane.add(newStudent,0,6);

        });

        layout.setCenter(gridPane);

        Scene view = new Scene(layout, 600, 600);
        stage.setTitle("GUI");
        stage.setScene(view);
        stage.show();
    }

    
}


