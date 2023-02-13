package pl.edu.pw.mini.projekt.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import pl.edu.pw.mini.projekt.Player;

public class LeftPanel extends JPanel {

	public JTextArea playerInfo;

	public LeftPanel(String username, boolean darkMode) throws MalformedURLException {

		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(290, 540));

		this.playerInfo = new JTextArea();
		playerInfo.setPreferredSize(new Dimension(290, 540));
		playerInfo.setText("");

		if (darkMode) {
			this.setDarkMode();
		} else {
			this.setLightMode();
		}

		this.add(playerInfo);

		String output = "";

		Player player = new Player(username);

		int fide = player.getStats().getFide();
		if (fide > 0) {
			output += "FIDE gracza: " + fide + "\n";
		}

		String title = player.getTitle();
		if (title != null) {
			output += "Tytu³ szachowy: " + title + "\n";
		}

		String name = player.getName();
		if (name != null) {
			output += "Imiê: " + player.getName() + "\n";
		}

		String location = player.getLocation();
		if (location != null) {
			output += "Lokalizacja: " + player.getLocation() + "\n";
		}

		output += "Najlepszy wynik w Puzzle rush: ";
		try {
			output += player.getStats().getPuzzle_rush().getBest().getScore() + "\n";
		} catch (Exception e) {
			output += "brak :(\n";
		}

		output += "Najwy¿szy ranking w trybie Tactics: ";
		try {
			output += player.getStats().getTactics().getHighest().getRating() + "\n";
		} catch (Exception e) {
			output += "brak :(\n";
		}

		output += "Ostatnio widziany " + DisplayHelper.getDate(player.getLast_online()) + "\n";

		try {
			output += "Obserwowany przez " + player.getFollowers() + " u¿ytkowników" + "\n" + "\n";
		} catch (Exception e) {
			;
		}

		output += "Obecne ratingi tego u¿ytkownika w trybach gry: " + "\n";
		String ratings = "";
		try {
			ratings += "Rapid: " + player.getStats().getChess_rapid().getLast().getRating() + "\n";
		} catch (Exception e) {
			;
		}
		try {
			ratings += "Blitz: " + player.getStats().getChess_blitz().getLast().getRating() + "\n";
		} catch (Exception e) {
			;
		}
		try {
			ratings += "Bullet: " + player.getStats().getChess_bullet().getLast().getRating() + "\n";
		} catch (Exception e) {
			;
		}
		try {
			ratings += "Daily: " + player.getStats().getChess_daily().getLast().getRating() + "\n";
		} catch (Exception e) {
			;
		}
		try {
			ratings += "Szachy 960: " + player.getStats().getChess960_daily().getLast().getRating() + "\n";
		} catch (Exception e) {
			;
		}

		if (ratings == "") {
			output += "Ten u¿ytkownik z nie posiada aktualnych ratingów.";
		} else {
			output += ratings;
		}

		if (player.onlineStatus()) {
			output += "\n" + "AKTYWNY W CI¥GU OSTATNIEJ GODZINY";
		}

		playerInfo.append(output);

		Image image = null;
		URL avatar = player.getAvatar();

		if (avatar != null) {
			try {
				image = ImageIO.read(avatar);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				image = ImageIO.read(new File("assets//avatar.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		playerInfo.setEditable(false);

		JLabel label = new JLabel(new ImageIcon(image));
		label.setBackground(Color.GREEN);
		label.setForeground(Color.magenta);

		this.add(label);
		this.add(playerInfo);
	}

	void setDarkMode() {
		this.setBackground(Color.BLACK);
		playerInfo.setBackground(Color.DARK_GRAY);
		playerInfo.setForeground(Color.LIGHT_GRAY);
	}

	void setLightMode() {
		this.setBackground(Color.WHITE);
		playerInfo.setBackground(Color.LIGHT_GRAY);
		playerInfo.setForeground(Color.BLACK);
	}

}
