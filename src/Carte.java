import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

public class Carte extends JPanel {
	
	private Coord min = new Coord(0,0);
	private Coord max = new Coord(0,0);
	private Coord centre = new Coord(0,0);
	private double echelleX = 1;
	private double echelleY = 1;
	private Aeroport aeroport;
	
	
	public void setAeroport(Aeroport a) {
		aeroport = a;
	}
	
	public double getEchelleX() {
		return echelleX;
	}

	public double getEchelleY() {
		return echelleY;
	}

	public void setEchelleX(double echelle) {
		this.echelleX = echelle;
	}
	
	public void setEchelleY(double echelle) {
		this.echelleY = echelle;
	}

	private static final long serialVersionUID = 1L;
	
	public void calculCentre()
	{
		if (max.getX()>(-min.getX())) centre.setX(max.getX()+10);
		else centre.setX(-min.getX()+10);
		if (max.getY()>(-min.getY())) centre.setY(max.getY()+10);
		else centre.setY(-min.getY()+10);
		
		echelleX = 880.0 / (2*centre.getX());
		echelleY = 580.0 / (2*centre.getY());
		System.out.println("echelle:" + echelleX + " " + echelleY);
		System.out.println("centre:" + centre.getX() + " " + centre.getY());
		System.out.println("max:" + ((max.getX()+centre.getX())*echelleX) + " " + ((centre.getY()+max.getY())*echelleY));
		System.out.println("min:" + ((min.getX()+centre.getX())*echelleX) + " " + ((centre.getY()+min.getY())*echelleY));
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("repaint Carte");
		super.paintComponent(g);
		setOpaque(true);
		if (aeroport == null) {
			g.setColor(Color.YELLOW);
			g.fillRect(200, 200, 50, 50);
			return;
		}
		int i;
		clean();
		this.getGraphics().setColor(Color.YELLOW);
		//this.getGraphics().drawLine((int)((centre.getX()+min.getX())*echelleX), (int)((centre.getY()+min.getY())*echelleY), (int)((centre.getX()+max.getX())*echelleX), (int)((centre.getY()+max.getY())*echelleY));
		System.out.println((int)((centre.getX()+min.getX())*echelleX) +" " + (int)((centre.getY()+min.getY())*echelleY) +" "+ (int)((centre.getX()+max.getX())*echelleX) +" "+ (int)((centre.getY()+max.getY())*echelleY));
		
		for(i=0;i<aeroport.getListeLine().size();i++)
		{
			for(int j=0;j<aeroport.getListeLine().get(i).getCoordonnees().size();j++)
			{ 
				if (isVisible(aeroport.getListeLine().get(i).getCoordonnees().get(j))) new Rond(centre, aeroport.getListeLine().get(i).getCoordonnees().get(j), Color.RED, 2, this.getGraphics(), getEchelleX(), getEchelleY());
			}
			for(int j=1;j<aeroport.getListeLine().get(i).getCoordonnees().size();j++)
			{
				if (isVisible(aeroport.getListeLine().get(i).getCoordonnees().get(j))) new Ligne(centre, aeroport.getListeLine().get(i).getCoordonnees().get(j-1), aeroport.getListeLine().get(i).getCoordonnees().get(j), Color.RED, 2, this.getGraphics(), getEchelleX(), getEchelleY());
			}
			
		}
		for(i=0;i<aeroport.getListeRunway().size();i++)
		{
			for(int j=0;j<aeroport.getListeRunway().get(i).getListePoint().size();j++)
			{
				if (isVisible(aeroport.getListeRunway().get(i).getListePoint().get(j).getCoordonnees())) new Rond(centre, aeroport.getListeRunway().get(i).getListePoint().get(j).getCoordonnees(), Color.GREEN, 2, this.getGraphics(), getEchelleX(), getEchelleY());
				//if ((a.getListeRunway().get(i).getListePoint().get(j).getCoordonnees().getX()>max.getX()) || (a.getListeRunway().get(i).getListePoint().get(j).getCoordonnees().getY()>max.getY()) || (a.getListeRunway().get(i).getListePoint().get(j).getCoordonnees().getX()<min.getX()) || (a.getListeRunway().get(i).getListePoint().get(j).getCoordonnees().getY()<min.getY())) System.out.println("probl�me coordonn�es");
			}
			for(int j=1;j<aeroport.getListeRunway().get(i).getListePoint().size();j++)
			{
				if (isVisible(aeroport.getListeRunway().get(i).getListePoint().get(j).getCoordonnees())) new Ligne(centre, aeroport.getListeRunway().get(i).getListePoint().get(j-1).getCoordonnees(), aeroport.getListeRunway().get(i).getListePoint().get(j).getCoordonnees(), Color.GREEN, 2, this.getGraphics(), getEchelleX(), getEchelleY());
			}
		}
		
		for(i=0;i<aeroport.getListePoint().size();i++)
		{
			if (isVisible(aeroport.getListePoint().get(i).getCoordonnees())) new Rond(centre, aeroport.getListePoint().get(i).getCoordonnees(), Color.YELLOW, 2, this.getGraphics(), getEchelleX(), getEchelleY());
		}
		
		
	}
	
	public void clean()
	{
		getGraphics().setColor(Color.BLACK);
		getGraphics().fillRect(0, 0, 900, 600);
	}

	public Coord getMin() {
		return min;
	}

	public void setMin(Coord min) {
		this.min = min;
	}

	public Coord getMax() {
		return max;
	}

	public void setMax(Coord max) {
		this.max = max;
	}

	public Coord getCentre() {
		return centre;
	}      

	void zoom(Aeroport a, Coord coordDebut, Coord coordFin) {
		setMin(coordDebut);
		setMax(coordFin);
		calculCentre();
		repaint();
	}
	
	boolean isVisible(Coord coordonnees) {
		if ((coordonnees.getX()<max.getX()) && (coordonnees.getX()>min.getX()) && (coordonnees.getY()<max.getY()) && (coordonnees.getY()>min.getY())) return true;
		else return false;
	}

}
