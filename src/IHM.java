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
	Coord oldCoord = new Coord(0,0);
	Coord newCoord = new Coord(0,0);
	JButton bChargerAeroport = new JButton("Charger fichier a�roport");
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
		// d�finition des tailles des panels
		bChargerAeroport.setPreferredSize(new Dimension(100,50));
		bChargerTrafic.setPreferredSize(new Dimension(100,50));
		panDroite.setPreferredSize(new Dimension(100,600));
		bChargerAeroport.setMaximumSize(new Dimension(100,50));
		bChargerTrafic.setMaximumSize(new Dimension(100,50));
		panDroite.setMaximumSize(new Dimension(100,600));
		
		carte.setPreferredSize(new Dimension(900, 600));
		vols.setPreferredSize(new Dimension(900, 600));

		visualisation.setPreferredSize(new Dimension(900, 600));
		visualisation.add(vols, 0); // layer la plus haute
		visualisation.add(carte, 100); // layer en dessous
		carte.setBounds(0, 0, 900, 600); // obligatoire sinon le layeredPane
											// n'affiche rien
		vols.setBounds(0, 0, 900, 600);
		visualisation.setOpaque(false); // non opaque
		visualisation.setVisible(true);
		panPrincipal.add(visualisation); // ajout dans le panel principal

		carte.addMouseWheelListener(new ListenerWheel()); // d�ctection de la
															// wheel de la
															// souris
		
		carte.addMouseListener(new Mouse());

		frame.setLocation(100, 200); // placement de la fenetre sur l'�cran, par
										// rapport au coin en haut � gauche
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
	
	public void translation(int x, int y) {
		new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						System.out.println("translation de : x : "+x+" y : "+y);
						carte.setMin(new Coord(carte.getMin().getX() - (int)(x*carte.getEchelleX()), carte
								.getMin().getY() - (int)(y*carte.getEchelleY())));
						carte.setMax(new Coord(carte.getMax().getX() - (int)(x*carte.getEchelleX()), carte
								.getMax().getY() - (int)(y*carte.getEchelleY())));
						carte.getGraphics().translate((int)(-x*carte.getEchelleX()), (int)(-y*carte.getEchelleY()));
						carte.setCentre();
						carte.calculCentre();
						vols.draw();
					}
				});
			}
		}.start();

	}

	class ChargerAeroport implements ActionListener {
		public void actionPerformed(ActionEvent charger) {

			new Thread() {
				public void run() {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							String fileName;
							JFileChooser dialogue = new JFileChooser();
							dialogue.showOpenDialog(null);
							System.out.println("Fichier choisi : "
									+ dialogue.getSelectedFile());
							if (dialogue.getSelectedFile() != null) {
								aeroport.chargerTexte(dialogue
										.getSelectedFile().getName());
								for (int i = 0; i < aeroport.getListeRunway()
										.size(); i++) {
									for (int j = 0; j < aeroport
											.getListeRunway().get(i)
											.getListePoint().size(); j++) {
										for (int k = 0; k < aeroport
												.getListePoint().size(); k++) {
											if (aeroport
													.getListeRunway()
													.get(i)
													.getListePoint()
													.get(j)
													.getNom()
													.equals(aeroport
															.getListePoint()
															.get(k).getNom())) {
												aeroport.getListeRunway()
														.get(i)
														.getListePoint()
														.get(j)
														.setCoordonnees(
																aeroport.getListePoint()
																		.get(k)
																		.getCoordonnees());
											}
										}
									}
								}
								carte.setAeroport(aeroport);
								carte.setMin(aeroport.getMin());
								carte.setMax(aeroport.getMax());
								carte.setCentre();
								System.out.println(aeroport.getMin().getX()
										+ " " + aeroport.getMin().getY());
								System.out.println(aeroport.getMax().getX()
										+ " " + aeroport.getMax().getY());
								carte.calculCentre();
								vols.draw();
							}
						}
					});
				}
			}.start();

		}
	}

	class ListenerWheel implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			new Thread() {
				public void run() {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							if (e.getWheelRotation() > 0) {
								System.out.println("zoom : "+e.getX()+" "+e.getY());
								carte.zoom(new Coord(e.getX(),e.getY()),true);
							}
							if (e.getWheelRotation() < 0) {
								System.out.println("zoom : "+e.getX()+" "+e.getY());
								carte.zoom(new Coord(e.getX(),e.getY()),false);
								
							}
							System.out.println("mousewheel");
							vols.draw();
						}
					});
				}
			}.start();
		}
	}
	
	class Mouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			oldCoord.setX(e.getX());
			oldCoord.setY(e.getY());
			System.out.println("pressed");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			newCoord.setX(e.getX());
			newCoord.setY(e.getY());
			translation(5*(newCoord.getX()-oldCoord.getX()), 5*(newCoord.getY()-oldCoord.getY()));
			System.out.println("released");
		}
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				IHM ihm = new IHM();
			}
		});

	}
}