package pl.edu.pw.mini.projekt.window;

import java.awt.Dimension;
import java.net.MalformedURLException;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import pl.edu.pw.mini.projekt.window.charts.PlotPanel;

public class MainPlotPanel extends JPanel {

	private JTabbedPane tabbedPane;

	private PlotPanel rapid;
	private PlotPanel blitz;
	private PlotPanel bullet;

	public MainPlotPanel(String username) throws MalformedURLException {

		this.setPreferredSize(new Dimension(492, 500));

		configureTabbedPane();

		rapid = new PlotPanel(username, "rapid");
		blitz = new PlotPanel(username, "blitz");
		bullet = new PlotPanel(username, "bullet");

		tabbedPane.add("Rapid", rapid);
		tabbedPane.add("Blitz", blitz);
		tabbedPane.add("Bullet", bullet);

	}

	private void configureTabbedPane() {
		tabbedPane = new JTabbedPane();
		tabbedPane.setFocusable(false);
		this.add(tabbedPane);
	}

	public void setDarkMode() {
		rapid.setDarkMode();
		blitz.setDarkMode();
		bullet.setDarkMode();
	}

	public void setLightMode() {
		rapid.setLightMode();
		blitz.setLightMode();
		bullet.setLightMode();
	}
}
