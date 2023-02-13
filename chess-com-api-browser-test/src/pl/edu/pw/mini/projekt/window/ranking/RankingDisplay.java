package pl.edu.pw.mini.projekt.window.ranking;

import java.net.MalformedURLException;
import java.util.ArrayList;

import pl.edu.pw.mini.projekt.ranking.Ranking;
import pl.edu.pw.mini.projekt.ranking.Record;

public class RankingDisplay {

	public static String displayRanking(String title) throws MalformedURLException {
		String output = "# " + "\t" + "FIDE" + "\t" + "NAZWA U¯YTKOWNIKA" + "\n";

		Ranking ranking = new Ranking(title);
		ArrayList<Record> records = ranking.records;

		int i = 0;
		for (Record r : records) {

			if (i >= 30) {
				break;
			}

			i++;
			output += (i + ". " + "\t" + r.ranking + "\t" + r.username + "\n");
		}

		return output;

	}
}
