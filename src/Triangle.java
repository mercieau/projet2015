import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class Triangle extends ElementGraphique {
	
	public Triangle(Coord centre, Coord coord, Color c, int t, Graphics g, double eX, double eY)
	{
		super (centre, coord, c, t, g, eX, eY);
	}
	
	void tracer(Graphics g) 
	{
		int xPoly[] = {getCoordonnees().getX(), getCoordonnees().getX()+(getTaille()/2), getCoordonnees().getX()-(getTaille()/2)};
        int yPoly[] = {getCoordonnees().getY()-(getTaille()/2), getCoordonnees().getY()+(getTaille()/2), getCoordonnees().getY()+(getTaille()/2)};


        Polygon triangle = new Polygon(xPoly, yPoly, xPoly.length);
		g.setColor(getCouleur());
		g.fillPolygon(triangle);

	}
	
}
