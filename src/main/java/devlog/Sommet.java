package devlog;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
   Classe pour repr�senter les sommets d'un graphe.
   Toutes variables priv�es:
       num�ro,
       forme,
       dates de d�but et fin,
       couleur,
       pere.
*/
public class Sommet
{
    private int numero ;             // num�ro du sommet dans le graphe
    private Ellipse2D.Double forme ; // forme g�om�trique de dessin
    private int dateA ;              // date de d�but de parcours (PP)
    private int dateZ ;              // date de fin de parcours (PP)
    private Color color ;            // couleur du sommet
    private Sommet pere ;            // p�re du sommet dans le graphe
    private double cout ;            // cout depuis l'origine

    static final double diametre=40.0 ;

    /**
       Constructeur par d�faut:
       initialisation de la couleur � non visit�,
       du p�re et forme � null,
       de toutes les autres variables � 0
       sauf cout � infini
    */
    public Sommet()
    {
	pere = null ;
	color = Graphe.colorNonVisite ;
	dateA = dateZ = numero = 0 ;
	forme = null ;
	cout = Graphe.dInfini ;
    }
    /**
	Constructeur � partir de coordonn�es et num�ro: cr�e la forme g�om�trique
    */
    public Sommet( double x, double y, int numero )
    {
	this() ;
	this.numero = numero ;
	forme = new Ellipse2D.Double( x,y,diametre,diametre) ;
    }

    /** setter pour numero */
    public void set_numero( int numero )
    {
	this.numero = numero ;
    }
    /** getter pour numero */
    public int get_numero()
    {
	return numero ;
    }
    /** setter pour forme */
    public void set_forme( Ellipse2D.Double forme )
    {
	this.forme = forme ;
    }
    /** getter pour forme */
    public Ellipse2D.Double get_forme()
    {
	return forme ;
    }
    /** setter pour color */
    public void set_color( Color color )
    {
	this.color = color ;
    }
    /** getter pour color */
    public Color get_color()
    {
	return color ;
    }
    /** setter pour dateA */
    public void set_dateA( int dateA )
    {
	this.dateA = dateA ;
    }
    /** getter pour dateA */
    public int get_dateA()
    {
	return dateA ;
    }
    /** setter pour dateZ */
    public void set_dateZ( int dateA )
    {
	this.dateZ = dateZ ;
    }
    /** getter pour dateA */
    public int get_dateZ()
    {
	return dateZ ;
    }
    /** setter pour pere */
    public void set_pere( Sommet pere )
    {
	this.pere = pere ;
    }
    /** getter pour pere */
    public Sommet get_pere()
    {
	return pere ;
    }
    /** setter pour distance � l'origine */
    public void set_cout( double cout )
    {
	this.cout = cout ;
    }
    /** getter pour distance � l'origine */
    public double get_cout()
    {
	return cout ;
    }
    /** m�thode v�rifiant qu'un point x,y est dans un sommet */
    public boolean contains( double x, double y )
    {
	return forme.contains(x,y) ;
    }
    /** m�thode de dessin, � appeler depuis paint.
	Si la variable showCout du graphe est vraie,
	on affiche les couts, sinon, le num�ro */
    public void draw( Graphics2D g2D )
    {
	String str ;
	Ellipse2D.Double ellipse = get_forme();
	g2D.setPaint( color ) ;
	g2D.fill( ellipse ) ;
	g2D.setPaint( Color.red  ) ;
	if ( Graphe.get_showCout() == false )
	    str = Integer.toString(numero) ;
	else {
	    if ( cout == Graphe.dInfini ) str = new String("x");
	    else str = Double.toString(cout) ;
	}
	g2D.drawString( str ,
			( int ) ellipse.getX() + 15,
			( int ) ellipse.getY() + 20);
    }

}
