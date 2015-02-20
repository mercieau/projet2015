import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

/** classe CarteVols
 * herite de la classe Carte
 * carte du trafic
 * @author Aurelie
 * @see Carte
 */
public class CarteVols extends Carte {
	
	private static final long serialVersionUID = 1L;
	/** secondes ecoulees depuis le debut de la journee */
	private int secondes = 0;
	/** variable utilisee pour afficher le trajet d'un avion selectionne un certain temps */
	private int t = 0;
	/** variable utilisee pour selectionner un avion sur la carte */
	private int rayon = 10;
	
	

	/** getter de rayon
	 * @return rayon
	 */
	public int getRayon() {
		return rayon;
	}

	/** setter de rayon
	 * @param rayon
	 */
	public void setRayon(int rayon) {
		this.rayon = rayon;
	}

	/** getter de secondes
	 * 
	 * @return secondes
	 */
	public int getSecondes() {
		return secondes;
	}

	/** setter de secondes
	 * 
	 * @param secondes
	 */
	public void setSecondes(int secondes) {
		this.secondes = secondes;
	}

	/** methode appelee par repaint() 
	 * met a jour les coordonnees des avions en fonction de l'echelle et du centre du repere
	 * affiche les avions a un instant t defini par le nombre de secondes ecoulees depuis le debut de la journee
	 * affiche les trajets des avions selectionnes
	 */
	protected void paintComponent(Graphics g) {
		/** effacement de la zone graphique */
		super.paintComponent(g);
		if (aeroport == null) {
			return;
		}
		
		/** mise a jour des coordonnees par rapport a la vue et a l'echelle */
		Coord coordonnees = new Coord(0, 0);
		int n = 0;
		int nb = 0;		
		g.setColor(Color.MAGENTA);
		for (int i = 0;i<aeroport.getListeVol().size(); i++) {
			/** pour chaque vol, si le vol est selectionne, dessin de son parcours jusqu'au temps t defini par secondes */
			if (aeroport.getListeVol().get(i).isSelection()) {
				t++;
				if (t>20) {
					/** affichage pendant un certain nombre de repaint */
					aeroport.getListeVol().get(i).setSelection(false);
					t=0;
				}
				nb = aeroport.getListeVol().get(i).getTemps();
				for (int m=0;m<aeroport.getListeVol().get(i).getCoordonnees().size()-1;m++) {
					g.drawLine((int) ((aeroport.getListeVol().get(i).getCoordonnees().get(m).getX()-centre.getX())*echelleX),(int) ((aeroport.getListeVol().get(i).getCoordonnees().get(m).getY()-centre.getY())*echelleY), (int) ((aeroport.getListeVol().get(i).getCoordonnees().get(m+1).getX()-centre.getX())*echelleX),(int) ((aeroport.getListeVol().get(i).getCoordonnees().get(m+1).getY()-centre.getY())*echelleY));
					nb +=5;
					if (nb>=secondes) break;
				}
			}
			/** mise a jour des coordonnees */
			for (int j = 0; j < aeroport.getListeVol().get(i).getCoordonnees()
					.size(); j++) {
					coordonnees
							.setX((int) (((aeroport.getListeVol().get(i)
									.getCoordonnees().get(j).getX() - centre
									.getX()) * echelleX)));
					coordonnees
							.setY((int) (((aeroport.getListeVol().get(i)
									.getCoordonnees().get(j).getY() - centre
									.getY()) * echelleY)));
					aeroport.getListeAvion().get(n).setxPos(coordonnees.getX());
					aeroport.getListeAvion().get(n).setyPos(coordonnees.getY());
					n++;
			}
		}
		/** dessin des avions presents au temps defini par secondes */
		for (int l=0;l<aeroport.getListeAvion().size();l++) aeroport.getListeAvion().get(l).setAffiche(false);
		for (int k=0;k<aeroport.getListePositions().get(secondes/5).size();k++) {
			aeroport.getListePositions().get(secondes/5).get(k).getAvion().setAffiche(true);
			if (aeroport.getListePositions().get(secondes/5).get(k).getAvion().isCollision()) g.setColor(Color.RED);
			else {
				if (aeroport.getListePositions().get(secondes/5).get(k).getAvion().getTypeVol().equals("DEP")) g.setColor(Color.YELLOW);
				else g.setColor(Color.CYAN);
				if (aeroport.getListePositions().get(secondes/5).get(k).getVol().isSelection()) g.setColor(new Color(52,55,158));
			}
			g.fillOval(((int)(aeroport.getListePositions().get(secondes/5).get(k).getAvion().getxPos()))-2,(int)(aeroport.getListePositions().get(secondes/5).get(k).getAvion().getyPos())-2,6,6); 
		}  
		
		
	}
	
	/** methode appelee lors d'un clic sur un avion
	 * analyse si un avion est dans le rayon du clic, retrouve le vol concerne
	 * @param x		abscisse de la position du clic
	 * @param y		ordonnee de la position du clic
	 * @return		Vol clique
	 */
	public Vol getClicked(int x, int y) {
		/** pour chaque vol, deselection (selection=false) */
		for (int k=0;k<aeroport.getListeVol().size();k++) aeroport.getListeVol().get(k).setSelection(false);
		/** pour chaque position d'avion, comparaison de sa date avec la date actuelle pour rechercher parmi les avions affiches */
		for (int i=0;i<aeroport.getListeAvion().size();i++) {
			if (aeroport.getListeAvion().get(i).getTime() == secondes) {
				if ((aeroport.getListeAvion().get(i).getxPos()+2 - x < rayon)
						&& (aeroport.getListeAvion().get(i).getxPos()+2 - x > (-rayon))
						&& (((aeroport.getListeAvion().get(i).getyPos()+2 - y < rayon)) && ((aeroport.getListeAvion()
								.get(i).getyPos()+2 - y > (-rayon))))) {
					for (int j = 0; j < aeroport.getListeVol().size(); j++) {
						/** recuperation du vol a retrouver */
						if (aeroport.getListeVol().get(j).getIdVol()
								.equals(aeroport.getListeAvion().get(i).getNomVol())) {
							aeroport.getListeVol().get(j).setSelection(true);
							return aeroport.getListeVol().get(j);
						} 
					}
				}
			}
		}
		return null;
	}

	/** methode d'ajout des positions d'avion sur la journee entiere
	 * 
	 */
	public void addAvions() {
		int n = 0;
		int k = 0;
		while (k <= (60 * 60 * 24)) {
			aeroport.getListePositions().add(new ArrayList<Position>());
			k+=5;
		}
		
		Coord coordonnees = new Coord(0, 0);
		for (int i = 0; i<aeroport.getListeVol().size(); i++) {
			for (int j = 0; j < aeroport.getListeVol().get(i).getCoordonnees()
					.size(); j++) {
					coordonnees.setX((int) (((aeroport.getListeVol().get(i)
									.getCoordonnees().get(j).getX() - centre
									.getX()) * echelleX)));
					coordonnees.setY((int) (((aeroport.getListeVol().get(i)
									.getCoordonnees().get(j).getY() - centre
									.getY()) * echelleY)));
					aeroport.getListeAvion().get(n).setCoordonnees(aeroport.getListeVol().get(i)
									.getCoordonnees().get(j));
					n++;
			}
		}
	}

	/** constructeur de CarteVols sans parametre
	 * 
	 */
	public CarteVols() {

	}
}