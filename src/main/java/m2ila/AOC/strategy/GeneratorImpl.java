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
	private AlgoDiffusion algo = new AtomicDiffusion();
	private List<Canal> canals = new ArrayList<Canal>();
	
	public void attach(ObserverAsync<Generator> c) {
		this.getCanals().add((Canal) c);
	}

	public void detach(ObserverAsync<Generator> c) {
		this.getCanals().remove(c);
	}
	
	public Integer getValue() {
		return this.v;
	}

	public void notifyCanals() {
		algo.execute();
	}

	public void change() {
		this.generateValue();
		this.notifyCanals();
	}

	private void generateValue() {
		this.v = (int) (Math.random()*(60+1));
	}

	public List<Canal> getCanals() {
		return canals;
	}

	public void setCanals(List<Canal> canals) {
		this.canals = canals;
	}
}
