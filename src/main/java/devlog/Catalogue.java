package devlog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>BCC : </p>
 * <p>logiciel gestion : </p>
 * <p>Copyright : Copyright (c) 2020</p>
 * <p>Société : Web Afrique</p>
 * @author JSekpon
 * @version 1.0
 */

public class Catalogue extends JDialog {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  GridLayout gridLayout1 = new GridLayout(1,2);
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField RefCata = new JTextField();
  JTextField NomCata = new JTextField();
  JTextField TypeCata = new JTextField();
  GridLayout gridLayout2 = new GridLayout(3,1);
  GridLayout gridLayout3 = new GridLayout(3,1);
  JPanel jPanel5 = new JPanel();
  JButton Fermer = new JButton();
  JButton Annuler = new JButton();
  JButton Enregistrer = new JButton();
  JButton Quitter = new JButton();
  GridLayout gridLayout4 = new GridLayout();

  public Catalogue(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public Catalogue() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    jPanel2.setLayout(gridLayout1);
    jLabel1.setText("      REFERENCE CATALOGUE");
    jLabel2.setText("      NOM CATALOGUE");
    jLabel3.setText("      TYPE CATALOGUE");
    NomCata.setSelectionStart(11);
    NomCata.setText("");
    jPanel3.setLayout(gridLayout2);
    jPanel4.setLayout(gridLayout3);
    Fermer.setText("FERMER");
    Fermer.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Fermer(e);
      }
    });

    Annuler.setText("ANNULER");
    Annuler.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Annuler(e);
      }
    });

    Enregistrer.setText("ENREGISTRER");
    Enregistrer.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Enregistrer(e);
      }
    });

    Quitter.setText("QUITTER");
    Quitter.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    jPanel1.setLayout(gridLayout4);
    RefCata.setText("");
    this.setResizable(false);
    this.setSize(new Dimension(579, 185));
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(Quitter, null);
    jPanel1.add(Enregistrer, null);
    jPanel1.add(Annuler, null);
    jPanel1.add(Fermer, null);
    this.getContentPane().add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jPanel3, null);
    jPanel3.add(jLabel1, null);
    jPanel3.add(jLabel2, null);
    jPanel3.add(jLabel3, null);
    jPanel2.add(jPanel4, null);
    jPanel4.add(RefCata, null);
    jPanel4.add(NomCata, null);
    jPanel4.add(TypeCata, null);
    this.getContentPane().add(jPanel5, BorderLayout.NORTH);
  }
  //
  public void Fermer(ActionEvent e){
    this.dispose();
  }
  //
  public void Annuler(ActionEvent e){
    RefCata.setText("");
    NomCata.setText("");
    TypeCata.setText("");
  }
  //
  public void Enregistrer(ActionEvent e){
    LireEtEcrire et = new LireEtEcrire();
    Format fr = new Format();
      String message;
      message= fr.devFormat(RefCata.getText())+"\t"+fr.devFormat(NomCata.getText())+"\t"+fr.devFormat(TypeCata.getText());
      et.ecrire(message,"Catalogue.xls");
      Annuler(e);
  }
  //

}
