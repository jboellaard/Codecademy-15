package GUI;

import DB.*;
import Domain.*;

import javafx.application.Application;
// import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        HomeScene home = new HomeScene();
        Scene homePage = home.getScene();
        stage.setTitle("GUI");
        stage.setScene(homePage);
        stage.show();
    }

    
}


