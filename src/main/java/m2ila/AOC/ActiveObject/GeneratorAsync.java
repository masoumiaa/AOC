package m2ila.AOC.ActiveObject;

import java.util.concurrent.Future;

public interface GeneratorAsync {
	public Future<Integer> getValue();
}
