package m2ila.AOC.ihm;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

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
	private String chosenAlgo = "";
	
	@FXML
	public void startDisplay() {
		// call generator
    	
	}
	
	@FXML
	public void stopDisplay() {
		// call generator
    	
	}
	
	
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
		
		myComboBox.getSelectionModel().selectedItemProperty()
	    .addListener(new ChangeListener<String>() {
	        public void changed(ObservableValue<? extends String> observable,
	                            String oldValue, String newValue) {
	            chosenAlgo = newValue;
	            System.out.println("Chosen Algorithm is : "+chosenAlgo);
	        }
	    });
	}
}
