package m2ila.AOC.Observer;

import m2ila.AOC.ObserverAsync;

public interface SubjectAsync<T> {
	public void attach(ObserverAsync<T> o);
	public void detach(ObserverAsync<T> o);
}
