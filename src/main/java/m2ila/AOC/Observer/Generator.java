package m2ila.AOC.Observer;

public interface Generator extends SubjectAsync<Generator> {
	public Integer getValue();
}
