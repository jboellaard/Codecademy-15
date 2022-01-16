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


public class ModuleOverview {

    public Scene getScene(Course course){
        TableView<CourseModule> table = new TableView<>();
        table.setItems(course.getModules());
 
        TableColumn<CourseModule,String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        TableColumn<CourseModule,String> versionCol = new TableColumn<>("Version");
        versionCol.setCellValueFactory(new PropertyValueFactory<>("Version"));
        TableColumn<CourseModule,LevelIndication> folCol = new TableColumn<>("FollowNumber");
        folCol.setCellValueFactory(new PropertyValueFactory<>("FollowNumber"));
        TableColumn<CourseModule,Integer> progCol = new TableColumn<>("Average progress");
        progCol.setCellValueFactory(new PropertyValueFactory<>("AverageProgress"));

        table.getColumns().add(titleCol);
        table.getColumns().add(versionCol);
        table.getColumns().add(folCol);
        table.getColumns().add(progCol);
        table.sort();
        
        VBox vBox = new VBox();
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

        // HBox error = new HBox(15);
        // error.setPadding(new Insets(5,25,15,25));
        // error.setAlignment(Pos.CENTER);
        // error.getChildren().add(noCourseSelected);
        // vBox.getChildren().add(error);


        Scene scene = new Scene(vBox, 700, 400);
        return scene;

    }
    
}
