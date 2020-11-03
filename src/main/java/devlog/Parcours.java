package devlog;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;


/**
   classe effectuant le parcours en largeur ou en profondeur
*/
public class Parcours extends Graphe implements Runnable
{
    // variables des parcours
    private int d[] ;
    private int p[] ;
    private int Fd[];
    private LinkedList F ;
    private Sommet pointInitial ;
    private int indexPointInitial ;
    private int tps ;
    // flag d'algo � lancer
    private boolean startPP = false ;
    private boolean startPL = false ;


    // pour la synchro
    private Thread runner ;

    // executable: appel au constructeur
    public static void main(String[] args ) {
	new Parcours();
    }

    /**
	�coutes suppl�mentaires du clic et du clavier, message de bienvenu
    */
    public Parcours()
    {
	// �coute clavier/souris
	addMouseListener( new MouseHandler() );
	addKeyListener( new KeyHandler() );
	// message
	set_welcomeMessage("'l' puis un sommet pour le PL, 'p' pour le PP");
    }

    /** synchronisation: cr�ation et lancement d'un thread */
    public void start()
    {
	runner=new Thread(this);
	runner.start();
    }
    /** m�thode run du thread : appel du parcours en largeur */
    public void run()
    {
	Thread thisThread = Thread.currentThread() ;
	if ( runner==thisThread ) {
	    if (startPL) PL() ;
	    if (startPP) PP() ;
	}

    }
    /** effectue un parcours en largeur depuis un sommet, et l'anime */
    public void PL()
    {
	System.out.println("parcours en largeur � partir du sommet " +
			   indexPointInitial ) ;
	d       = new int[sommetList.size()];
	p       = new int[sommetList.size()];
	F       = new LinkedList();

	for( int i = 0; i < sommetList.size() ; i++ ) {
	    Sommet g_som = (Sommet) sommetList.get(i);
	    if( g_som.equals(pointInitial) == false ) {
		g_som.set_color( colorNonVisite ) ;
		d[i] = Graphe.iInfini ;
		p[i] = Graphe.nil ;
	    }
	}

	int s = pointInitial.get_numero();
	pointInitial.set_color(colorEnCours) ;

	repaint() ;
	pause() ;

	d[s] = 0;
	p[s] = Graphe.nil;
	F.add((Object)pointInitial);// A decommenter apres avoir ecrit le code de la boucle while ci dessous
	Sommet u;

	while( F.size() != 0 ) {
	   u =(Sommet) F.getFirst();
	  //for(int i=0;i<adjList.size();i++){
	  //for(int i=0;i<this.nVoisins(u);i++){
	  //System.out.println("valeur de sommet liste size "+sommetList.size());
	  //System.out.println("valeur de adj liste size "+adjList.size());
	  for(int i=0;i<nVoisins(u);i++){
	    Sommet v= iemeVoisin(u,i);
	    if (v.get_color()==colorNonVisite){
	      v.set_color(colorEnCours);
	      repaint();
	      pause();
	      d[v.get_numero()]=d[u.get_numero()]+1;
	      p[v.get_numero()]=u.get_numero();
	      F.add((Object)v);
	    }
	  }
	 F.removeFirst();
	u.set_color(colorDejaVisite);
	repaint();
	pause();
	}


    }

    /** parcours en profondeur: tout le graphe */
    public void PP()
    {
      Sommet vpp_som;
      //System.out.println("parcours en profondeur non impl�ment�");
      p = new int[sommetList.size()];
      for (int i = 0; i < sommetList.size(); i++) {
        vpp_som = (Sommet) sommetList.get(i);
        if (vpp_som.equals(pointInitial) == false) {
          vpp_som.set_color(colorNonVisite);
          p[i] = Graphe.nil;
        }
      }
      repaint();
      pause();
      tps = 0;
      for (int i = 0; i < sommetList.size(); i++) {
        vpp_som = (Sommet) sommetList.get(i);
        if (vpp_som.get_color() == colorNonVisite) {
          Visiter_PP(vpp_som);
          if ( ( (nVoisins(vpp_som)) != 0) &&(vpp_som.get_pere()==null)) {
            vpp_som.set_color(Color.yellow);
            repaint();
           pause();
          }
        }
        //      repaint();
      }
    }





//visiter pp

public void Visiter_PP(Sommet sommet_pp)
  {

    Sommet v;
    sommet_pp.set_color(colorEnCours);
    repaint();
    pause();
    	d       = new int[sommetList.size()];
	p       = new int[sommetList.size()];
	Fd       = new int[sommetList.size()];
	d[sommet_pp.get_numero()]=tps;
	tps++;
	  int nbr_vois = nVoisins(sommet_pp);
	  for (int i=0;i<nbr_vois;i++)
	    {
	      v = iemeVoisin(sommet_pp,i);
	      if (v.get_color()==colorNonVisite)
		{
		  p[v.get_numero()]=sommet_pp.get_numero();
		  Visiter_PP(v);
		}
	    }
	      sommet_pp.set_color(colorDejaVisite);
	      repaint();
	      pause();

	      Fd[sommet_pp.get_numero()]=tps;
	      tps++;
  }





    // sous classe pour la gestion de la souris
    class MouseHandler extends MouseAdapter
    {
	private boolean found ;
	private Sommet cur_som ;
	// m�thode appel�e lors de l'�v�nement 'click'
	public void mousePressed(MouseEvent e)
	{
	    double coord_x = ( double ) e.getX();
	    double coord_y = ( double ) e.getY(); // coordonn�es du point cliqu�

	    // il faut que le graphe soit termin�
	    if ( !ready ) return ;

	    if ( startPL ) {
		// on cherche le sommet de d�part
		found = false ; // indique si l'on a bien cliqu� un sommet
		// recherche du sommet cliqu� s'il existe
		for ( int i=0 ; i< sommetList.size() ; i++ ) {
		    cur_som = (Sommet)sommetList.get( i ) ;
		    if ( cur_som.contains( coord_x, coord_y ) ) {
			found = true ;
			indexPointInitial = i ;
			break ;
		    }
		}
		if ( found ) { // on a bien cliqu� un sommet
		    pointInitial = cur_som ;
		    start() ;
		}
		else
		    System.out.println("veuillez cliquer dans un sommet");
	    } // if startPL

	} // mousePressed
    } // MouseHandler

    class KeyHandler extends KeyAdapter
    {
	public void keyTyped( KeyEvent e )
	{
	    // il faut que le graphe soit termin�
	    if ( !ready ) return ;

	    switch ( e.getKeyChar() ) {
	    case 'l' : // on demande le parcours en largeur
		startPL = true ;
		startPP = false ;
		break ;
	    case 'p':
		startPP = true ;
		startPL = false ;
		start() ;
		break ;
	    } // switch
	} // keyTyped
    } // KeyHandler

} // class Parcours

