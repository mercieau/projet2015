import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** classe LecteurVideo
 * herite de JPanel
 * implemente Observer et ChangeListener
 * @author dardekna
 *
 */

/** classe LecteurVideo
 * permet de deployer un lecteur graphique avec un slider, un affichage horaire 
 * et des fonctions de modification du temps(acceleration, changement de sens de parcours..)
 *  dépendant du timer de la classe heure 
 * @author Dardekna
 *
 */
public class LecteurVideo extends JPanel implements Observer,ChangeListener {

	private Container contenu;
	private JPanel p1,p2,p3;
	
	private TimeListener timeListener;
	
	private JLabel t1 =new JLabel();
	/**le slider*/
	private JSlider s1 =new JSlider(0, 86400,0);
	/**les differents boutons utilises*/
	private JButton fast_rewind,rewind,play,fast_forward,restart;
	/**boolean pour renseigner l'etat du bouton play/stop*/
	private boolean play_state=true;
	/**instance de la classe heure*/
	private Heure h;
	
	public LecteurVideo(){
		
		h=new Heure(this);
		
		/************fenetre*****************/
		
		//contenu.setLayout(new java.awt.FlowLayout());
		//contenu.setLayout(new GridLayout(3,1));

		
		/************menu_fenetre*****************/
		
		/**************Panel 1**************/
		
		p1 = new JPanel();	//cree le premier panel
		p1.setLayout(new GridLayout(1,1)); //definition (dimension) du panel
		p1.setBackground(Color.LIGHT_GRAY); //change la couleur de  l'arriere plan
		//p1.setBorder(BorderFactory.createLineBorder(Color.black));
		p1.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.GRAY));
		
		t1.setForeground(Color.RED); // change la couleur du texte
		//t1.setHorizontalTextPosition(JLabel.CENTER);
		t1.setHorizontalAlignment(SwingConstants.CENTER); 
		//t1.setVerticalTextPosition(JLabel.CENTER);

		t1.setText(h.getHeure());
		t1.setFont(new Font("Serif", Font.BOLD, 14)); //police et taille du texte
		p1.add(t1);	
		
		
		/**************Panel 2**************/
		
		p2 = new JPanel(); //cree le deuxiieme panel
		p2.setLayout(new GridLayout(1,1));
		
		/**definitions et creation des differents repere sur le slider*/
		s1.setMajorTickSpacing(3600);
		s1.setMinorTickSpacing(60);
		s1.setPaintTicks(true);
		/*insertion de texte sur les repere du slider grace a la class Hastable*/
		Hashtable labelTable = new Hashtable();
		labelTable.put( new Integer(0), new JLabel("00h") );
		labelTable.put( new Integer(86400/24*6), new JLabel("06h") );
		labelTable.put( new Integer(86400/24*12), new JLabel("12h") );
		labelTable.put( new Integer(86400/24*18), new JLabel("18h") );
		labelTable.put( new Integer(86400), new JLabel("24h") );
		s1.setLabelTable( labelTable );
		s1.setPaintLabels(true);
		
		s1.addChangeListener(this);
		p2.add(s1);		
		
		
		/**************Panel 3**************/
		p3 = new JPanel(); //cree le troisieme panel
		p3.setLayout(new GridLayout(1,2));
		
		/** mdefinitions des differents bouton**/
		fast_rewind = new JButton("Fast Rewind     "); 
		//fast_rewind = new JButton(new ImageIcon("fastrewind.png")); //attendre la fin pou placer les images 
		p3.add(fast_rewind); 
		
		rewind = new JButton("Rewind"); 
		//rewind = new JButton(new ImageIcon("rewind.png")); //attendre la fin pou placer les images 
		p3.add(rewind); 
		
		play = new JButton("Play"); 
		//play = new JButton(new ImageIcon("play.png")); 
		p3.add(play); 						
		
		fast_forward = new JButton("Fast Forward     "); 
		//fast_forward = new JButton(new ImageIcon("fastforward.png")); 
		p3.add(fast_forward); 
		
		restart = new JButton("Restart"); 
		//restart= new JButton(new ImageIcon("restart.png")); 
		p3.add(restart); 						
		
		/*evenement des boutons*/
		fast_rewind.addActionListener(new FastRewindListener());
		rewind.addActionListener(new RewindListener());
		play.addActionListener(new PlayListener());
		fast_forward.addActionListener(new FastForwardListener());
		restart.addActionListener(new RestartListener());

		this.setLayout(new GridLayout(3, 1)); // utilisation d'un panel
		// avec un layout en
		// grille de 3 lignes et
		// 1 colonne
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.setPreferredSize(new Dimension(200,80));
	}
	
	/** setter du pas de h
	 * 
	 * @param pas	pas choisi pour h
	 */
	public void setPasHeure(int pas) {
		h.setPas(pas);
	}
	
	/** getter du pas de h
	 * 
	 * @return pas 	pas de h
	 */
	public int getPasHeure() {
		return h.getPas();
	}
	
	/** methode permettant a une classe utilisatrice de LecteurVideo de savoir lorsque l'heure a evolue
	 * 
	 * @param time	heure actuelle (en secondes)
	 */
    protected void TimeUpdated(int time) {
        timeListener.timeUpdated(time);
    }
    
    /** ajout d'un TimeListener pour savoir lorsque l'heure change
     * 
     * @param listener		listener attribue
     */
    public void addTimeListener(TimeListener listener) {
        timeListener = listener;
    }
    
    /** methode appelee a chaque fois que l'objet observe change d'etat
     * 
     * @param Heure
     * @param arg1
     */
	public void update(Observable Heure, Object arg1) {
		//ces 3 objet observent la classe heure particulierement la variable sec
		/** appel de la methode TimeUpdated pour prevenir les classes utilisatrices */
		TimeUpdated(h.getSec());
		//rafrachir slider 
		s1.setValue(h.getSec());
		//rafranchir heure affichee 
		t1.setText(h.getHeure());
		}		

	/**
	 * methode appelee a chaque fois que le slider change d'etat
	 * 
	 * @param arg0
	 */
	public void stateChanged(ChangeEvent arg0) {
		int val, val_arrondie = 0;
		val = s1.getValue();
		if (val % 5 >= 2.5)
			val_arrondie = val + (5 - (val % 5));
		if (val % 5 < 2.5)
			val_arrondie = val - (val % 5);
		h.setSec(val_arrondie); // sec prend la bonne valeur selon le slider
		t1.setText(h.getHeure()); // rafranchir heure affich�e
	}
	
	/** classe inner pour un appui sur Rewind
	 *
	 */
	class RewindListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			h.setSens(false);
			h.start();
			System.out.println("appui backplay");
		}
	}

	/** classe inner pour un appui sur Play
	 *
	 */
   class PlayListener implements ActionListener {		   
		public void actionPerformed(ActionEvent e)
		{
			if (play_state==true)
			{
				h.setSens(true);
				h.start();
				play_state=false;
				play.setText("Stop");
				//play = new JButton(new ImageIcon("stop.png")); 
				//System.out.println("appui play");
			}
			else{
				h.stop();
				play_state=true;
				play.setText("Play");
				//play = new JButton(new ImageIcon("play.png")); 
			}
		}
   }

	/** classe inner pour un appui sur Stop
	 *
	 */
	class StopListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			h.stop();
		}
	}

	/** classe inner pour un appui sur Restart
	 *
	 */
	class RestartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			s1.setValue(0);
			h.setSec(0);
			t1.setText(h.getHeure());
			h.stop();
			System.out.println("appui restart");
		}
	}
	
	/** classe inner pour un appui sur FastRewind
	 *
	 */
   class FastRewindListener implements ActionListener {		   
		public void actionPerformed(ActionEvent e)
		{
			h.setSens(false);
			if(fast_rewind.getText()=="Fast Rewind     ")
			{
				h.setPas(h.getPas()*2);
				fast_rewind.setText("Fast Rewind x2");
			}
			else if(fast_rewind.getText()=="Fast Rewind x2")
			{
				h.setPas(h.getPas()*2);
				fast_rewind.setText("Fast Rewind x4");
			}
			else if(fast_rewind.getText()=="Fast Rewind x4")
			{
				h.setPas(h.getPas()*2);
				fast_rewind.setText("Fast Rewind x8");
			}
			else if(fast_rewind.getText()=="Fast Rewind x8")
			{
				h.setPas(h.getPas()*2);
				fast_rewind.setText("Fast Rewind x16");
			}
			else if(fast_rewind.getText()=="Fast Rewind x16")
			{
				h.resetPas();
				fast_rewind.setText("Fast Rewind     ");
			}
			h.start();
			System.out.println("appui backplay");
		}
   }
   
	/** classe inner pour un appui sur FastForward
	 *
	 */
   class FastForwardListener implements ActionListener {		   
		public void actionPerformed(ActionEvent e)
		{
			h.setSens(true);
			if(fast_forward.getText()=="Fast Forward     ")
			{
				h.setPas(h.getPas()*2);
				fast_forward.setText("Fast Forward x2");
			}
			else if(fast_forward.getText()=="Fast Forward x2")
			{
				h.setPas(h.getPas()*2);
				fast_forward.setText("Fast Forward x4");
			}
			else if(fast_forward.getText()=="Fast Forward x4")
			{
				h.setPas(h.getPas()*2);
				fast_forward.setText("Fast Forward x8");
			}
			else if(fast_forward.getText()=="Fast Forward x8")
			{
				h.setPas(h.getPas()*2);
				fast_forward.setText("Fast Forward x16");
			}
			else if(fast_forward.getText()=="Fast Forward x16")
			{
				h.resetPas();
				fast_forward.setText("Fast Forward     ");
			}
			h.start();
			System.out.println("appui play");
		}
   }
   
}
