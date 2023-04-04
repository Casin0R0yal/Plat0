package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import util.FrameUtil;

public class WaitFrame extends JFrame {

	public WaitFrame() {
		// Définissez le nom
		this.setTitle("Mini Monopoly-Java Edition");
		// Définir la propriété de fermeture par défaut (fin du programme)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Réglez la taille de la fenêtre
		this.setSize(380, 370);
		// Les utilisateurs ne sont pas autorisés à modifier la taille de la fenêtre.
		this.setResizable(false);
		// Entre deux parties
		FrameUtil.setFrameCenter(this);
		add(new JLabel("Chargement, veuillez patienter un instant...",JLabel.CENTER));
		setVisible(true);
	}

}
