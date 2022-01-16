package GUI.StudentScenes;

import DB.DBConnection;
import Domain.*;
import Domain.Tools.*;
import GUI.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class NewStudentScene {

    public Scene getCreateScene(){
        String[] txtValues = new String[]{"", "", "", "", "", "", "", "", "", "", ""};
        return getScene(true, null, txtValues);
    } 

    public Scene getUpdateScene(Student exiStudent){
        String[] dob = exiStudent.getDateOfBirth().split("-");
        Address a = exiStudent.getAddress();
        String[] txtValues = new String[]{exiStudent.getName(), exiStudent.getEmailAddress(),dob[0],dob[1],dob[2],
            a.getZipCode(),String.valueOf(a.getHouseNumber()),a.getSuffix(),a.getStreet(),a.getCity(),a.getCountry()};
        return getScene(false, exiStudent, txtValues);
    }

    private Scene getScene(boolean create, Student exiStudent, String[] txtValues){
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10,10,10,10));
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        Label name = new Label("Full name:");
        TextField nameInput = new TextField(txtValues[0]);

        Label email = new Label("Emailaddress:");
        TextField emailInput = new TextField(txtValues[1]);

        Label dOB = new Label("Date of birth: ");
        TextField dOBDay = new TextField(txtValues[4]);
        dOBDay.setPrefWidth(40);
        dOBDay.setPromptText("dd");
        TextField dOBMonth = new TextField(txtValues[3]);
        dOBMonth.setPrefWidth(40);
        dOBMonth.setPromptText("mm");
        TextField dOBYear = new TextField(txtValues[2]);
        dOBYear.setPrefWidth(80);
        dOBYear.setPromptText("yyyy");
        GridPane dOBInput = new GridPane();
        dOBInput.add(dOBDay,2,0);
        dOBInput.add(dOBMonth,1,0);
        dOBInput.add(dOBYear,0,0);

        Label gender = new Label("Gender:");
        ToggleGroup tg = new ToggleGroup();
        RadioButton GM = new RadioButton("Male");
        RadioButton GF = new RadioButton("Female");
        RadioButton GO = new RadioButton("Other");
        GM.setSelected(true);
        if (!create){
            if (exiStudent.getGender().equals(Gender.F)) GF.setSelected(true); 
            if (exiStudent.getGender().equals(Gender.O)) GO.setSelected(true); 
        }
        GM.setToggleGroup(tg);
        GF.setToggleGroup(tg);
        GO.setToggleGroup(tg);
        GridPane genderInput = new GridPane();
        genderInput.add(GM,0,0);
        genderInput.add(GF,1,0);
        genderInput.add(GO,2,0);

        Label address = new Label("Address: ");
        Label zipCode = new Label("Zipcode: ");
        TextField zipCodeInput = new TextField(txtValues[5]);
        zipCodeInput.setMaxWidth(100);
        Label houseNo = new Label("No: ");
        TextField houseNoInput = new TextField(txtValues[6]);
        houseNoInput.setMaxWidth(50);
        Label suffix = new Label("Suffix: ");
        TextField suffixInput = new TextField(txtValues[7]);
        suffixInput.setMaxWidth(50);
        Label street = new Label("Street: ");
        TextField streetInput = new TextField(txtValues[8]);
        Label city = new Label("City: ");
        TextField cityInput = new TextField(txtValues[9]);
        Label country = new Label("Country: ");
        TextField countryInput = new TextField(txtValues[10]);
        GridPane addressInput = new GridPane();
        addressInput.add(zipCode,0,0,1,1);
        addressInput.add(zipCodeInput,1,0,1,1);
        addressInput.add(houseNo,2,0,1,1);
        addressInput.add(houseNoInput,3,0,1,1);
        addressInput.add(suffix,4,0,1,1);
        addressInput.add(suffixInput,5,0,1,1);
        addressInput.add(street,0,1,1,1);
        addressInput.add(streetInput,1,1,5,1);
        addressInput.add(city,0,2,1,1);
        addressInput.add(cityInput,1,2,5,1);
        addressInput.add(country,0,3,2,1);
        addressInput.add(countryInput,1,3,5,1);
        Label emailError = new Label("");
        gridPane.add(emailError,2,2);
        Label dateError = new Label("");
        gridPane.add(dateError,2,3);
        Label addressError = new Label("");
        gridPane.add(addressError,2,6);

        Label studentAdded = new Label();

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(5,5,5,5));
        Button submit = new Button("Submit");
        submit.setOnAction((event) -> {
            boolean validInput = true;
            String studentName = nameInput.getText().trim();
            String studentEmail = "";
            String studentDOB = "";
            Gender studentGender = Gender.M;
            if (GF.isSelected()) studentGender = Gender.F;
            if (GO.isSelected()) studentGender = Gender.O;

            String studentZipCode = "";
            int studentHouseNo = -1;
            String studentSuffix = null;
            if (suffixInput.getText()!=null && !suffixInput.getText().isBlank()) studentSuffix = suffixInput.getText().trim();
            String studentStreet = streetInput.getText().trim();
            String studentCity = cityInput.getText().trim();
            String studentCountry = countryInput.getText().trim();

            emailError.setText("");
            dateError.setText("");
            addressError.setText("");
            if (EmailTools.isValidEmail(emailInput.getText())){
                studentEmail = emailInput.getText().trim();
            } else {
                emailError.setText("This is not a valid email.");
                validInput = false;
            }
            
            try {
                int day = Integer.parseInt(dOBDay.getText());
                int month = Integer.parseInt(dOBMonth.getText());
                int year = Integer.parseInt(dOBYear.getText());

                if (DateTools.isValidDate(day, month, year)){
                    studentDOB = DateTools.formatDate(day, month, year);
                } else {
                    dateError.setText("This is not a valid date.");
                    validInput = false;
                }
            } catch (NumberFormatException nfe) {
                dateError.setText("Please enter a number.");
                validInput = false;
            }

            try {
                studentHouseNo = Integer.parseInt(houseNoInput.getText());
            } catch (NumberFormatException nfe) {
                addressError.setText("Please enter a housenumber.");
                validInput = false;
            }
            try {
                studentZipCode = ZipCodeTools.formatZipCode(zipCodeInput.getText());
            } catch (NullPointerException nfe) {
                addressError.setText("Please enter a zipcode.");
                validInput = false;
            } catch (IllegalArgumentException iae) {
                addressError.setText("This is not a valid zipcode.");
                validInput = false;
            }

            if (validInput){
                Address studentAddress = new Address(studentZipCode,studentHouseNo,studentSuffix,studentStreet,studentCity,studentCountry);
                if (!create){
                    Student alteredStudent = new Student(studentName,studentEmail,studentDOB,studentGender,studentAddress);
                    if (DBConnection.studentRepo.update(alteredStudent,exiStudent)) {
                        studentAdded.setText("Student succefully updated");
                    } else {
                        studentAdded.setText("Unfortunately the student could not be updated");
                    }
                } else {
                    Student newStudent = new Student(studentName,studentEmail,studentDOB,studentGender,studentAddress);
                    if (DBConnection.studentRepo.create(newStudent)){
                        studentAdded.setText("Student succefully created");
                    } else {
                        studentAdded.setText("Unfortunately the student could not be created");
                    }
                }
            }

        });
        buttons.getChildren().add(submit);

        Button back = new Button("Go back");
        back.setOnAction((event -> {
            StudentOverview overview = new StudentOverview();
            GUI.getStage().setScene(overview.getScene());
        }));
        buttons.getChildren().add(back);

        gridPane.add(name,0,1);
        gridPane.add(nameInput,1,1);
        gridPane.add(email,0,2);
        gridPane.add(emailInput,1,2);
        gridPane.add(dOB,0,3);
        gridPane.add(dOBInput,1,3);
        gridPane.add(gender,0,4);
        gridPane.add(genderInput,1,4);
        gridPane.add(address,0,6);
        gridPane.add(addressInput,1,6,1,4);
        gridPane.add(buttons,0,10,2,1);
        gridPane.add(studentAdded,0,11,2,1);

        layout.setCenter(gridPane);
        Scene newStudentView = new Scene(layout, 700, 400);
        return newStudentView;
    }
    
}
