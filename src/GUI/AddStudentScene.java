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

        Label name = new Label("Full name:");
        TextField nameInput = new TextField();

        Label email = new Label("Emailaddress:");
        TextField emailInput = new TextField();

        Label dOB = new Label("Date of birth: ");
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
        dOBInput.add(dOBDay,0,0);
        dOBInput.add(dOBMonth,1,0);
        dOBInput.add(dOBYear,2,0);

        Label gender = new Label("Gender:");
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

        Label address = new Label("Address:");
        Label zipCode = new Label("Zipcode:");
        TextField zipCodeInput = new TextField();
        Label street = new Label("Street:");
        TextField streetInput = new TextField();
        Label houseNumber = new Label("No:");
        TextField houseNumberInput = new TextField();
        houseNumberInput.setMaxWidth(50);
        Label suffix = new Label("Suffix:");
        TextField suffixInput = new TextField();
        suffixInput.setMaxWidth(50);
        Label city = new Label("City:");
        TextField cityInput = new TextField();
        Label country = new Label("Country");
        TextField countryInput = new TextField();
        GridPane addressInput = new GridPane();
        addressInput.add(street,0,0);
        addressInput.add(streetInput,1,0);
        addressInput.add(houseNumber,0,1);
        addressInput.add(houseNumberInput,1,1);
        addressInput.add(suffix,2,1);
        addressInput.add(suffixInput,3,1);
        addressInput.add(city,0,2);
        addressInput.add(cityInput,1,2);
        addressInput.add(country,0,3);
        addressInput.add(countryInput,1,3);

        Button newStudent = new Button("submit form");
        Label emailError = new Label("");
        gridPane.add(emailError,2,2);
        Label dateError = new Label("");
        gridPane.add(dateError,2,3);
        newStudent.setOnAction((event) -> {
            emailError.setText("");
            dateError.setText("");
            if (!Email.isValidEmail(emailInput.getText())){
                emailError.setText("This is not a valid email");
            }
            
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
