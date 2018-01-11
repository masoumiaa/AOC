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
	private int latence = 0;
	private Generator generator;
	private ExecutorService scheduler = Executors.newFixedThreadPool(Integer.MAX_VALUE);
	
	public void attach(Observer<Generator> o, int latence) {
		this.display = (DisplayImpl) o;
		this.latence = latence;
	}
	
	public void detach(Observer<Generator> o) {
		this.display = null;
	}
	
	public Future<Integer> getValue() {
		return scheduler.submit(()->{ 
			return generator.getValue();//sync
		});
	}
	
	public Future<Void> update(Generator subject) {
		try {
			Thread.sleep(latence);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scheduler.submit(()->{
			display.update(subject);//sync
			return null;//void
		});
	}

}
