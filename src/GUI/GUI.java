package GUI;

import DB.*;
import GUI.StudentScenes.*;
import GUI.CourseScenes.*;
import javafx.application.Application;
// import javafx.geometry.Insets;
import javafx.geometry.Pos;
// import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
// import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
    public static Stage GUIStage;
    public static DBConnection dBConnection = new DBConnection();

    public static Stage getStage() {
        return GUIStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        GUIStage = stage;
        Scene homePage = GUI.getHomeScene();
        stage.setTitle("Joy Boellaard (2354217), Joey van Kuijk (), Lisa Tyem () & Tim van Vliet ()");
        stage.setScene(homePage);
        stage.show();
    }

    public static Scene getHomeScene(){
        VBox vBox = new VBox(30);
        vBox.setPrefWidth(150);

        Label title = new Label("Codecademy Statistics");
        title.setFont(new Font(28));
        vBox.getChildren().add(title);

        Button allStudents = new Button("All students");
        allStudents.setStyle("-fx-font: 18 arial;");
        vBox.getChildren().add(allStudents);
        allStudents.setOnAction((event) -> {
            StudentOverview overview = new StudentOverview();
            GUI.getStage().setScene(overview.getScene());
        });

        Button allCourses = new Button("All courses");
        allCourses.setStyle("-fx-font: 18 arial;");
        vBox.getChildren().add(allCourses);
        allCourses.setOnAction((event) -> {
            CourseOverview overview = new CourseOverview();
            GUI.getStage().setScene(overview.getScene());
        });

        Button statistics = new Button("Statistics");
        statistics.setStyle("-fx-font: 18 arial;");
        vBox.getChildren().add(statistics);
        statistics.setOnAction((event) -> {
            // StatisticsOverview overview = new StatisticsOverview();
            // GUI.getStage().setScene(overview.getScene());
        });


        vBox.setAlignment(Pos.CENTER);
        allStudents.setMinWidth(vBox.getPrefWidth());
        allCourses.setMinWidth(vBox.getPrefWidth());
        statistics.setMinWidth(vBox.getPrefWidth());
        Scene home = new Scene(vBox, 600, 400);
        return home;
    }

}


