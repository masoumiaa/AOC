package m2ila.AOC.strategy;

/**
 * @author aminesoumiaa
 *
 */
public class SequentialDiffusion implements AlgoDiffusion {

	private GeneratorImpl generator;
	
	/**
	 * Sequential diffusion constructor
	 * @param generator
	 */
	public SequentialDiffusion(GeneratorImpl generator) {
		this.generator = generator;
	}
	
	public void configure() {
	}	

	/**
	 * execute method for all generator canals
	 */
	public void execute() {
		this.generator.getCanals().forEach(c->{
			c.update(this.generator);
		});
	}

}
