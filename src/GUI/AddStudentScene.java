package GUI;

import DB.*;
import Domain.*;

// import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.scene.text.Font;

public class AddStudentScene {

    public Scene getScene(){
        BorderPane layout = new BorderPane();
        GridPane gridPane = new GridPane();

        Label studentName = new Label("Student name:");
        TextField studentNameInput = new TextField();
        Label studentEmail = new Label("Student email:");
        TextField studentEmailInput = new TextField();
        Label studentDOB = new Label("Student date of birth:");
        TextField studentDOBInput = new TextField("01-01-2000");
        // DatePicker studentDOBInput = new DatePicker();
        Label studentGender = new Label("Student gender:");
        ToggleGroup tg = new ToggleGroup();
        RadioButton GM = new RadioButton("M");
        RadioButton GF = new RadioButton("F");
        RadioButton GO = new RadioButton("O");
        GM.setSelected(true);
        GM.setToggleGroup(tg);
        GF.setToggleGroup(tg);
        GO.setToggleGroup(tg);
        GridPane studentGenderInput = new GridPane();
        studentGenderInput.add(GM,0,0);
        studentGenderInput.add(GF,1,0);
        studentGenderInput.add(GO,2,0);
        Label studentAddress = new Label("Student address:");
        TextField studentAddressInput = new TextField();

        Button newStudent = new Button("submit form");
        newStudent.setOnAction((event) -> {
            int[] dOBList = Date.stringToIntList(studentDOBInput.getText());
            Address address = new Address(studentAddressInput.getText());
            if (Date.isValidDate(dOBList[0],dOBList[1],dOBList[2]) && Address.isValidAddress() && 
                Email.isValidEmail(studentEmailInput.getText())){
                String name = studentNameInput.getText();
                Email email = new Email(studentEmailInput.getText());
                Date dOB = new Date(dOBList[0],dOBList[1],dOBList[2]);
                Gender gender = Gender.M;
                if (GF.isSelected()) gender = Gender.F;
                if (GO.isSelected()) gender = Gender.O;
                StudentRepo repo = new StudentRepo();
                Student hans = new Student(name,email,dOB,gender,address);
                repo.create(hans);
                gridPane.add(new Label(""+repo.getAllStudents()),0,7);
            }
        });

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

        layout.setCenter(gridPane);
        Scene studentView = new Scene(layout, 600, 600);
        return studentView;
    }
    
}
