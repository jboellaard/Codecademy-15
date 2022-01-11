package GUI;

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
        
        Button createStudent = new Button("Create student");
        gridPane.add(createStudent,0,0);
        createStudent.setOnAction((event) -> {
            NewStudentScene addStudent = new NewStudentScene();
            GUI.getStage().setScene(addStudent.getScene());
            GUI.getStage().setTitle("Add new Student");
        });

        layout.setCenter(gridPane);
        Scene home = new Scene(layout, 600, 600);
        return home;
    }
    
}
