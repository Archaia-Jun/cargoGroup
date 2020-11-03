package devlog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2006</p>
 * <p>Soci�t� : </p>
 * @author non attribuable
 * @version 1.0
 */

public class Accueil extends JFrame {
  JPanel jPanel1 = new JPanel();
  JPanel Main = new JPanel();
  JPanel jPanel3 = new JPanel();
  GridLayout gridLayout1 = new GridLayout(2,2);
  JButton Client = new JButton();
  JButton Produit = new JButton();
  JButton Itineraire = new JButton();
  JButton Commande = new JButton();
  JButton Quitter = new JButton();
  BorderLayout borderLayout1 = new BorderLayout();
  JButton Admin = new JButton();
  BorderLayout borderLayout2 = new BorderLayout();

  public Accueil() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    Accueil accueil = new Accueil();
    accueil.setVisible(true);
  }
  private void jbInit() throws Exception {
    Main.setLayout(gridLayout1);
    Client.setFont(new Font("Dialog", 1, 14));
    Client.setText("CLIENT");
    Client.addActionListener(new ActionListener()  {
          public void actionPerformed(ActionEvent e) {
            Client_actionPerformed(e);
          }
        });

    Produit.setFont(new Font("Dialog", 1, 14));
    Produit.setText("PRODUIT");
    Produit.addActionListener(new ActionListener()  {
          public void actionPerformed(ActionEvent e) {
            Produit_actionPerformed(e);
          }
        });

    Itineraire.setFont(new Font("Dialog", 1, 14));
    Itineraire.setText("ITINERAIRE");
    Itineraire.addActionListener(new ActionListener()  {
          public void actionPerformed(ActionEvent e) {
            Itin_actionPerformed(e);
          }
        });

    Commande.setFont(new Font("Dialog", 1, 14));
    Commande.setText("COMMANDE");
    Commande.addActionListener(new ActionListener()  {
          public void actionPerformed(ActionEvent e) {
            Commande_actionPerformed(e);
          }
        });

    Quitter.setFont(new Font("Dialog", 1, 15));
    Quitter.setForeground(Color.red);
    Quitter.setToolTipText("");
    Quitter.setVerifyInputWhenFocusTarget(true);
    Quitter.setText("QUITTER");
    Quitter.addActionListener(new ActionListener()  {
          public void actionPerformed(ActionEvent e) {
           System.exit(0);
          }
        });

    jPanel1.setLayout(borderLayout1);
    Admin.setText("ADMINISTRATION");
    Admin.addActionListener(new ActionListener()  {
          public void actionPerformed(ActionEvent e) {
           Admin_actionPerformed(e);
          }
        });

    jPanel3.setLayout(borderLayout2);
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(Quitter, BorderLayout.CENTER);
    this.getContentPane().add(Main, BorderLayout.CENTER);
    Main.add(Client, null);
    Main.add(Commande, null);
    Main.add(Itineraire, null);
    Main.add(Produit, null);
    this.getContentPane().add(jPanel3, BorderLayout.NORTH);
    jPanel3.add(Admin, BorderLayout.CENTER);
    this.setSize(600,200);
  }
  //client
  public void Client_actionPerformed(ActionEvent e) {
    //this.dispose();
    AfficheClient cadre=new AfficheClient();
    cadre.setVisible(true);
  }

  //commande
  public void Commande_actionPerformed(ActionEvent e) {
    //this.dispose();
    AfficheCommandes cadre=new AfficheCommandes();
    cadre.setVisible(true);
  }
  //LOGIN
  public void Admin_actionPerformed(ActionEvent e) {
    //this.dispose();
    Log cadre=new Log();
    cadre.setVisible(true);
  }

//client
  public void Produit_actionPerformed(ActionEvent e) {
    //this.dispose();
    AfficheProduit cadre=new AfficheProduit();
    cadre.setVisible(true);
  }
//client
  public void Itin_actionPerformed(ActionEvent e) {
   Parcours p =new Parcours();
  }
//Red�fini, ainsi nous pouvons sortir quand la fen�tre est ferm�e
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

}
