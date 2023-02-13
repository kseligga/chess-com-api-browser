package pl.edu.pw.mini.projekt.ranking;

public class Record implements Comparable<Record> {

	public String username;
	public int ranking;

	public Record(String username, int ranking) {
		super();
		this.username = username;
		this.ranking = ranking;
	}

	@Override
	public int compareTo(Record r) {
		return r.ranking - ranking;
	}

}
