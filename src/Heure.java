
public class Heure {
	
	private double secondes = 0;
	
	public double getSecondes() {
		return secondes;
	}
	public void setSecondes(double secondes) {
		this.secondes = secondes;
	}
	
	public void increment(double n) {
		secondes += n;
	}
	
	
	/**
	 * 
	 */
	public Heure() {
		
	}
	
	public Heure(double n) {
		secondes = n;
	}
	
	@Override
	public String toString() {
		return ""+(int)(secondes/3600)+":"+(int)((secondes-(int)((int)(secondes/3600)*3600))/60)+":"+(int)(secondes - ((int)(secondes/60)*60));
	}
	
}
