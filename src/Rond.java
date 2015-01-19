import java.awt.Color;
import java.awt.Graphics;


public class Rond extends ElementGraphique{

	public Rond(Coord centre, Coord coord, Color c, int t, Graphics g, double d, double e)
	{
		super (centre, coord, c, t, g, d, e);
	}

	@Override
	void tracer(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getCouleur());
		g.fillOval(getCoordonnees().getX()-getTaille()/2,getCoordonnees().getY()-getTaille()/2,getTaille(),getTaille());
	}
}
