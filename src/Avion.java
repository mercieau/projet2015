/** classe Avion
 * un avion est une position d'un avion a un temps precis, avec ses coordonnees
 * @author Aurelie
 *
 */

public class Avion {
	/** position en abscisse sur la carte */
	private int xPos = 0;
	/** position en ordonnee sur la carte */
	private int yPos = 0;
	/** date (en secondes) de l'affichage a cette position */
	private int time = 0;
	/** coordonnees de la position */	
	private Coord coordonnees = new Coord(0,0);
	/** identifiant du vol */
	private String nomVol = "";
	/** type de vol (DEP ou ARR) */
	private String typeVol = "";
	/** booleen indiquant si cet avion doit avoir son trajet indique */
	private boolean affiche = false;
	/** booleen indiquant si une collision se produit a cette date precise */
	private boolean collision = false;
	
	
	
	
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

	/** getter de nomVol
	 * 
	 * @return nomVol
	 */
	public String getNomVol() {
		return nomVol;
	}

	/** setter de nomVol
	 * 
	 * @param nomVol
	 */
	public void setNomVol(String nomVol) {
		this.nomVol = nomVol;
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

	/** getter de affiche
	 * 
	 * @return affiche
	 */
	public boolean isAffiche() {
		return affiche;
	}

	/** setter de affiche
	 * 
	 * @param affiche
	 */
	public void setAffiche(boolean affiche) {
		this.affiche = affiche;
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
	
	/** getter de xPos
	 * 
	 * @return xPos
	 */
	public int getxPos() {
		return xPos;
	}
	
	/** setter de xPos
	 * 
	 * @param xPos
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
	/** getter de yPos
	 * 
	 * @return yPos
	 */
	public int getyPos() {
		return yPos;
	}
	
	/** setter de yPos
	 * 
	 * @param yPos
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}	

	/** constructeur de la classe avec parametres
	 * 
	 * @param xPos 	position en abscisse sur la carte
	 * @param yPos	position en ordonnee sur la carte
	 * @param t		temps (en secondes depuis le debut de la journee) de cette position
	 * @param name	identifiant du vol
	 * @param type	type du vol
	 * 
	 */
	public Avion(int xPos, int yPos, int t, String name, String type) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.time = t;
		this.nomVol = name;
		this.typeVol = type;
	}
	
	/** constructeur de la classe sans parametre 
	 * 
	 */
	public Avion() {
		super();
	}
	
	
	
}
