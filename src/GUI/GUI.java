package GUI;

import DB.*;
import GUI.StudentScenes.*;
import GUI.CourseScenes.*;
import javafx.application.Application;
// import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
    public static Stage GUIStage;
    public static AddressRepo addressRepo = new AddressRepo();
    public static StudentRepo studentRepo = new StudentRepo();
    public static CourseRepo courseRepo = new CourseRepo();

    public static Stage getStage() {
        return GUIStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        GUIStage = stage;
        Scene homePage = GUI.getHomeScene();
        stage.setTitle("Home");
        stage.setScene(homePage);
        stage.show();
    }

    public static Scene getHomeScene(){
        BorderPane layout = new BorderPane();
        GridPane gridPane = new GridPane();

        Button allStudents = new Button("All students");
        gridPane.add(allStudents,1,0);
        allStudents.setOnAction((event) -> {
            StudentOverview overview = new StudentOverview();
            GUI.getStage().setScene(overview.getScene());
            GUI.getStage().setTitle("All students");
        });

        Button allCourses = new Button("All courses");
        gridPane.add(allCourses,2,0);
        allCourses.setOnAction((event) -> {
            CourseOverview overview = new CourseOverview();
            GUI.getStage().setScene(overview.getScene());
            GUI.getStage().setTitle("All courses");
        });

        layout.setCenter(gridPane);
        Scene home = new Scene(layout, 600, 400);
        return home;
    }

}


