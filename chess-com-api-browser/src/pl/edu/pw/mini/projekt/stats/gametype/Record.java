package pl.edu.pw.mini.projekt.stats.gametype;

public class Record {
	private int win;
	private int loss;
	private int draw;
	private int time_per_move;
	private double timeout_percent;

	public int getWin() {
		return win;
	}

	public int getLoss() {
		return loss;
	}

	public int getDraw() {
		return draw;
	}

	public int getTime_per_move() {
		return time_per_move;
	}

	public double getTimeout_percent() {
		return timeout_percent;
	}
}
