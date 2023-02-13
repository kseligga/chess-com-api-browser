package pl.edu.pw.mini.projekt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;

import pl.edu.pw.mini.projekt.window.AppWindow;

public class Titled {

	private String title;
	private ArrayList<String> players;

	public Titled(String title) {
		this.title = title;

		try {
			URL titledURL = new URL("https://api.chess.com/pub/titled/" + title);

			BufferedReader in = new BufferedReader(new InputStreamReader(titledURL.openStream()));
			String inputLine;
			inputLine = in.readLine();

			in.close();

			Gson g = new Gson();
			Titled titled = g.fromJson(inputLine, Titled.class);
			ArrayList<String> lista = titled.getPlayers();

			this.players = lista;

		} catch (Exception e) {
			AppWindow.displayQueryError();
		}
	}

	public ArrayList<String> getPlayers() {
		return players;
	}

	public String getTitle() {
		return title;
	}

}
