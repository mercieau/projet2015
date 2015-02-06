import java.awt.Color;
import java.awt.Graphics;


public class Carre extends ElementGraphique{

	public Carre(Coord centre, Coord coord, Color c, int t, Graphics g, double eX, double eY)
	{
		super (centre, coord, c, t, g, eX, eY);
	}

	@Override
	void tracer(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getCouleur());
		g.fillRect(getCoordonnees().getX()-getTaille()/2,getCoordonnees().getY()-getTaille()/2,getTaille(),getTaille());
	}
}
