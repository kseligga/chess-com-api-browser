package pl.edu.pw.mini.projekt.ratings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pl.edu.pw.mini.projekt.window.AppWindow;

public class RatingsList {

	ArrayList<RatingWithTimestamp> ratingsInTime;

	public ArrayList<RatingWithTimestamp> getRatingsInTime() {
		return ratingsInTime;
	}

	public RatingsList(String username, int daysAgo, String type) throws MalformedURLException {

		
		URL apiURL = new URL("https://www.chess.com/callback/live/stats/" + username + "/chart?daysAgo=" + daysAgo
				+ "&type=" + type);

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(apiURL.openStream()));
			String inputLine;
			inputLine = in.readLine();

			in.close();

			Type listOfRatingsWithTimestamp = new TypeToken<ArrayList<RatingWithTimestamp>>() {
			}.getType();

			Gson gson = new Gson();
			ArrayList<RatingWithTimestamp> outputList = gson.fromJson(inputLine, listOfRatingsWithTimestamp);

			this.ratingsInTime = outputList;

		} catch (Exception e) {
			AppWindow.displayQueryError();
		}
	}

}
