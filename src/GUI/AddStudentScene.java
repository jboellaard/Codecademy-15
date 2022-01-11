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

        Label name = new Label("Student name:");
        TextField nameInput = new TextField();
        Label email = new Label("Student email:");
        TextField emailInput = new TextField();
        Label dOB = new Label("Student date of birth: ");
        TextField dOBDay = new TextField();
        dOBDay.setPrefWidth(40);
        dOBDay.setPromptText("dd");
        TextField dOBMonth = new TextField();
        dOBMonth.setPrefWidth(40);
        dOBMonth.setPromptText("mm");
        TextField dOBYear = new TextField();
        dOBYear.setPrefWidth(80);
        dOBYear.setPromptText("yyyy");
        GridPane dOBInput = new GridPane();
        // dOBInput.add(day,0,0);
        dOBInput.add(dOBDay,0,0);
        // dOBInput.add(month,0,1);
        dOBInput.add(dOBMonth,1,0);
        // dOBInput.add(year,0,2);
        dOBInput.add(dOBYear,2,0);
        // DatePicker studentDOBInput = new DatePicker();
        Label gender = new Label("Student gender:");
        ToggleGroup tg = new ToggleGroup();
        RadioButton GM = new RadioButton("Male");
        RadioButton GF = new RadioButton("Female");
        RadioButton GO = new RadioButton("Other");
        GM.setSelected(true);
        GM.setToggleGroup(tg);
        GF.setToggleGroup(tg);
        GO.setToggleGroup(tg);
        GridPane genderInput = new GridPane();
        genderInput.add(GM,0,0);
        genderInput.add(GF,1,0);
        genderInput.add(GO,2,0);
        Label address = new Label("Student address:");
        TextField addressInput = new TextField();

        Button newStudent = new Button("submit form");
        Label dateError = new Label("");
        gridPane.add(dateError,2,3);
        newStudent.setOnAction((event) -> {
            dateError.setText("");
            try {
                Integer.parseInt(dOBDay.getText());
                Integer.parseInt(dOBMonth.getText());
                Integer.parseInt(dOBYear.getText());

                if (!DateTools.isValidDate(Integer.parseInt(dOBDay.getText()), Integer.parseInt(dOBMonth.getText()), Integer.parseInt(dOBYear.getText()))){
                    dateError.setText("This is not a valid date");
                }
            } catch (NumberFormatException nfe) {
                dateError.setText("Please enter a number");
            }
            
            
            // Address address = new Address(studentAddressInput.getText());
            
            // if (DateTools.isValidDate(dOBList[0],dOBList[1],dOBList[2]) && Address.isValidAddress() && 
            //     Email.isValidEmail(studentEmailInput.getText())){
            //     String name = studentNameInput.getText();
            //     Email email = new Email(studentEmailInput.getText());
            //     DateTools dOB = new DateTools(dOBList[0],dOBList[1],dOBList[2]);
            //     Gender gender = Gender.M;
            //     if (GF.isSelected()) gender = Gender.F;
            //     if (GO.isSelected()) gender = Gender.O;
            //     StudentRepo repo = new StudentRepo();
            //     Student hans = new Student(name,email,dOB,gender,address);
            //     repo.create(hans);
            //     gridPane.add(new Label(""+repo.getAllStudents()),0,7);
            // }
        });

        gridPane.add(name,0,1);
        gridPane.add(nameInput,1,1);
        gridPane.add(email,0,2);
        gridPane.add(emailInput,1,2);
        gridPane.add(dOB,0,3);
        gridPane.add(dOBInput,1,3);
        gridPane.add(gender,0,4);
        gridPane.add(genderInput,1,4);
        gridPane.add(address,0,5);
        gridPane.add(addressInput,1,5);
        gridPane.add(newStudent,0,6);

        layout.setCenter(gridPane);
        Scene studentView = new Scene(layout, 600, 600);
        return studentView;
    }
    
}
