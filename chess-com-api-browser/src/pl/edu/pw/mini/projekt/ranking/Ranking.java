package pl.edu.pw.mini.projekt.ranking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import pl.edu.pw.mini.projekt.Titled;
import pl.edu.pw.mini.projekt.window.AppWindow;

public class Ranking {

	public String title;
	public ArrayList<Record> records;

	public Ranking(String title) {

		Titled titled = new Titled(title);

		ArrayList<Record> records = new ArrayList<>();

		ArrayList<String> playerList = titled.getPlayers();

		for (String username : playerList) {

			try {
				URL apiUrl = new URL("https://api.chess.com/pub/player/" + username + "/stats");

				BufferedReader in;

				in = new BufferedReader(new InputStreamReader(apiUrl.openStream()));
				String inputLine;
				inputLine = in.readLine();
				in.close();

				JsonObject jobj = new Gson().fromJson(inputLine, JsonObject.class);

				int ranking = 0;

				try {
					ranking = jobj.get("fide").getAsInt();
					if (ranking == 0) {
						continue;
					}
				} catch (Exception e) {
					continue;
				}

				Record r = new Record(username, ranking);
				records.add(r);

			} catch (IOException e) {
				AppWindow.displayQueryError();
			}

		}

		ArrayList<Record> sortedRecords = (ArrayList<Record>) records.stream().sorted().collect(Collectors.toList());
		this.records = sortedRecords;
	}

}
