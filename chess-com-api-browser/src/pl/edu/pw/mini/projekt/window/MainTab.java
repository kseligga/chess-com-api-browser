package pl.edu.pw.mini.projekt.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainTab extends JPanel implements ActionListener {

	private JPanel mainPlayerPanel;
	private JPanel searchMainPanel;
	private JLabel searchLabel;
	private JTextField searchBox;
	private JButton submit;
	private JCheckBox theme;
	private JPanel leftPanel;
	private JPanel mainPlotPanel;

	private String username;

	private boolean playerExists = false;

	public MainTab() {

		this.setLayout(new BorderLayout());
		mainPlayerPanel = new JPanel();
		mainPlayerPanel.setLayout(new BorderLayout());
		mainPlayerPanel.setBackground(Color.white);

		configureSearchBar();

		this.add(mainPlayerPanel, BorderLayout.CENTER);
	}

	private void configureSearchBar() {
		searchMainPanel = new JPanel();
		searchMainPanel.setBackground(Color.lightGray);
		searchMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		searchLabel = new JLabel("Podaj nazwê u¿ytkownika");
		searchLabel.setForeground(Color.BLACK);
		searchMainPanel.add(searchLabel);

		searchBox = new JTextField();
		searchBox.setPreferredSize(new Dimension(300, 40));
		searchBox.addActionListener(this);
		searchMainPanel.add(searchBox);

		submit = new JButton("Wyszukaj");
		submit.addActionListener(this);
		submit.setFocusable(false);
		searchMainPanel.add(submit);

		theme = new JCheckBox("Ciemny motyw");
		theme.setFocusable(false);
		theme.addActionListener(this);

		searchMainPanel.add(theme);

		this.add(searchMainPanel, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			username = searchBox.getText();
			try {
				leftPanel = new LeftPanel(username, theme.isSelected());
				mainPlayerPanel.removeAll();
				mainPlayerPanel.add(leftPanel, BorderLayout.WEST);
				mainPlotPanel = new MainPlotPanel(username);
				mainPlayerPanel.add(mainPlotPanel, BorderLayout.EAST);
				mainPlayerPanel.setVisible(true);
				playerExists = true;
				revalidate();
			} catch (MalformedURLException e1) {
				mainPlayerPanel.removeAll();
				mainPlayerPanel.setVisible(false);
				AppWindow.displayUserError();
				playerExists = false;
				revalidate();
			}
		}
		if (e.getSource() == theme || e.getSource() == submit) {
			if (theme.isSelected()) {

				searchMainPanel.setBackground(Color.darkGray);
				searchLabel.setForeground(Color.white);
				mainPlayerPanel.setBackground(Color.black);
				this.setBackground(Color.black);

				if (playerExists) {
					mainPlayerPanel.setBackground(Color.black);
					if (leftPanel != null) {
						((LeftPanel) leftPanel).setDarkMode();
					}
					((MainPlotPanel) mainPlotPanel).setDarkMode();
				}
			} else {

				searchMainPanel.setBackground(Color.lightGray);
				searchLabel.setForeground(Color.BLACK);
				mainPlayerPanel.setBackground(Color.white);
				this.setBackground(Color.white);

				if (playerExists) {
					mainPlayerPanel.setBackground(Color.white);
					if (leftPanel != null) {
						((LeftPanel) leftPanel).setLightMode();
					}
					((MainPlotPanel) mainPlotPanel).setLightMode();
				}
			}
		}
	}

}
