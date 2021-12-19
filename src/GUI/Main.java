package GUI;

import DB.*;
// import Domain.*;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        StudentRepo repo = new StudentRepo();
        // repo.create(new Student("Hans1",new Email("Hans1@hans.com"),new Date("01-01-2000"),Gender.M,new Address("tempAddress")));
        Application.launch(GUI.class);
    }



    
}
