package m2ila.AOC.strategy;

import java.util.concurrent.ExecutionException;

public class AtomicDiffusion implements AlgoDiffusion {

	private GeneratorImpl generator;
	
	/**
	 * Sequential diffusion constructor
	 * @param generator
	 */
	public AtomicDiffusion(GeneratorImpl generator) {
		this.generator = generator;
	}
	
	public void configure() {
	}
	
	/**
	 * execute method for all generator canals
	 */
	public void execute() {
		this.generator.getCanals().forEach(c->{
			try {
				c.update(this.generator).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
	}

}
