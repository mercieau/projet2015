/**
 * @author Natacha, Aurelie
 * classe aeroport contenant toutes les donnees extraites des fichiers
 * @version 1.1
 */

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/** classe Aeroport
 * contient les elements extraits des fichiers d'aeroport et de trafic
 * possede des methodes de recherche des collisions en fonction du fichier de trafic
 * @author Aurelie
 *
 */
public class Aeroport {
	/** liste des points extraits */
	private ArrayList<Point> listePoint;
	/** liste des lines extraites */
	private ArrayList<Line> listeLine;
	/** liste des runways extraits */
	private ArrayList<Runway> listeRunway;
	/** liste des vols extraits */
	private ArrayList<Vol> listeVol;
	/** liste des collisions calculees */
	private ArrayList<Collision> listeCollision;
	/** liste des positions */
	private ArrayList<ArrayList<Position>> listePositions; /** pour optimiser le calcul des collisions -> si parcours de la liste d'avion -> min 5 minutes de traitement, par cette methode de parcours par heure -> 2-3sec */
	/** liste des avions (toutes les positions des avions sur une journee) */
	private ArrayList<Avion> listeAvion;
	/** nom de l'aeroport */
	private String name;
	/** distance minimale entre deux avions avant collision */
	private int distanceCollision = 0;
	/** coordonnees du minimum */
	private Coord min;
	/** coordonnees du maximum */
	private Coord max;

	/** getter de listeCollision
	 * 
	 * @return listeCollision
	 */
	public ArrayList<Collision> getListeCollision() {
		return listeCollision;
	}

	/** setter de listeCollision
	 * 
	 * @param listeCollision
	 */
	public void setListeCollision(ArrayList<Collision> listeCollision) {
		this.listeCollision = listeCollision;
	}

	/** getter de listePositions
	 * 
	 * @return listePositions
	 */
	public ArrayList<ArrayList<Position>> getListePositions() {
		return listePositions;
	}

	/** setter de listePositions
	 * 
	 * @param listePositions
	 */
	public void setListePositions(ArrayList<ArrayList<Position>> listePositions) {
		this.listePositions = listePositions;
	}

	/** getter de distanceCollision
	 * 
	 * @return distanceCollision
	 */
	public int getDistanceCollision() {
		return distanceCollision;
	}

	/** setter de distanceCollision
	 * 
	 * @param distanceCollision
	 */
	public void setDistanceCollision(int distanceCollision) {
		this.distanceCollision = distanceCollision;
		testCollision();
	}

	/** getter de listeAvion
	 * 
	 * @return listeAvion
	 */
	public ArrayList<Avion> getListeAvion() {
		return listeAvion;
	}

	/** setter de listeAvion
	 * 
	 * @param listeAvion
	 */
	public void setListeAvion(ArrayList<Avion> listeAvion) {
		this.listeAvion = listeAvion;
	}

	/** getter de min
	 * 
	 * @return min
	 */
	public Coord getMin() {
		return min;
	}

	/** setter de min
	 * 
	 * @param min
	 */
	public void setMin(Coord min) {
		this.min = min;
	}

	/** getter de max
	 * 
	 * @return max
	 */
	public Coord getMax() {
		return max;
	}

	/** setter de max
	 * 
	 * @param max
	 */
	public void setMax(Coord max) {
		this.max = max;
	}

	/** getter de name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/** setter de name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** getter de listePoint
	 * 
	 * @return listePoint
	 */
	public ArrayList<Point> getListePoint() {
		return listePoint;
	}

	/** setter de listePoint
	 * 
	 * @param listePoint
	 */
	public void setListePoint(ArrayList<Point> listePoint) {
		this.listePoint = listePoint;
	}

	/** getter de listeLine
	 * 
	 * @return listeLine
	 */
	public ArrayList<Line> getListeLine() {
		return listeLine;
	}

	/** setter de listeLine
	 * 
	 * @param listeLine
	 */
	public void setListeLine(ArrayList<Line> listeLine) {
		this.listeLine = listeLine;
	}

	/** getter de listeRunway
	 * 
	 * @return listeRunway
	 */
	public ArrayList<Runway> getListeRunway() {
		return listeRunway;
	}

	/** setter de listeRunway
	 * 
	 * @param listeRunway
	 */
	public void setListeRunway(ArrayList<Runway> listeRunway) {
		this.listeRunway = listeRunway;
	}

	/** getter de listeVol
	 * 
	 * @return listeVol
	 */
	public ArrayList<Vol> getListeVol() {
		return listeVol;
	}

	/** setter de listeVol
	 * 
	 * @param listeVol
	 */
	public void setListeVol(ArrayList<Vol> listeVol) {
		this.listeVol = listeVol;
	}

