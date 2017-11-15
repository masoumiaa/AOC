package m2ila.AOC.proxy;

import m2ila.AOC.Observer.Generator;
import m2ila.AOC.Observer.ObserverGenerator;

public class DisplayImpl implements ObserverGenerator{

	private Canal canal;
	private String name;
	
	public DisplayImpl(String name){
		this.name = name;
	}
	
	public void update(Generator subject) {
		int v=subject.getValue();
		
		System.out.println("Displayer "+this.name+" - Value :"+v);
	}

}
