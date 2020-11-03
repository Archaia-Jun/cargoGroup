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

public class Log extends JFrame {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JTextField Login = new JTextField();
  JPasswordField Password = new JPasswordField();
  JButton Valider = new JButton();
  JButton Annuler = new JButton();
  GridLayout gridLayout2 = new GridLayout(2,1);
  GridLayout gridLayout3 = new GridLayout(2,1);
  GridLayout gridLayout4 = new GridLayout();

  public Log() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setResizable(false);
    this.setSize(new Dimension(417, 100));
    jPanel1.setLayout(gridLayout1);
    jLabel1.setText("LOGIN");
    jLabel2.setText("PASSWORD");
    Login.setText("");
    Valider.setText("ENTRER");
    Valider.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Enter_actionPerformed(e);
      }
    });

    Annuler.setText("ANNULER");
    Annuler.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Annuler_actionPerformed(e);
      }
    });

    jPanel2.setLayout(gridLayout2);
    jPanel3.setLayout(gridLayout3);
    jPanel4.setLayout(gridLayout4);
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel2, null);
    jPanel2.add(jLabel1, null);
    jPanel2.add(jLabel2, null);
    jPanel1.add(jPanel3, null);
    jPanel3.add(Login, null);
    jPanel3.add(Password, null);
    this.getContentPane().add(jPanel4,  BorderLayout.SOUTH);
    jPanel4.add(Valider, null);
    jPanel4.add(Annuler, null);
  }
  //fermer
  public void Annuler_actionPerformed(ActionEvent e){
    this.dispose();
  }
//administration user
  public void  Enter_actionPerformed(ActionEvent  e) {
    System.out.print(Login.getText()+"   \t"+Password.getText()+"\n");
    String  logi,passi;
    logi=Login.getText();
    passi=Password.getText();
    if(isValide(logi,passi))
    {
      Admin ad =  new Admin();
      ad.setVisible(true);
      this.dispose();
    }
    else  {

      JOptionPane.showMessageDialog(null, "Message d'erreur","Login et/ou password  invalide",JOptionPane.WARNING_MESSAGE);
  }
 }
 //veriification du mot  de passe et du nom d'uiitilisateur
 public  boolean  isValide(String  Login,  String Password){
   boolean valide=false;

   String log  = "benito1er";
   String pass = "benito";
   if((log.equalsIgnoreCase(Login))&&(pass.equalsIgnoreCase(Password))) {
       valide = true;
       System.out.println("dans la boucle if " + valide + "\n");
     }



   System.out.print(valide+"\n");
   return valide;
 }
}