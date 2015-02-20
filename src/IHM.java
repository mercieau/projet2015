import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/** classe IHM
 * affiche l'IHM de l'application
 * @author Natacha - Aurelie
 *
 */
public class IHM extends JFrame
{
	private static final long serialVersionUID = 1L;
	/** hauteur de l'ecran en pixels */
	private int height;
	private int hauteur;
	/** largeur de l'ecran en pixels */
	private int width;
	private int largeur;
	/** coordonnees d'avant le drag */
	private Coord oldCoord = new Coord(0,0);
	/** coordonnees d'apres le drag */
	private Coord newCoord = new Coord(0,0);
	/** aeroport contenant les informations extraites des fichiers */
	private Aeroport aeroport = new Aeroport();
	/** LayeredPane pour superposer les deux cartes */
	private JLayeredPane visualisation = new JLayeredPane();
	/** carte de l'aeroport */
	private CarteAeroport carte = new CarteAeroport();
	/** carte des vols */
	private CarteVols vols = new CarteVols();
	/** player pour gerer le temps dans le rejeu */
	private LecteurVideo lecteur = new LecteurVideo();
	/** tableau utilise pour mettre a jour la liste des vols */
	private String []listData;


	private JFrame frame = new JFrame("Surveillance Aeroport");
	
	/************************* Declarations panels ***************************/
	
	JPanel panel1 = new JPanel();
	JPanel panelMap = new JPanel();
	JPanel panelLeft = new JPanel();
	JPanel panelRight = new JPanel();
	JPanel panelPlayer = new JPanel();
	JPanel panelVol = new JPanel();
	JPanel panelInfosVol = new JPanel();
	JPanel panelReglages = new JPanel();
	JPanel panelFlightList = new JPanel();
	
	JScrollPane panelscrollFlightList;
	JList<String> list;
	
	JPanel panelRapport = new JPanel();
	JPanel panelTools = new JPanel();
	
	//JScrollPane listeAvion = new JScrollPane();
	
	/************************* Declarations menu ***************************/
	JMenuBar menuBar = new JMenuBar();	
	
	// Menus
	JMenu menuFile = new JMenu("Fichier");
	JMenu menuEdit = new JMenu("Edition");
	JMenu menuLecture = new JMenu("Lecture");
	JMenu menuHelp = new JMenu("Aide");
	
	// Sous-menus
	JMenu menuLoad = new JMenu("Charger");
	JMenuItem itemOpen = new JMenuItem("Ouvrir..."); //permet de choisir l'aeroport a afficher
	JMenuItem itemExit = new JMenuItem("Quitter");
	JMenuItem itemPref = new JMenuItem("Preferences");
	JMenuItem itemSave = new JMenuItem("Sauvegarder");
	JMenuItem itemPasLecture = new JMenuItem("Pas de lecture");
	JMenuItem itemHelp = new JMenuItem("Aide");
	JMenuItem itemAbout = new JMenuItem("A propos");
	
	JCheckBoxMenuItem fileAeroport = new JCheckBoxMenuItem("Fichier aeroport");
	JCheckBoxMenuItem fileTrafic = new JCheckBoxMenuItem("Fichier trafic");
	
	JLabel labelRapport = new JLabel("Rapport");
	JLabel labelTools = new JLabel("Reglages");
	JLabel labelFlightList = new JLabel("Vols actuels", SwingConstants.CENTER);
	JLabel test = new JLabel("Test");
	
