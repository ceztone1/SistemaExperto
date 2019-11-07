package expertSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Clase Main, controla todo el proyecto y lanza la vista principal
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/mainView.fxml"));
        root.getStylesheets().add("./CSS/css.css");
        primaryStage.setTitle("Expert system");
        Scene scene=new Scene(root);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
