package m2ila.AOC.proxy;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import m2ila.AOC.ActiveObject.GeneratorAsync;
import m2ila.AOC.ActiveObject.ObserverGeneratorAsync;
import m2ila.AOC.Observer.Generator;
import m2ila.AOC.Observer.Observer;

public class Canal implements GeneratorAsync,ObserverGeneratorAsync{
	
	private Integer v = 0;
	private DisplayImpl display;
	private int latence = 0;
	private Generator generator;
	private ScheduledExecutorService scheduler;
	
	/**
	 * Attach displayer to canal & set scheduler & latence
	 * @param o
	 * @param latence
	 * @param scheduler
	 */
	public void attach(Observer<Generator> o, int latence, ScheduledExecutorService scheduler) {
		this.display = (DisplayImpl) o;
		this.latence = latence;
		this.scheduler = scheduler;
	}
	
	/**
	 * Detach displayer from canal
	 * @param o
	 */
	public void detach(Observer<Generator> o) {
		this.display = null;
	}
	
	/**
	 * @return Future of value
	 */
	public Future<Integer> getValue() {
		return scheduler.submit(()->{ 
			return generator.getValue();//sync
		});
	}
	
	/**
	 * @return Future of update
	 */
	public Future<Void> update(Generator subject) {
		return scheduler.schedule(()->{
			display.update(subject);//sync
			return null;//void
		},latence, TimeUnit.MILLISECONDS);
	}

}
