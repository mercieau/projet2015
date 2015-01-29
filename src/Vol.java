import java.util.ArrayList;


public class Vol {
	private String typeVol;
	private String idVol;
	private String catAvion;
	private String QFU;
	private Point pointDepart;
	private Heure temps;
	private ArrayList<Coord> coordonnees;
	
	
	
	
	public String getTypeVol() {
		return typeVol;
	}
	public void setTypeVol(String typeVol) {
		this.typeVol = typeVol;
	}
	public String getIdVol() {
		return idVol;
	}
	public void setIdVol(String idVol) {
		this.idVol = idVol;
	}
	public String getCatAvion() {
		return catAvion;
	}
	public void setCatAvion(String catAvion) {
		this.catAvion = catAvion;
	}
	public String getQFU() {
		return QFU;
	}
	public void setQFU(String qFU) {
		QFU = qFU;
	}
	public Point getPointDepart() {
		return pointDepart;
	}
	public void setPointDepart(Point pointDepart) {
		this.pointDepart = pointDepart;
	}
	public ArrayList<Coord> getCoordonnees() {
		return coordonnees;
	}
	public void setCoordonnees(ArrayList<Coord> coordonnees) {
		this.coordonnees = coordonnees;
	}
	public Heure getTemps() {
		return temps;
	}
	public void setTemps(Heure temps) {
		this.temps = temps;
	}
	
	
	
	
	
	public Vol(String typeVol, String idVol, String catAvion, String qFU,
			Point pointDepart, Heure temps, ArrayList<Coord> coordonnees) {
		this.typeVol = typeVol;
		this.idVol = idVol;
		this.catAvion = catAvion;
		this.QFU = qFU;
		this.pointDepart = pointDepart;
		this.temps = temps;
		this.coordonnees = coordonnees;
	}
	
	
	void afficheVol()
	{
		System.out.println("Vol: " + typeVol + " " + idVol + " " + catAvion + " " + QFU + " " + (pointDepart.getCoordonnees().getX()) + " " + temps.getSecondes() + " " + coordonnees.get(0).getX() + "," + coordonnees.get(0).getY());
	}
	
	
	
	
	
}
