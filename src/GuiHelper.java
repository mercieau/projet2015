
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GuiHelper {

	public static void showOnFrame(JComponent component, String frameName) {
		JFrame frame = new JFrame(frameName);
		
		WindowAdapter wa = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		
		frame.setLocation(100,200);			// placement de la fenetre sur l'écran, par rapport au coin en haut à gauche
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fermeture par appui sur la croix
		JPanel pan1 = new JPanel() ;
		pan1.setLayout(new GridLayout(1,2));		// utilisation d'un panel avec un layout en grille de 1 lignes et 2 colonnes
		
		frame.addWindowListener(wa);
		frame.getContentPane().add(pan1);
		pan1.add(component);
		pan1.add(new JLabel());
		frame.pack();
		frame.setVisible(true);
	}

}
