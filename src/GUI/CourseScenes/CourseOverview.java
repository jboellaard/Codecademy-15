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

public class CourseOverview {

    public Scene getScene(){
        TableView<Course> table = new TableView<>();
        ObservableList<Course> allCourses = DBConnection.courseRepo.getAllCourses();
        table.setItems(allCourses);
 
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
        vBox.getChildren().add(table);

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(15,15,5,15));
        buttons.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttons);

        Label noCourseSelected = new Label("");
        
        Button createCourse = new Button("Add new course");
        createCourse.setOnAction((event -> {
            NewCourseScene newCourse = new NewCourseScene();
            GUI.getStage().setScene(newCourse.getScene());
        }));
        buttons.getChildren().add(createCourse);

        Button addModule = new Button("Add new module");
        addModule.setOnAction((event -> {
            Course selectedCourse = table.getSelectionModel().getSelectedItem();
            if (selectedCourse!=null){
                NewModuleScene newModule = new NewModuleScene();
            GUI.getStage().setScene(newModule.getScene(selectedCourse));
            } else {
                noCourseSelected.setText("Please select a course first");
            }
        }));
        buttons.getChildren().add(addModule);

        Button back = new Button("Go back");
        back.setOnAction((event -> {
            GUI.GUIStage.setScene(GUI.getHomeScene());
        }));
        
        buttons.getChildren().add(back);

        HBox buttonsSecondRow = new HBox(15);
        buttonsSecondRow.setPadding(new Insets(5,15,5,15));
        buttonsSecondRow.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttonsSecondRow);

        //delete button

        Button delete = new Button("Delete course");
        delete.setOnAction((event -> {
            Course selectedCourse = table.getSelectionModel().getSelectedItem();
            if (selectedCourse!=null){
                if (DBConnection.courseRepo.delete(selectedCourse)){
                    noCourseSelected.setText("Course succesfully deleted");
                } else {
                    noCourseSelected.setText("Course could not be deleted");
                }
            } else {
                noCourseSelected.setText("Please select a course first");
            }
        }));
        buttonsSecondRow.getChildren().add(addModule);

        
        Button showProgress = new Button("Show modules course and progress all students");
        showProgress.setOnAction((event -> {
            Course selectedCourse = table.getSelectionModel().getSelectedItem();
            if (selectedCourse!=null){
                ModuleOverview modules = new ModuleOverview();
                GUI.getStage().setScene(modules.getScene(selectedCourse));
            } else {
                noCourseSelected.setText("Please select a course first");
            }
        }));
        buttonsSecondRow.getChildren().add(showProgress);

        HBox error = new HBox(15);
        error.setPadding(new Insets(5,25,15,25));
        error.setAlignment(Pos.CENTER);
        error.getChildren().add(noCourseSelected);
        vBox.getChildren().add(error);


        Scene scene = new Scene(vBox, 700, 400);
        return scene;
    }
    
}
