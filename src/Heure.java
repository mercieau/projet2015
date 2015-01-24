
public class Heure {
	private int pas = 5;
	private int secondes = 0;
	public int getPas() {
		return pas;
	}
	public void setPas(int pas) {
		this.pas = pas;
	}
	public int getSecondes() {
		return secondes;
	}
	public void setSecondes(int secondes) {
		this.secondes = secondes;
	}
	public void increment() {
		secondes += pas;
	}
	
	@Override
	public String toString() {
		return ""+secondes/60+":"+(secondes - (secondes/60));
	}
	
}
