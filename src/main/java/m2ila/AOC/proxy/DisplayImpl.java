package m2ila.AOC.proxy;

import javafx.application.Platform;
import javafx.scene.control.Button;
import m2ila.AOC.Observer.Generator;
import m2ila.AOC.Observer.ObserverGenerator;

public class DisplayImpl implements ObserverGenerator{

	private Canal canal;
	private String name;
	private Button displayer;
	
	/**
	 * Displayer Constructor
	 * @param name
	 * @param displayer
	 */
	public DisplayImpl(String name, Button displayer){
		this.name = name;
		this.displayer = displayer;
	}
	/**
	 * Update dispalyer value
	 * @param subject
	 */
	public void update(Generator subject) {
		int v = subject.getValue();
		
		// binding JavaFx displayer
		Platform.runLater(() -> displayer.setText(v + ""));
	}

}
