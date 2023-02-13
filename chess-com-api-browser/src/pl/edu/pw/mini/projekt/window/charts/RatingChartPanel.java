package pl.edu.pw.mini.projekt.window.charts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import pl.edu.pw.mini.projekt.window.AppWindow;

public class RatingChartPanel extends JPanel implements ActionListener {

	private LineChartPanel lineChartPanel;
	private JButton submitRange;
	private JComboBox jComboBox;
	private JButton save;
	private JPanel searchMainPanel;
	private JLabel searchLabel;

	private String username;
	private int daysAgo;
	private String type;

	public RatingChartPanel(String username, String type) throws MalformedURLException {

		this.username = username;
		this.type = type;

		if (this.daysAgo == 0) {
			this.daysAgo = 7;
		}

		this.setLayout(new BorderLayout());

		configureOptionsBar();
		updateLineChartPanel();

	}

	private void updateLineChartPanel() {

		if (this.lineChartPanel != null) {
			this.remove(lineChartPanel);
		}

		try {
			lineChartPanel = RatingInTimeChart.getRatingInTimeChart(this.username, this.daysAgo, this.type);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (lineChartPanel == null) {
			JTextArea info = new JTextArea();
			info.setPreferredSize(new Dimension(245, 240));
			info.setText("Ten u¿ytkownik nie posiada tego ratingu w badanym okresie."
					+ "\n Mo¿e gra³ w tym czasie w warcaby...");
			info.setEditable(false);
			this.add(info, BorderLayout.SOUTH);
		} else {
			lineChartPanel.setPreferredSize(new Dimension(245, 240));

			this.add(lineChartPanel, BorderLayout.SOUTH);
		}
	}

	private void configureOptionsBar() {
		String[] optionsToChoose = { "7", "14", "30" };

		searchMainPanel = new JPanel();
//		searchMainPanel.setBackground(Color.GRAY);
		searchMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		searchLabel = new JLabel("Wybierz z ilu dni:");
//		searchLabel.setForeground(Color.black);
		searchMainPanel.add(searchLabel);

		jComboBox = new JComboBox<>(optionsToChoose);
		jComboBox.addActionListener(this);
		searchMainPanel.add(jComboBox);

		submitRange = new JButton("Poka¿");
		submitRange.addActionListener(this);
		submitRange.setFocusable(false);
		searchMainPanel.add(submitRange);

		save = new JButton("Zapisz wykres");
		save.addActionListener(this);
		save.setFocusable(false);
		searchMainPanel.add(save, FlowLayout.LEADING);

		this.add(searchMainPanel, BorderLayout.NORTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitRange) {
			this.daysAgo = Integer.parseInt((String) jComboBox.getItemAt(jComboBox.getSelectedIndex()));
			updateLineChartPanel();
			revalidate();
		}
		if (e.getSource() == save) {
			if (this.lineChartPanel == null) {
				AppWindow.displaySavingChartError();
			} else {
				saveCurrentChart();
			}
		}

	}

	private void saveCurrentChart() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Wybierz lokalizacjê zapisu pliku");

		int response = fileChooser.showSaveDialog(null);

		File file;

		if (response == JFileChooser.APPROVE_OPTION) {
			file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".png");
		} else {
			return;
		}

		BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		this.lineChartPanel.paint(g);
		g.dispose();

		try {
			ImageIO.write(bi, "png", file);
		} catch (Exception e) {
			AppWindow.displaySavingChartError();
		}
	}

	public void setDarkMode() {
		searchMainPanel.setBackground(Color.DARK_GRAY);
		searchLabel.setForeground(Color.white);
	}

	public void setLightMode() {
		searchMainPanel.setBackground(Color.GRAY);
		searchLabel.setForeground(Color.black);
	}

}
