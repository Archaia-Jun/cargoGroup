package devlog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Vector;

/** classe g�rant les graphes: interface graphique, construction de la liste d'adjacence */
public class Graphe extends JFrame
{
    // diff�rentes constantes
    static final int waitForSommet = 0 ;
    static final int waitForDebutArete = 1 ;
    static final int waitForFinArete = 2 ;
    static final int waitForDebutNoSens =3;
    static final int waitForFinNoSens = 4;
    static final float mon_stroke=(float)2.0  ;         // taille du pinceau de dessin
    private final Color backgroundColor = Color.lightGray ;
    /** couleur d'un sommet non visit� */
    public static final Color colorNonVisite = Color.white ;
    /** couleur d'un sommet en cours de visite */
    public static final Color colorEnCours = Color.gray ;
    /** couleur d'un sommet d�j� visit� */
    public static final Color colorDejaVisite = Color.black ;
    /** bool�en indiquant le graphe est fini et pr�t pour les algos */
    protected boolean ready = false ;
    /** infini (int) */
    protected static final int iInfini = 100000 ;
    /** infini (double) */
    protected static final double dInfini = 100000000.0 ;
    /** nil */
    protected static final int nil = -1 ;
    // flag indiquant si on affiche les distances ou les num�ros
    private static boolean showCout = false ;
    /** liste des sommets */
    protected Vector sommetList ; // liste des sommets
    /** liste d'adjacence: vecteur index� par les sommets, chaque �l�ment est une LinkedList
     contenant la liste des ar�tes partant de ce sommet */
    protected Vector adjList ;
    private Vector areteList ; // liste des aretes
    private int curIndexSommet = 0 ; // indice du sommet courant
    private int mouseStatus = waitForSommet ; // ce qu'on fait � la souris
    private Sommet startSommet , endSommet ; // sommets de d�but et fin d'ar�te
    private int indexStartSommet , indexEndSommet ; // leur num�ros
    private double poids = 1.0 ; // poids initial d'une arete
  private String messageStr, messageStr1="rentrez les sommets puis tapez 'a'", welcomeMessageStr="" ;
    private Graphe monInstance = this ;


    /** sp�cification de la fen�tre et �coute clavier/souris , alloc vecteurs*/
    public Graphe ()
    {
	// specification de la fen�tre
	Toolkit theKit = getToolkit();
	Dimension wndSize = theKit.getScreenSize();
	setBounds(wndSize.width/6, wndSize.height/6, 2*wndSize.width/3, 2*wndSize.height/3);
	setDefaultCloseOperation( HIDE_ON_CLOSE ) ;
	setBackground( backgroundColor  ) ;
	setTitle( "Algorithmes de graphe" ) ;
	setVisible(true);
	// �coute clavier/souris
	addMouseListener(new MouseHandler());
	addKeyListener( new KeyHandler());
	// allocation des vecteurs de sommets, d'ar�tes et et adjacence
	sommetList = new Vector() ;
	areteList = new Vector() ;
	adjList = new Vector() ;
        messageStr = "Voulez vous un graphe Oriente 'o'/'n'          Pour une nouvelle fenetre appuyer sur 'e' pour quitter 'q'";

    }

    // methode executable: uniquement un appel au constructeur
    public static void main(String[] args )
    {
	Graphe obj = new Graphe();
    }

