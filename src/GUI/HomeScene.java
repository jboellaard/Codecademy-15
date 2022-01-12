package GUI;

import GUI.StudentScenes.NewStudentScene;
import GUI.StudentScenes.StudentOverview;

// import DB.*;
// import Domain.*;

// import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.scene.text.Font;

public class HomeScene {

    public Scene getScene(){
        BorderPane layout = new BorderPane();
        GridPane gridPane = new GridPane();

        Button allStudents = new Button("All students");
        gridPane.add(allStudents,1,0);
        allStudents.setOnAction((event) -> {
            StudentOverview overview = new StudentOverview();
            GUI.getStage().setScene(overview.getScene());
            GUI.getStage().setTitle("All students");
        });

        layout.setCenter(gridPane);
        Scene home = new Scene(layout, 600, 400);
        return home;
    }
    
}