	JLabel labelVol = new JLabel("Details du vol", SwingConstants.CENTER);
	JLabel lidVol = new JLabel("Identifiant du vol", SwingConstants.CENTER);
	JLabel ltypeVol = new JLabel("Type du vol", SwingConstants.CENTER);
	JLabel lheureVol = new JLabel("Heure du vol", SwingConstants.CENTER);
	JLabel lcatVol = new JLabel("Categorie du vol", SwingConstants.CENTER);
	JLabel lqfuVol = new JLabel("QFU du vol", SwingConstants.CENTER);
	JLabel idVol = new JLabel("", SwingConstants.CENTER);
	JLabel typeVol = new JLabel("", SwingConstants.CENTER);
	JLabel heureVol = new JLabel("", SwingConstants.CENTER);
	JLabel catVol = new JLabel("", SwingConstants.CENTER);
	JLabel qfuVol = new JLabel("", SwingConstants.CENTER);
	JLabel labelEtat = new JLabel("Etat: "); // depart ou arrivee
	JLabel labelImmat = new JLabel("Immatriculation: ");
	JLabel labelCat = new JLabel("Categorie: ");
	JLabel labelQFU = new JLabel("QFU: ");
	JLabel labelTime = new JLabel("Heure: "); //heure d'arrivee/depart
	
	// Panel Reglages
	JLabel labelPasLecture = new JLabel("Pas de lecture (multiple de 5s) : ");
	JLabel labelDistanceCollision = new JLabel("Distance de collision (m) : ");
	
	JTextField tfPasLecture = new JTextField("",4);
	JTextField tfDistanceCollision = new JTextField("",4);
	
