package devlog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

//import com.borland.jbcl.layout.*;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2006</p>
 * <p>Soci�t� : </p>
 * @author non attribuable
 * @version 1.0
 */

public class AfficheProduit extends JFrame {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  JComboBox NomProduit = new JComboBox();
  JButton Affiche = new JButton();
  BorderLayout borderLayout2 = new BorderLayout();
  JScrollPane aff;
  GridLayout gridLayout1 = new GridLayout();

  public AfficheProduit() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {

    jPanel2.setLayout(borderLayout1);
    jLabel1.setText("         NOM DU PRODUIT");
    jPanel4.setLayout(gridLayout1);
    Affiche.setText("DEMONSTRATION");
    Affiche.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Affiche_actionPerformed(e);
      }
    });
    this.setSize(new Dimension(770, 376));
    this.setResizable(false);
    this.setTitle("LISTE DES PRODUITS");
    lecombo();
    jPanel1.setLayout(borderLayout2);
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    aff();
    jPanel1.add(aff, BorderLayout.CENTER);
    this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jPanel4, BorderLayout.CENTER);
    jPanel4.add(jLabel1, null);
    jPanel4.add(NomProduit, null);
    jPanel4.add(Affiche, null);
    this.getContentPane().add(jPanel3, BorderLayout.NORTH);

  }
  //String d'affichage
  public String lacle(){
    String lcl;
    lcl=(String)NomProduit.getSelectedItem();
    return lcl;
  }

  public void Affiche_actionPerformed(ActionEvent e){
    this.dispose();
    Produit prod = new Produit();

    prod.setVisible(true);
  }
  //lecture et affichage

  private void aff(){
    String nom="";
    Vector v1=new Vector();
    v1.addElement("Reference du Produit");
    v1.addElement("Nom du Produit");
    v1.addElement("Quantit� du produit");
    v1.addElement("Reference Catalogue");
    v1.addElement("Image du Produit");
    v1.addElement("Pr�sentation du produit");
    v1.addElement("Type du produit");
    v1.addElement("Prix du produit");
    v1.addElement("Description");
    v1.addElement("date");
    v1.addElement("heure");
    LireEtEcrire li =new LireEtEcrire();
    // Vector v=li.ListeLecture("Produit.xls");
    //System.out.println(texte + "contient"+ heure + minute + second);
    //JTable Table = new JTable(v,v1);
    aff= new JScrollPane(li.tableLecture("Produit.xls",v1));
  }
  //
  public void lecombo(){
    LireEtEcrire le = new LireEtEcrire();
    int a=0;
    int i=0;
    String mot="";
    while(a<le.ListeLecture("Produit.xls").size()){
      NomProduit.addItem(le.ListeLecture("Produit.xls").elementAt(i));
      a++;
      i++;
    }
  NomProduit.revalidate();
 }

}
