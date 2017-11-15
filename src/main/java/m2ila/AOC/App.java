package m2ila.AOC;

import org.omg.Messaging.SyncScopeHelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import m2ila.AOC.proxy.Canal;
import m2ila.AOC.proxy.DisplayImpl;
import m2ila.AOC.strategy.GeneratorImpl;

public class App extends Application {

	//déssocier l'appel des méthodes de leur execution
	
	//active
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("displayers.fxml"));
        primaryStage.setTitle("Displayers");
        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws InterruptedException {
        //launch(args);
    	// Create generator
    	GeneratorImpl gen = new GeneratorImpl();
    	
    	// Create Displayers
    	DisplayImpl d1 = new DisplayImpl("Displayer 1");
    	DisplayImpl d2 = new DisplayImpl("Displayer 2");
    	DisplayImpl d3 = new DisplayImpl("Displayer 3");
    	DisplayImpl d4 = new DisplayImpl("Displayer 4");
    	
    	// Create canals
    	Canal c1 = new Canal();
    	Canal c2 = new Canal();
    	Canal c3 = new Canal();
    	Canal c4 = new Canal();
    	
    	// Attach canals to generator
    	gen.attach(c1);
    	gen.attach(c2);
    	gen.attach(c3);
    	gen.attach(c4);
    	
    	// Attach every displayer to its canal
    	c1.attach(d1);
    	c2.attach(d2);
    	c3.attach(d3);
    	c4.attach(d4);
    	
    	//choose algorithm
    	gen.setChosenAlgo("atomic");
    	    	
    	// Change value Every 2s
    	for(int i=0;i<5;i++){
    		gen.change();
    		Thread.sleep(2000);
    		System.out.println();
    	}
    }
}
