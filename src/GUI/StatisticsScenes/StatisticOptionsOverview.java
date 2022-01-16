package GUI.StatisticsScenes;

import DB.DBConnection;
import DB.EnrollmentRepo;
import Domain.Certificate;
import Domain.Gender;
import GUI.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StatisticOptionsOverview {

    public Scene getScene(){
        VBox vBox = new VBox(30);
        vBox.setPrefWidth(250);

        Button perGender = new Button("Percentage of certificates per gender");
        perGender.setOnAction((event -> {
            HBox hBox = new HBox();
            Label m = new Label("Male: ");
            Label resultM = new Label(String.valueOf(EnrollmentRepo.certificatesPerGender(Gender.M)));
            hBox.getChildren().add(m);
            hBox.getChildren().add(resultM);
            vBox.getChildren().add(hBox);
            // NewStudentScene newStudent = new NewStudentScene();
            // GUI.getStage().setScene(newStudent.getCreateScene());
        }));
        // buttons.getChildren().add(createStudent);
        vBox.getChildren().add(perGender);

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
