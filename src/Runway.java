/**
 * @author Natacha
 * @version 1.0
 */


import java.util.ArrayList;

/** classe Runway
 * Runway extrait du fichier de l'aeroport
 * @author Natacha
 *
 */
public class Runway 
{
	/** nom du Runway */
	private String name;
	/** QFU (tableau de String) du Runway */
	private String QFU[];	// Pour separer les 2 QFU de la piste on les met dans un tableau
	/** coordonnees du debut du Runway */
	private Coord coordonneesDebut;
	/** coordonnees de la fin du Runway */
	private Coord coordonneesFin;
	/** liste de Point du Runway */
	private ArrayList<Point> listePoint = new ArrayList<Point>();
	
	/** constructeur de la classe avec parametre
	 * 
	 * @param name			nom du Runway
	 * @param QFU			QFU
	 * @param cd			coordonnees du debut
	 * @param cf			coordonnees de la fin
	 * @param listePoint	liste de Point
	 */
	public Runway(String name, String QFU[], Coord cd, Coord cf, ArrayList<Point> listePoint)
	{
		this.name = name;
		this.QFU = QFU;
		this.coordonneesDebut = cd;
		this.coordonneesFin = cf;
		this.listePoint = listePoint;
	}
	
	/** getter de name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/** setter de name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** getter de QFU
	 * 
	 * @return QFU
	 */
	public String[] getQFU() {
		return QFU;
	}

	/** setter de QFU
	 * 
	 * @param qFU
	 */
	public void setQFU(String[] qFU) {
		QFU = qFU;
	}

	/** getter de coordonneesDebut
	 * 
	 * @return coordonneesDebut
	 */
	public Coord getCoordonneesDebut() {
		return coordonneesDebut;
	}

	/** setter de coordonneesDebut
	 * 
	 * @param coordonneesDebut
	 */
	public void setCoordonneesDebut(Coord coordonneesDebut) {
		this.coordonneesDebut = coordonneesDebut;
	}

	/** getter de coordonneesFin
	 * 
	 * @return coordonneesFin
	 */
	public Coord getCoordonneesFin() {
		return coordonneesFin;
	}

	/** setter de coordonneesFin
	 * 
	 * @param coordonneesFin
	 */
	public void setCoordonneesFin(Coord coordonneesFin) {
		this.coordonneesFin = coordonneesFin;
	}

	/** getter de listePoint
	 * 
	 * @return listePoint
	 */
	public ArrayList<Point> getListePoint() {
		return listePoint;
	}

	/** setter de listePoint
	 * 
	 * @param listePoint
	 */
	public void setListePoint(ArrayList<Point> listePoint) {
		this.listePoint = listePoint;
	}

	/** methode d'affichage des informations du Runway */
	void afficheRunway()
	{
		System.out.println("Runway: " + name + " " + QFU[0] + " " + QFU[1] + " " + coordonneesDebut.getX() + "," + coordonneesFin.getY() + listePoint.get(0).getNom());
	}
}