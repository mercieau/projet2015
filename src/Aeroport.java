/**
 * @author Natacha, Aurélie
 * @version 1.0
 */


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Aeroport 
{
	private ArrayList<Point> listePoint;
	private ArrayList<Line> listeLine;
	private ArrayList<Runway> listeRunway;
	private String name;
	public Coord getMin() {
		return min;
	}

	public void setMin(Coord min) {
		this.min = min;
	}

	public Coord getMax() {
		return max;
	}

	public void setMax(Coord max) {
		this.max = max;
	}

	private Coord min = new Coord(0,0);
	private Coord max = new Coord(0,0);
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public ArrayList<Point> getListePoint() {
		return listePoint;
	}

	public void setListePoint(ArrayList<Point> listePoint) {
		this.listePoint = listePoint;
	}

	public ArrayList<Line> getListeLine() {
		return listeLine;
	}

	public void setListeLine(ArrayList<Line> listeLine) {
		this.listeLine = listeLine;
	}

	public ArrayList<Runway> getListeRunway() {
		return listeRunway;
	}

	public void setListeRunway(ArrayList<Runway> listeRunway) {
		this.listeRunway = listeRunway;
	}

	public Aeroport()
	{
		listePoint = new ArrayList<Point>();
		listeLine = new ArrayList<Line>();
		listeRunway = new ArrayList<Runway>();
	}
	
	public void chargerTexte(String ficname)
	{
		Scanner scan = null;
		
		try
		{
			scan = new Scanner(new FileReader(ficname));
			scan.useDelimiter("\n");
			name = scan.next(); //Extraction du nom de l'aéroport
			listePoint.clear();
			while(scan.hasNext())
			{
				Scanner pt = new Scanner(scan.next());
				pt.useDelimiter(" ");
				String element = pt.next();
				
				/* Si la ligne de texte correspond à un point */
				if(element.equals("P"))
				{
					String name = pt.next();
					int type = pt.nextInt();
					String coordonnees[];
					coordonnees = pt.next().split(",");
					int x = Integer.parseInt(coordonnees[0]);
					int y = Integer.parseInt(coordonnees[1]);
					y = -y;
					if (x>max.getX()) max.setX(x);
					if (y>max.getY()) max.setY(y);
					if (x<min.getX()) min.setX(x);
					if (y<min.getY()) min.setY(y);
					listePoint.add(new Point(name, type, new Coord(x,y)));
					
				}
				
				/* Si la ligne de texte correspond à une ligne */
				if(element.equals("L"))
				{
					ArrayList<Coord> listeCoord = new ArrayList<Coord>();
					String name = pt.next();
					int speedlimit = pt.nextInt();
					String category = pt.next();
					String direction = pt.next();
					Scanner pl = new Scanner(pt.next());
					pl.useDelimiter(";"); //pl = Point Line
					while(pl.hasNext()) //tant qu'on trouve des points on les ajoute à la liste 
					{
						String coordonnees[];
						coordonnees = pl.next().split(",");
						int x = Integer.parseInt(coordonnees[0]);
						int y = Integer.parseInt(coordonnees[1]);
						y = -y;
						if (x>max.getX()) max.setX(x);
						if (y>max.getY()) max.setY(y);
						if (x<min.getX()) min.setX(x);
						if (y<min.getY()) min.setY(y);
						listeCoord.add(new Coord(x,y));
					}
					listeLine.add(new Line(name, speedlimit, category, direction, listeCoord));
				}
				
				/* Si la ligne de texte correspond à un runway */
				if(element.equals("R"))
				{
					String name = pt.next();
					String QFU[] = new String[2];
					String QFU1 = pt.next();
					String QFU2 = pt.next();
					QFU[0] = QFU1;
					QFU[1] = QFU2;
					String coordonnees[];
					coordonnees = pt.next().split(";");
					String cd[];
					cd = coordonnees[0].split(",");
					int cdX = Integer.parseInt(cd[0],10);
					int cdY = Integer.parseInt(cd[1],10);
					cdY = -cdY;
					Coord coordDebut = new Coord(cdX, cdY);
					String cf[];
					cf = coordonnees[1].split(",");
					int cfX = Integer.parseInt(cf[0],10);
					int cfY = Integer.parseInt(cf[1],10);
					cfY = -cfY;
					Coord coordFin = new Coord(cfX, cfY);
					if (cdX>max.getX()) max.setX(cdX);
					if (cdY>max.getY()) max.setY(cdY);
					if (cdX<min.getX()) min.setX(cdX);
					if (cdY<min.getY()) min.setY(cdY);
					if (cfX>max.getX()) max.setX(cfX);
					if (cfY>max.getY()) max.setY(cfY);
					if (cfX<min.getX()) min.setX(cfX);
					if (cfY<min.getY()) min.setY(cfY);
					ArrayList<Point> listePoint = new ArrayList<Point>();
					Scanner pr = new Scanner(pt.next());
					pr.useDelimiter(";");	
					while(pr.hasNext()) //tant qu'on trouve des points on les ajoute à la liste 
					{
						listePoint.add(new Point(pr.next(), 0, new Coord(0, 0)));
					}
					
					listeRunway.add(new Runway(name, QFU, coordDebut, coordFin, listePoint));
				}
			}
			
		}
		catch(FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		
		/** Partie Test: Affichage dans la console **/
		
		System.out.println(name);
		
		for(int i=0;i<listePoint.size();i++) 
		{
			listePoint.get(i).affichePoint();
		}
		
		for(int i=0;i<listeLine.size();i++) 
		{
			listeLine.get(i).afficheLine();
		}
		
		for(int i=0;i<listeRunway.size();i++) 
		{
			listeRunway.get(i).afficheRunway();
		}		
	}
}

