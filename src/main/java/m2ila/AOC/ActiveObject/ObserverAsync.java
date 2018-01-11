package m2ila.AOC.ActiveObject;

import java.util.concurrent.Future;

public interface ObserverAsync<T> {
	public Future<Void> update(T subject);
}
