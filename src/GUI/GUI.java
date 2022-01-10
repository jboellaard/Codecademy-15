package GUI;

import javafx.application.Application;
// import javafx.scene.Node;
import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.layout.*;
// import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
    private static Stage GUIStage;

    public static Stage getStage() {
        return GUIStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        GUIStage = stage;
        HomeScene home = new HomeScene();
        Scene homePage = home.getScene();
        stage.setTitle("Home");
        stage.setScene(homePage);
        stage.show();
    }

    
}


