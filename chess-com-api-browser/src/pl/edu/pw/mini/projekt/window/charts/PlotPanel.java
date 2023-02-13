package pl.edu.pw.mini.projekt.window.charts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.MalformedURLException;

import javax.swing.JPanel;

public class PlotPanel extends JPanel {

	private JPanel winsChartPanel;
	private RatingChartPanel ratingChartPanel;

	public PlotPanel(String username, String type) throws MalformedURLException {

		this.setPreferredSize(new Dimension(490, 452));
		this.setLayout(new BorderLayout());

		winsChartPanel = WinLoseChart.getHistogramPanel(username, type);

		ratingChartPanel = new RatingChartPanel(username, type);

		this.add(winsChartPanel, BorderLayout.NORTH);
		this.add(ratingChartPanel, BorderLayout.SOUTH);
	}

	public void setDarkMode() {
		ratingChartPanel.setDarkMode();
	}

	public void setLightMode() {
		ratingChartPanel.setLightMode();
	}
}
