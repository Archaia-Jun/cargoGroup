package devlog;

import com.google.zxing.WriterException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

/**
 * <p>BCC : </p>
 * <p>logiciel gestion : </p>
 * <p>Copyright : Copyright (c) 2020</p>
 * <p>Société : Web Afrique</p>
 * @author JSekpon
 * @version 1.0
 */


public class AfficheCommandes extends JFrame {
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JScrollPane aff = new JScrollPane();
    GridLayout gridLayout1 = new GridLayout();
    JButton NouvCommande = new JButton();
    JButton Fermer = new JButton();
    JButton Quitter = new JButton();
    GridLayout gridLayout2 = new GridLayout();

    public AfficheCommandes() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        affi();
        aff.setDebugGraphicsOptions(0);
        jPanel1.setLayout(gridLayout1);
        NouvCommande.setText("NOUVELLE COMMANDE");
        NouvCommande.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NouvCommande(e);
            }
        });

        Fermer.setText("FERMER");
        Fermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fermer(e);
            }
        });

        Quitter.setToolTipText("");
        Quitter.setText("QUITTER");
        Quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jPanel2.setLayout(gridLayout2);
        this.setSize(new Dimension(841, 452));
        this.setResizable(true);
        this.setTitle("LA LISTE DES COMMANDES");
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
        jPanel1.add(aff, null);
        this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
        jPanel2.add(Fermer, null);
        jPanel2.add(NouvCommande, null);
        jPanel2.add(Quitter, null);
    }

    //fermeture
    public void fermer(ActionEvent e) {
        this.dispose();
    }

    //
    public void NouvCommande(ActionEvent e) {
        this.dispose();
        FenetreCommande co = null;
        try {
            co = new FenetreCommande();
        } catch (WriterException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        co.setVisible(true);
    }

    private void affi() {
        String nom = "";
        Vector v1 = new Vector();
        v1.addElement("NUMERO FACTURE");
        v1.addElement("NOM DU CLIENT");
        v1.addElement("DOSSIER");
        v1.addElement("POIDS BRUT");
        v1.addElement("DEBOURS / MAINS D'OEUVRE");
        v1.addElement("PRESTATION");
        v1.addElement("MONTANT HT");
        v1.addElement("TVA");
        v1.addElement("MONTANT TVA");
        v1.addElement("MONTANT TTC");
        v1.addElement("AIB");
        v1.addElement("NET A PAYER");
        LireEtEcrire li = new LireEtEcrire();
        // Vector v=li.ListeLecture("Produit.xls");
        //System.out.println(texte + "contient"+ heure + minute + second);
        //JTable Table = new JTable(v,v1);
        aff = new JScrollPane(li.tableLecture("Commandes.xls", v1));
    }

}
/*

public class Commandes extends JDialog {
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JComboBox Ref = new JComboBox();
  JLabel jLabel1 = new JLabel();
  JTextField Quant = new JTextField();
  JLabel jLabel7 = new JLabel();
  JTextField Montant = new JTextField();
  JPanel jPanel7 = new JPanel();
  GridLayout gridLayout1 = new GridLayout(5,2);
  JLabel jLabel6 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JTextField NomPro = new JTextField();
  JLabel jLabel5 = new JLabel();
  JPanel jPanel1 = new JPanel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JPanel jPanel5 = new JPanel();
  JLabel jLabel4 = new JLabel();
  GridLayout gridLayout2 = new GridLayout(3,2);
  JTextField jTextField1 = new JTextField();
  JLabel jLabel10 = new JLabel();
  JTextArea jTextArea1 = new JTextArea();
  JLabel jLabel11 = new JLabel();
  JTextField jTextField2 = new JTextField();

  public Commandes(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public Commandes() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    jLabel1.setText("R�f�rence Produit");
    Quant.setText("jTextField4");
    jLabel7.setText("Nom du produit");
    Montant.setText("jTextField6");
    jPanel7.setLayout(gridLayout1);
    jLabel6.setText("type du PRODUIT");
    jLabel9.setText("Prix");
    NomPro.setText("jTextField5");
    jLabel5.setText("dESCRIPTION");
    panel1.setMinimumSize(new Dimension(314, 375));
    panel1.setPreferredSize(new Dimension(314, 375));
    jButton1.setText("jButton1");
    jButton2.setText("jButton2");
    jLabel4.setText("jLabel4");
    jPanel5.setLayout(gridLayout2);
    jTextField1.setText("jTextField1");
    jLabel10.setText("jLabel10");
    jTextArea1.setText("jTextArea1");
    jLabel11.setText("jLabel11");
    jTextField2.setText("jTextField2");
    panel1.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jButton1, null);
    jPanel2.add(jButton2, null);
    panel1.add(jPanel3, BorderLayout.WEST);
    panel1.add(jPanel4, BorderLayout.EAST);
    panel1.add(jPanel7,  BorderLayout.CENTER);
    jPanel7.add(jLabel1, null);
    jPanel7.add(Ref, null);
    jPanel7.add(jLabel7, null);
    jPanel7.add(NomPro, null);
    jPanel7.add(jLabel6, null);
    jPanel7.add(Quant, null);
    jPanel7.add(jLabel5, null);
    jPanel7.add(jPanel5, null);
    jPanel5.add(jLabel4, null);
    jPanel5.add(jTextField1, null);
    jPanel5.add(jLabel10, null);
    jPanel5.add(jTextArea1, null);
    jPanel5.add(jLabel11, null);
    jPanel5.add(jTextField2, null);
    jPanel7.add(jLabel9, null);
    jPanel7.add(Montant, null);
    panel1.add(jPanel1, BorderLayout.NORTH);
    this.getContentPane().add(panel1, BorderLayout.CENTER);
  }
}
*/