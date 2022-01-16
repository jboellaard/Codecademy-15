package GUI.StatisticsScenes;

import DB.DBConnection;
import DB.EnrollmentRepo;
import Domain.Certificate;
import Domain.Gender;
import GUI.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.collections.*;
import javafx.beans.value.*;
import javafx.stage.Stage;

public class StatisticOptionsOverview {

    public Scene getScene(){
        VBox vBox = new VBox(20);
        vBox.setPrefWidth(250);

        Button perGender = new Button("Percentage of certificates per gender"); 
        vBox.getChildren().add(perGender);

        GridPane genderGrid = new GridPane();
        genderGrid.setAlignment(Pos.CENTER);
        genderGrid.setHgap(10);
        genderGrid.setVgap(5);
        vBox.getChildren().add(genderGrid);

        Label choice = new Label("Choose a gender: ");
        Label gender = new Label();
        Label result = new Label();
        
        Gender[] genderList = new Gender[]{Gender.M,  Gender.F, Gender.O};
        ChoiceBox<Gender> genderDropDown = new ChoiceBox<>(FXCollections.observableArrayList(genderList));
        
        
        genderDropDown.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                gender.setText(String.valueOf(genderList[new_value.intValue()])+": ");
                result.setText(String.valueOf(EnrollmentRepo.certificatesPerGender(genderList[new_value.intValue()])) +"%");
                
            }
        });

        perGender.setOnAction((event -> {
            genderGrid.add(choice,0,0);
            genderGrid.add(genderDropDown,1,0);
            genderGrid.add(gender,0,1);
            genderGrid.add(result,1,1);
        }));
        

        Button mostViewedWebcasts = new Button("Top 3 viewed webcasts");
        mostViewedWebcasts.setOnAction((event -> {
            //tableview or not most viewed webcasts
        }));
        // buttons.getChildren().add(updateStudent);
        vBox.getChildren().add(mostViewedWebcasts);

        Button mostCertificates = new Button("Top 3 courses with most certificates");
        mostCertificates.setOnAction((event -> {
            
            // TableView<Certificate> table = new TableView<>();
            // ObservableList<Certificate> allStudents = DBConnection.studentRepo.getAllStudents();
            // table.setItems(allStudents);
    
            // TableColumn<Certificate,String> nameCol = new TableColumn<>("Name");
            // nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            // TableColumn<Student,String> emailCol = new TableColumn<>("Email Address");
            // emailCol.setCellValueFactory(new PropertyValueFactory<>("EmailAddress"));
            // TableColumn<Student,String> dobCol = new TableColumn<>("Date of birth");
            // dobCol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
            // TableColumn<Student,Gender> genderCol = new TableColumn<>("Gender");
            // genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
            // TableColumn<Student,Integer> addressIDCol = new TableColumn<>("Address ID");
            // addressIDCol.setCellValueFactory(new PropertyValueFactory<>("addressID"));

            // table.getColumns().add(nameCol);
            // table.getColumns().add(emailCol);
            // table.getColumns().add(dobCol);
            // table.getColumns().add(genderCol);
            // table.getColumns().add(addressIDCol);
            // table.sort();
            
            // VBox vBox = new VBox(10);
            // vBox.getChildren().add(table);

        }));
        vBox.getChildren().add(mostCertificates);

        Button back = new Button("Go back");
        back.setOnAction((event -> {
            GUI.GUIStage.setScene(GUI.getHomeScene());
        }));
        vBox.getChildren().add(back);

        vBox.setAlignment(Pos.CENTER);
        perGender.setMinWidth(vBox.getPrefWidth());
        mostViewedWebcasts.setMinWidth(vBox.getPrefWidth());
        mostCertificates.setMinWidth(vBox.getPrefWidth());

        Scene scene = new Scene(vBox, 600, 400);
        return scene;
    }

    
}
