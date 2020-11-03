package devlog;

import com.google.zxing.WriterException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.StringTokenizer;

//import com.borland.jbcl.layout.*;

/**
 * <p>BCC : </p>
 * <p>logiciel gestion : </p>
 * <p>Copyright : Copyright (c) 2020</p>
 * <p>Société : Web Afrique</p>
 * @author JSekpon
 * @version 1.0
 */

public class Produit extends JFrame {
  JPanel jPanel1 = new JPanel();
  JPanel ContentButton = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JComboBox NumProduit = new JComboBox();
  JTextField NomProduit = new JTextField();
  JTextField TypeProduit = new JTextField();
  JTextArea Description = new JTextArea();
  JTextArea Image = new JTextArea();
  JTextField Prix = new JTextField();
  GridLayout gridLayout2 = new GridLayout(6,1);
  GridLayout gridLayout3 = new GridLayout(6,1);
  JLabel jLabel5 = new JLabel();
  JLabel Image1;
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel5 = new JPanel();
  JButton Commande = new JButton();
  JButton Apercu = new JButton();
  JPanel jPanel6 = new JPanel();
  JButton Quitter = new JButton();
  JButton Fermer = new JButton();
  GridLayout gridLayout4 = new GridLayout();
  GridLayout gridLayout5 = new GridLayout();
  GridLayout gridLayout6 = new GridLayout(2,1);
  ImageIcon  ic;


  public Produit() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    ic = new ImageIcon("images/benz.jpg");
    Image1 = new JLabel(ic);
    this.setResizable(false);
    this.setSize(new Dimension(600, 218));
    jPanel1.setLayout(gridLayout1);
    jLabel6.setText("                    Numero du Produit");
    jLabel1.setText("                    NOM DU  PRODUIT");
    jLabel2.setText("                    TYPE DU PRODUIT");
    jLabel3.setText("                    DESCRIPTION");
    jLabel4.setText("                    PRIX");

    NomProduit.setText(" ");
    TypeProduit.setText(" ");
    ic = new ImageIcon("images/benz.jpg");
    Image.setText(" ");

    jPanel3.setLayout(gridLayout2);
    jPanel4.setLayout(gridLayout3);
    jLabel5.setText("");
    Description.setText("");
    Prix.setText("");

    Commande.setText("COMMANDER");
    Commande.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        FenetreCommande cm = null;
        try {
          cm = new FenetreCommande();
        } catch (WriterException e1) {
          e1.printStackTrace();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
        cm.setVisible(true);
      }
    });

    Apercu.setText("CLIQUER ICI POUR UNE DEMONSTRACTION");
    Apercu.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        son();
      }
    });
    Quitter.setDoubleBuffered(false);
    Quitter.setText("QUITTER");
    Quitter.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    Fermer.setToolTipText("");
    Fermer.setText("FERMER");
    Fermer.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Fermer_actionPerformed(e);
      }
    });
    ContentButton.setLayout(gridLayout6);
    jPanel5.setLayout(gridLayout4);
    jPanel6.setLayout(gridLayout5);
    ContentButton.add(jPanel5, null);
    jPanel5.add(Commande, null);
    jPanel5.add(Apercu, null);
    ContentButton.add(jPanel6, null);
    jPanel6.add(Quitter, null);
    jPanel6.add(Fermer, null);
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel3, null);
    jPanel3.add(jLabel6,null);
    jPanel3.add(jLabel1, null);
    jPanel3.add(jLabel2, null);
    jPanel3.add(jLabel3, null);
    jPanel3.add(jLabel4, null);
    jPanel3.add(jLabel5, null);
    jPanel1.add(jPanel4, null);
    jPanel1.add(Image1, null);
    jPanel4.add(NumProduit,null);
    jPanel4.add(NomProduit, null);
    jPanel4.add(TypeProduit, null);
    jPanel4.add(Description, null);
    jPanel4.add(Prix, null);
    this.getContentPane().add(ContentButton,  BorderLayout.SOUTH);
      //son();
  }

  //fermer
  public void Fermer_actionPerformed(ActionEvent e){
    this.dispose();
  }
  //jouer son
  public void son(){
	Son s = new Son("abcd.wav");
	//s.run();
  }

  void Apercu() {
    String val;
    String v1,v2,v3,v4,v5,v6,v7,v8,v9;
    AfficheProduit af= new AfficheProduit();
    val= af.lacle();
    StringTokenizer tk = new StringTokenizer(val,"\t");
    NumProduit.addItem( tk.nextToken());
    NomProduit.setText(tk.nextToken());
    TypeProduit.setText(tk.nextToken());
    Description.setText(tk.nextToken());
    Image.setText(tk.nextToken());
    Prix.setText(tk.nextToken());
    ic = new ImageIcon("images/benz.jpg");
  }





}
