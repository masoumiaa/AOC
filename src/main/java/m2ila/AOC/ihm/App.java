package m2ila.AOC.ihm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Displayers");
        primaryStage.setScene(new Scene(root, 550, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
