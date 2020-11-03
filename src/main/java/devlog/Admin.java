package devlog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2006</p>
 * <p>Soci�t� : </p>
 * @author non attribuable
 * @version 1.0
 */

public class Admin extends JFrame {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JButton SaisiProduit = new JButton();
  JButton SaisiClient = new JButton();
  JButton SaisiCatalogue = new JButton();
  GridLayout gridLayout1 = new GridLayout(10,1);
  JButton SaisiAdmin = new JButton();
  JButton Quitter = new JButton();
  JButton Fermer = new JButton();
  GridLayout gridLayout2 = new GridLayout();

  public Admin() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setResizable(false);
    this.setSize(new Dimension(600, 373));
    this.setTitle("ADMINISTRATION");
    SaisiProduit.setText("SAISIE DE PRODUIT");
    SaisiProduit.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        SaisiProduit(e);
      }
    });

    SaisiClient.setText("SAISIE DE  CLIENT");
    SaisiClient.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        SaisiClient(e);
      }
    });

    SaisiCatalogue.setText("SAISIE DE  CATALOGUE");
    SaisiCatalogue.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        SaisiCatalogue(e);
      }
    });

    jPanel2.setLayout(gridLayout1);
    SaisiAdmin.setText("SAISIE ADMINISTRATEUR");
    SaisiAdmin.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        saisiAdmin(e);
      }
    });

    Quitter.setText("QUIITTER");
    Quitter.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
       System.exit(0);
      }
    });

    Fermer.setText("FERMER");
    Fermer.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Fermer_actionPerformed(e);
      }
    });

    jPanel1.setLayout(gridLayout2);
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(Fermer, null);
    jPanel1.add(Quitter, null);
    this.getContentPane().add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(SaisiProduit, null);
    jPanel2.add(SaisiClient, null);
    jPanel2.add(SaisiCatalogue, null);
    this.getContentPane().add(jPanel3, BorderLayout.NORTH);
    jPanel2.add(SaisiAdmin, null);
  }
//fermer
  public  void Fermer_actionPerformed(ActionEvent e){
    this.dispose();
  }
  //saisi produit
  public  void SaisiProduit(ActionEvent  e){
    SaisiProduit sprod = new SaisiProduit();
    sprod.setVisible(true);
  }
  //saisi catalogue
  public  void SaisiCatalogue(ActionEvent  e){
    Catalogue cata = new Catalogue();
    cata.setVisible(true);
  }
//saisi client
  public  void SaisiClient(ActionEvent  e){
    FenetreClient c =new FenetreClient();
    c.setVisible(true);

  }
//saisi Admin
  public void saisiAdmin(ActionEvent e){

  }

}