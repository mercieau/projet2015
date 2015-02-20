import java.util.ArrayList;

/** classe Vol
 * possede des attributs extraits pour chaque ligne du fichier de trafic
 * @author Aurelie
 *
 */
public class Vol {
	/** type du vol */
	private String typeVol;
	/** identifiant du vol */
	private String idVol;
	/** categorie de l'avion */
	private String catAvion;
	/** QFU du vol */
	private String QFU;
	/** Point de depart du vol */
	private Point pointDepart;
	/** temps en secondes depuis le debut de la journee a partir duquel les coordonnees sont a prendre en compte */
	private int temps;
	/** liste des coordonnees de l'avion a partir de temps */
	private ArrayList<Coord> coordonnees;
	/** booleen indiquant si le vol est selectionne */
	private boolean selection = false;
	/** booleen indiquant si le vol subit une collision */
	private boolean collision = false;
	
	
	/** getter de selection
	 * 
	 * @return selection
	 */
	public boolean isSelection() {
		return selection;
	}
	
	/** setter de selection
	 * 
	 * @param selection
	 */
	public void setSelection(boolean selection) {
		this.selection = selection;
	}
	
	/** getter de collision
	 * 
	 * @return collision
	 */
	public boolean isCollision() {
		return collision;
	}
	
	/** setter de collision 
	 * 
	 * @param collision
	 */
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	
	/** getter de typeVol
	 * 
	 * @return typeVol
	 */
	public String getTypeVol() {
		return typeVol;
	}
	
	/** setter de typeVol
	 * 
	 * @param typeVol
	 */
	public void setTypeVol(String typeVol) {
		this.typeVol = typeVol;
	}
	
	/** getter de idVol
	 * 
	 * @return idVol
	 */
	public String getIdVol() {
		return idVol;
	}
	
	/** setter de idVol
	 * 
	 * @param idVol
	 */
	public void setIdVol(String idVol) {
		this.idVol = idVol;
	}
	
	/** getter de catAvion
	 * 
	 * @return catAvion
	 */
	public String getCatAvion() {
		return catAvion;
	}
	
	/** setter de catAvion
	 * 
	 * @param catAvion
	 */
	public void setCatAvion(String catAvion) {
		this.catAvion = catAvion;
	}
	
	/** getter de QFU
	 * 
	 * @return QFU
	 */
	public String getQFU() {
		return QFU;
	}
	
	/** setter de QFU
	 * 
	 * @param qFU
	 */
	public void setQFU(String qFU) {
		QFU = qFU;
	}
	
	/** getter de pointDepart
	 * 
	 * @return pointDepart
	 */
	public Point getPointDepart() {
		return pointDepart;
	}
	
	/** setter de pointDepart
	 * 
	 * @param pointDepart
	 */
	public void setPointDepart(Point pointDepart) {
		this.pointDepart = pointDepart;
	}
	
	/** getter de coordonnees
	 * 
	 * @return coordonnees
	 */
	public ArrayList<Coord> getCoordonnees() {
		return coordonnees;
	}
	
	/** setter de coordonnees
	 * 
	 * @param coordonnees
	 */
	public void setCoordonnees(ArrayList<Coord> coordonnees) {
		this.coordonnees = coordonnees;
	}
	
	/** getter de temps
	 * 
	 * @return temps
	 */
	public int getTemps() {
		return temps;
	}
	
	/** setter de temps
	 * 
	 * @param temps
	 */
	public void setTemps(int temps) {
		this.temps = temps;
	}
	
	/** constructeur de la classe avec parametres
	 * 
	 * @param typeVol
	 * @param idVol
	 * @param catAvion
	 * @param qFU
	 * @param pointDepart
	 * @param temps
	 * @param coordonnees
	 */
	public Vol(String typeVol, String idVol, String catAvion, String qFU,
			Point pointDepart, int temps, ArrayList<Coord> coordonnees) {
		this.typeVol = typeVol;
		this.idVol = idVol;
		this.catAvion = catAvion;
		this.QFU = qFU;
		this.pointDepart = pointDepart;
		this.temps = temps;
		this.coordonnees = coordonnees;
	}
	
	/** methode d'affichage des attributs de la classe
	 * 
	 */
	void afficheVol()
	{
		System.out.println("Vol: " + typeVol + " " + idVol + " " + catAvion + " " + QFU + " " + (pointDepart.getCoordonnees().getX()) + " " + temps + " " + coordonnees.get(0).getX() + "," + coordonnees.get(0).getY());
	}
	
	
	
	
	
}