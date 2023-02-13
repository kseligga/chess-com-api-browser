package pl.edu.pw.mini.projekt.window.charts;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

import pl.edu.pw.mini.projekt.ratings.RatingWithTimestamp;
import pl.edu.pw.mini.projekt.ratings.RatingsList;

public class RatingInTimeChart {

	public static LineChartPanel getRatingInTimeChart(String username, int daysAgo, String type)
			throws MalformedURLException {

		RatingsList rl = new RatingsList(username, daysAgo, type);
		ArrayList<RatingWithTimestamp> rwtList = rl.getRatingsInTime();

		if (rwtList.size() >= 31) {
			return null;
		}

		// kiedy uzytkownik stworzy³ konto w wybranym okresie, okres bez konta
		// wype³niany zerami
		if (rwtList.size() < daysAgo) {
			int i = rwtList.size();
			while (i < daysAgo) {
				RatingWithTimestamp rwt = new RatingWithTimestamp(0, 0);
				rwtList.add(0, rwt);
				i++;
			}
		}

		Double[] values = new Double[daysAgo];
		// Arrays.fill(values, 0);

		int i = 0;
		for (RatingWithTimestamp rwt : rwtList) {

			values[i] = (double) rwt.getRating();
			i++;
		}

		return new LineChartPanel(Arrays.asList(values));

	}

}
