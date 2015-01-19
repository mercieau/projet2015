import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class IHM extends JPanel {
	/**
*
*/
	private static final long serialVersionUID = 1L;
	private Aeroport aeroport = new Aeroport();
	JLayeredPane visualisation = new JLayeredPane();
	Carte carte = new Carte();
	CarteVols vols = new CarteVols();
	JPanel pVols = new JPanel();
	JFrame frame = new JFrame("");
	JPanel panPrincipal = new JPanel();
	JPanel panDroite = new JPanel();
	JButton bChargerAeroport = new JButton("Charger fichier aéroport");
	JButton bChargerTrafic = new JButton("Charger fichier trafic");
	WindowAdapter wa = new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	};

	public IHM() {
		// carte
		System.out.println(carte.getCentre().getX() + " "
				+ carte.getCentre().getY());
		// définition des tailles des panels
		carte.setPreferredSize(new Dimension(900, 600));
		vols.setPreferredSize(new Dimension(900, 600));
		
		visualisation.setPreferredSize(new Dimension(900, 600));
		visualisation.add(vols, 0); // layer la plus haute
		visualisation.add(carte, 100); // layer en dessous
		carte.setBounds(0, 0, 900, 600); // obligatoire sinon le layeredPane n'affiche rien
		vols.setBounds(0, 0, 900, 600);
		visualisation.setOpaque(false);	// non opaque
		visualisation.setVisible(true);
		panPrincipal.add(visualisation); // ajout dans le panel principal

		carte.addMouseWheelListener(new ListenerWheel()); // déctection de la wheel de la souris
		
		frame.setLocation(100, 200); // placement de la fenetre sur l'écran, par
										// rapport au coin en haut à gauche
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fermeture par
																// appui sur la
																// croix
		panPrincipal.setLayout(new GridLayout(1, 2)); // utilisation d'un panel
														// avec un layout en
														// grille de 1 lignes et
														// 2 colonnes
		panDroite.setLayout(new GridLayout(5, 2)); // utilisation d'un panel
													// avec un layout en grille
													// de 5 lignes et 2 colonnes
		frame.addWindowListener(wa);
		frame.getContentPane().add(panPrincipal);
		panPrincipal.add(panDroite);
		panDroite.add(bChargerAeroport);
		panDroite.add(bChargerTrafic);
		bChargerAeroport.addActionListener(new ChargerAeroport());
		frame.pack();
		frame.setVisible(true);
	}

	class ChargerAeroport implements ActionListener {
		public void actionPerformed(ActionEvent charger) {
			String fileName;
			// Exemple numéro 1
			// Boîte de sélection de fichier à partir du répertoire
			// "home" de l'utilisateur
			// création de la boîte de dialogue
			JFileChooser dialogue = new JFileChooser();
			// affichage
			dialogue.showOpenDialog(null);
			// récupération du fichier sélectionné
			System.out
					.println("Fichier choisi : " + dialogue.getSelectedFile());
			if (dialogue.getSelectedFile() != null) {
				aeroport.chargerTexte(dialogue.getSelectedFile().getName());
				for (int i = 0; i < aeroport.getListeRunway().size(); i++) {
					for (int j = 0; j < aeroport.getListeRunway().get(i)
							.getListePoint().size(); j++) {
						for (int k = 0; k < aeroport.getListePoint().size(); k++) {
							if (aeroport
									.getListeRunway()
									.get(i)
									.getListePoint()
									.get(j)
									.getNom()
									.equals(aeroport.getListePoint().get(k)
											.getNom())) {
								aeroport.getListeRunway()
										.get(i)
										.getListePoint()
										.get(j)
										.setCoordonnees(
												aeroport.getListePoint().get(k)
														.getCoordonnees());
							}
						}
					}
				}
				carte.setAeroport(aeroport);
				carte.setMin(aeroport.getMin());
				carte.setMax(aeroport.getMax());
				System.out.println(aeroport.getMin().getX() + " "
						+ aeroport.getMin().getY());
				System.out.println(aeroport.getMax().getX() + " "
						+ aeroport.getMax().getY());
				carte.calculCentre();
				vols.draw();
			}
		}
	}

	class ListenerWheel implements MouseWheelListener {
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() > 0) {
				carte.setEchelleX(carte.getEchelleX() * 2);
				carte.setEchelleY(carte.getEchelleY() * 2);
			}
			if (e.getWheelRotation() < 0) {
				carte.setEchelleX(carte.getEchelleX() / 2);
				carte.setEchelleY(carte.getEchelleY() / 2);
			}
			System.out.println("mousewheel");
			vols.draw();
		}
	}

	public static void main(String[] args) {
		IHM ihm = new IHM();
	}
}