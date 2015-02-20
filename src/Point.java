/**
 * @author Natacha
 * @version 1.0
 */


import java.util.*;

/** classe Point
 * Point extrait du fichier de l'aeroport
 * @author Natacha
 *
 */
public class Point 
{
	/** nom du Point */
	private String nom;
	/** coordonnees du Point */
	private Coord coordonnees;
	/** type du Point */
	private int type;
	
	/** getter de nom
	 * 
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/** setter de nom
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** getter de coordonnees
	 * 
	 * @return coordonnees
	 */
	public Coord getCoordonnees() {
		return coordonnees;
	}

	/** setter de coordonnees
	 * 
	 * @param coordonnees
	 */
	public void setCoordonnees(Coord coordonnees) {
		this.coordonnees = coordonnees;
	}

	/** getter de type
	 * 
	 * @return type
	 */
	public int getType() {
		return type;
	}

	/** setter de type
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/** constructeur de la classe Point avec parametres
	 * 
	 * @param nom	nom du Point
	 * @param type	type
	 * @param c		coordonnees
	 */
	public Point(String nom, int type, Coord c)
	{
		this.nom = nom;
		this.coordonnees = c;
		this.type = type;
	}
	
	/** methode d'affichage des informations d'un Point
	 * 
	 */
	void affichePoint()
	{
		System.out.println("Point: " + nom + " " + type + " " + coordonnees.getX() + " " + coordonnees.getY());
	}
	
	
}
