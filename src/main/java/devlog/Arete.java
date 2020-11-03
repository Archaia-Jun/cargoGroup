package devlog;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
    Classe pour repr�senter les ar�tes d'un graphe:
    Toutes variables priv�es:
         sommet de d�but,
	 sommet de fin,
	 co�t.
*/
public class Arete
{
    private Sommet debut ; // sommet de d�but
    private Sommet fin ;   // sommet de fin
    private double cout ;      // co�t
    private Color color ; // couleur de dessin
    static final Color lineColor = Color.blue ; // ar�tes dessinn�es en bleu
    static final Color arrowColor = Color.green ; // fl�ches en vert
    static final Color stringColor = Color.red ; // couleur du co�t
    static final double arrowSize = 40.0 ; // taille d'une fl�che

    /** constructeur par d�faut: tout � z�ro, poids � 1 couleur � bleu */
    public Arete()
    {
	debut = null ;
	fin = null ;
	cout = 1.0 ;
	color = lineColor ;
    }
    /** constructeur pour ar�te sans co�t (=1), couleur � bleu */
    public Arete(Sommet debut , Sommet fin )
    {
	this() ;
	this.debut = debut ;
	this.fin = fin ;
	cout = 1 ;
    }
    /** constructeur avec toutes variables sauf couleur � bleu */
    public Arete(Sommet debut , Sommet fin , double cout )
    {
	this( debut , fin ) ;
	this.cout = cout ;
    }
    /** setter pour debut */
    public void set_debut( Sommet debut )
    {
	this.debut = debut ;
    }
    /** getter pour debut */
    public Sommet get_debut()
    {
	return debut ;
    }
    /** setter pour fin */
    public void set_fin( Sommet fin )
    {
	this.fin = fin ;
    }
    /** getter pour fin */
    public Sommet get_fin()
    {
	return fin ;
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
    /** setter pour cout */
    public void set_cout( double cout )
    {
	this.cout = cout ;
    }
    /** getter pour cout */
    public double get_cout()
    {
	return cout ;
    }
    /** methode de dessin, � appeler depuis paint */
    public void draw( Graphics2D g2D )
    {
	// calcul des points de d�part et d'arriv�e de l'ar�te
	Point2D start,end ;
	start = new Point2D.Double() ;
	end = new Point2D.Double() ;
	start.setLocation( (debut.get_forme()).getX() + (debut.get_forme()).getWidth()/2.0 ,
			   (debut.get_forme().getY()) + (debut.get_forme()).getHeight()/2.0 ) ;
	end.setLocation( (fin.get_forme()).getX() + (fin.get_forme()).getWidth()/2.0 ,
			   (fin.get_forme()).getY() + (fin.get_forme()).getHeight()/2.0 ) ;
	// on la dessine
	g2D.setPaint( color ) ;
	if(((debut.get_forme()).getX()==(fin.get_forme()).getX())&&((debut.get_forme()).getY()==(fin.get_forme().getY()))){
		  g2D.draw( new Ellipse2D.Double((debut.get_forme()).getX(),(debut.get_forme()).getY(),(debut.get_forme()).getWidth()*1.5 ,(debut.get_forme()).getHeight()/1.20));

		  //on calcul l'endroit o� l'on va dessiner la fl�che pour les elipse

		  Ellipse2D.Double mml = new Ellipse2D.Double();
		  mml.setFrame((0.33*start.getX()),(0.33*start.getY()), (debut.get_forme()).getWidth()/10.33, (debut.get_forme()).getHeight()/10.332) ;
		  //vecteur normale a origine-->extremite
		   double al = ( double ) start.getX()/5.0;
		   double bl = ( double ) start.getY()/5.0;
		   Ellipse2D.Double nnl = new Ellipse2D.Double();
		   nnl.setFrame(bl/(Math.sqrt(al*al+bl*bl)),-al/(Math.sqrt(al*al+bl*bl)),(debut.get_forme()).getWidth()/4.0,(debut.get_forme()).getHeight()/4.0 ) ;
		   //calcul des points pour la g�ometrie de la fleche
		   Ellipse2D.Double ppl = new Ellipse2D.Double();
		   double x1l = (debut.get_forme()).getX() + arrowSize*nnl.getX();
		   double y1l = (debut.get_forme()).getY() + arrowSize*nnl.getY();
		   double w1l = (debut.get_forme()).getWidth() +arrowSize*nnl.getWidth();
		   double h1l = (debut.get_forme()).getHeight() +arrowSize*nnl.getHeight();
		   double x2l = mml.getX();
		   double y2l = mml.getY();
		   double w2l = mml.getWidth();
		   double h2l = mml.getHeight();
		   ppl.setFrame( (0.1*x1l + 0.9*x2l), (0.1*y1l + 0.9*y2l),(0.1*w1l+0.9*w2l),(0.1*h1l+ 0.9*h2l) );
		   g2D.setPaint( arrowColor );
		   g2D.draw( new Line2D.Double( ppl.getX(),ppl.getY(), mml.getX(),mml.getY()));
		   x1l = (debut.get_forme()).getX() - arrowSize*nnl.getX();
		   y1l = (debut.get_forme()).getY() - arrowSize*nnl.getY();
		   w1l = (debut.get_forme()).getWidth() - arrowSize*nnl.getWidth();
		   h1l = (debut.get_forme()).getHeight() - arrowSize*nnl.getHeight();
		   x2l = mml.getX();
		   y2l = mml.getY();
		   w2l = mml.getWidth();
		   h2l = mml.getHeight();
		   ppl.setFrame( (0.1*x1l + 0.9*x2l), (0.1*y1l + 0.9*y2l),(0.1*w1l + 0.9*w2l),(0.1*h1l + 0.9*h2l) );
		   g2D.setPaint( arrowColor );
		   g2D.draw( new Line2D.Double( ppl.getX(),ppl.getY(),mml.getX(),mml.getY()));
		   g2D.setPaint( stringColor ) ;
		   g2D.drawString( Double.toString( cout ) , (int)mml.getX() + 10 , (int)mml.getY() + 10 ) ;

	}
	else {
		   g2D.draw( new Line2D.Double( start , end ) ) ;
		   // on calcule l'endroit o� l'on va dessiner la fl�che: au 3/5 vers la fin
		   Point2D.Double mm = new Point2D.Double() ;
		   mm.setLocation( (2.0 * start.getX() + 3.0 * end.getX()) / 5.0 ,
			(2.0 * start.getY() + 3.0 * end.getY()) / 5.0 ) ;
		   // vecteur normal a  origine-->extremite
		   double a = ( double ) end.getX() - start.getX();
		   double b = ( double ) end.getY() - start.getY();
		   Point2D.Double nn = new Point2D.Double();
		   nn.setLocation( b/(Math.sqrt(a*a+b*b)) ,
			-a/(Math.sqrt(a*a+b*b)));
		   // calcul des points pour la geometrie de la fleche
		   Point2D.Double pp = new Point2D.Double();
		   double x1 = start.getX() + arrowSize*nn.getX();
		   double y1 =  start.getY() + arrowSize*nn.getY();
		   double x2 = mm.getX();
		   double y2 = mm.getY();
		   pp.setLocation(  0.1*x1 + 0.9*x2, 0.1*y1 + 0.9*y2 );
		   g2D.setPaint( arrowColor );
		   g2D.draw( new Line2D.Double( pp, mm ));
		   x1 = start.getX() - arrowSize*nn.getX();
		   y1 = start.getY() - arrowSize*nn.getY();
		   x2 = mm.getX();
		   y2 = mm.getY();
		   pp.setLocation( 0.1*x1 + 0.9*x2, 0.1*y1 + 0.9*y2 );
		   g2D.setPaint( arrowColor );
		   g2D.draw( new Line2D.Double( (Point2D) pp, (Point2D) mm ));
		   g2D.setPaint( stringColor ) ;
		   g2D.drawString( Double.toString( cout ) , (int)mm.getX() + 10 , (int)mm.getY() + 10 ) ;
	}

    }
}
