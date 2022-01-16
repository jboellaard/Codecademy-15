package GUI.CourseScenes;

import DB.DBConnection;
import Domain.*;
import GUI.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

//This scene is used to add a new course.

public class NewCourseScene {

    public Scene getScene(){
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10,10,10,10));
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        Label name = new Label("Course name:");
        TextField nameInput = new TextField();

        Label subject = new Label("Subject:");
        TextField subjectInput = new TextField();

        Label intro = new Label("Introduction text: ");
        TextField introInput = new TextField();

        Label level = new Label("Level:");
        ToggleGroup tg = new ToggleGroup();
        RadioButton LBeginner = new RadioButton("Beginner");
        RadioButton LAdvanced = new RadioButton("Advanced");
        RadioButton LExpert = new RadioButton("Expert");
        LBeginner.setSelected(true);
        LBeginner.setToggleGroup(tg);
        LAdvanced.setToggleGroup(tg);
        LExpert.setToggleGroup(tg);
        GridPane levelInput = new GridPane();
        levelInput.add(LBeginner,0,0);
        levelInput.add(LAdvanced,1,0);
        levelInput.add(LExpert,2,0);


        Label module = new Label("First module: \n(you can add more modules later)");
        ChoiceBox<CourseModule> firstModule = new ChoiceBox<>(DBConnection.courseModuleRepo.getUnusedModules());


        Label courseAdded = new Label();

        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(5,5,5,5));
        Button submit = new Button("Submit");
        submit.setOnAction((event) -> {
            CourseModule moduleInput = firstModule.getValue();
            String courseName = nameInput.getText().trim();
            String courseSubject = subjectInput.getText().trim();
            String courseIntro = introInput.getText().trim();
            LevelIndication courseLevel = LevelIndication.Beginner;
            if (LAdvanced.isSelected()) courseLevel = LevelIndication.Advanced;
            if (LExpert.isSelected()) courseLevel = LevelIndication.Expert;

            Course newCourse = new Course(courseName,courseSubject,courseIntro,courseLevel);
            if (DBConnection.courseRepo.create(newCourse, moduleInput)){
                courseAdded.setText("Course succefully created");
            } else {
                courseAdded.setText("Unfortunately the course could not be created");
            }

        });
        buttons.getChildren().add(submit);

        Button back = new Button("Go back");
        back.setOnAction((event -> {
            CourseOverview overview = new CourseOverview();
            GUI.getStage().setScene(overview.getScene());
        }));
        buttons.getChildren().add(back);

        gridPane.add(name,0,1);
        gridPane.add(nameInput,1,1);
        gridPane.add(subject,0,2);
        gridPane.add(subjectInput,1,2);
        gridPane.add(intro,0,3);
        gridPane.add(introInput,1,3);
        gridPane.add(level,0,4);
        gridPane.add(levelInput,1,4);
        gridPane.add(module,0,5);
        gridPane.add(firstModule,1,5);
        gridPane.add(buttons,0,6,2,1);
        gridPane.add(courseAdded,0,7,2,1);

        layout.setCenter(gridPane);
        Scene newCourseView = new Scene(layout, 700, 400);
        return newCourseView;
    }
    
}
