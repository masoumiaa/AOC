package m2ila.AOC.ihm;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import m2ila.AOC.proxy.Canal;
import m2ila.AOC.proxy.DisplayImpl;
import m2ila.AOC.strategy.GeneratorImpl;

public class Controller implements Initializable {
	
	@FXML
	private ComboBox<String> myComboBox;
	
	@FXML
	private Button b00;
	@FXML
	private Button b01;
	@FXML
	private Button b10;
	@FXML
	private Button b11;
	
	@FXML
	private Button buttonDisplay;
	
	@FXML
	private Button buttonStop;
	
	private Button[][] tab = new Button[2][2];
	
	private String chosenAlgo = "atomic";
	private GeneratorImpl gen;

	private ScheduledExecutorService service;
	private ScheduledExecutorService scheduler;
	
	private static final int frequence = 2000;
	private boolean started = false;

	private ScheduledFuture<?> action;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		this.tab[0][0] = b00;
		this.tab[1][0] = b01;
		this.tab[0][1] = b10;
		this.tab[1][1] = b11;
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				tab[i][j].setText("0");
			}
		}
		
		initDisplayers();
		
		myComboBox.getSelectionModel().selectedItemProperty()
	    .addListener(new ChangeListener<String>() {
	        public void changed(ObservableValue<? extends String> observable,
	                            String oldValue, String newValue) {
	            
	            chosenAlgo = getAlgo(newValue);
	        }
	    });
		
		buttonStop.setDisable(true);
	}
	
	@FXML
	public void startDisplay() throws InterruptedException {
		if(!started){
			// set algorithm
	    	gen.setChosenAlgo(this.chosenAlgo);
	    	
	    	// Init scheduler
	    	scheduler = Executors.newScheduledThreadPool(Integer.MAX_VALUE);
	    	
	    	// run generator
	    	service = Executors.newScheduledThreadPool(1);
			action = service.scheduleAtFixedRate(gen::generate, 0, frequence, TimeUnit.MILLISECONDS);
			
			buttonDisplay.setDisable(true);
			buttonStop.setDisable(false);
			started = true;
		}
	}
	
	@FXML
	public void stopDisplay() {
		if (started && (service != null)) {
			// stop generating values
			action.cancel(false);
			
			// stop scheduler
			scheduler.shutdownNow();
			
			started = false;
			buttonDisplay.setDisable(false);
			buttonStop.setDisable(true);
		}	
	}
	
	public void initDisplayers(){
		// Create generator
    	gen = new GeneratorImpl();
    	
    	// Init scheduler
    	scheduler = Executors.newScheduledThreadPool(Integer.MAX_VALUE);
    	
    	// Create Displayers
    	DisplayImpl d1 = new DisplayImpl("Displayer 1",b00);
    	DisplayImpl d2 = new DisplayImpl("Displayer 2",b01);
    	DisplayImpl d3 = new DisplayImpl("Displayer 3",b10);
    	DisplayImpl d4 = new DisplayImpl("Displayer 4",b11);
    	
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
    	c1.attach(d1,100,scheduler);
    	c2.attach(d2,400,scheduler);
    	c3.attach(d3,5000,scheduler);
    	c4.attach(d4,800,scheduler);
		
	}
	
	public String getAlgo(String algo){
		String algostring = "";
		if(algo.equals("Sequential algorithm")){
			algostring = "sequential";
		}
        else if(algo.equals("Atomic algorithm")){
        	algostring = "atomic";
        }
		return algostring;
	}
}
