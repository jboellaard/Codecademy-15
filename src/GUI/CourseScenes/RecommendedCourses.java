package GUI.CourseScenes;

import DB.DBConnection;
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

//This scene shows a recommendation of coursed based on the selected course.

public class RecommendedCourses {

    public Scene getScene(Course course){
        TableView<Course> table = new TableView<>();
        ObservableList<Course> recommendedCourses = DBConnection.courseRepo.getRecommendedCourses(course);
        table.setItems(recommendedCourses);
 
        TableColumn<Course,String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        TableColumn<Course,String> subjectCol = new TableColumn<>("Subject");
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        TableColumn<Course,String> introCol = new TableColumn<>("Introduction");
        introCol.setCellValueFactory(new PropertyValueFactory<>("IntroductionText"));
        TableColumn<Course,LevelIndication> levelCol = new TableColumn<>("Level");
        levelCol.setCellValueFactory(new PropertyValueFactory<>("LevelIndication"));

        table.getColumns().add(nameCol);
        table.getColumns().add(subjectCol);
        table.getColumns().add(introCol);
        table.getColumns().add(levelCol);
        table.sort();
        
        VBox vBox = new VBox();

        HBox name = new HBox(15);
        name.setPadding(new Insets(15,15,15,15));
        name.setAlignment(Pos.CENTER);
        vBox.getChildren().add(name);

        Label courseName = new Label("Recommended courses when you've finished "+course.getCourseName());
        name.getChildren().add(courseName);

        vBox.getChildren().add(table);

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(15,15,5,15));
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
