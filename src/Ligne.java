import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Ligne extends ElementGraphique {

	Coord coordonneesA = new Coord(0,0);
	Coord coordonneesB = new Coord(0,0);
	
	public Ligne(Coord centre, Coord coordA, Coord coordB, Color c, int t, Graphics g, double eX, double eY) {
		this.coordonneesA.setX((int) (((centre.getX() + coordA.getX()) * eX)));
		this.coordonneesA.setY((int) (((centre.getY() + coordA.getY()) * eY)));
		this.coordonneesB.setX((int) (((centre.getX() + coordB.getX()) * eX)));
		this.coordonneesB.setY((int) (((centre.getY() + coordB.getY()) * eY)));
		setCouleur(c);
		setTaille(t);
		tracer(g);

	}

	void tracer(Graphics g) {
		g.setColor(getCouleur());
		g.drawLine(coordonneesA.getX(), coordonneesA.getY(), coordonneesB.getX(), coordonneesB.getY());
	}

}
