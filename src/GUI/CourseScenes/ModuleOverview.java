package GUI.CourseScenes;

import DB.EnrollmentRepo;
import Domain.*;
import GUI.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//This scene shows an overview of the available modules

public class ModuleOverview {

    public Scene getScene(Course course) {
        TableView<CourseModule> table = new TableView<>();
        table.setItems(course.getModules());
        // System.out.println(course.getCourseName()+ ": "+
        // course.getModules().get(0).getTitle());

        TableColumn<CourseModule, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        TableColumn<CourseModule, String> versionCol = new TableColumn<>("Version");
        versionCol.setCellValueFactory(new PropertyValueFactory<>("Version"));
        TableColumn<CourseModule, LevelIndication> folCol = new TableColumn<>("FollowNumber");
        folCol.setCellValueFactory(new PropertyValueFactory<>("FollowNumber"));
        TableColumn<CourseModule, Integer> progCol = new TableColumn<>("Average progress");
        progCol.setCellValueFactory(new PropertyValueFactory<>("AverageProgress"));

        table.getColumns().add(titleCol);
        table.getColumns().add(versionCol);
        table.getColumns().add(folCol);
        table.getColumns().add(progCol);
        table.sort();

        VBox vBox = new VBox();
        vBox.getChildren().add(table);

        Label certificates = new Label("Amount of students that completed the course: ");
        Label amount = new Label(String.valueOf(EnrollmentRepo.getTotalCertificatesPerCourse(course)));

        HBox total = new HBox(15);
        total.setPadding(new Insets(15, 15, 5, 15));
        total.setAlignment(Pos.CENTER);
        vBox.getChildren().add(total);
        total.getChildren().add(certificates);
        total.getChildren().add(amount);

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(15, 15, 5, 15));
        buttons.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttons);

        Button back = new Button("Go back");
        back.setOnAction((event -> {
            CourseOverview overview = new CourseOverview();
            GUI.GUIStage.setScene(overview.getScene());
        }));

        buttons.getChildren().add(back);

        Scene scene = new Scene(vBox, 700, 400);
        return scene;

    }

}
