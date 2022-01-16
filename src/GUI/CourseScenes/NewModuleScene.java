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

public class NewModuleScene {

    public Scene getScene(Course course){

        TableView<CourseModule> table = new TableView<>();
        ObservableList<CourseModule> unusedModules = DBConnection.courseModuleRepo.getUnusedModules();
        table.setItems(unusedModules);
 
        TableColumn<CourseModule,String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        TableColumn<CourseModule,String> versionCol = new TableColumn<>("Version");
        versionCol.setCellValueFactory(new PropertyValueFactory<>("Version"));
        TableColumn<CourseModule,LevelIndication> folCol = new TableColumn<>("FollowNumber");
        folCol.setCellValueFactory(new PropertyValueFactory<>("FollowNumber"));

        table.getColumns().add(titleCol);
        table.getColumns().add(versionCol);
        table.getColumns().add(folCol);
        table.sort();
        
        VBox vBox = new VBox();
        vBox.getChildren().add(table);

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(15,15,5,15));
        buttons.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttons);

        Label noCourseSelected = new Label("");
        
        Button addModule = new Button("Add module");
        addModule.setOnAction((event -> {
            CourseModule selectedModule = table.getSelectionModel().getSelectedItem();
            if (selectedModule!=null){
                if (DBConnection.courseModuleRepo.updateModuleUsed(course, selectedModule)){
                    noCourseSelected.setText("Module succesfully added");
                } else {
                    noCourseSelected.setText("Unfortunately the module could not be added");
                }
            } else {
                noCourseSelected.setText("Please select a module first");
            }
        }));
        buttons.getChildren().add(addModule);


        Button back = new Button("Go back");
        back.setOnAction((event -> {
            GUI.GUIStage.setScene(GUI.getHomeScene());
        }));
        
        buttons.getChildren().add(back);

        HBox error = new HBox(15);
        error.setPadding(new Insets(5,25,15,25));
        error.setAlignment(Pos.CENTER);
        error.getChildren().add(noCourseSelected);
        vBox.getChildren().add(error);


        Scene scene = new Scene(vBox, 600, 400);
        return scene;

    }
    
}
