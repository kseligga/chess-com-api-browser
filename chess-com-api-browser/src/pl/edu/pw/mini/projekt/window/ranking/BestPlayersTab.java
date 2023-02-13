package pl.edu.pw.mini.projekt.window.ranking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import pl.edu.pw.mini.projekt.window.AppWindow;

public class BestPlayersTab extends JPanel implements ActionListener {

	private JPanel mainRankingPanel;
	private JComponent textRankingPanel;
	private JButton submitTitle;
	private JComboBox jComboBox;
	private JCheckBox theme;

	public BestPlayersTab() {
		this.setLayout(new BorderLayout());

		configureSelectTitleBar();

		mainRankingPanel = new JPanel();
		mainRankingPanel.setLayout(new BorderLayout());
		mainRankingPanel.setBackground(Color.white);

		textRankingPanel = new JTextArea();
		textRankingPanel.setPreferredSize(new Dimension(290, 540));
		textRankingPanel.setToolTipText("");
		textRankingPanel.setBackground(Color.white);

		mainRankingPanel.add(textRankingPanel, BorderLayout.CENTER);

		this.add(mainRankingPanel, BorderLayout.CENTER);
	}

	private void configureSelectTitleBar() {

		String[] optionsToChoose = { "GM", "IM", "FM", "CM", "WGM", "WIM", "WFM", "WCM" };

		JPanel searchMainPanel = new JPanel();
		searchMainPanel.setBackground(Color.DARK_GRAY);
		searchMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel searchLabel = new JLabel("Wybierz tytu³ szachowy");
		searchLabel.setForeground(Color.white);
		searchMainPanel.add(searchLabel);

		jComboBox = new JComboBox<>(optionsToChoose);
		jComboBox.addActionListener(this);
		searchMainPanel.add(jComboBox);

		submitTitle = new JButton("Poka¿");
		submitTitle.addActionListener(this);
		submitTitle.setFocusable(false);
		searchMainPanel.add(submitTitle);

		theme = new JCheckBox("Ciemny motyw");
		theme.setFocusable(false);
		theme.addActionListener(this);

		searchMainPanel.add(theme);

		this.add(searchMainPanel, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitTitle) {
			textRankingPanel.setToolTipText("");
			try {
				String title = (String) jComboBox.getItemAt(jComboBox.getSelectedIndex());
				String rankingText = RankingDisplay.displayRanking(title);
				((JTextArea) textRankingPanel).append(rankingText);
			} catch (MalformedURLException e1) {
				AppWindow.displayUnknownError();
			}
		}
		if (e.getSource() == theme) {
			if (theme.isSelected()) {
				textRankingPanel.setBackground(Color.DARK_GRAY);
				textRankingPanel.setForeground(Color.white);
			} else {
				textRankingPanel.setBackground(Color.white);
				textRankingPanel.setForeground(Color.black);
			}
		}

	}

}
