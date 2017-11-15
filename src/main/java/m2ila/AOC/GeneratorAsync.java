package m2ila.AOC;

import java.util.concurrent.Future;

public interface GeneratorAsync {
	public Future<Integer> getValue();
}
