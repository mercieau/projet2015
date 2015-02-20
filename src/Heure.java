import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;

/** classe Heure
 * herite de Observable
 * implemente ActionListener
 * @author dardekna
 *
 */

/** classe Heure
 * permet de generer un timer de 24h avec un pas reglable 
 * ainsi que toutes les fonctions temporelles qui vont avec
 * ce timer sera la reference temporaire pour le lecteur video
 * @author Dardekna
 *
 */

public class Heure extends Observable implements ActionListener{
	/** nombre de secondes */
	private int sec=0; 		//nombre de secondes total
	/** temps courant exploite en format heure minutes secondes */
	private int heures=0,minutes=0,secondes=0;
	/** nombre de tours de timer pour faire une seconde */
	private int delay=1000;
	/** declaration du timer */
	private Timer timer;
	/** pas par default du temps (avance de 5sec pour 1 seconde reelle) */
	private int pas_default=5;
	/** pas courant */
	private int pas=5;
	/** bouleen definissant le sens de parcours du temps */
	private boolean sens=true;
	

	/** constructeur de la classe avec parametre
	 * 
	 * @param lect		lecteur video associe
	 */
	public Heure(LecteurVideo lect){
		timer = new Timer(delay, this);
		/**on signale que la classe heure est observer par lect*/
		addObserver(lect);
	}
	
	/** methode permettant de recuperer le pas du timer
	 * 
	 * @return pas	pas du timer
	*/
	public int getPas() {
		return pas;
	}

	/** methode permettant de configurer le pas du timer
	 * @param pas 	pas du timer
	*/
	public void setPas(int pas) {
		this.pas = pas;
	}
	
	/** methode permettant de remettre le pas au pas par defaut
	 *  
	*/
	public void resetPas() {
		// TODO Auto-generated method stub
		pas=pas_default;
	}
	
	/** methode permettant de recuperer le sens de parcours du temps
	 * @return sens
	*/
	public boolean isSens() {
		return sens;
	}

	/** methode permettant de mettre le sens voulu au parcours du temps
	 * @param sens
	*/
	public void setSens(boolean sens) {
		this.sens = sens;
	}
	
	/** methode permettant de lancer le timer
	 * 
	*/
	public void start() {
		timer.start();
	}
	
	/** methode permettant de stoper le timer
	 *  
	*/
	public void stop() {
		timer.stop();
	}
	
	/** methode renvoyant le nombre total de secondes 
	 * @return message (heure au format h:m:s)
	 */
	 public int getSec() {
		return sec;
	}
	 
	/** methode permettant de mettre les secondes totales a la valeur voulue
	 *
	 * @param sec	valeur a laquelle parametrer sec
	 */
	public void setSec(int sec) {
		this.sec = sec;
	}
	
	/** methode pour renvoyer un message contenant l'heure explicite 
	 * au format h:m:s
	 *
	 * @return message (heure au format h:m:s)
	 */
	public String getHeure(){
		if(sec<=0) setSec(0);
		if(sec>=86400) setSec(86400);
		setHeure(sec);
		
		String message=heures+":";
		if(minutes<10) message+="0"+minutes+":";
		else message+=minutes+":";
		if(secondes<10) message+="0"+secondes;
		else message+=secondes;

		return message;
	}
	
	/** methode permettant de definir l'heure au format h:m:s
	 * en fonction du nombres de secondes totales 
	 *
	 * @param sec
	 */
	public void setHeure(int sec){
		int temp;
		heures=sec/3600;
		temp=sec%3600;
		minutes=temp/60;
		secondes=temp%60;	
	}
	
	/** methode permettant de faire passer le curseur a 0 s'il depasse 
	 * d'un cote ou d'un autre les 24 h de visualition
	 *
	 */
	private void rafraichirHeure() {
		if (sec>=86400 || (sec<=0 & sens==false))
		{
			timer.stop();
			sec=0;
		}		
		else{
			if(sens)
			{
				sec=sec+pas;
			}
			else{
				sec=sec-pas;
			}	
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		rafraichirHeure();
		//System.out.println(sec);
		setChanged();  //informe que l'objet Heure a changer 
		notifyObservers(); //previens les observeurs de cette objet qu'il a chang� 
						   //appel leur update 
						   //puis la fonction clearchanged pour inform� que le changement d'etat est fini (jusqu'au prochain)
	}

	/** methode static pour la conversion d'un nombre de secondes en String  hh:mm:ss
	 * 
	 * @param sec	nombre de secondes a convertir
	 * @return		String hh:mm:ss
	 */
	static String convertSecondes(int sec) {
		int temp;
		int heures=sec/3600;
		temp=sec%3600;
		int minutes=temp/60;
		int secondes=temp%60;
		
		String message=heures+":";
		if(minutes<10) message+="0"+minutes+":";
		else message+=minutes+":";
		if(secondes<10) message+="0"+secondes;
		else message+=secondes;

		return message;
	}
}
