/**
 * @author Natacha
 * @version 1.0
 */

import java.util.ArrayList;

/** classe Line
 * Line extraite du fichier de l'aeroport
 * @author Natacha
 *
 */
public class Line 
{
	/** nom de la Line */
	private String name;
	/** limite de vitesse de la Line */
	private int speedlimit;
	/** categorie de la Line */
	private String category;
	/** direction de la Line */
	private String direction;
	/** liste de coordonnees de la Line */
	private ArrayList<Coord> coordonnees;
	
	/** getter de name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/** setter de name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** getter de speedlimit
	 * 
	 * @return speedlimit
	 */
	public int getSpeedlimit() {
		return speedlimit;
	}

	/** setter de speedlimit
	 * 
	 * @param speedlimit
	 */
	public void setSpeedlimit(int speedlimit) {
		this.speedlimit = speedlimit;
	}

	/** getter de category
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

	/** setter de category
	 * 
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/** getter de direction
	 * 
	 * @return direction
	 */
	public String getDirection() {
		return direction;
	}

	/** setter de direction
	 * 
	 * @param direction
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/** getter de la liste de coordonnees 
	 * 
	 * @return coordonnees
	 */
	public ArrayList<Coord> getCoordonnees() {
		return coordonnees;
	}

	/** setter de coordonnees
	 * 
	 * @param coordonnees
	 */
	public void setCoordonnees(ArrayList<Coord> coordonnees) {
		this.coordonnees = coordonnees;
	}

	/** constructeur de  la classe Line avec parametre
	 * 
	 * @param name			nom de la Line
	 * @param speedlimit	speedlimit	
	 * @param category		categorie
	 * @param direction		direction
	 * @param listeCoord	liste de coordonnees
	 */
	public Line(String name, int speedlimit, String category, String direction, ArrayList<Coord> listeCoord)
	{
		this.name = name;
		this.speedlimit = speedlimit;
		this.category = category;
		this.direction = direction;
		this.coordonnees = listeCoord;
	}
	
	/** methode pour differencier les Line qui sont des taxiways de celles qui ne le sont pas
	 * 
	 */
	boolean getTaxiway()
	{
		if(name.equals("_"))
		{
			return false;
		}
	return true;
	}
	
	/** methode pour afficher les informations d'une Line
	 * 
	 */
	void afficheLine()
	{
		System.out.println("Line: " + name + " " + speedlimit + " " + category + " " + direction + " " + coordonnees.get(0).getX() + "," + coordonnees.get(0).getY());
	}
}