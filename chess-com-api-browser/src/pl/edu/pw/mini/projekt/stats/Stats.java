package pl.edu.pw.mini.projekt.stats;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

import pl.edu.pw.mini.projekt.stats.gametype.GameType;
import pl.edu.pw.mini.projekt.window.AppWindow;

public class Stats {
	GameType chess_daily;
	GameType chess960_daily;
	GameType chess_blitz;
	GameType chess_rapid;
	GameType chess_bullet;
	HighLow tactics;
	HighLow lessons;
	PuzzleRush puzzle_rush;
	int fide;

	public Stats(String username) throws MalformedURLException {
		URL apiUrl = new URL("https://api.chess.com/pub/player/" + username + "/stats");

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(apiUrl.openStream()));
			String inputLine;
			inputLine = in.readLine();
			in.close();

			Gson g = new Gson();
			Stats stats = g.fromJson(inputLine, Stats.class);

			chess_daily = stats.chess_daily;
			chess960_daily = stats.chess960_daily;
			chess_blitz = stats.chess_blitz;
			chess_rapid = stats.chess_rapid;
			chess_bullet = stats.chess_bullet;
			tactics = stats.tactics;
			lessons = stats.lessons;
			puzzle_rush = stats.puzzle_rush;
			fide = stats.fide;

		} catch (Exception e) {
			AppWindow.displayQueryError();
		}
	}

	public GameType getChess_daily() {
		return chess_daily;
	}

	public GameType getChess960_daily() {
		return chess960_daily;
	}

	public GameType getChess_blitz() {
		return chess_blitz;
	}

	public GameType getChess_rapid() {
		return chess_rapid;
	}

	public GameType getChess_bullet() {
		return chess_bullet;
	}

	public HighLow getTactics() {
		return tactics;
	}

	public HighLow getLessons() {
		return lessons;
	}

	public PuzzleRush getPuzzle_rush() {
		return puzzle_rush;
	}

	public int getFide() {
		return fide;
	}

}
