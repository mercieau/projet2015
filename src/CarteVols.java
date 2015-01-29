import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.*;

public class CarteVols extends JPanel {
	
	private Coord min = new Coord(0,0);
	private Coord max = new Coord(0,0);
	private Coord centre = new Coord(0,0);
	private double echelleX = 1;
	private double echelleY = 1;
	private int entier = 0;
	private Timer timer = new Timer();
	private ArrayList<Carre> listeCarre = new ArrayList<Carre>();
	
	private Aeroport aeroport;
	
	RedSquare redSquare = new RedSquare();
	
		
	
	
	public Aeroport getAeroport() {
		return aeroport;
	}

	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}

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
	
	/*public void calculCentre()
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
	}*/
	public void calculCentre()
	{
		/*if (max.getX()>(-min.getX())) centre.setX(max.getX()+10);
		else centre.setX(-min.getX()+10);
		if (max.getY()>(-min.getY())) centre.setY(max.getY()+10);
		else centre.setY(-min.getY()+10);*/
		
		
		/*echelleX = 880.0 / (2*centre.getX());
		echelleY = 580.0 / (2*centre.getY());*/
		echelleX = 880.0 / (max.getX()-min.getX());
		echelleY = 580.0 / (max.getY()-min.getY());
		
		
		System.out.println("echelle:" + echelleX + " " + echelleY);
		System.out.println("centre:" + centre.getX() + " " + centre.getY());
		System.out.println("max:" + ((max.getX()-centre.getX())*echelleX) + " " + ((-centre.getY()+max.getY())*echelleY));
		System.out.println("min:" + ((min.getX()-centre.getX())*echelleX) + " " + ((-centre.getY()+min.getY())*echelleY));
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
	
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("repaint CarteVols");

		setOpaque(false);
		if (aeroport == null) {
			// setOpaque(false);
			System.out.println("repaint CarteVols : aéroport non défini");
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			g.fillRect(120 + entier * 5, 120 + entier * 5, 100 / (entier + 1),
					100 / (entier + 1));
			return;
		}
		super.paintComponent(g);
		// clean();
		// this.getGraphics().setColor(Color.YELLOW);
		// this.getGraphics().drawLine((int)((centre.getX()+min.getX())*echelleX),
		// (int)((centre.getY()+min.getY())*echelleY),
		// (int)((centre.getX()+max.getX())*echelleX),
		// (int)((centre.getY()+max.getY())*echelleY));
		// System.out.println((int)((centre.getX()+min.getX())*echelleX) +" " +
		// (int)((centre.getY()+min.getY())*echelleY) +" "+
		// (int)((centre.getX()+max.getX())*echelleX) +" "+
		// (int)((centre.getY()+max.getY())*echelleY));

		/*new Thread() {
			public void run() {
				
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {*/
							// effacer tous les carrés de la liste avant de
							// redessiner
							for (int k = 0; k < listeCarre.size(); k++)
								listeCarre.get(k).effacer(getGraphics());

							Coord coordonnees = new Coord(0, 0);

							for (int i = 0; /* i<aeroport.getListeVol().size() */i < 1; i++) {
								System.out.println("dessin Carrés");
								for (int j = 0; j < aeroport.getListeVol()
										.get(i).getCoordonnees().size(); j++) {
									if (isVisible(aeroport.getListeVol().get(i)
											.getCoordonnees().get(j))) {
										// listeCarre.add(new Carre(centre,
										// aeroport.getListeVol().get(i).getCoordonnees().get(j),
										// Color.MAGENTA, 8, this.getGraphics(),
										// getEchelleX(), getEchelleY()));
										coordonnees
												.setX((int) (((aeroport
														.getListeVol().get(i)
														.getCoordonnees()
														.get(j).getX() - centre
														.getX()) * echelleX)));
										coordonnees
												.setY((int) (((aeroport
														.getListeVol().get(i)
														.getCoordonnees()
														.get(j).getY() - centre
														.getY()) * echelleY)));
										g.setColor(Color.MAGENTA);
										g.fillRect(coordonnees.getX() - 8 / 2,
												coordonnees.getY() - 8 / 2, 8,
												8);

										// System.out.println(j);
									}

								}
							}
						/*}
					});
				
			}
		}.start();*/
		System.out.println("fin repaint CarteVols");

	}
	
	void zoom(Coord coord, boolean plus) {
		if (plus) {
			setMin(new Coord(min.getX() - (int)((coord.getX()-440)*echelleX), min.getY() - (int)((coord.getY()-290)*echelleY)));
			setMax(new Coord(max.getX() - (int)((coord.getX()-440)*echelleX), max.getY() - (int)((coord.getY()-290)*echelleY)));
			getGraphics().translate((int)(-(coord.getX()-440)*echelleX), (int)(-(coord.getY()-290)*echelleY));	
			setCentre();
			calculCentre();
			echelleX *= 2;
			echelleY *= 2;
			setMax(new Coord((int)(min.getX()+880/echelleX),(int)(min.getY()+580/echelleY)));
			setCentre();
			calculCentre();
		} else {
			setMin(new Coord(min.getX() - (int)((coord.getX()-440)*echelleX), min.getY() - (int)((coord.getY()-290)*echelleY)));
			setMax(new Coord(max.getX() - (int)((coord.getX()-440)*echelleX), max.getY() - (int)((coord.getY()-290)*echelleY)));
			getGraphics().translate((int)(-(coord.getX()-440)*echelleX), (int)(-(coord.getY()-290)*echelleY));
			setCentre();
			calculCentre();
			echelleX /= 2;
			echelleY /= 2;
			setMax(new Coord((int)(min.getX()+880/echelleX),(int)(min.getY()+580/echelleY)));
			setCentre();
			calculCentre();
		}
	}
	
	public void draw() {
		System.out.println("draw");
		// repaint(120+entier*5, 120+entier*5, 100/(entier+1), 100/(entier+1));
		if (aeroport == null) {
			repaint(120 + entier * 5, 120 + entier * 5, 100 / (entier + 1),
					100 / (entier + 1));
			return;
		}
		// repaint(0,0,1,1);

		// clean();
		// this.getGraphics().setColor(Color.YELLOW);
		// this.getGraphics().drawLine((int)((centre.getX()+min.getX())*echelleX),
		// (int)((centre.getY()+min.getY())*echelleY),
		// (int)((centre.getX()+max.getX())*echelleX),
		// (int)((centre.getY()+max.getY())*echelleY));
		// System.out.println((int)((centre.getX()+min.getX())*echelleX) +" " +
		// (int)((centre.getY()+min.getY())*echelleY) +" "+
		// (int)((centre.getX()+max.getX())*echelleX) +" "+
		// (int)((centre.getY()+max.getY())*echelleY));

		/*new Thread() {
			public void run() {
			
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {*/

		Coord coordonnees = new Coord(0, 0);
		for (int i = 0; /* i<aeroport.getListeVol().size() */i < 1; i++) {
			for (int j = 0; /*j < aeroport.getListeVol().get(i).getCoordonnees().size()*/j<50; j++) {
				if (isVisible(aeroport.getListeVol().get(i).getCoordonnees()
						.get(j))) {
					coordonnees
							.setX((int) (((aeroport.getListeVol().get(i)
									.getCoordonnees().get(j).getX() - centre
									.getX()) * echelleX)));
					coordonnees
							.setY((int) (((aeroport.getListeVol().get(i)
									.getCoordonnees().get(j).getY() - centre
									.getY()) * echelleY)));
					repaint(coordonnees.getX(), coordonnees.getY(), 1, 1);
					System.out.println("repaint : x=" + coordonnees.getX()
							+ " y=" + coordonnees.getY());
				}
			}
		}

						/*}
					});
				
			}
		}.start();*/

	}
				
			
		
	
	
	public void setCentre() {
		getGraphics().translate(-centre.getX(), -centre.getY());
		getGraphics().translate(min.getX(), min.getY());
		centre.setX(min.getX());
		centre.setY(min.getY());
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