    /** m�thode pour le redessin de la fen�tre: sommets et ar�tes */
    public void paint(Graphics g )
    {
	// cr�ation d'un objet Graphics2D
	Graphics2D g2D = (Graphics2D) g;
	// sp�cification de la taille du pinceau de dessin
	g2D.setStroke( new BasicStroke( mon_stroke )  );

	// dessin du message
	show_message(g2D);

	// dessin des ar�tes
	if ( areteList != null )
	    for ( int i=0 ; i<areteList.size() ; i++ ) {
		Arete arete = (Arete)areteList.get(i) ;
		arete.draw( g2D ) ;
	    }
	// dessin des sommets
	if ( sommetList != null )
	    for ( int i = 0 ; i<sommetList.size() ; i++ ) {
		Sommet som = (Sommet)sommetList.get(i) ;
		som.draw( g2D ) ;
	    }
    }
    /** setter pour messageStr : message s'affichant � chaque appel � repaint()  */
    public void set_message( String messageStr )
    {
	this.messageStr = messageStr ;
    }
    /** setter pour welcomeMessageStr: message s'affichant quand le graphe est rentr�,
	cette m�thode doit �tre appel�e par toutes les classes d�riv�es de Graphe */
    public void set_welcomeMessage( String welcomeMessageStr )
    {
	this.welcomeMessageStr = welcomeMessageStr ;
    }
    /** setter pour showCout: si 'true' on affiche la distance � l'origine
	des sommets, sinon, on affiche le num�ro du sommet */
    public static void set_showCout( boolean status )
    {
	showCout = status ;
    }
    /** getter pour showCout */
    public static boolean get_showCout()
    {
	return showCout ;
    }
    /** affichage de la chaine de message */
    public void show_message(Graphics2D g2D)
    {
	g2D.setPaint(backgroundColor ) ;
	g2D.fill( new Rectangle2D.Double( 30,30,600,40) ) ;
	g2D.setPaint(Color.red );
	if ( messageStr != null )
	    g2D.drawString( messageStr , 50, 50 ) ;
    }
    /** pause d'une demi-seconde */
    public void pause()
    {
	try {
	    Thread.sleep(500);
	}
	catch ( InterruptedException e ) {}
    }
    /** pause variable */
    public void pause(int milliSecondes)
    {
	try {
	    Thread.sleep(milliSecondes);
	}
	catch ( InterruptedException e ) {}
    }
    /** conversion en chaine de caract�res de la liste d'adjacence */
    public String toString()
    {
	String retStr ;
	LinkedList sommetAdjList ;
	Sommet cur ;

	retStr = "Structure du graphe\n" ;
	for ( int i=0 ; i<sommetList.size() ; i++ ) {
	    retStr += "Sommet num�ro " + i + " : " ;
	    sommetAdjList = (LinkedList)adjList.get(i) ;
	    for (int j=0 ; j<sommetAdjList.size() ; j++ ) {
		cur = iemeVoisin( i , j ) ;
		retStr += " " + cur.get_numero() + "(" + W(i,cur) + ") "  ;
	    }
	    if ( i<sommetList.size()-1 ) retStr += "\n" ;
	}
	return retStr ;
    }
    /** m�thode donnant le nombre de voisin d'un sommet u */
    public int nVoisins( Sommet u )
    {
	LinkedList sommetAdjList = (LinkedList)adjList.get( u.get_numero() ) ;
	return sommetAdjList.size() ;

    }
    /** m�thode donnant le nombre de voisin du sommet num�ro numSom, retour -1 si i trop grand */
    public int nVoisins( int numSom )
    {
	if ( numSom >= adjList.size() ) return -1 ;
	LinkedList sommetAdjList = (LinkedList)adjList.get( numSom ) ;
	return sommetAdjList.size() ;

    }
    /** m�thode donnant le sommet adjacent num�ro i d'un sommet u.
	Retour null si pas de i�me voisin  */
    public Sommet iemeVoisin(Sommet u , int i )
    {
	LinkedList sommetAdjList = (LinkedList)adjList.get( u.get_numero() ) ;
	if ( i >= sommetAdjList.size() ) return null ;
	Arete ar = (Arete)sommetAdjList.get(i);
	return ar.get_fin() ;
    }
    /** m�thode donnant le sommet adjacent num�ro i d'un sommet num�ro numSom.
	Retour null si pas de i�me voisin ou si numSom trop grand   */
    public Sommet iemeVoisin(int numSom , int i )
    {
	if ( numSom >= adjList.size() ) return null ;
	LinkedList sommetAdjList = (LinkedList)adjList.get( numSom ) ;
	if ( i >= sommetAdjList.size() ) return null ;
	Arete ar = (Arete)sommetAdjList.get(i);
	return ar.get_fin() ;
    }
    /** m�thode donnant le poids de parcours d'un sommet u � un sommet adjacent v.
	Retour infini si sommet non adjacent, le poids de l'ar�te sinon.
	Equivalent de W( Sommet u , Sommet v) � utiliser si on connait d�j� l'index de u */
    public double W(Sommet u , Sommet v )
    {
	Sommet som ;
	int numerodeU = u.get_numero() ;
	return W( numerodeU , v ) ;
    }
   /** m�thode donnant le poids de parcours
       du sommet num�ro numSom � un sommet adjacent v.
       Retour infini si sommet non adjacent ou si pas de sommet num�ro numSom,
       le poids de l'ar�te sinon */
    public double W( int numSom , Sommet v )
    {
	Sommet som ;
	if ( numSom >= sommetList.size() ) return dInfini ;
	LinkedList sommetAdjList = (LinkedList)adjList.get( numSom ) ;
	for (int i=0 ; i<sommetAdjList.size() ; i++ ) {
	    som = iemeVoisin( numSom , i ) ;
	    if ( som.equals( v ) )
		return ( (Arete)sommetAdjList.get(i) ).get_cout() ;
	}
	return dInfini ;

    }
    /** m�thode donnant le poids de parcours depuis le sommet num�ro numSom vers son
	i�me voisin. Retour infini si pas de sommet numSom ou pas de i�me voisin.
	Cette m�thode est plus efficace que les autres impl�mentations de W
	car elle ne n�cessite pas de parcourir les sommets pour trouver la bonne ar�te
	parmi la liste d'adjacence */
    public double W ( int numSom , int i )
    {
	if ( numSom >= sommetList.size() ) return dInfini ;
	LinkedList sommetAdjList = (LinkedList)adjList.get( numSom ) ;
	if ( i>= sommetAdjList.size() ) return dInfini ;
	return ((Arete)sommetAdjList.get(i)).get_cout() ;
    }
    /** m�thode donnant le poids de parcours depuis le sommet u vers son
	i�me voisin. Retour infini si pas de i�me voisin.
	Equivalent de W( Sommet u, int i ), � utiliser si on connait d�j� l'index de u */
    public double W (Sommet u , int i )
    {
	int numSom = u.get_numero() ;
	return W( numSom , i ) ;
    }

