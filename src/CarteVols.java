import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CarteVols extends JPanel {
	
	private Coord min = new Coord(0,0);
	private Coord max = new Coord(0,0);
	private Coord centre = new Coord(0,0);
	private double echelleX = 1;
	private double echelleY = 1;
	private int entier = 0;
	private Timer timer = new Timer();
	
	RedSquare redSquare = new RedSquare();
	
		
	
	public void incEntier() {
		entier++;
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
	
	public void restartTimer() {
		timer.setHeure(new Heure(0));
	}
	
	public void setTimer(double n) {
		timer.setHeure(new Heure(n));
	}
	
	public void stopTimer() {
		timer.setEtat(0);
	}
	
	public void startTimer() {
		timer.setEtat(1);
	}
	
	public void startAntiTimer() {
		timer.setEtat(-1);
	}
	
	public void setPasTimer(int n) {
		timer.setPas(n);
	}
	
	public int getPasTimer() {
		return timer.getPas();
	}
	
	public String getTime() {
		return timer.getHeure().toString();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("repaint CarteVols");
		setOpaque(false);
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(120+entier*5, 120+entier*5, 100/(entier+1), 100/(entier+1));
	}
	
	public void draw(){
		repaint(120+entier*5, 120+entier*5, 100/(entier+1), 100/(entier+1));
	}
	
	public void clean()
	{
		getGraphics().setColor(Color.WHITE);
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
	
	public CarteVols() {
		setBorder(BorderFactory.createLineBorder(Color.black));

	}
	
	

}
