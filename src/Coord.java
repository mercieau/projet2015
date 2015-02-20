/**
 * @author Aurelie
 * @version 1.0
 */

/** classe Coord
 * coordonnees en abscisse et ordonnee
 * @author Aurelie
 *
 */
public class Coord {
	/** abscisse */
	private int x;
	/** ordonnee */
	private int y;
	
	/** constructeur de la classe avec parametre
	 * 
	 * @param x		abscisse
	 * @param y		ordonnee
	 */
	public Coord(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/** methode de parametrage des coordonnees
	 * 
	 * @param x		abscisse
	 * @param y		ordonnee
	 */
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/** getter de x
	 * 
	 * @return x
	 */ 
	public int getX() {
		return x;
	}
	
	/** setter de x
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/** getter de y
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/** setter de y
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/** surcharge de la methode toString
	 * affichage de x et y
	 */
	public String toString() {
		return ("x="+x+" y="+y);
	}
}