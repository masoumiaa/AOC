package m2ila.AOC.strategy;

import java.util.ArrayList;
import java.util.List;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import m2ila.AOC.ActiveObject.ObserverAsync;
import m2ila.AOC.Observer.Generator;
import m2ila.AOC.Observer.Observer;
import m2ila.AOC.Observer.ObserverGenerator;
import m2ila.AOC.proxy.Canal;

public class GeneratorImpl implements Generator{
	
	private Integer v = 0;
	private List<ObserverGenerator> observers = new ArrayList<ObserverGenerator>();;
	private AlgoDiffusion chosenAlgo;
	private List<Canal> canals = new ArrayList<Canal>();
	
	/**
	 *  Attach Canal
	 */
	public void attach(ObserverAsync<Generator> c) {
		this.getCanals().add((Canal) c);
	}
	
	/**
	 *  Detach canal
	 */
	public void detach(ObserverAsync<Generator> c) {
		this.getCanals().remove(c);
	}
	
	/**
	 * @return chosen algorithm
	 */
	public AlgoDiffusion getChosenAlgo() {
		return chosenAlgo;
	}

	/**
	 *  set chosen algorithm
	 * @param algoName
	 */
	public void setChosenAlgo(String algoName) {
		if(algoName.equals("atomic")){
			this.chosenAlgo = new AtomicDiffusion(this);
		}
		else if (algoName.equals("sequential")){
			this.chosenAlgo = new SequentialDiffusion(this);
		}
	}
	
	/**
	 *  Get value
	 */
	public Integer getValue() {
		return this.v;
	}

	/**
	 *  Execute strategy algorithm
	 */
	public void notifyCanals() {
		chosenAlgo.execute();
	}

	/**
	 *  Generate the value & call notifyCanals
	 */
	public void generate() {
		this.generateValue();
		this.notifyCanals();
	}

	/**
	 *  Generate the value
	 */
	private void generateValue() {
		this.v = (int) (Math.random()*(60+1));
	}

	/**
	 * @return generator canals
	 */
	public List<Canal> getCanals() {
		return canals;
	}

	/**
	 *  Set canals
	 * @param canals
	 */
	public void setCanals(List<Canal> canals) {
		this.canals = canals;
	}
}
