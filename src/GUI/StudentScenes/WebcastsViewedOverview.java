package GUI.StudentScenes;

import DB.EnrollmentRepo;
import DB.WebcastRepo;
import Domain.*;
import GUI.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WebcastsViewedOverview {

    public Scene getScene(Student student){

        TableView<ProgressWebcast> table = new TableView<>();
        ObservableList<ProgressWebcast> webcasts = WebcastRepo.getWebcastsViewed(student);

        table.setItems(webcasts);
 
        TableColumn<ProgressWebcast,String> titleCol = new TableColumn<>("Title webcast");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        TableColumn<ProgressWebcast,String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        TableColumn<ProgressWebcast,Integer> progCol = new TableColumn<>("Progress");
        progCol.setCellValueFactory(new PropertyValueFactory<>("Progress"));
        TableColumn<ProgressWebcast,String> nameCol = new TableColumn<>("Name Speaker");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("NameSpeaker"));
        TableColumn<ProgressWebcast,String> urlCol = new TableColumn<>("URL");
        urlCol.setCellValueFactory(new PropertyValueFactory<>("Url"));

    
        table.getColumns().add(titleCol);
        table.getColumns().add(descCol);
        table.getColumns().add(progCol);
        table.getColumns().add(nameCol);
        table.getColumns().add(urlCol);
        table.sort();
        
        VBox vBox = new VBox();

        HBox name = new HBox(15);
        name.setPadding(new Insets(15,15,15,15));
        name.setAlignment(Pos.CENTER);
        vBox.getChildren().add(name);

        Label studentName = new Label("Progress webcasts of "+student.getName());
        name.getChildren().add(studentName);

        vBox.getChildren().add(table);

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(15,15,5,15));
        buttons.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttons);

        Button back = new Button("Go back");
        back.setOnAction((event -> {
            StudentOverview overview = new StudentOverview();
            GUI.GUIStage.setScene(overview.getScene());
        }));
        buttons.getChildren().add(back);
        

        Scene enrollments = new Scene(vBox, 700, 400);
        return enrollments;
    }
    
}
