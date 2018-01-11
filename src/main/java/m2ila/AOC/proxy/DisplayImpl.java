package m2ila.AOC.proxy;

import javafx.application.Platform;
import javafx.scene.control.Button;
import m2ila.AOC.Observer.Generator;
import m2ila.AOC.Observer.ObserverGenerator;

public class DisplayImpl implements ObserverGenerator{

	private Canal canal;
	private String name;
	private Button displayer;
	
	public DisplayImpl(String name, Button displayer){
		this.name = name;
		this.displayer = displayer;
	}
	
	public void update(Generator subject) {
		int v = subject.getValue();
		// binding javafx displayer
		Platform.runLater(() -> displayer.setText(v + ""));
		System.out.println("Displayer "+this.name+" - Value :"+v);
	}

}
