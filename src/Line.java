/**
 * @author Natacha
 * @version 1.0
 */

import java.util.ArrayList;

public class Line 
{
	private String name;
	private int speedlimit;
	private String category;
	private String direction;
	private ArrayList<Coord> coordonnees;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpeedlimit() {
		return speedlimit;
	}

	public void setSpeedlimit(int speedlimit) {
		this.speedlimit = speedlimit;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public ArrayList<Coord> getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(ArrayList<Coord> coordonnees) {
		this.coordonnees = coordonnees;
	}

	
	public Line(String name, int speedlimit, String category, String direction, ArrayList<Coord> listeCoord)
	{
		this.name = name;
		this.speedlimit = speedlimit;
		this.category = category;
		this.direction = direction;
		this.coordonnees = listeCoord;
	}
	
	/** Méthode pour différencier les Line qui sont des taxiways de celles qui ne le sont pas **/
	
	boolean getTaxiway()
	{
		if(name.equals("_"))
		{
			return false;
		}
		
	return true;
	}
	
	
	
	void afficheLine()
	{
		System.out.println("Line: " + name + " " + speedlimit + " " + category + " " + direction + " " + coordonnees.get(0).getX() + "," + coordonnees.get(0).getY());
	}
}