    // sous classe pour la gestion de la souris
    class MouseHandler extends MouseAdapter
    {
	private Sommet cur_som ;
	private LinkedList sommetAdjList ;
        private Arete cur_arete,cur2_arete ;
	private boolean found ;

	// m�thode appel�e lors de l'�v�nement 'click'
	public void mousePressed(MouseEvent e)
	{
	    double coord_x = ( double ) e.getX();
	    double coord_y = ( double ) e.getY(); // coordonn�es du point cliqu�

	    switch ( mouseStatus ) {
	    case waitForSommet : // on attend un sommet
		coord_x -= Sommet.diametre / 2.0 ;
		coord_y -= Sommet.diametre / 2.0 ;
		// cr�ation du sommet
		cur_som = new Sommet( coord_x , coord_y , curIndexSommet++ ) ;
		// ajout dans le vecteur de sommets
		sommetList.add( (Object)cur_som ) ;
		// cr�ation de la liste d'adjacence du sommet
		sommetAdjList = new LinkedList() ;
		// ajout � la liste d'adjacence du graphe
		adjList.add( (Object)sommetAdjList ) ;
		break ;

	    case waitForDebutArete : // on attend un d�but d'ar�te
		found = false ; // indique si l'on a bien cliqu� un sommet
		// recherche du sommet cliqu� s'il existe
		for ( int i=0 ; i< sommetList.size() ; i++ ) {
		    cur_som = (Sommet)sommetList.get( i ) ;
		    if ( cur_som.contains( coord_x, coord_y ) ) {
			found = true ;
			indexStartSommet = i ;
			break ;
		    }
		}
		if ( found ) { // on a bien cliqu� un sommet
		    mouseStatus = waitForFinArete ;
		    startSommet = cur_som ;
		}
		else
		    System.out.println("veuillez cliquer dans un sommet");
		break ;

	    case waitForFinArete : // on attend une fin d'ar�te
		found = false ; // indique si l'on a bien cliqu� un sommet
		// recherche du sommet cliqu� s'il existe
		for ( int i=0 ; i< sommetList.size() ; i++ ) {
		    cur_som = (Sommet)sommetList.get( i ) ;
		    if ( cur_som.contains( coord_x, coord_y ) ) {
			found = true ;
			indexEndSommet = i ;
			break ;
		    }
		}
		if ( found ) { // on a bien cliqu� un sommet
		    mouseStatus = waitForDebutArete ;
		    endSommet = cur_som ;
		    // cr�ation de l'ar�te
		    cur_arete = new Arete( startSommet , endSommet , poids ) ;
		    // ajout de l'ar�te � la liste d'adjacence
		    sommetAdjList = (LinkedList)adjList.get( indexStartSommet ) ;
		    sommetAdjList.addLast( (Object) cur_arete ) ;
		    // ajout de l'ar�te � la liste des ar�tes
		    areteList.add( (Object)cur_arete ) ;
		}
		else
		    System.out.println("veuillez cliquer dans un sommet");
	    break ;
	    //***************************************************************************************************
	    case waitForDebutNoSens : // on attend un d�but d'ar�te pour le non orient�
	      found = false ; // indique si l'on a bien cliqu� un sommet
		// recherche du sommet cliqu� s'il existe
		for ( int i=0 ; i< sommetList.size() ; i++ ) {
		    cur_som = (Sommet)sommetList.get( i ) ;
		    if ( cur_som.contains( coord_x, coord_y ) ) {
			found = true ;
			indexStartSommet = i ;
			break ;
		    }
		}
		if ( found ) { // on a bien cliqu� un sommet
		    mouseStatus = waitForFinNoSens ;
		    startSommet = cur_som ;
		}
		else
		    System.out.println("veuillez cliquer dans un sommet");
		break ;
            //***************************************************************************
	    case waitForFinNoSens : // on attend une fin d'ar�te non orien�(double sens)
		found = false ; // indique si l'on a bien cliqu� un sommet
		// recherche du sommet cliqu� s'il existe
		for ( int i=0 ; i< sommetList.size() ; i++ ) {
		    cur_som = (Sommet)sommetList.get( i ) ;
		    if ( cur_som.contains( coord_x, coord_y ) ) {
			found = true ;
			indexEndSommet = i ;
			break ;
		    }
		}
		if ( found ) { // on a bien cliqu� un sommet
		    mouseStatus = waitForDebutNoSens ;
		    endSommet = cur_som ;
		    // cr�ation de l'ar�te
		    cur_arete = new Arete( startSommet , endSommet , poids ) ;
		    cur2_arete = new Arete(endSommet, startSommet, poids);
		    // ajout de l'ar�te � la liste d'adjacence
		    sommetAdjList = (LinkedList)adjList.get( indexStartSommet ) ;
		    sommetAdjList.addLast( (Object) cur_arete ) ;
		    // ajout de l'ar�te � la liste des ar�tes
		    areteList.add( (Object)cur_arete ) ;
		    sommetAdjList = (LinkedList) adjList.get(indexEndSommet);
		    sommetAdjList.addLast((Object) cur2_arete);
		    areteList.add((Object)cur2_arete);
		}
		else
		    System.out.println("veuillez cliquer dans un sommet");
	    break ;
		/*********************************************************/


	    } // switch mouseStatus

	    // force le redessin de la fen�tre
	    repaint() ;

	} // mousePressed

    } // MouseHandler

