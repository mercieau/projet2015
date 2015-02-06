/**
 * @author Natacha
 * @version 1.0
 */


import java.util.ArrayList;

public class Runway 
{
	private String name;
	private String QFU[];	//Pour séparer les 2 QFU de la piste on les met dans un tableau
	private Coord coordonneesDebut;
	private Coord coordonneesFin;
	private ArrayList<Point> listePoint = new ArrayList<Point>();
	
	
	public Runway(String name, String QFU[], Coord cd, Coord cf, ArrayList<Point> listePoint)
	{
		this.name = name;
		this.QFU = QFU;
		this.coordonneesDebut = cd;
		this.coordonneesFin = cf;
		this.listePoint = listePoint;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getQFU() {
		return QFU;
	}

	public void setQFU(String[] qFU) {
		QFU = qFU;
	}

	public Coord getCoordonneesDebut() {
		return coordonneesDebut;
	}

	public void setCoordonneesDebut(Coord coordonneesDebut) {
		this.coordonneesDebut = coordonneesDebut;
	}

	public Coord getCoordonneesFin() {
		return coordonneesFin;
	}

	public void setCoordonneesFin(Coord coordonneesFin) {
		this.coordonneesFin = coordonneesFin;
	}

	public ArrayList<Point> getListePoint() {
		return listePoint;
	}

	public void setListePoint(ArrayList<Point> listePoint) {
		this.listePoint = listePoint;
	}

	void afficheRunway()
	{
		System.out.println("Runway: " + name + " " + QFU[0] + " " + QFU[1] + " " + coordonneesDebut.getX() + "," + coordonneesFin.getY() + listePoint.get(0).getNom());
	}
}
