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

public class AfficheClient extends JFrame {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JScrollPane aff = new JScrollPane();
  GridLayout gridLayout1 = new GridLayout();
  JButton NouvClient = new JButton();
  JButton Fermer = new JButton();
  JButton Quitter = new JButton();
  GridLayout gridLayout2 = new GridLayout();

  public AfficheClient() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    affi();
    aff.setDebugGraphicsOptions(0);
    jPanel1.setLayout(gridLayout1);
    NouvClient.setText("NOUVEAU CLIENT");
    NouvClient.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        NouvClient(e);
      }
    });

    Fermer.setText("FERMER");
    Fermer.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        fermer(e);
      }
    });

    Quitter.setToolTipText("");
    Quitter.setText("QUITTER");
    Quitter.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    jPanel2.setLayout(gridLayout2);
    this.setSize(new Dimension(841, 452));
    this.setResizable(true);
    this.setTitle("LA LISTE DES CLIENTS");
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(aff, null);
    this.getContentPane().add(jPanel2,  BorderLayout.SOUTH);
    jPanel2.add(Fermer, null);
    jPanel2.add(NouvClient, null);
    jPanel2.add(Quitter, null);
  }
  //fermeture
  public void fermer(ActionEvent e){
    this.dispose();
  }
  //
  public void NouvClient(ActionEvent e){
    this.dispose();
    FenetreClient cl =new FenetreClient();
    cl.setVisible(true);
  }

  private void affi(){
    String nom="";
    Vector v1=new Vector();
    v1.addElement("Reference FenetreClient");
    v1.addElement("Nom du client");
    v1.addElement("Dossier");
    v1.addElement("Adresse du FenetreClient");
    v1.addElement("Contrat");
    v1.addElement("Type de client");
    v1.addElement("Num de Tel du FenetreClient");
    v1.addElement("Num de Fax du FenetreClient");
    v1.addElement("Email du FenetreClient");
    v1.addElement("date");
    v1.addElement("heure");
    LireEtEcrire li =new LireEtEcrire();
    // Vector v=li.ListeLecture("Produit.xls");
    //System.out.println(texte + "contient"+ heure + minute + second);
    //JTable Table = new JTable(v,v1);
    aff= new JScrollPane(li.tableLecture("Clients.xls",v1));
  }



}