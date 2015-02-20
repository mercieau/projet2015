import java.awt.*;

import javax.swing.*;

/** classe Carte
 * herite de JPanel
 * contient les methodes et attributs communs aux cartes
 * @author Aurelie
 *
 */
public class Carte extends JPanel {
	
	private static final long serialVersionUID = 1L;
	/** coordonnees du minimum */
	protected Coord min = new Coord(0,0);
	/** coordonnees du maximum */
	protected Coord max = new Coord(0,0);
	/** coordonnees du centre */
	protected Coord centre = new Coord(0,0);
	/** coordonnees de l'origine */
	protected Coord origine = new Coord(0,0);
	/** echelle en abscisse */
	protected double echelleX = 1;
	/** echelle en ordonnee */
	protected double echelleY = 1;
	/** aeroport charge */
	protected Aeroport aeroport;
	/** hauteur attribuee a la carte */
	protected int hauteur = 600;
	/** largeur attribuee a la carte */
	protected int largeur = 800;
	
	/** getter de hauteur
	 * 
	 * @return hauteur
	 */
	public int getHauteur() {
		return hauteur;
	}

	/** setter de hauteur
	 * 
	 * @param hauteur
	 */
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	/** getter de largeur
	 * 
	 * @return largeur
	 */
	public int getLargeur() {
		return largeur;
	}

	/** setter de largeur
	 * 
	 * @param largeur
	 */
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	
	
	public double getEchelleX() {
		return echelleX;
	}

	public double getEchelleY() {
		return echelleY;
	}

	public void setEchelleX(double echelle) {
		this.echelleX = echelle;
	}
	
	public void setEchelleY(double echelle) {
		this.echelleY = echelle;
	}

	/** getter de aeroport
	 * 
	 * @return aeroport
	 */
	public Aeroport getAeroport() {
		return aeroport;
	}

	/** setter de aeroport
	 * 
	 * @param aeroport
	 */
	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}
	
	/** getter de centre
	 * 
	 * @return centre
	 */
	public Coord getCentre() {
		return centre;
	} 

	/** setter de centre
	 * 
	 * @param centre
	 */
	public void setCentre(Coord centre) {
		this.centre = centre;
	}

	/** getter de min
	 * 
	 * @return min
	 */
	public Coord getMin() {
		return min;
	}

	/** setter de min
	 * 
	 * @param min
	 */
	public void setMin(Coord min) {
		this.min = min;
	}

	/** getter de max
	 * 
	 * @return max
	 */
	public Coord getMax() {
		return max;
	}

	/** setter de max
	 * 
	 * @param max
	 */
	public void setMax(Coord max) {
		this.max = max;
	}
	
	/** getter de origine
	 * 
	 * @return origine
	 */
	public Coord getOrigine() {
		return origine;
	}

	/** setter de origine
	 * 
	 * @param origine
	 */
	public void setOrigine(Coord origine) {
		this.origine = origine;
	}

	/** methode de zoom
	 * 
	 * @param coord		coordonnees du curseur au moment du zoom
	 * @param plus		sens du zoom : true +, false -
	 */
	public void zoom(Coord coord, boolean plus) {
		if (plus) {
			if (echelleX<=6) {
				transformAffine((int)(coord.getX()), (int)(coord.getY()));
				echelleX *= 1.5;
				echelleY *= 1.5;
			}
		} else {
			if (echelleX>=0.0625) {
				transformAffine((int)(-coord.getX()), (int)(-coord.getY()));
				echelleX /= 1.5;
				echelleY /= 1.5;
			}
		}
	}
	
	/** methode de deplacement de l'origine du repere
	 * modification des coordonnnees du centre et de l'origine
	 * @param x		deplacement en abscisse
	 * @param y		deplacement en ordonnee
	 */
	public void transformAffine(int x, int y) {
		getGraphics().translate(x, y);
		origine.set(x+origine.getX(),y+origine.getY());
		centre.set((int)(origine.getX()+(largeur/2)*echelleX), (int)(origine.getY()+(hauteur/2)*echelleY));
	}
	
	/** constructeur de la classe sans parametre
	 * 
	 */
	public Carte() {
	}	
}
