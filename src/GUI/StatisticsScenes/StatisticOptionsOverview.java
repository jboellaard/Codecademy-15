package GUI.StatisticsScenes;

import DB.DBConnection;
import DB.EnrollmentRepo;
import DB.WebcastRepo;
import Domain.Gender;
import GUI.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*;
import javafx.beans.value.*;

public class StatisticOptionsOverview {

    public Scene getScene(){
        VBox vBox = new VBox(20);
        vBox.setPrefWidth(250);

        Button perGender = new Button("Percentage of certificates per gender"); 
        vBox.getChildren().add(perGender);

        GridPane genderGrid = new GridPane();
        genderGrid.setAlignment(Pos.CENTER);
        genderGrid.setHgap(10);
        genderGrid.setVgap(5);
        vBox.getChildren().add(genderGrid);

        Label choice = new Label("Choose a gender: ");
        Label gender = new Label();
        Label result = new Label();
        
        Gender[] genderList = new Gender[]{Gender.M,  Gender.F, Gender.O};
        ChoiceBox<Gender> genderDropDown = new ChoiceBox<>(FXCollections.observableArrayList(genderList));
        
        
        genderDropDown.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                gender.setText(String.valueOf("Percentage enrollments with a certificate "+genderList[new_value.intValue()])+": ");
                result.setText(String.valueOf(EnrollmentRepo.certificatesPerGender(genderList[new_value.intValue()])) +"%"); 
            }
        });

        perGender.setOnAction((event -> {
            genderGrid.add(choice,0,0);
            genderGrid.add(genderDropDown,1,0);
            genderGrid.add(gender,0,1);
            genderGrid.add(result,1,1);
        }));

        HBox topThreeWebcasts = new HBox(15);
        topThreeWebcasts.setPadding(new Insets(5,25,15,25));
        topThreeWebcasts.setAlignment(Pos.CENTER);
        topThreeWebcasts.getChildren().add(new Label());

        Button mostViewedWebcasts = new Button("Top 3 viewed webcasts");
        mostViewedWebcasts.setOnAction((event -> {
            GridPane topThree = new GridPane();
            String[][] webCasts = WebcastRepo.getTopThreeMostViewedWebcasts();
            for (int i = 0; i<3; i++){
                topThree.add(new Label(webCasts[i][0] +": "),0,i);
                topThree.add(new Label(webCasts[i][1]),1,i);
            }
            topThreeWebcasts.getChildren().set(0,topThree);
        }));
        vBox.getChildren().add(mostViewedWebcasts);
        vBox.getChildren().add(topThreeWebcasts);

        HBox topThreeCourses = new HBox(15);
        topThreeCourses.setPadding(new Insets(5,25,15,25));
        topThreeCourses.setAlignment(Pos.CENTER);
        topThreeCourses.getChildren().add(new Label());

        Button mostCertificates = new Button("Top 3 courses with most certificates");
        mostCertificates.setOnAction((event -> {
            GridPane topThree = new GridPane();
            String[][] courses = DBConnection.courseRepo.getTop3CoursesWithMostCertificates();
            for (int i = 0; i<3; i++){
                topThree.add(new Label(courses[i][0] +": "),0,i);
                topThree.add(new Label(courses[i][1]),1,i);
            }
            topThreeCourses.getChildren().set(0,topThree);

        }));
        vBox.getChildren().add(mostCertificates);
        vBox.getChildren().add(topThreeCourses);

        Button back = new Button("Go back");
        back.setOnAction((event -> {
            GUI.GUIStage.setScene(GUI.getHomeScene());
        }));
        vBox.getChildren().add(back);

        vBox.setAlignment(Pos.CENTER);
        perGender.setMinWidth(vBox.getPrefWidth());
        mostViewedWebcasts.setMinWidth(vBox.getPrefWidth());
        mostCertificates.setMinWidth(vBox.getPrefWidth());

        Scene scene = new Scene(vBox, 700, 400);
        return scene;
    }

    
}
