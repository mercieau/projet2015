import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

public class Carte extends Canvas {
	
	private Coord min = new Coord(0,0);
	private Coord max = new Coord(0,0);
	private Coord centre = new Coord(0,0);
	private double echelleX = 1;
	private double echelleY = 1;
	
	Graphics gCarte;
	Graphics gVols;
	Canvas lCarte;
	Canvas lVols;
	
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
	
	
	public void draw(Aeroport a)
	{
		int i;
		this.getGraphics().setColor(Color.YELLOW);
		this.getGraphics().drawLine((int)((centre.getX()+min.getX())*echelleX), (int)((centre.getY()+min.getY())*echelleY), (int)((centre.getX()+max.getX())*echelleX), (int)((centre.getY()+max.getY())*echelleY));
		System.out.println((int)((centre.getX()+min.getX())*echelleX) +" " + (int)((centre.getY()+min.getY())*echelleY) +" "+ (int)((centre.getX()+max.getX())*echelleX) +" "+ (int)((centre.getY()+max.getY())*echelleY));
		for(i=0;i<a.getListePoint().size();i++)
		{
			if (isVisible(a.getListePoint().get(i).getCoordonnees())) new Rond(centre, a.getListePoint().get(i).getCoordonnees(), Color.YELLOW, 2, this.getGraphics(), getEchelleX(), getEchelleY());
		}
		for(i=0;i<a.getListeLine().size();i++)
		{
			for(int j=0;j<a.getListeLine().get(i).getCoordonnees().size();j++)
			{ 
				if (isVisible(a.getListeLine().get(i).getCoordonnees().get(j))) new Rond(centre, a.getListeLine().get(i).getCoordonnees().get(j), Color.RED, 2, this.getGraphics(), getEchelleX(), getEchelleY());
			}
			for(int j=1;j<a.getListeLine().get(i).getCoordonnees().size();j++)
			{
				if (isVisible(a.getListeLine().get(i).getCoordonnees().get(j))) new Ligne(centre, a.getListeLine().get(i).getCoordonnees().get(j-1), a.getListeLine().get(i).getCoordonnees().get(j), Color.RED, 2, this.getGraphics(), getEchelleX(), getEchelleY());
			}
			
		}
		for(i=0;i<a.getListeRunway().size();i++)
		{
			for(int j=0;j<a.getListeRunway().get(i).getListePoint().size();j++)
			{
				if (isVisible(a.getListeRunway().get(i).getListePoint().get(j).getCoordonnees())) new Rond(centre, a.getListeRunway().get(i).getListePoint().get(j).getCoordonnees(), Color.GREEN, 2, this.getGraphics(), getEchelleX(), getEchelleY());
				//if ((a.getListeRunway().get(i).getListePoint().get(j).getCoordonnees().getX()>max.getX()) || (a.getListeRunway().get(i).getListePoint().get(j).getCoordonnees().getY()>max.getY()) || (a.getListeRunway().get(i).getListePoint().get(j).getCoordonnees().getX()<min.getX()) || (a.getListeRunway().get(i).getListePoint().get(j).getCoordonnees().getY()<min.getY())) System.out.println("probl�me coordonn�es");
			}
			for(int j=1;j<a.getListeRunway().get(i).getListePoint().size();j++)
			{
				if (isVisible(a.getListeRunway().get(i).getListePoint().get(j).getCoordonnees())) new Ligne(centre, a.getListeRunway().get(i).getListePoint().get(j-1).getCoordonnees(), a.getListeRunway().get(i).getListePoint().get(j).getCoordonnees(), Color.GREEN, 2, this.getGraphics(), getEchelleX(), getEchelleY());
			}
		}
	}
	
	

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 900, 600);
		new Carre(centre, new Coord(100,40), Color.GREEN, 100, g, getEchelleX(), getEchelleY());
		new Rond(centre, new Coord(10,-10), Color.BLUE, 50, g, getEchelleX(), getEchelleY());
		new Rond(centre, new Coord(100,40), Color.RED, 65, g, getEchelleX(), getEchelleY());
		new Triangle(centre, new Coord(100, 40), Color.BLACK, 40, g, getEchelleX(), getEchelleY());
		g.setColor(c);
		
		/*
		// Layers 1&2 are the same size
	    lCarte = new Canvas();
	    lVols = new Canvas();
	    
	    lCarte.setSize(900, 600);
	    lVols.setSize(900, 600);
	        
	    // Obtain Graphics Contexts
	    gCarte = lCarte.getGraphics();
	    gVols = lVols.getGraphics();*/
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
		draw(a);
	}
	
	boolean isVisible(Coord coordonnees) {
		if ((coordonnees.getX()<max.getX()) && (coordonnees.getX()>min.getX()) && (coordonnees.getY()<max.getY()) && (coordonnees.getY()>min.getY())) return true;
		else return false;
	}

}
