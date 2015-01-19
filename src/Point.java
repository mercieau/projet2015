/**
 * @author Natacha
 * @version 1.0
 */


import java.util.*;

public class Point 
{
	private String nom;
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Coord getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coord coordonnees) {
		this.coordonnees = coordonnees;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private Coord coordonnees;
	private int type;
	
	public Point(String nom, int type, Coord c)
	{
		this.nom = nom;
		this.coordonnees = c;
		this.type = type;
	}
	
	void affichePoint()
	{
		System.out.println("Point: " + nom + " " + type + " " + coordonnees.getX() + " " + coordonnees.getY());
	}
	
	
}