    // classe pour la gestion du clavier
    class KeyHandler extends KeyAdapter
    {
	public void keyTyped( KeyEvent e )
	{
	  // set_message( );

	    switch ( e.getKeyChar() ) {

	    case 'o':
	      set_message("Graphe orient�");
	      set_message("Creer les sommet et taper 'a'");
	      set_message(messageStr1);
	      repaint();
	      break;
	    case 'n':
	      set_message("Graphe non orient� d�but�");
	      set_message("Creer les sommet et taper 'z'");
	      repaint();
	      break;
	    case 'a' :
		mouseStatus = waitForDebutArete ;
		set_message("la prochaine ar�te aura le poids " + poids + " (r�gler avec +/-), 'd' quand termin�");
		repaint() ;
		break ;
	    case 'z':
	      mouseStatus = waitForDebutNoSens;
		set_message("la prochaine ar�te aura le poids " + poids + " (r�gler avec +/-), 'd' quand termin�");
		repaint() ;
		break ;
	    case 'd':
		System.out.println( monInstance ) ;
		set_message("graphe termin� - " + welcomeMessageStr );
		ready = true ;
		mouseStatus = -1 ;
		repaint() ;
		break ;
	    case '+':
		poids ++ ;
		set_message("la prochaine ar�te aura le poids " + poids + " (r�gler avec +/-), 'd' quand termin�");
		repaint();
		break ;
	    case '-' :
		poids -- ;
		if ( poids < 0 ) poids = 0.0 ;
		set_message("la prochaine ar�te aura le poids " + poids + " (r�gler avec +/-), 'd' quand termin�");
		repaint() ;
		break ;
	    case 'q':
	      //System.exit(0) ;
	      //dispose();
	      setVisible(false);
	    case 'e':
              setVisible(false);
	      new Parcours();
	      break;


	    }// switch e.getKeyChar()

	} // keyTyped

    } // KeyHandler

} // Graphe
