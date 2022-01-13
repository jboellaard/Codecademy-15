package GUI.EnrollmentScenes;

import DB.*;
import Domain.*;

import javafx.application.Application;
// import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EnrollmentScene {

    public Scene getScene(){
        BorderPane layout = new BorderPane();
        GridPane gridPane = new GridPane();

        Label signUpDate = new Label("Sign up date:");
        DatePicker courseSUP = new DatePicker();
        Label course = new Label("course:");
        TextField courseInput = new TextField();
        Label studentName = new Label("Student name:");
        TextField studentNameInput = new TextField();

        Button newStudent = new Button("submit form");
        newStudent.setOnAction((event) -> {
            // Date sUP = new Date(String.valueOf(courseSUP.getValue()));
            // String course = courseInput.getText();
            // String student = new Email(studentNameInput.getText());
            
            // EnrollmentRepo repo = new EnrollmentRepo();
            // Enrollment math = new Enrollment(signUpDate, course, student);
            // repo.create(hans);
            // gridPane.add(new Label(""+repo.getAllStudents()),0,7);
        });
        
        Button createStudent = new Button("Create enrollment");
        gridPane.add(createStudent,0,0);
        createStudent.setOnAction((event) -> {
            gridPane.add(signUpDate,0,1);
            gridPane.add(courseSUP,1,1);
            gridPane.add(course,0,2);
            gridPane.add(courseInput,1,2);
            gridPane.add(studentName,0,3);
            gridPane.add(studentNameInput,1,3);
            // gridPane.add(createEnrollment,0,6);

        });

        layout.setCenter(gridPane);

        Scene view = new Scene(layout, 600, 600);
        return view;
    }

        

}