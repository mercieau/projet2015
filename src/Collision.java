import java.util.ArrayList;

/** classe Collision
 * une collision est un rapport d'une collision survenue entre plusieurs avions dont les identifiants de vol sont recuperes, a des coordonnees et une date precise
 * @author Aurelie
 *
 */
public class Collision {
	/** liste des avions en cause */
	private ArrayList<String> listeVols = new ArrayList<String>();
	/** coordonnees de la collision */
	private Coord coordonnees = new Coord(0,0);
	/** date (en secondes depuis le debut de la journee) de la collision */
	private int time = 0;
	
	/** getter de listeVols
	 * 
	 * @return listeVols
	 */
	public ArrayList<String> getListeVols() {
		return listeVols;
	}
	
	/** setter de listeVols
	 * 
	 * @param listeVols
	 */
	public void setListeVols(ArrayList<String> listeVols) {
		this.listeVols = listeVols;
	}
	
	/** getter de coordonnees
	 * 
	 * @return coordonnees
	 */
	public Coord getCoordonnees() {
		return coordonnees;
	}
	
	/** setter de coordonnees
	 * 
	 * @param coordonnees
	 */
	public void setCoordonnees(Coord coordonnees) {
		this.coordonnees = coordonnees;
	}
	
	/** getter de time
	 * 
	 * @return time
	 */
	public int getTime() {
		return time;
	}
	
	/** setter de time 
	 * 
	 * @param time
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	/** constructeur de la classe avec parametres
	 * 
	 * @param listeVols		liste des avions en cause
	 * @param coordonnees	coordonnees de l'un des avions
	 * @param time			date (nombre de secondes depuis le debut de la journee) precise de la collision
	 */
	public Collision(ArrayList<String> listeVols, Coord coordonnees, int time) {
		super();
		this.listeVols = listeVols;
		this.coordonnees = coordonnees;
		this.time = time;
	}
	
	/** surcharge de la methode toString
	 * affichage des avions en cause, des coordonnees et de l'heure de la collision
	 */
	public String toString() {
		String s = "collision entre les vols : ";
		for (int i =0; i< listeVols.size();i++) {
			s += ""+listeVols.get(i) + " ";
		}
		s+= "aux coordonnees : x="+coordonnees.getX()+" y="+(-coordonnees.getY())+ " heure : "+Heure.convertSecondes(time);
		return s;
	}
}
