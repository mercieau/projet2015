import java.awt.Color;
import java.awt.Graphics;


abstract public class ElementGraphique {
	
	private Coord coordonnees = new Coord(0,0);
	private Color couleur;
	private int taille;
	
	public ElementGraphique()
	{
		this.coordonnees = new Coord(0,0);
		this.couleur = Color.BLACK;
		this.taille = 0;
	}
	
	public ElementGraphique(Coord centre, Coord coord, Color c, int t, Graphics g, double echelleX, double echelleY)
	{
		this.coordonnees.setX((int) (((centre.getX()+coord.getX())*echelleX)));
		this.coordonnees.setY((int) (((centre.getY()+coord.getY())*echelleY)));
		this.couleur = c;
		this.taille = t;
		tracer(g);
	}
	
	abstract void tracer(Graphics g);
	
	void setCoordonnees(Coord c) 
	{
		this.coordonnees = c;
	}
	
	Coord getCoordonnees() 
	{
		return coordonnees;
	}
	
	void setCouleur(Color c) 
	{
		this.couleur = c;
	}
	
	Color getCouleur() 
	{
		return couleur;
	}
	
	void setTaille(int t) 
	{
		this.taille = t;
	}
	
	int getTaille() 
	{
		return taille;
	}
}
