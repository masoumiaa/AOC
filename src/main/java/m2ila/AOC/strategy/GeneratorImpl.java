package m2ila.AOC.strategy;

import java.util.ArrayList;
import java.util.List;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import m2ila.AOC.ObserverAsync;
import m2ila.AOC.Observer.Generator;
import m2ila.AOC.Observer.Observer;
import m2ila.AOC.Observer.ObserverGenerator;
import m2ila.AOC.proxy.Canal;

public class GeneratorImpl implements Generator{
	
	private Integer v = 0;
	private List<ObserverGenerator> observers = new ArrayList<ObserverGenerator>();;
	private List<AlgoDiffusion> algos = new ArrayList<AlgoDiffusion>();
	private List<Canal> canals = new ArrayList<Canal>();
	
	public void attach(ObserverAsync<Generator> c) {
		this.canals.add((Canal) c);
	}

	public void detach(ObserverAsync<Generator> c) {
		this.canals.remove(c);
	}
	
	public Integer getValue() {
		return this.v;
	}

	public void notifyCanals() {
		this.canals.forEach(c->{
			c.update(this);
		});
	}

	public void change() {
		this.generateValue();
		this.notifyCanals();
	}

	private void generateValue() {
		this.v = (int) (Math.random()*(60+1));
	}
}
