/** classe Position
 * permet de classer les positions des avions de facon differente
 * une position possede plusieurs attributs : coordonnees, identifiant du vol et avion
 * @author Aurelie
 *
 */
public class Position {
	/** coordonnees de l'avion */
	private Coord coordonnees;
	/** Avion lie a cette Position */
	private Avion avion;
	/** Vol d'ou est issue cette position */
	private Vol vol;
	
	
	
	
	/** getter de vol
	 * 
	 * @return vol
	 */
	public Vol getVol() {
		return vol;
	}

	/** setter de vol
	 * 
	 * @param vol 
	 */
	public void setVol(Vol vol) {
		this.vol = vol;
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
	
	/** getter de avion
	 * 
	 * @return avion
	 */
	public Avion getAvion() {
		return avion;
	}
	
	/** setter de avion
	 * 
	 * @param avion
	 */
	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	
	/** constructeur de la classe avec parametres
	 * 
	 * @param coordonnees	coordonnees de l'avion
	 * @param avion			Avion lie a cette Position
	 * @param vol			Vol lie a cette Position
	 */
	public Position(Coord coordonnees, String idVol, Avion avion, Vol vol) {
		this.coordonnees = coordonnees;
		this.avion = avion;
		this.vol = vol;
	}
	
	/** constructeur de la classe sans parametre 
	 * 
	 */
	public Position() {
		
	}
	
	/** methode set de tous les attributs de la classe
	 * 
	 * @param coordonnees	coordonnees de l'avion
	 * @param avion			Avion lie a cette Position
	 * @param vol			Vol lie a cette Position
	 */
	public void setPosition(Coord coordonnees, Avion avion, Vol vol) {
		this.coordonnees = coordonnees;
		this.avion = avion;
		this.vol = vol;
	}
	
}
