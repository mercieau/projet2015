
public class Timer extends Thread {
	private Heure heure = new Heure();
	private int pas = 5;
	private int etat = 0;
	private Thread thread = new Thread();
	
	
	
	/**
	 * 
	 */
	public Timer() {
		start();
		System.out.println("run timer");
	}

	public int getPas() {
		return pas;
	}
	
	public void setPas(int pas) {
		this.pas = pas;
	}
	
	public void incrementer() {
		heure.increment(pas);
	}
	
	public void decrementer() {
		heure.increment(-pas);
	}
	
	public Heure getHeure() {
		return heure;
	}

	public void setHeure(Heure heure) {
		this.heure = heure;
	}
	

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (heure.getSecondes()<(24*60*60) && (heure.getSecondes()>=0)) { // tant que 24 heures ne se sont pas écoulées
			try {
				synchronized (this) {
					wait(1000); // attente une seconde
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // attente une seconde
			switch(etat) {
			case 0: 
				break;
			case 1: 
				incrementer();
				break;
			case -1: 
				decrementer();
				break;
			}
		}
	}
	
	
}
