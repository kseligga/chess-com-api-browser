package pl.edu.pw.mini.projekt.window;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayHelper {

	public static String getDate(long timestamp) {
		Date date = new Date(timestamp);
		Format format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(date);
	}
}
