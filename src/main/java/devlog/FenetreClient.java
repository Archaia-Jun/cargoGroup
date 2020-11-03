package devlog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import com.borland.jbcl.layout.*;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2020</p>
 * <p>Société : Web</p>
 * @author non attribuable
 * @version 1.0
 */

public class FenetreClient extends JFrame {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  GridLayout gridLayout1 = new GridLayout(1,2);
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JTextField RefClient = new JTextField();
  JTextField NomClient = new JTextField();
  JTextField PreClient = new JTextField();
  JTextField AdreClient = new JTextField();
  Object[]  civ = {"Mme", "Mlle",  "Mr"};
  JComboBox Civil = new JComboBox(civ);
  Object[]   type  = {"CLIENT PARTICULIER","ENTREPRISE"};
  JComboBox Type = new JComboBox(type);
  JTextField NumTelClient = new JTextField();
  JTextField NumFaxClient = new JTextField();
  JTextField EmailClient = new JTextField();
  GridLayout gridLayout2 = new GridLayout(10,1);
  GridLayout gridLayout3 = new GridLayout(10,1);
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  GridLayout gridLayout4 = new GridLayout(2,1);
  JButton Annuler = new JButton();
  JButton Valider = new JButton();
  JButton Quitter = new JButton();
  JButton Fermer = new JButton();
  GridLayout gridLayout5 = new GridLayout();
  GridLayout gridLayout6 = new GridLayout();

  public FenetreClient() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setSize(new Dimension(600, 256));
    jPanel1.setLayout(gridLayout1);
    jLabel1.setText("    REFERENCE CLIENT");
    jLabel2.setText("    NOM DU CLIENT");
    jLabel3.setText("    PRENOM DU CLIENT");
    jLabel4.setText("    ADRESSE");
    jLabel5.setText("    CIVILITE");
    jLabel6.setText("    TYPE");
    jLabel7.setText("    NUMERO DE TEL");
    jLabel8.setText("    NUMERO DE FAX");
    jLabel9.setText("    EMAIL");
    jPanel2.setLayout(gridLayout2);
    jPanel3.setLayout(gridLayout3);
    RefClient.setText("");
    NomClient.setText("");
    PreClient.setText("");
    AdreClient.setText("");
    NumTelClient.setText("");
    NumFaxClient.setText("");
    jPanel4.setLayout(gridLayout4);
    Annuler.setText("ANNULER");
    Annuler.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Annuler(e);
      }
    });
    Valider.setText("VALIDER");
    Valider.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Valider(e);
        Valider2(e);
      }
    });
    Quitter.setToolTipText("");
    Quitter.setText("QUITTER");
    Quitter.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    Fermer.setText("FERMER");
    Fermer.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Fermer(e);
      }
    });
    jPanel6.setLayout(gridLayout5);
    jPanel5.setLayout(gridLayout6);
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel2, null);
    jPanel2.add(jLabel1, null);
    jPanel2.add(jLabel2, null);
    jPanel2.add(jLabel3, null);
    jPanel2.add(jLabel4, null);
    jPanel1.add(jPanel3, null);
    jPanel2.add(jLabel5, null);
    jPanel2.add(jLabel6, null);
    jPanel2.add(jLabel7, null);
    jPanel2.add(jLabel8, null);
    jPanel2.add(jLabel9, null);
    jPanel3.add(RefClient, null);
    jPanel3.add(NomClient, null);
    jPanel3.add(PreClient, null);
    jPanel3.add(AdreClient, null);
    jPanel3.add(Civil, null);
    jPanel3.add(Type, null);
    jPanel3.add(NumTelClient, null);
    jPanel3.add(NumFaxClient, null);
    jPanel3.add(EmailClient, null);
    this.getContentPane().add(jPanel4,  BorderLayout.SOUTH);
    jPanel4.add(jPanel6, null);
    jPanel6.add(Valider, null);
    jPanel6.add(Annuler, null);
    jPanel4.add(jPanel5, null);
    jPanel5.add(Fermer, null);
    jPanel5.add(Quitter, null);
  }

  //annuler
  public void Annuler(ActionEvent e){
    RefClient.setText("");
    NomClient.setText("");
    PreClient.setText("");
    AdreClient.setText("");
    Civil.setSelectedIndex(0);
    Type.setSelectedIndex(0);
    NumTelClient.setText("");
    NumFaxClient.setText("");
    EmailClient.setText("");
  }

  //valider
  // affichage en ligne pour consulter liste clients depuis le logiciel
  public void Valider(ActionEvent e){
    LireEtEcrire et = new LireEtEcrire();
      Format fr = new Format();
      String message;
      message= fr.devFormat(RefClient.getText())+"\t"+fr.devFormat(NomClient.getText())+"\t"+fr.devFormat(PreClient.getText())+"\t"+fr.devFormat(AdreClient.getText())+"\t"+fr.devFormat((String)Civil.getSelectedItem())+"\t"+fr.devFormat((String)Type.getSelectedItem())+"\t"+fr.devFormat(NumTelClient.getText())+"\t"+fr.devFormat(NumFaxClient.getText())+"\t"+fr.devFormat(EmailClient.getText());
      et.ecrire(message,"Clients.xls");
      Annuler(e);
  }

  // affichage avec mise en page pour impression facture
  public void Valider2(ActionEvent e){
    LireEtEcrire et = new LireEtEcrire();
    Format fr = new Format();
    String message;
    message= "\t\n\n" + "REFERENCE_CLIENT: " + fr.devFormat(RefClient.getText())+"\t\n" + "NOM_CLIENT: " +fr.devFormat(NomClient.getText())+"\t\n" + "PRENOM_CLIENT: " + fr.devFormat(PreClient.getText())+"\t\n" + "ADRESSE_CLIENT: " +fr.devFormat(AdreClient.getText())+"\t\n" + "CIVILITE: " +fr.devFormat((String)Civil.getSelectedItem())+"\t\n" + "TYPE: " + fr.devFormat((String)Type.getSelectedItem())+"\t\n" + "NUMERO_CLIENT: " + fr.devFormat(NumTelClient.getText())+"\t\n" + "FAX_CLIENT: " +fr.devFormat(NumFaxClient.getText())+"\t\n" + "MAIL: " + fr.devFormat(EmailClient.getText()) + "\t\n";
    et.ecrire(message,"Clients_imprimer.xls");
    Annuler(e);
  }

  //fermer
  public void Fermer(ActionEvent e){
    this.dispose();
    AfficheClient afc = new AfficheClient();
    afc.setVisible(true);

  }

}
