package m2ila.AOC.Observer;

public interface Subject<T> {
	public void attach(Observer<T> o);
	public void detach(Observer<T> o);
}
