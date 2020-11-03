package devlog;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//import com.borland.jbcl.layout.*;


public class SaisiProduit extends JFrame {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel LesLables = new JPanel();
  JPanel LesTextFields = new JPanel();
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
  GridLayout gridLayout2 = new GridLayout(10,1);
  GridLayout gridLayout3 = new GridLayout(10,1);
  JTextField RefProduit = new JTextField();
  JTextField NomProduit = new JTextField();
  JTextField QuanProduit = new JTextField();
  Object [] ra={"default","tout_produit"};
  JComboBox RefCatalogue = new JComboBox(ra);
  JPanel ImageProduit = new JPanel();
  JTextField NomFichier = new JTextField();
  JButton Parcourir = new JButton();
  JPanel presentationProduit = new JPanel();
  JTextField TypeProduit = new JTextField();
  JTextField PrixProduit = new JTextField();
  JTextField DesProduit = new JTextField();
  JTextField NomFichier2 = new JTextField();
  JButton Parcourir2 = new JButton();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  JButton Fermer = new JButton();
  JButton Annuler = new JButton();
  JButton Enregistrer = new JButton();
  JButton Quitter = new JButton();
  GridLayout gridLayout4 = new GridLayout();

  public SaisiProduit(){
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }
  private void jbInit() throws Exception {
    this.setResizable(false);
    this.setSize(new Dimension(638, 300));
    this.setTitle("       SAISIE DES PRODUITS");
    jPanel1.setLayout(gridLayout1);
    jLabel1.setText("     REFERENCE  DU PRODUIIT");
    jLabel2.setText("     NOM DU PRODUIIT");
    jLabel3.setText("     QUANTITE DISPONIIBLE");
    jLabel4.setText("     REFERENCE  CATALOGUE");
    jLabel5.setText("     IMAGE DU PRODUIT");
    jLabel6.setText("     PRESENTATION  DU PRODUIT");
    jLabel7.setText("     TYPE  DU PRODUIT");
    jLabel8.setText("     PRIX  DU PRODUIT");
    jLabel9.setText("     DESCRIPTION");
    LesLables.setLayout(gridLayout2);
    LesTextFields.setLayout(gridLayout3);
    Parcourir.setText("Parcourir");
    Parcourir.addActionListener(new ActionListener()  {
          public void actionPerformed(ActionEvent e) {
	    RetournePath(NomFichier,"wav");
          }
        });

    ImageProduit.setLayout(borderLayout2);
    NomFichier.setText("");
    RefProduit.setText("");
    NomProduit.setText("");
    QuanProduit.setText("");
    Parcourir2.setText("Parcourir");
    Parcourir2.addActionListener(new ActionListener()  {
          public void actionPerformed(ActionEvent e) {
	    RetournePath(NomFichier2,"jpg");
          }
        });

    presentationProduit.setLayout(borderLayout1);
    NomFichier2.setText("");
    TypeProduit.setText("");
    PrixProduit.setText("");
    DesProduit.setText("");
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
    jPanel2.setLayout(gridLayout4);
    ImageProduit.add(NomFichier, BorderLayout.CENTER);
    ImageProduit.add(Parcourir, BorderLayout.EAST);

    presentationProduit.add(NomFichier2, BorderLayout.CENTER);
    presentationProduit.add(Parcourir2, BorderLayout.EAST);
    lecombo();
    this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(Quitter, null);
    jPanel2.add(Enregistrer, null);
    jPanel2.add(Annuler, null);
    jPanel2.add(Fermer, null);
    this.getContentPane().add(jPanel3, BorderLayout.NORTH);
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(LesLables, null);
    LesLables.add(jLabel1, null);
    LesLables.add(jLabel2, null);
    LesLables.add(jLabel3, null);
    LesLables.add(jLabel4, null);
    LesLables.add(jLabel5, null);
    LesLables.add(jLabel6, null);
    LesLables.add(jLabel7, null);
    LesLables.add(jLabel8, null);
    LesLables.add(jLabel9, null);
    jPanel1.add(LesTextFields, null);
    LesTextFields.add(RefProduit, null);
    LesTextFields.add(NomProduit, null);
    LesTextFields.add(QuanProduit, null);
    LesTextFields.add(RefCatalogue, null);
    LesTextFields.add(ImageProduit, null);
    LesTextFields.add(presentationProduit, null);
    LesTextFields.add(TypeProduit, null);
    LesTextFields.add(PrixProduit, null);
    LesTextFields.add(DesProduit, null);

  }


  public void RetournePath(JTextField zone, String ext){
    String fil="";
    JFileChooser choix =new JFileChooser();
    MonFiltre mft = new MonFiltre( new String[]{ext},"les fichiers de type (*."+ext+")");
    choix. addChoosableFileFilter(mft);
    int retour = choix.showOpenDialog(this);
    if(retour == JFileChooser.APPROVE_OPTION) {
         try{
           String file = choix.getSelectedFile().getCanonicalPath();
           fil=file;
           zone.setText(file);
           zone.revalidate();
         }
         catch(IOException e){
         }
      }
      else return;
    }

    //annuler
    public void Annuler(ActionEvent e){
      RefProduit.setText("");
      NomProduit.setText("");
      QuanProduit.setText("");
      NomFichier.setText("");
      NomFichier2.setText("");
      //RefCatalogue.setSelectedIndex(1);
      TypeProduit.setText("");
      PrixProduit.setText("");
      DesProduit.setText("");

    }

    // fermer
    public void Fermer(ActionEvent e){
      this.dispose();
    }

    //enregisrer
    public void Enregistrer(ActionEvent e){
      LireEtEcrire et = new LireEtEcrire();
      Format fr = new Format();
      String message;
      message= fr.devFormat(RefProduit.getText())+"\t"+fr.devFormat(NomProduit.getText())+"\t"+fr.devFormat(QuanProduit.getText())+"\t"+fr.devFormat(NomFichier.getText())+"\t"+fr.devFormat(NomFichier2.getText())+"\t"+RefCatalogue.getSelectedItem()+"\t"+fr.devFormat(TypeProduit.getText())+"\t"+fr.devFormat(PrixProduit.getText())+"\t"+fr.devFormat(DesProduit.getText());
      et.ecrire(message,"Produit.xls");
      Annuler(e);
    }

  public void lecombo(){
    LireEtEcrire le = new LireEtEcrire();
    int a=0;
    int i=0;
    String mot="";
    while(a<le.ListeLecture("Catalogue.xls").size()){
      RefCatalogue.addItem(le.ListeLecture("Catalogue.xls").elementAt(i));
      a++;
      i++;
  }
  RefCatalogue.revalidate();
 }

}