	JTextArea rapport = new JTextArea();
	
	
	Font font = new Font("Arial", Font.BOLD,13);
	
	
	WindowAdapter wa = new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	};
	
	
	
	/** constructeur de la classe sans parametre 
	 * realise le dimensionnement de chaque element de la fenetre
	 * positionne les cartes avec leur niveau
	 * 
	 */
	public IHM() 
	{
		
		// Recuperation de la taille de l'ecran
		ScreenSize(); 
		System.out.println("hauteur : " + height + " largeur: " + width); // affichage de la taille de l'ecran
		
		
		/****************************** partie carte *************************************/
		int largeurCartes = (int) (width*0.6667);
		int hauteurCartes = (int) (((width*0.6667)/800.0)*600.0);
		carte.setPreferredSize(new Dimension(largeurCartes, hauteurCartes));
		vols.setPreferredSize(new Dimension(largeurCartes, hauteurCartes));

		visualisation.setPreferredSize(new Dimension(largeurCartes, hauteurCartes));
		
		
		visualisation.add(vols, 0); // layer la plus haute
		visualisation.add(carte, 100); // layer en dessous
		carte.setBounds(0, 0, largeurCartes, hauteurCartes); // obligatoire sinon le layeredPane
											// n'affiche rien
		vols.setBounds(0, 0, largeurCartes, hauteurCartes);
		carte.setHauteur(hauteurCartes);
		vols.setHauteur(hauteurCartes);
		carte.setLargeur(largeurCartes);
		vols.setLargeur(largeurCartes);
		vols.setOpaque(false);
		carte.setOpaque(true);
		visualisation.setOpaque(false); // non opaque
		visualisation.setVisible(true);
		lecteur.addTimeListener(new UpdateTime());
		lecteur.setPreferredSize(new Dimension(width/6,height/10));
		visualisation.addMouseWheelListener(new ListenerWheel()); // dectection de la wheel de la souris
		visualisation.addMouseListener(new Mouse());

		
		/**************************** Panel principal (panel1) ***************************/
		
		// Ajout d'un GridBagLayout au panels
		panel1.setLayout(new GridBagLayout()); 
		
		GridBagConstraints gc1 = new GridBagConstraints(); // Le gridBagConstraints va dÃ©finir la position et la taille des Ã©lÃ©ments
		gc1.fill = GridBagConstraints.BOTH ; // permet d'occuper tout l'espace disponible horizontalement et verticalement
		
				
		
		/**************************** Partie gauche ***************************/
		
		//*************************** Panel Carte (panelMap) ******************//
		
		gc1.weightx = 6;
		gc1.weighty = 1; 
		gc1.gridx=0; // 0ème colonne
		gc1.gridy=0; //0ème ligne
		gc1.gridheight=3;
		gc1.gridwidth=1;
		
		panel1.add(panelMap, gc1);
		
/* !!!! Ajouter la carte ici !!!! */
		panelMap.add(visualisation);

		
		
		//******************** Barre de lecture (panelPlayer) ******************//
		
		gc1.weightx = 6; 
		gc1.weighty = 1; 
		gc1.gridx=0;
		gc1.gridy=1; 
		gc1.gridheight=1;
		gc1.gridwidth=1;
		panel1.add(lecteur);
		panel1.add(panelPlayer, gc1);
		
		
		/**************************** Partie droite ***************************/
		
		//*************************** Label liste des vols ******************//
		
		labelFlightList.setFont(font);
		
		//panelRight.add(labelFlightList, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		/*
		gc1.weightx = 1; 
		gc1.weighty = 1; 
		gc1.gridx=1; //1ère colonne
		gc1.gridy=0; //0ème ligne
		gc1.gridheight=1;
		gc1.gridwidth=1;
		panel1.add(labelFlightList, gc1);
		*/
		
		panel1.add(labelFlightList, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		//*************************** Panel liste des vols ******************//
		
		// ScrollPane
		String categories[] = {};
		list = new JList<String>(categories);
		list.addListSelectionListener(new ListenerList());
		panelscrollFlightList= new JScrollPane(list);
		getContentPane().add(panelscrollFlightList, BorderLayout.CENTER);
		
		panelscrollFlightList.setBorder(BorderFactory.createLoweredBevelBorder());
		
		gc1.weightx = 1;
		gc1.weighty = 5;
		gc1.gridx=1; //1ère colonne
		gc1.gridy=1; //1ère ligne
		gc1.gridheight=1;
		gc1.gridwidth=1;
		panel1.add(panelscrollFlightList, gc1);
		
		//*************************** Panel Vol *****************************//
		
		panelVol.setLayout(new GridBagLayout());
		GridBagConstraints gcVol = new GridBagConstraints(); // Le gridBagConstraints va definir la position et la taille des elements
		gcVol.fill = GridBagConstraints.BOTH ;
		
		gc1.weightx = 1;
		gc1.weighty = 1;
		gc1.gridx=2; //2eme colonne
		gc1.gridy=0; //0eme ligne
		gc1.gridheight=2;
		gc1.gridwidth=1;
		gc1.gridwidth = GridBagConstraints.REMAINDER;
		panel1.add(panelVol, gc1);
		
		gcVol.weightx = 1;
		gcVol.weighty = 1;
		gcVol.gridx=0; //0eme colonne
		gcVol.gridy=0; //0eme ligne
		gcVol.gridheight=2;
		gcVol.gridwidth=1;
		panelInfosVol.setLayout(new GridLayout(6, 2));
		panelInfosVol.add(labelVol);
		panelInfosVol.add(new JLabel());
		panelInfosVol.add(lidVol);
		panelInfosVol.add(idVol);
		panelInfosVol.add(ltypeVol);
		panelInfosVol.add(typeVol);
		panelInfosVol.add(lcatVol);
		panelInfosVol.add(catVol);
		panelInfosVol.add(lheureVol);
		panelInfosVol.add(heureVol);
		panelInfosVol.add(lqfuVol);
		panelInfosVol.add(qfuVol);
		panelVol.add(panelInfosVol, gcVol);
		panelVol.setBorder(BorderFactory.createLoweredBevelBorder());
		
		labelVol.setFont(font);
		
		//*************************** Panel Rapport **************************//
		
		gc1.weightx = 1;
		gc1.weighty = 1;
		gc1.gridx=1; //1ere colonne
		gc1.gridy=2; //2eme ligne
		gc1.gridwidth=2; // le panel prendra 2 cases au lieu d'1
		gc1.gridheight=1;
		gc1.gridwidth = GridBagConstraints.REMAINDER;
		
		panel1.add(panelRapport, gc1);
		//panelRapport.add(labelRapport, gc1);
		panelRapport.setLayout(new GridLayout(1, 1));
		//panelRapport.add(labelRapport);
		panelRapport.add(rapport);
		rapport.setEditable(false);
		panelRapport.setBorder(BorderFactory.createLoweredBevelBorder());

		labelRapport.setFont(font);
		
		//*************************** Panel Reglages ***************************//
		
		panelTools.setLayout(new GridBagLayout());
		GridBagConstraints gcTools = new GridBagConstraints(); // Le gridBagConstraints va definir la position et la taille des elements
		gcTools.fill = GridBagConstraints.BOTH ;
		
		gc1.weightx = 1;
		gc1.weighty = 1; 
		gc1.gridx=1; //1ere colonne
		gc1.gridy=3; //3eme ligne
		gc1.gridwidth=2; // le panel prendra 2 cases au lieu d'1
		gc1.gridheight=1;
		panel1.add(panelTools, gc1);
		
		gcTools.weightx = 1;
		gcTools.weighty = 1;
		gcTools.gridx=0; //0è colonne
		gcTools.gridy=0; //0è ligne
		gcTools.gridwidth=2;
		gcTools.fill = GridBagConstraints.REMAINDER ;
		gcTools.insets = new Insets(0,0,3,0);
		panelTools.add(labelTools, gcTools);
		labelTools.setFont(font);
		
		gcTools.gridx=0; 
		gcTools.gridy=1; 
		panelReglages.setLayout(new GridLayout(2, 2));
		panelReglages.add(labelPasLecture);
		panelReglages.add(tfPasLecture);
		panelReglages.add(labelDistanceCollision);
		panelReglages.add(tfDistanceCollision);
		panelTools.add(panelReglages);
		
		tfPasLecture.setText(Integer.toString(lecteur.getPasHeure()));
		tfDistanceCollision.setText(Integer.toString(aeroport.getDistanceCollision()));
		tfPasLecture.addActionListener(new ChangePas());
		tfDistanceCollision.addActionListener(new ChangeDistance());
		
		panelTools.setBorder(BorderFactory.createLoweredBevelBorder());
		
		
		
		//*********************************************************************//
		
		frame.add(panel1);
		frame.addWindowListener(wa);
		

		menuLoad.addActionListener(new ChargerAeroport());
		
		/**************************** MENU ***************************/
		frame.setJMenuBar(menuBar);
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuLecture);
		menuBar.add(menuHelp);
		
		// Menu Fichier
		menuFile.add(itemOpen);
		menuFile.add(menuLoad);
		menuLoad.add(fileAeroport);
		menuLoad.add(fileTrafic);
		menuFile.add(itemSave);
		menuFile.add(itemExit);
		fileAeroport.addActionListener(new ChargerAeroport());
		fileTrafic.addActionListener(new ChargerTrafic());
		
		
		// Menu Lecture
		menuLecture.add(itemPasLecture);
		
		// Menu Aide
		menuHelp.add(itemHelp);
		menuHelp.add(itemAbout);
		
		itemExit.setActionCommand("exit");
		
		menuEdit.add(itemPref);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fermeture par appui sur la croix		
		frame.setSize(width, height); // la fenetre se met en plein ecran selon la taille de l'ecran
		frame.setVisible(true); // affichage de la fenÃªtre
	}
	
	
	/** methode de translation sur les cartes
	 * applique la mathode transformAffine sur carte et vols
	 * applique un repaint sur visualisation (qui appelle le repaint sur chaque element de ce JLayeredPane)
	 * @param x		abscisse du vecteur
	 * @param y		ordonnee du vecteur
	 */
	public void translation(int x, int y) {
		new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						carte.transformAffine((int)(x*0.5*(1/carte.getEchelleX())), (int)(y*0.5*(1/carte.getEchelleX())));
						vols.transformAffine((int)(x*0.5*(1/vols.getEchelleX())), (int)(y*0.5*(1/vols.getEchelleX())));
						visualisation.repaint();
					}
				});
			}
		}.start();

	}
	
	/** classe UpdateTime
	 * implemente TimeListener
	 * utilisee pour realiser des traitements lorsque l'heure de lecteur est modifiee
	 *
	 */
	class UpdateTime implements TimeListener {

		/** surcharge de la methode timeUpdated
		 * @param time	heure du lecteur
		 */
		public void timeUpdated(int time) {
			vols.setSecondes(time);
			/** applique un repaint seulement sur la carte des vols */
			vols.repaint();
			new Thread() {
				public void run() {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							String s = "";
							int n = 0;
							int m = 0;
							for (int j = 0; j < 50; j++) {
								listData[j] = "";	
							}
							for (int i = 0; i < aeroport.getListeVol().size(); i++) {
								if ((aeroport.getListeVol().get(i).getTemps() < time)
										&& ((aeroport.getListeVol().get(i).getTemps() + aeroport
												.getListeVol().get(i).getCoordonnees()
												.size() * 5) > time)) {
									/** si l'avion est affiche a la date time, son identifiant est ajoute au tableau listData */
									listData[n] = aeroport.getListeVol().get(i)
											.getIdVol();
									n++;
								}
							}
							/** construction du rapport a afficher a l'ecran en fonction des collisions a venir dans les minutes suivantes */
							for(int k=0;k<aeroport.getListeCollision().size();k++) {
								if ((aeroport.getListeCollision().get(k).getTime()>=time) && (aeroport.getListeCollision().get(k).getTime()<=time+120)) {
									/** si la collision a lieu dans les 120 prochaines secondes */
									s += aeroport.getListeCollision().get(k).toString() + "\n";
									m++;
								}
							}
							list.setListData(listData);
							s = ""+m+" collisions \n" + s;
							s += "\n"+n+" avions presents";
							rapport.setText(s);
						}
					});
				}
			}.start();
		}
		
	}

	/** classe ChangePas
	 * implemente ActionListener
	 * 
	 */
	class ChangePas implements ActionListener {
		/** methode appelee a chaque fois que tfPasLecture est modifie
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			if (Integer.parseInt(tfPasLecture.getText())%5==0) lecteur.setPasHeure(Integer.parseInt(tfPasLecture.getText()));
		}
	}
	
	/** classe ChangeDistance
	 * implemente ActionListener
	 * 
	 */
	class ChangeDistance implements ActionListener {
		/** methode appelee a chaque fois que tfSetDistanceCollision est modifie
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			aeroport.setDistanceCollision(Integer.parseInt(tfDistanceCollision.getText()));
		}
	}

	/** classe ChargerAeroport
	 * implemente ActionListener
	 *
	 *
	 */
	class ChargerAeroport implements ActionListener {
		/** methode appelee lors d'une action sur un menu
		 * 
		 */
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
								aeroport.chargerAeroport(dialogue
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
								carte.transformAffine(aeroport.getMin().getX(), aeroport.getMin().getY());
								frame.setTitle(aeroport.getName());
								visualisation.repaint();
							}
						}
					});
				}
			}.start();

		}
	}

	/** classe ChargerTrafic
	 * implemente ActionListener
	 *
	 */
	class ChargerTrafic implements ActionListener {
		/** methode appelee lors d'une action sur un menu
		 * 
		 */
		public void actionPerformed(ActionEvent charger) {
			new Thread() {
				public void run() {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								String fileName;
								JFileChooser dialogue = new JFileChooser();
								dialogue.showOpenDialog(null);
								
							if (dialogue.getSelectedFile() != null) {
								System.out.println("Fichier choisi : "
										+ dialogue.getSelectedFile());
								aeroport.chargerTrafic(dialogue
										.getSelectedFile().getName());
								listData = new String[aeroport.getListeVol()
										.size()];
								for (int i = 0; i < aeroport.getListeVol()
										.size(); i++) {
									for (int k = 0; k < aeroport
											.getListePoint().size(); k++) {
										if (aeroport.getListeVol().get(i).getPointDepart().getNom().equals(aeroport.getListePoint().get(k).getNom())) {
											aeroport.getListeVol().get(i).getPointDepart().setCoordonnees(aeroport.getListePoint().get(k).getCoordonnees());
										}
									}
								}

								vols.setAeroport(aeroport);
								vols.setMin(aeroport.getMin());
								vols.setMax(aeroport.getMax());
								vols.transformAffine(aeroport.getMin().getX(),aeroport.getMin().getY());
								visualisation.repaint();
							}
							}
						});
				}
			}.start();
		}
	}
	
	/** classe ListenerList
	 * implemente ListSelectionListener
	 * utilisee concernant les JList
	 *
	 */
	class ListenerList implements ListSelectionListener {
		/** methode appelee lors de la selection d'un element d'une JList
		 * 
		 */
		public void valueChanged(ListSelectionEvent arg0) {
			int i = 0;
			boolean find = false;
			for (i=0;i<aeroport.getListeVol().size();i++) {
				aeroport.getListeVol().get(i).setSelection(false);
			}
			for (i=0;i<aeroport.getListeVol().size();i++) {
				if (aeroport.getListeVol().get(i).getIdVol().equals(listData[arg0.getFirstIndex()])) {
					find = true;
					break;
				}
			}
			if (find) {
				afficherDetailsVol(aeroport.getListeVol().get(i));
				aeroport.getListeVol().get(i).setSelection(true);
			}	
		}
	}
	
	/** methode d'affichage des details d'un vol
	 * 
	 * @param v		vol dont les informations doivent etre affichees
	 */
	public void afficherDetailsVol(Vol v) {
		idVol.setText(v.getIdVol());
		catVol.setText(v.getCatAvion());
		heureVol.setText(Heure.convertSecondes(v.getTemps()));
		typeVol.setText(v.getTypeVol());
		qfuVol.setText(v.getQFU());
	}

	/** classe ListenerWheel
	 * implemente MouseWheelListener
	 * utilisee concernant la wheel de la souris
	 */
	class ListenerWheel implements MouseWheelListener {
		/** methode appelee lors d'un mouvement de la wheel de la souris
		 * 
		 */
		public void mouseWheelMoved(MouseWheelEvent e) {
			new Thread() {
				public void run() {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							if (e.getWheelRotation() < 0) {
								carte.zoom(new Coord(e.getX(), e.getY()), true);
								vols.zoom(new Coord(e.getX(), e.getY()), true);
							}
							if (e.getWheelRotation() > 0) {
								carte.zoom(new Coord(e.getX(), e.getY()), false);
								vols.zoom(new Coord(e.getX(), e.getY()), false);

							}
							visualisation.repaint();
						}
					});
				}
			}.start();
		}
	}

	/** classe Mouse 
	 * implemente MouseListener
	 * utilisee concernant les actions sur les clics de la souris
	 *
	 */
	class Mouse implements MouseListener {
		/** methode appelee lors d'un clic de la souris
		 * 
		 */
		public void mouseClicked(MouseEvent e) {
			Vol vol;
			if ((vol = vols.getClicked(e.getX(), e.getY())) != null) {
				afficherDetailsVol(vol);
			}
		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		/** methode appelee lors de l'appui prolonge sur le clic de la souris
		 * 
		 */
		public void mousePressed(MouseEvent e) {
			oldCoord.setX(e.getX());
			oldCoord.setY(e.getY());
		}

		/** methode appelee lors du relachement du clic de la souris
		 * 
		 */
		public void mouseReleased(MouseEvent e) {
			newCoord.setX(e.getX());
			newCoord.setY(e.getY());
			translation(1 * -(newCoord.getX() - oldCoord.getX()), 1 * -(newCoord.getY() - oldCoord.getY()));
		}

	}
	
	public void actionPerformed(ActionEvent ev) 
	{
		AbstractButton bouton = (AbstractButton) ev.getSource();
		
		if(bouton.getActionCommand().equals("exit"))
		{
			System.exit(0);
		}
		
	}
	
	public void ScreenSize()
	{
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.height = (int)dimension.getHeight();
		this.width = (int)dimension.getWidth();
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				IHM ihm = new IHM();
			}
		});

	}
}