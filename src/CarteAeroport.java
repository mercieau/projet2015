import java.awt.*;

import javax.swing.*;

/** classe CarteAeroport
 * herite de la classe Carte
 * carte de l'aeroport
 * @author Aurelie
 * @see Carte
 */
public class CarteAeroport extends Carte {
	
	private static final long serialVersionUID = 1L;	
	
	/** methode appelee par repaint() 
	 * dessine les Line, Point, Runway de l'aeroport en fonction de l'echelle et de la vue
	 * 
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (aeroport == null) {
			return;
		}
		int i;
		setBackground(Color.LIGHT_GRAY);
		for(i=0;i<aeroport.getListeLine().size();i++)
		{
			for(int j=0;j<aeroport.getListeLine().get(i).getCoordonnees().size();j++)
			{ 
				g.setColor(new Color(100,100,100));
				g.fillOval((int)((aeroport.getListeLine().get(i).getCoordonnees().get(j).getX()-centre.getX())*echelleX),(int)((aeroport.getListeLine().get(i).getCoordonnees().get(j).getY()-centre.getY())*echelleY),2,2);
			}
			for(int j=1;j<aeroport.getListeLine().get(i).getCoordonnees().size();j++)
			{
				g.setColor(new Color(100,100,100));
				g.drawLine((int)((aeroport.getListeLine().get(i).getCoordonnees().get(j-1).getX()-centre.getX())*echelleX),(int)((aeroport.getListeLine().get(i).getCoordonnees().get(j-1).getY()-centre.getY())*echelleY),(int)((aeroport.getListeLine().get(i).getCoordonnees().get(j).getX()-centre.getX())*echelleX),(int)((aeroport.getListeLine().get(i).getCoordonnees().get(j).getY()-centre.getY())*echelleY));
			}
			
		}
		for(i=0;i<aeroport.getListeRunway().size();i++)
		{
			for(int j=0;j<aeroport.getListeRunway().get(i).getListePoint().size();j++)
			{
				g.setColor(Color.GREEN);
				g.fillOval((int)((aeroport.getListeRunway().get(i).getListePoint().get(j).getCoordonnees().getX()-centre.getX())*echelleX),(int)((aeroport.getListeRunway().get(i).getListePoint().get(j).getCoordonnees().getY()-centre.getY())*echelleY),2,2);
			}
			for(int j=1;j<aeroport.getListeRunway().get(i).getListePoint().size();j++)
			{
				g.setColor(Color.GREEN);
				g.drawLine((int)((aeroport.getListeRunway().get(i).getListePoint().get(j-1).getCoordonnees().getX()-centre.getX())*echelleX),(int)((aeroport.getListeRunway().get(i).getListePoint().get(j-1).getCoordonnees().getY()-centre.getY())*echelleY),(int)((aeroport.getListeRunway().get(i).getListePoint().get(j).getCoordonnees().getX()-centre.getX())*echelleX),(int)((aeroport.getListeRunway().get(i).getListePoint().get(j).getCoordonnees().getY()-centre.getY())*echelleY));
			}
		}
		
		for(i=0;i<aeroport.getListePoint().size();i++)
		{
			g.setColor(Color.YELLOW);
			g.fillOval((int)((aeroport.getListePoint().get(i).getCoordonnees().getX()-centre.getX())*echelleX),(int)((aeroport.getListePoint().get(i).getCoordonnees().getY()-centre.getY())*echelleY),2,2);
		}	
		
		/** affichage de l'echelle en fonction du niveau de zoom */
		g.setColor(Color.BLACK);
		if (echelleX<0.5) {
			g.drawLine(10, 10, (int)(echelleX*1000+10), 10);
			g.drawLine(10, 11, (int)(echelleX*1000+10), 11);
			g.drawString("echelle : 1km", 10, 30);
		}
		else if (echelleX<2){
			g.drawLine(10, 10, (int)(echelleX*500+10), 10);
			g.drawLine(10, 11, (int)(echelleX*500+10), 11);
			g.drawString("echelle : 500m", 10, 30);
		}
		else {
			g.drawLine(10, 10, (int)(echelleX*100+10), 10);
			g.drawLine(10, 11, (int)(echelleX*100+10), 11);
			g.drawString("echelle : 100m", 10, 30);
		}
		
	}
	
	/** constructeur de la classe sans parametre 
	 * 
	 */
	public CarteAeroport() {

	}

}
