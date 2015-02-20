/** interface TimeListener
 * liee a la classe LecteurVideo
 * 
 * @author Aurelie
 * @see LecteurVideo
 */
public interface TimeListener {
	/** methode timeUpdated
	 * methode a definir : appelee lors d'un changement de l'attribut time
	 * @param time
	 */
	void timeUpdated(int time);
}