	/** constructeur de la classe Aeroport sans parametre 
	 * 
	 */
	public Aeroport() {
		listePoint = new ArrayList<Point>();
		listeLine = new ArrayList<Line>();
		listeRunway = new ArrayList<Runway>();
		listeVol = new ArrayList<Vol>();
		listeCollision = new ArrayList<Collision>();
		listePositions = new ArrayList<ArrayList<Position>>();
		listeAvion = new ArrayList<Avion>();
		min = new Coord(0, 0);
		max = new Coord(0, 0);
	}

	/** methode de chargement du fichier d'aeroport 
	 * 
	 * @param ficname nom du fichier
	 */
	public void chargerAeroport(String ficname) {
		Scanner scan = null;

		try {
			scan = new Scanner(new FileReader(ficname));
			scan.useDelimiter("\n");
			/** Extraction du nom de l'aeroport */
			name = scan.next(); 
			listePoint.clear();
			while (scan.hasNext()) {
				Scanner pt = new Scanner(scan.next());
				pt.useDelimiter(" ");
				String element = pt.next();

				/** Si la ligne de texte correspond a un point */
				if (element.equals("P")) {
					String name = pt.next();
					int type = pt.nextInt();
					String coordonnees[];
					coordonnees = pt.next().split(",");
					int x = Integer.parseInt(coordonnees[0]);
					int y = Integer.parseInt(coordonnees[1]);
					y = -y;
					if (x > max.getX())
						max.setX(x);
					if (y > max.getY())
						max.setY(y);
					if (x < min.getX())
						min.setX(x);
					if (y < min.getY())
						min.setY(y);
					listePoint.add(new Point(name, type, new Coord(x, y)));

				}

				/** Si la ligne de texte correspond a une ligne */
				if (element.equals("L")) {
					ArrayList<Coord> listeCoord = new ArrayList<Coord>();
					String name = pt.next();
					int speedlimit = pt.nextInt();
					String category = pt.next();
					String direction = pt.next();
					Scanner pl = new Scanner(pt.next());
					pl.useDelimiter(";"); 
					while (pl.hasNext()) /** tant qu'on trouve des points on les ajoute a la liste */
					{
						String coordonnees[];
						coordonnees = pl.next().split(",");
						int x = Integer.parseInt(coordonnees[0]);
						int y = Integer.parseInt(coordonnees[1]);
						y = -y;
						if (x > max.getX())
							max.setX(x);
						if (y > max.getY())
							max.setY(y);
						if (x < min.getX())
							min.setX(x);
						if (y < min.getY())
							min.setY(y);
						listeCoord.add(new Coord(x, y));
					}
					listeLine.add(new Line(name, speedlimit, category,
							direction, listeCoord));
				}

				/** Si la ligne de texte correspond a un runway */
				if (element.equals("R")) {
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
					int cdX = Integer.parseInt(cd[0], 10);
					int cdY = Integer.parseInt(cd[1], 10);
					cdY = -cdY;
					Coord coordDebut = new Coord(cdX, cdY);
					String cf[];
					cf = coordonnees[1].split(",");
					int cfX = Integer.parseInt(cf[0], 10);
					int cfY = Integer.parseInt(cf[1], 10);
					cfY = -cfY;
					Coord coordFin = new Coord(cfX, cfY);
					/** recuperation des coordonnees min et max */
					if (cdX > max.getX())
						max.setX(cdX);
					if (cdY > max.getY())
						max.setY(cdY);
					if (cdX < min.getX())
						min.setX(cdX);
					if (cdY < min.getY())
						min.setY(cdY);
					if (cfX > max.getX())
						max.setX(cfX);
					if (cfY > max.getY())
						max.setY(cfY);
					if (cfX < min.getX())
						min.setX(cfX);
					if (cfY < min.getY())
						min.setY(cfY);
					ArrayList<Point> listePoint = new ArrayList<Point>();
					Scanner pr = new Scanner(pt.next());
					pr.useDelimiter(";");
					while (pr.hasNext()) /** tant qu'on trouve des points on les ajoute a la liste */
					{
						listePoint
								.add(new Point(pr.next(), 0, new Coord(0, 0)));
					}

					listeRunway.add(new Runway(name, QFU, coordDebut, coordFin,
							listePoint));
				}
			}
			scan.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	/** methode de chargement du fichier de trafic 
	 * 
	 * @param ficname nom du fichier
	 */
	public void chargerTrafic(String ficname) {
		Scanner scan = null;

		try {
			scan = new Scanner(new FileReader(ficname));
			scan.useDelimiter("\n");
			listeVol.clear();
			while (scan.hasNext()) {
				Scanner pt = new Scanner(scan.next());
				pt.useDelimiter(" ");

				ArrayList<Coord> listeCoord = new ArrayList<Coord>();
				String typeVol = pt.next();
				String idVol = pt.next();
				String catAvion = pt.next();
				String nomPoint = pt.next();
				Point p = new Point(nomPoint, 0, new Coord(0,0));
				String QFU = pt.next();
				int tempsDepart = pt.nextInt();
				String tmp = pt.next();
				while (pt.hasNext()) /** tant qu'on trouve des coordonnees on les ajoute a la liste */
				{
					String coordonnees[];
					coordonnees = pt.next().split(",");
					int x = Integer.parseInt(coordonnees[0]);
					int y = Integer.parseInt(coordonnees[1]);
					/** inversion de l'axe des ordonnees */
					y = -y;
					/** ajout des coordonnees a la liste */
					listeCoord.add(new Coord(x, y));
				}
				listeVol.add(new Vol(typeVol, idVol, catAvion, QFU,
						p, tempsDepart, listeCoord));
			}
			
				int n = 0;
				int k = 0;
				while (k <= (60 * 60 * 24)) {
					listePositions.add(new ArrayList<Position>());
					k+=5;
				}
				for (int i = 0; i<listeVol.size(); i++) {
					for (int j = 0; j < listeVol.get(i).getCoordonnees()
							.size(); j++) {
							listeAvion.add(new Avion(0,0, listeVol.get(i).getTemps()+j*5,listeVol.get(i).getIdVol(),listeVol.get(i).getTypeVol()));
							listePositions.get(listeVol.get(i).getTemps()/5+j).add(new Position(listeVol.get(i)
											.getCoordonnees().get(j),listeVol.get(i).getIdVol(), listeAvion.get(n),listeVol.get(i)));
							n++;
					}
				}
				testCollision();
			scan.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}
	
	/** methode qui teste si deux positions d'avions sont en collision 
	 * 
	 * @param a		premiere position a comparer
	 * @param b		deuxieme position a comparer
	 * @return		true si collision, false sinon
	 */
	public boolean testCollision (Position a, Position b) {
		boolean result = false;
		if ((((a.getCoordonnees().getX()-b.getCoordonnees().getX())<=distanceCollision) && ((a.getCoordonnees().getX()-b.getCoordonnees().getX())>=(-distanceCollision))) && (((a.getCoordonnees().getY()-b.getCoordonnees().getY())<=distanceCollision) && ((a.getCoordonnees().getY()-b.getCoordonnees().getY())>=(-distanceCollision)))) {
			result = true;
		}
		else result = false;
		return result;
	}
	
	/** methode de test des collisions sur la journee
	 * met a jour la listeCollision
	 * enregistre le rapport des collisions dans un fichier texte
	 */
	public void testCollision() {
		new Thread(){
			public void run(){	
				/** effacement du contenu de listeCollision */
				listeCollision.clear(); 
				for (int l=0;l<listeVol.size();l++) listeVol.get(l).setCollision(false);
				/** verification des distances entre les avions presents sur la carte a chaque date (toutes les 5 secondes) */
				for (int i=0;i<listePositions.size();i++) { /** pour chaque date */
					for (int j=0;j<listePositions.get(i).size();j++) { /** pour chaque avion present a cette date */
						for (int k=j+1;k<listePositions.get(i).size();k++) { 
							/** appel de la methode testCollision(Position,Position) */
							if (testCollision(listePositions.get(i).get(j), listePositions.get(i).get(k))) {
								listePositions.get(i).get(j).getAvion().setCollision(true);
								listePositions.get(i).get(k).getAvion().setCollision(true);
								ArrayList<String> listeVols = new ArrayList<String>();
								listeVols.add(listePositions.get(i).get(j).getVol().getIdVol());
								listeVols.add(listePositions.get(i).get(k).getVol().getIdVol());
								listeCollision.add(new Collision(listeVols, new Coord(listePositions.get(i).get(j).getCoordonnees().getX(),listePositions.get(i).get(j).getCoordonnees().getY()), i*5));
								/** indique une collision pendant le vol ce jour au niveau des deux vols */
								listePositions.get(i).get(j).getVol().setCollision(true);
								listePositions.get(i).get(k).getVol().setCollision(true);
							}
						}
					}
				}
				
				
				/** ecriture du rapport de collision dans un fichier texte */
				BufferedWriter fs;
				try {
					fs = new BufferedWriter(new FileWriter("rapportCollisions"));
					Iterator<Collision> i = listeCollision.iterator();
					Collision t;
					/** ecriture du nombre de collisions sur la journee */
					Integer l = listeCollision.size();
					String buffer = l.toString() + " collisions";
					fs.write(buffer, 0, buffer.length());
					fs.newLine();
					while (l > 0) {
						t = i.next();
						/** appel de la methode toString() de la classe Collision */
						buffer = t.toString();
						fs.write(buffer, 0, buffer.length());
						fs.newLine();
						l--;
					}
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}