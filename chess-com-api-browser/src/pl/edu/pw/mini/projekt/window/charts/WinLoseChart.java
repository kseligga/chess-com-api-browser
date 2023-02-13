package pl.edu.pw.mini.projekt.window.charts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.net.MalformedURLException;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import pl.edu.pw.mini.projekt.Player;
import pl.edu.pw.mini.projekt.stats.Stats;
import pl.edu.pw.mini.projekt.stats.gametype.GameType;

public class WinLoseChart {

	public static JPanel getHistogramPanel(String username, String type) throws MalformedURLException {

		Player player = new Player(username);

		Stats stats = player.getStats();

		GameType mode;

		if (type == "rapid") {
			mode = stats.getChess_rapid();
		} else if (type == "bullet") {
			mode = stats.getChess_bullet();
		} else if (type == "blitz") {
			mode = stats.getChess_blitz();
		} else {
			return null;
		}

		if (mode == null) {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setPreferredSize(new Dimension(245, 175));

			JTextArea info = new JTextArea();
			info.setPreferredSize(new Dimension(245, 175));
			info.setText("Ten u¿ytkownik nie posiada rekordu w tym trybie."
					+ "\n Mo¿e œrubowa³ swój rekord w szachoboksie...");
			info.setEditable(false);
			panel.add(info, BorderLayout.CENTER);
			return panel;
		}

		int winCount = mode.getRecord().getWin();
		int drawCount = mode.getRecord().getDraw();
		int loseCount = mode.getRecord().getLoss();

		HistogramPanel panel = new HistogramPanel();
		panel.addHistogramColumn("Zwyciêstwa", winCount, Color.GREEN);
		panel.addHistogramColumn("Remisy", drawCount, Color.GRAY);
		panel.addHistogramColumn("Pora¿ki", loseCount, Color.RED);
		panel.layoutHistogram();

		return panel;

	}

}
