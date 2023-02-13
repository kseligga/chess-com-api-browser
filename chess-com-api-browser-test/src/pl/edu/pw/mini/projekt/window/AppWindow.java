package pl.edu.pw.mini.projekt.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import pl.edu.pw.mini.projekt.window.ranking.BestPlayersTab;

public class AppWindow extends JFrame implements ActionListener {

	private JTabbedPane tabbedPane;

	public AppWindow() {

		configureWindowProperties();
		configureTabbedPane();

		JPanel mainTab = new MainTab();
		JPanel bestPlayersTab = new BestPlayersTab();

		tabbedPane.add("Wyszukaj gracza", mainTab);
		tabbedPane.add("Najlepsi gracze", bestPlayersTab);

		this.setVisible(true);
	}

	private void configureWindowProperties() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Przegl¹darka do danych graczy szachów online");
	}

	private void configureTabbedPane() {
		tabbedPane = new JTabbedPane();
		tabbedPane.setFocusable(false);
		this.add(tabbedPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		;
	}

	static void displayUserError() {
		JOptionPane.showMessageDialog(new JFrame(), "U¿ytkownik o podanej nazwie nie istnieje", "B³¹d",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void displayUnknownError() {
		JOptionPane.showMessageDialog(new JFrame(), "Coœ posz³o nie tak", "B³¹d", JOptionPane.ERROR_MESSAGE);
	}

	public static void displaySavingChartError() {
		JOptionPane.showMessageDialog(new JFrame(), "Brak wykresu do zapisania", "B³¹d zapisu",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void displayQueryError() {
		JOptionPane.showMessageDialog(new JFrame(), "Wyst¹pi³ b³¹d kwerendy do API. Spróbuj ponownie", "B³¹d",
				JOptionPane.ERROR_MESSAGE);
	}
}
