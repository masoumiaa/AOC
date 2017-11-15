package m2ila.AOC.proxy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import m2ila.AOC.GeneratorAsync;
import m2ila.AOC.ObserverGeneratorAsync;
import m2ila.AOC.Observer.Generator;
import m2ila.AOC.Observer.Observer;

public class Canal implements GeneratorAsync,ObserverGeneratorAsync{
	
	private Integer v = 0;
	private DisplayImpl display;
	private Generator generator;
	private ExecutorService scheduler = Executors.newFixedThreadPool(4); ;
	
	public void attach(Observer<Generator> o) {
		this.display = (DisplayImpl) o;
	}
	
	public void detach(Observer<Generator> o) {
		this.display = null;
	}
	
	public Future<Integer> getValue() {
		//this.v=v;
		return scheduler.submit(()->{ 
			return generator.getValue();//sync
		});
	}
	
	public Future<Void> update(Generator subject) {
		//display.update(subject);	
		return scheduler.submit(()->{
			display.update(subject);//sync
			return null;//void
		});
	}

}
