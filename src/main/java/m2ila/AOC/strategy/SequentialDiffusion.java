package m2ila.AOC.strategy;

public class SequentialDiffusion implements AlgoDiffusion {

	private GeneratorImpl generator;
	
	public void setGenerator(GeneratorImpl generator) {
		this.generator = generator;
	}
	
	public void configure() {
	}	

	public void execute() {
		this.generator.getCanals().forEach(c->{
			c.update(this.generator);
		});
	}

}
