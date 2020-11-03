package devlog;

import com.google.zxing.WriterException;

import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2005</p>
 * <p>Société : </p>
 * @author non attribuable
 * @version 1.0
 */



public class FenetreCommande extends JFrame {
  private BufferedImage image;
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
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel15 = new JLabel();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel17 = new JLabel();

  private Object ImageIcon;

  JTextField Date = new JTextField();
  JTextField NumFacture = new JTextField();
  JTextField NomClient = new JTextField();
  JTextField Dossier = new JTextField();
  JTextField Contrat = new JTextField();
  JTextField Campagne = new JTextField();
  JTextField NbrBalles = new JTextField();
  JTextField PdsBrut  = new JTextField();
//  Object[]  civ = {"Mme", "Mlle",  "Mr"};
//  JComboBox Civil = new JComboBox(civ);
//  Object[]   type  = {"CLIENT PARTICULIER","ENTREPRISE"};
//  JComboBox Type = new JComboBox(type);
  JTextField DeboursMOE = new JTextField("600");
  JTextField Prestation = new JTextField("50");
  JTextField MontantHT = new JTextField();
  JTextField TVA = new JTextField();
  JTextField MontantTVA = new JTextField();
  JTextField MontantTTC = new JTextField();
  JTextField AIB = new JTextField();
  JTextField NetAPayer = new JTextField();
  JPanel QRCode = new JPanel();
  GridLayout gridLayout2 = new GridLayout(18,1);
  GridLayout gridLayout3 = new GridLayout(18,1);
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  GridLayout gridLayout4 = new GridLayout(2,1);
  JButton Annuler = new JButton();
  JButton Valider = new JButton();
  JButton Quitter = new JButton();
  JButton Fermer = new JButton();
  JButton CalcTVA = new JButton();
  JButton CalcNAP = new JButton();
  JButton Imprimer = new JButton();
  GridLayout gridLayout5 = new GridLayout();
  GridLayout gridLayout6 = new GridLayout();
  private double montHT, montTTC, montTVA;
    private AbstractButton tf1, tf3;
    private AbstractButton tf2;
    private Object b1, b2;
  private JTable table;
  private TablePrinter.HeadBandPrintable header;
  protected Object message;

  public FenetreCommande() throws WriterException, IOException {
      // affiche DONE si QR code généré
      System.out.println("DONE");
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setSize(new Dimension(600, 456));
    jPanel1.setLayout(gridLayout1);
    jLabel1.setText("    DATE");
    jLabel2.setText("    NUMERO FACTURE");
    jLabel3.setText("    NOM DU CLIENT");
    jLabel4.setText("    DOSSIER");
    jLabel5.setText("    CONTRAT");
    jLabel6.setText("    CAMPAGNE");
    jLabel7.setText("    NOMBRE DE BALLES");
    jLabel8.setText("    POIDS BRUT");
    jLabel9.setText("    DEBOURS / MAINS D'OEUVRE");
    jLabel10.setText("    PRESTATION");
    jLabel11.setText("    MONTANT HT");
    jLabel12.setText("    TVA (%)");
    jLabel13.setText("    MONTANT TVA");
    jLabel14.setText("    MONTANT TTC");
    jLabel15.setText("    AIB");
    jLabel16.setText("    NET A PAYER");
    jLabel17.setText("    QRCode");
    jPanel2.setLayout(gridLayout2);
    jPanel3.setLayout(gridLayout3);
 //   RefClient.setText("");
    NomClient.setText("");
  //  PreClient.setText("");
 //   AdreClient.setText("");
 //   NumTelClient.setText("");
 //   NumFaxClient.setText("");

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
        //Valider2(e);
        if(e.getSource() == QRCode) {
          QRCode.setVisible(true);
        }
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
      CalcTVA.setText("CALCUL TVA");
      CalcTVA.addActionListener(new ActionListener()  {
          public void actionPerformed(ActionEvent e) {
              CalcTVA(e);
          }
      });CalcNAP.setText("NET A PAYER");
      CalcNAP.addActionListener(new ActionListener()  {
          public void actionPerformed(ActionEvent e) {
              CalcNAP(e);
          }
      });Imprimer.setText("Imprimer");
    Imprimer.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Impression(e);
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
    jPanel2.add(jLabel10, null);
    jPanel2.add(jLabel11, null);
    jPanel2.add(jLabel12, null);
    jPanel2.add(jLabel13, null);
    jPanel2.add(jLabel14, null);
    jPanel2.add(jLabel15, null);
    jPanel2.add(jLabel16, null);
    jPanel2.add(jLabel17, null);
    jPanel3.add(Date, null);
    jPanel3.add(NumFacture, null);
    jPanel3.add(NomClient, null);
    jPanel3.add(Dossier, null);
    jPanel3.add(Contrat, null);
    jPanel3.add(Campagne, null);
    jPanel3.add(NbrBalles, null);
    jPanel3.add(PdsBrut, null);
    jPanel3.add(DeboursMOE, null);
    jPanel3.add(Prestation, null);
    jPanel3.add(MontantHT, null);
    jPanel3.add(TVA, null);
    jPanel3.add(MontantTVA, null);
    jPanel3.add(MontantTTC, null);
    jPanel3.add(AIB, null);
    jPanel3.add(NetAPayer, null);
    jPanel3.add(QRCode, null);
    this.getContentPane().add(jPanel4,  BorderLayout.SOUTH);
    jPanel4.add(jPanel6, null);
    jPanel6.add(Valider, null);
    jPanel6.add(Annuler, null);
      jPanel6.add(CalcTVA, null);
      jPanel6.add(CalcNAP, null);
    jPanel4.add(jPanel5, null);
    jPanel5.add(Fermer, null);
    jPanel5.add(Quitter, null);
    jPanel5.add(Imprimer, null);
  }


  private void CalcTVA(ActionEvent e) {
        String s1=MontantHT.getText();
        String s2=TVA.getText();
        String s3=AIB.getText();
        double a=Double.parseDouble(s1);
        double b=Double.parseDouble(s2);
        double c=0;
        double d=0;
        if(e.getSource()==CalcTVA){
            //  prixTTC = prixHT + valTVA/100 * prixHT = (1 + valTVA / 100) * prixHT
            c= (1 + b / 100) * a; // TTC
            d= (a/100) * 1; // AIB
        }else if(e.getSource()==CalcNAP){
            System.out.println(c=a-b);
        }
        String result=String.valueOf(c);
        MontantTTC.setText(result);

        String resultTVA= String.valueOf(d);
        AIB.setText(resultTVA);
        MontantTVA.setText(String.valueOf(c - a));
    }

    private void CalcNAP(ActionEvent e) {

        String s4=MontantTTC.getText();
        String s5=AIB.getText();
        String s6=MontantHT.getText();
        String s7=MontantTVA.getText();
        String s8=PdsBrut.getText();
        String s9=DeboursMOE.getText();
        String s10=Prestation.getText();
        double f=Double.parseDouble(s4);
        double g=Double.parseDouble(s5);
        double i=Double.parseDouble(s6);
        double j=Double.parseDouble(s7);
        double k=Double.parseDouble(s8);
        double l=Double.parseDouble(s9);
        double m=Double.parseDouble(s10);

      double h=0;
        if(e.getSource()==CalcNAP){
            h= i+j-g; // Net à payer
            l= 600*k; // Debours MOE
            m= 50*k; // Prestation
        }else if(e.getSource()==CalcNAP){
            System.out.println(h=f-g);
        }
        String resultNAP=String.valueOf(h);
        NetAPayer.setText(resultNAP);
        DeboursMOE.setText(String.valueOf(l));
        Prestation.setText(String.valueOf(m));


    }

    // ouvre une page pour l'impression
  private void Impression(ActionEvent e) {


    TablePrinter.HeadBandPrintable header = null;
    try {
      header = new TablePrinter.HeadBandPrintable() {

        BufferedImage image = ImageIO.read(new File("C:\\Users\\LENOVO\\IdeaProjects\\bccLog\\BCC_SARL_LOGO.png"));
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
          graphics.drawImage(image, (int)pageFormat.getImageableX(), (int)pageFormat.getImageableY(), null);
          graphics.setFont(Font.decode("Arial-BOLD-25"));
          TablePrinterDemo.TextUtils.drawString(graphics, "Mon impression avec logo", new Rectangle2D.Double(pageFormat.getImageableX()+image.getWidth(),pageFormat.getImageableY(),pageFormat.getImageableWidth()-image.getWidth(),pageFormat.getImageableHeight()), SwingConstants.CENTER, SwingConstants.CENTER);
          return Printable.NO_SUCH_PAGE;
        }

        @Override
        public double getHeight() {
          return image.getHeight();
        }

      };
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    TablePrinter.HeadBandPrintable footer = new TablePrinter.HeadBandPrintable() {

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
          graphics.setFont(Font.decode("Arial-NORMAL-12"));
          TablePrinterDemo.TextUtils.drawString(graphics, "Page " + (pageIndex+1), new Rectangle2D.Double(pageFormat.getImageableX(),pageFormat.getImageableY(),pageFormat.getImageableWidth(),pageFormat.getImageableHeight()), pageIndex%2==0?SwingConstants.RIGHT:SwingConstants.LEFT, SwingConstants.CENTER);
          return Printable.NO_SUCH_PAGE;
        }

        @Override
        public double getHeight() {
          return 20;
        }


      };


      JFrame frame = new JFrame("Facture");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      String[] columns = {"DATE","N° FACTURE","CLIENT","DOSSIER","CONTRAT","CAMPAGNE","BALLES","POIDS BRUT","DEBOURS / MAINS D'OEUVRE","PRESTATION"," HORS TAXE","TVA(%)"," MTNT TVA"," TTC","AIB","NET A PAYER"};

      Object[][] data = {
              {
                      Date.getText(),NumFacture.getText(), NomClient.getText(), Dossier.getText(),Contrat.getText(),Campagne.getText(),NbrBalles.getText(), PdsBrut.getText(), DeboursMOE.getText(), Prestation.getText(), MontantHT.getText(), TVA.getText(), MontantTVA.getText(), MontantTTC.getText(), AIB.getText(), NetAPayer.getText()

              }
      };
      for(Object[] row : data) {

          }
      JTable table = new JTable(data, columns);
      JPanel panel = new JPanel(new BorderLayout());
      panel.add(table);
      panel.add(table.getTableHeader(),BorderLayout.NORTH);
      frame.add(new JScrollPane(panel));

      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING,5,5));
      JButton printButton = new JButton("Imprimer");
    TablePrinter.HeadBandPrintable finalHeader = header;
    printButton.addActionListener(i->printTable(table, finalHeader, footer));
      buttonPanel.add(printButton);
      frame.add(buttonPanel, BorderLayout.SOUTH);

      frame.setSize(300, 300);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);

  }



    public static void printTable(JTable table, TablePrinter.HeadBandPrintable header, TablePrinter.HeadBandPrintable footer) {

      PrinterJob job = PrinterJob.getPrinterJob();
      job.setPrintable(new TablePrinter(table, JTable.PrintMode.FIT_WIDTH, header, footer));
      boolean doPrint = job.printDialog();
      if (doPrint) {
        try {
          job.print();
        } catch (PrinterException e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(table, "Erreur d'impression");
        }
      }
    }

     class PrintPreview extends JFrame implements Runnable
    {
      protected JScrollPane displayArea;
      protected int m_wPage;
      protected int m_hPage;
      protected int width;
      protected int height;
      protected Printable m_target;
      protected JComboBox m_cbScale;
      protected devlog.PrintPreview.PreviewContainer m_preview;
      protected PageFormat pp_pf = null;
      protected JButton formatButton;
      protected JButton shrinkButton;
      protected JButton zoomin = new JButton ();
      protected JButton zoomout = new JButton();
      protected String tooltipText;
      protected JCheckBox headerBox;
      protected JTextField headerField = new JTextField("Gestion des équipements");
      protected JButton printThisPage = new JButton();
      @SuppressWarnings("unused")
      private static final int INCH = 72;
      private boolean bScallToFitOnePage = false;
      private String msg;
      JTable table;
      TablePrinter.HeadBandPrintable header;
      TablePrinter.HeadBandPrintable footer;


      protected void getThePreviewPages()
      {

        m_target =new TablePrinter(table, JTable.PrintMode.FIT_WIDTH, header, footer);// table.getPrintable(JTable.PrintMode.FIT_WIDTH, new MessageFormat(headerField.getText()), new MessageFormat("{0}"));
        m_wPage = (int)(pp_pf.getWidth());
        m_hPage = (int)(pp_pf.getHeight());
        int scale = getDisplayScale();
        width = (int)Math.ceil(m_wPage*scale/100);
        height = (int)Math.ceil(m_hPage*scale/100);


        int pageIndex = 0;
        try {
          while (true) {
            BufferedImage img = new BufferedImage(m_wPage, m_hPage,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = img.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, m_wPage, m_hPage);
            if (bScallToFitOnePage) {
              m_target.print(g, pp_pf, -1);
              devlog.PrintPreview.PagePreview pp = new devlog.PrintPreview.PagePreview(width, height, img);
              m_preview.add(pp);
              break;
            } else
            if (m_target.print(g, pp_pf, pageIndex) !=  Printable.PAGE_EXISTS)
              break;
            devlog.PrintPreview.PagePreview pp = new devlog.PrintPreview.PagePreview(width, height, img);
            m_preview.add(pp);
            pageIndex++;
          }
        } catch (OutOfMemoryError om) {
          JOptionPane.showMessageDialog(this,
                  "image is too big that run out of memory.", "Print Preview",
                  JOptionPane.INFORMATION_MESSAGE);
        }
        catch (PrinterException e) {
          e.printStackTrace();
          System.err.println("Printing error: "+e.toString());
        }
      }

      protected void previewThePages(int orientation)
      {
        if (displayArea != null) displayArea.setVisible(false);
        m_preview =null;
        m_preview = new devlog.PrintPreview.PreviewContainer();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../icones/Imprimer.png")));
        getThePreviewPages();
        JPanel panel = new JPanel();
        panel.add(m_preview);
        JPanel panel1 = new JPanel();
        panel1.add(panel);

        displayArea = new JScrollPane(panel1);
        getContentPane().add(displayArea, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setResizable(true);
        setExtendedState(this.MAXIMIZED_BOTH);
        setVisible(true);
        System.gc();
      }


      protected void createButtons(JToolBar tb, boolean shrink) {
        tooltipText = "Ajouter en-tête";
        headerBox = new JCheckBox("En-tête:", true);
        headerBox.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            headerField.setEnabled(headerBox.isSelected());
          }
        });
        tb.add(headerBox);
        headerBox.setToolTipText(tooltipText);
        tooltipText = " Champ en-tête";
        headerField = new JTextField("Gestion des équipements");
        headerField.setToolTipText(tooltipText);
        tb.add(headerField);
        JButton print = new JButton();

        print.setContentAreaFilled(false);
        print.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        print.setNextFocusableComponent(print);
        tooltipText = "Imprimer";
        print.setToolTipText(tooltipText);
        print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/printico.png")));

        tooltipText = "Imprimer page courante";
        printThisPage.setToolTipText(tooltipText);
        printThisPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/printcurrent.png")));
        printThisPage.setPreferredSize(new Dimension(24, 21));

        printThisPage.setContentAreaFilled(false);
        printThisPage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printThisPage.setNextFocusableComponent(print);

        ActionListener lst = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            try {

              MessageFormat header = null;
              MessageFormat footer = null;
              footer = new MessageFormat("{0}");

              boolean fitWidth = true;
              boolean showPrintDialog = true;
              boolean interactive = true;

              JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH
                      : JTable.PrintMode.NORMAL;

              @SuppressWarnings("unused")
              boolean complete=false;
              header = new MessageFormat(headerField.getText());
              HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
              if(pp_pf.getOrientation() == PageFormat.PORTRAIT)
                set.add(OrientationRequested.PORTRAIT);
              else
                set.add(OrientationRequested.LANDSCAPE);

              complete = table.print(mode, header, footer,
                      showPrintDialog, set,
                      interactive, null);

              if(complete)
                JOptionPane.showMessageDialog(null, "Impression complète");
            }
            catch (PrinterException ex) {
              ex.printStackTrace();
              System.err.println("Printing error: "+ex.toString());
            }
          }

        };
        print.addActionListener(lst);
        print.setAlignmentY(0.5f);
        print.setMargin(new Insets(4,6,4,6));
        tb.add(print);
        lst = new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            printTable(table, header, footer);

          }

        };
        printThisPage.addActionListener(lst);
        printThisPage.setAlignmentY(0.5f);
        printThisPage.setMargin(new Insets(4,6,4,6));
        tb.add(printThisPage);

        if (pp_pf.getOrientation() == PageFormat.PORTRAIT)
        {
          formatButton = new JButton();
          formatButton.setContentAreaFilled(false);
          formatButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
          formatButton.setNextFocusableComponent(print);
          tooltipText = "Paysage";
          formatButton.setToolTipText(tooltipText);
          formatButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/paysage.png")));

        }
        else
        {
          formatButton = new JButton();
          formatButton.setContentAreaFilled(false);
          formatButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
          formatButton.setNextFocusableComponent(print);
          tooltipText = "Portrait";
          formatButton.setToolTipText(tooltipText);
          formatButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/portrait.png")));
        }

        lst = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if (pp_pf.getOrientation() == PageFormat.PORTRAIT) {
              pp_pf.setOrientation(PageFormat.LANDSCAPE);
              previewThePages(PageFormat.LANDSCAPE);
              tooltipText = "Portrait";
              formatButton.setToolTipText(tooltipText);
              formatButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/portrait.png")));


            } else {
              pp_pf.setOrientation(PageFormat.PORTRAIT);
              previewThePages(PageFormat.PORTRAIT);
              tooltipText = "Paysage";
              formatButton.setToolTipText(tooltipText);
              formatButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/paysage.png")));

            }
          }
        };
        formatButton.addActionListener(lst);
        formatButton.setAlignmentY(0.5f);
        formatButton.setMargin(new Insets(4,6,4,6));
        tb.add(formatButton);

        if (shrink) {
          shrinkButton = new JButton("Shrink to fit");

          lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              bScallToFitOnePage = !bScallToFitOnePage;
              previewThePages(pp_pf.getOrientation());
            }
          };
          shrinkButton.addActionListener(lst);
          shrinkButton.setAlignmentY(0.5f);
          shrinkButton.setMargin(new Insets(4,6,4,6));
          tb.add(shrinkButton);
        }

        zoomin.setContentAreaFilled(false);
        zoomin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        zoomin.setNextFocusableComponent(print);
        tooltipText = "agrandir";
        zoomin.setToolTipText(tooltipText);
        zoomin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/zoomin.png")));


        lst = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            int i = m_cbScale.getSelectedIndex();
            i++;
            if(i<=9)
              m_cbScale.setSelectedIndex(i);
          }
        };
        zoomin.addActionListener(lst);

        zoomout.setContentAreaFilled(false);
        zoomout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        zoomout.setNextFocusableComponent(print);
        tooltipText = "réduire";
        zoomout.setToolTipText(tooltipText);
        zoomout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/zoomout.png")));

        lst = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            int i = m_cbScale.getSelectedIndex();
            i--;
            if(i>=0)
              m_cbScale.setSelectedIndex(i);
          }
        };
        zoomout.addActionListener(lst);
      }

      public void buildHeaderFooter() throws IOException{
        header = new TablePrinter.HeadBandPrintable() {
          BufferedImage imageg = ImageIO.read(getClass().getResource("JD.png"));
          BufferedImage imaged = ImageIO.read(getClass().getResource("BCC_SARL_LOGO.png"));
          @Override
          public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            graphics.drawImage(imageg, (int)pageFormat.getImageableX()-30, (int)pageFormat.getImageableY()+10, null);
            graphics.setFont(Font.decode("Arial-BOLD-20"));
            devlog.PrintPreview.TextUtils.drawString(graphics, headerField.getText(),
                    new Rectangle2D.Double(pageFormat.getImageableX()+imageg.getWidth(),
                            pageFormat.getImageableY()+imageg.getHeight()-20,pageFormat.getImageableWidth()-2*imageg.getWidth(),
                            pageFormat.getImageableHeight()), SwingConstants.CENTER, SwingConstants.CENTER);
            graphics.drawImage(imaged,(int)pageFormat.getImageableX()+ (int)pageFormat.getImageableWidth()
                    -imaged.getWidth()+30,(int)pageFormat.getImageableY()+7, null);
            return Printable.NO_SUCH_PAGE;

          }

          public double getHeight() {
            return 2*imageg.getHeight();
          }

        };

        footer = new TablePrinter.HeadBandPrintable() {

          @Override
          public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            graphics.setFont(Font.decode("Arial-NORMAL-12"));
            devlog.PrintPreview.TextUtils.drawString(graphics, "Page " + (pageIndex+1),new Rectangle2D.Double(pageFormat.getImageableX(),
                            pageFormat.getImageableY(),pageFormat.getImageableWidth(),pageFormat.getImageableHeight()),
                    SwingConstants.CENTER, SwingConstants.CENTER);
            return Printable.NO_SUCH_PAGE;
          }


          public double getHeight() {
            return 20;
          }

        };


      }
      private void printTable(JTable table, TablePrinter.HeadBandPrintable header, TablePrinter.HeadBandPrintable footer) {

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new TablePrinter(table, JTable.PrintMode.FIT_WIDTH, header, footer));
        boolean doPrint = job.printDialog();
        if (doPrint) {
          try {
            job.print();
          } catch (PrinterException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(table, "Erreur d'impression");
          }
        }
      }

      public int getDisplayScale() {
        String str = m_cbScale.getSelectedItem().toString();
        if (str.endsWith("%")) str = str.substring(0, str.length()-1);
        str = str.trim();
        int scale = 0;
        try { scale = Integer.parseInt(str); }
        catch (NumberFormatException ex) { return 25; }
        return scale ;
      }

      public PrintPreview(Printable target,JTable table2) {
        this(target, "Aperçu avant impression", false, table2);
      }

      public PrintPreview(Printable target, String title,JTable table2) {
        this(target, title, false, table2);
      }
      public PrintPreview(Printable target, String title, boolean shrink,JTable table2) {
        setTitle(title);
        table=table2;

        try {
          buildHeaderFooter();
        } catch (IOException ex) {
          Logger.getLogger(devlog.PrintPreview.class.getName()).log(Level.SEVERE, null, ex);
        }

        bScallToFitOnePage = false;  // reset to default
        PrinterJob prnJob = PrinterJob.getPrinterJob();

        pp_pf = prnJob.defaultPage();
        if (pp_pf.getHeight()==0 || pp_pf.getWidth()==0) {
          System.err.println("Unable to determine default page size");
          return;
        }
        setSize(670, 750);
        m_target = target;

        displayArea = null;
        m_preview = null;

        JToolBar tb = new JToolBar();
        createButtons(tb, shrink);

        String[] scales = { "10 %", "25 %", "50 %", "100 %", "125 %", "150 %", "175 %","200 %","225 %","250 %"};
        m_cbScale = new JComboBox(scales);
        m_cbScale.setSelectedIndex(3);
        ActionListener lst = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            Thread runner = new Thread(PrintPreview.this);
            runner.start();
          }
        };
        m_cbScale.addActionListener(lst);
        m_cbScale.setMaximumSize(m_cbScale.getPreferredSize());
        m_cbScale.setEditable(true);
        tb.addSeparator();
        tb.add(zoomout);
        tb.add(m_cbScale);
        tb.add(zoomin);
        getContentPane().add(tb, BorderLayout.NORTH);

        previewThePages(pp_pf.getOrientation());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setExtendedState(this.MAXIMIZED_BOTH);
        setVisible(true);
      }

      public void run() {
        int scale = getDisplayScale();
        width = (int)(m_wPage*scale/100);
        height = (int)(m_hPage*scale/100);

        Component[] comps = m_preview.getComponents();
        for (int k=0; k<comps.length; k++) {
          if (!(comps[k] instanceof devlog.PrintPreview.PagePreview))
            continue;
          devlog.PrintPreview.PagePreview pp = (devlog.PrintPreview.PagePreview)comps[k];
          pp.setScaledSize(width, height);
        }
        m_preview.doLayout();
        m_preview.getParent().getParent().validate();

      }

      class PreviewContainer extends JPanel
      {
        protected int H_GAP = 16;
        protected int V_GAP = 10;

        public Dimension getPreferredSize() {
          int n = getComponentCount();
          if (n == 0)
            return new Dimension(H_GAP, V_GAP);
          Component comp = getComponent(0);
          Dimension dc = comp.getPreferredSize();
          int w = dc.width;
          int h = dc.height;

          Dimension dp = getParent().getSize();
          int nCol = Math.max((dp.width-H_GAP)/(w+H_GAP), 1);
          int nRow = n/nCol;
          if (nRow*nCol < n)
            nRow++;

          int ww = nCol*(w+H_GAP) + H_GAP;
          int hh = nRow*(h+V_GAP) + V_GAP;
          Insets ins = getInsets();
          return new Dimension(ww+ins.left+ins.right,
                  hh+ins.top+ins.bottom);
        }

        public Dimension getMaximumSize() {
          return getPreferredSize();
        }

        public Dimension getMinimumSize() {
          return getPreferredSize();
        }

        public void doLayout() {
          Insets ins = getInsets();
          int x = ins.left + H_GAP;
          int y = ins.top + V_GAP;

          int n = getComponentCount();
          if (n == 0)
            return;
          Component comp = getComponent(0);
          Dimension dc = comp.getPreferredSize();
          int w = dc.width;
          int h = dc.height;

          Dimension dp = getParent().getSize();
          int nCol = Math.max((dp.width-H_GAP)/(w+H_GAP), 1);
          int nRow = n/nCol;
          if (nRow*nCol < n)
            nRow++;

          int index = 0;
          for (int k = 0; k<nRow; k++) {
            for (int m = 0; m<nCol; m++) {
              if (index >= n)
                return;
              comp = getComponent(index++);
              comp.setBounds(x, y, w, h);
              x += w+H_GAP;
            }
            y += h+V_GAP;
            x = ins.left + H_GAP;
          }
        }
      }

      class PagePreview extends JPanel
      {
        protected int m_w;
        protected int m_h;
        protected Image m_source;
        protected Image m_img;

        public PagePreview(int w, int h, Image source) {
          m_w = w;
          m_h = h;
          m_source= source;
          m_img = m_source.getScaledInstance(m_w, m_h, Image.SCALE_SMOOTH);
          m_img.flush();
          setBackground(Color.white);
          setBorder(new MatteBorder(1, 1, 2, 2, Color.black));
        }

        public void setScaledSize(int w, int h) {
          m_w = w;
          m_h = h;
          m_img = m_source.getScaledInstance(m_w, m_h, Image.SCALE_SMOOTH);
          repaint();
        }

        public Dimension getPreferredSize() {
          Insets ins = getInsets();
          return new Dimension(m_w+ins.left+ins.right,  m_h+ins.top+ins.bottom);
        }

        public Dimension getMaximumSize() {
          return getPreferredSize();
        }

        public Dimension getMinimumSize() {
          return getPreferredSize();
        }

        public void paint(Graphics g) {
          g.setColor(getBackground());
          g.fillRect(0, 0, getWidth(), getHeight());
          g.drawImage(m_img, 0, 0, this);
          paintBorder(g);
        }
      }

      class TextUtils {
        public void drawString(Graphics graphics, String s, Rectangle2D.Double aDouble, int center, int center1) {
        }
      }




// ouvre un document excel pour l'impression
/*
      final String NAV = "rundll32 url.dll,FileProtocolHandler";

        try {
          File tmpFile = File.createTempFile("Test", ".html");
          String filePath = "C:\\Users\\LENOVO\\IdeaProjects\\bccLog\\Commandes.odf";
          //pour imprimer un doc depuis navigateur:
          //String filePath = tmpFile.getCanonicalPath();
          FileOutputStream fos = new FileOutputStream(tmpFile);
          PrintStream doc = new PrintStream(fos);
          doc.println("<HTML><head><title>Coucou print</title></head>");
          doc.println("<body>");
          doc.println("<h1>Une petit test d'impression</h1>");
          doc.println("Imprimer depuis un programme Java sans trop se fatiguer.");
          doc.println("<p>Le fichier généré est <code>" + filePath + "</code>.");
          doc.println("</body></Html>");
          doc.flush();
          doc.close();
          fos.close();

          Runtime run = Runtime.getRuntime();
          run.exec(NAV + " " + filePath);
          run.wait(5000); // Time to run navigator before removing file.
          tmpFile.deleteOnExit();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
*/
  }



    //annuler
  public void Annuler(ActionEvent e){
    NumFacture.setText("");
    NomClient.setText("");
    Dossier.setText("");
    PdsBrut.setText("");
   // Civil.setSelectedIndex(0);
  //  Type.setSelectedIndex(0);
    DeboursMOE.setText("600");
    Prestation.setText("50");
    MontantHT.setText("");
    TVA.setText("");
    MontantTVA.setText("");
    MontantTTC.setText("");
    AIB.setText("");
    NetAPayer.setText("");

  }


  //valider
  // affichage en ligne pour consulter liste commandes depuis le logiciel
  public void Valider(ActionEvent e) {


    //int nbrAleatoire = 0 + (int) (Math.random() * ((10000 - 1) + 1));

    // Affiche QRCode dans une fenêtre
   // new QRImage();

     // Commande commande = new Commande(11455, "testTRAINm", "bP", 98, 9, "livr", 98, 6, 5, 1031, 98, 109);
      //commande.add(this);



      LireEtEcrire et = new LireEtEcrire();
      Format fr = new Format();
      String message;
      message= fr.devFormat(NumFacture.getText())+"\t"+fr.devFormat(NomClient.getText())+"\t"+fr.devFormat(Dossier.getText())+"\t"+fr.devFormat(PdsBrut.getText())+"\t"+fr.devFormat(DeboursMOE.getText())+"\t"+fr.devFormat(Prestation.getText())+"\t"+fr.devFormat(MontantHT.getText())+"\t"+fr.devFormat(TVA.getText())+"\t"+fr.devFormat(MontantTVA.getText())+"\t"+fr.devFormat(MontantTTC.getText())+"\t"+fr.devFormat(AIB.getText())+"\t"+fr.devFormat(NetAPayer.getText());
      System.out.println(message);
      et.ecrire(message,"Commandes.xls");

      Annuler(e);

   /*   NomClient.getText();
      Dossier.getText();
      PdsBrut.getText();
      DeboursMOE.getText();
      Prestation.getText();
      MontantHT.getText();
      TVA.getText();
      MontantTVA.getText();
      MontantTTC.getText();
      AIB.getText();
      NetAPayer.getText();*/

  }
    //-------------------------------------
      // Affiche QRCode dans une fenêtre
  //  new QRImage();
  /*  LireEtEcrire et = new LireEtEcrire();
    Format fr = new Format();
    String message;
    message= fr.devFormat(NumFacture.getText())+"\t"+fr.devFormat(NomClient.getText())+"\t"+fr.devFormat(Dossier.getText())+"\t"+fr.devFormat(PdsBrut.getText())+"\t"+fr.devFormat(DeboursMOE.getText())+"\t"+fr.devFormat(Prestation.getText())+"\t"+fr.devFormat(MontantHT.getText())+"\t"+fr.devFormat(TVA.getText())+"\t"+fr.devFormat(MontantTVA.getText())+"\t"+fr.devFormat(MontantTTC.getText())+"\t"+fr.devFormat(AIB.getText())+"\t"+fr.devFormat(NetAPayer.getText());
      System.out.println(message);
    et.ecrire(message,"Commandes.xls");
    Annuler(e);
    JPanel panelQRCode = new JPanel();*/
    // affichage qr code
   // ImageIcon QRCode = new ImageIcon("JD.png");
    // QRCode.paintIcon(add(jLabel13), getGraphics(), 290,250);
    //jLabel13.add((Component) ImageIcon);
   // jLabel13.setIcon(QRCode);
    //-----------------------------------------




  //valider
  // affichage avec mise en page pour impression de facture
  /*public void Valider2(ActionEvent e){
    LireEtEcrire et = new LireEtEcrire();
    Format fr = new Format();
    String message;
    message= "\t\n\n" + "NUMERO_FACTURE: " + fr.devFormat(NumFacture.getText())+"\t\n" + "NOM_CLIENT: " +fr.devFormat(NomClient.getText())+"\t\n"+ "DOSSIER: " +fr.devFormat(Dossier.getText())+"\t\n"+ "POIDS_BRUT: " +fr.devFormat(PdsBrut.getText())+"\t\n"+ "DEBOURS_MAINS_OEUVRE: " +fr.devFormat(DeboursMOE.getText())+"\t\n"+ "PRESTATION: " +fr.devFormat(Prestation.getText())+"\t\n"+ "MONTANT_HT: " +fr.devFormat(MontantHT.getText())+"\t\n"+ "TVA: " +fr.devFormat(TVA.getText())+"\t\n"+ "MONTANT_TVA: " +fr.devFormat(MontantTVA.getText())+"\t\n"+ "MONTANT_TTC: " +fr.devFormat(MontantTTC.getText())+"\t\n"+ "AIB: " +fr.devFormat(AIB.getText())+"\t\n"+ "NET_A_PAYER: " +fr.devFormat(NetAPayer.getText());
      System.out.println(message);
    et.ecrire(message,"Commandes_imprimer.xls");
    Annuler(e);
  }*/

  //fermer
  public void Fermer(ActionEvent e){
    this.dispose();
    AfficheCommandes afc = new AfficheCommandes();
    afc.setVisible(true);

  }

}

/*
public class FenetreCommande extends JFrame {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
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
//  JComboBox refProd = new JComboBox();
  JTextField NumFacture = new JTextField();
  JTextField NomClient = new JTextField();
  JTextField Dossier = new JTextField();
  JTextField PdsBrut = new JTextField();
  JTextField DeboursMOE = new JTextField();
  JTextField Prestation = new JTextField();
  JTextField MontantHT = new JTextField();
  JTextField TVA = new JTextField();
  JTextField MontantTTC = new JTextField();
  JTextField AIB = new JTextField();
  JTextField NetAPayer = new JTextField();
  //JTextField Dossier = new JTextField();
  Object[] civ = {"Mme", "Mlle", "Mr"};
 // JComboBox Civil = new JComboBox(civ);
  JTextField NbrBalles = new JTextField();
  JTextField Campagne = new JTextField();
  JTextField montCommande = new JTextField();
  Object[] pai= {"Chèque bancaire", "Espèce","Carte bleu", "Chèques"};
  JComboBox moyPaiement = new JComboBox(pai);
  GridLayout gridLayout3 = new GridLayout(10,1);
  JButton Valider = new JButton();
  JButton Annuler = new JButton();
  GridLayout gridLayout4 = new GridLayout();
  JLabel jLabel10 = new JLabel();
 // JComboBox Dossier = new JComboBox();
  JPanel jPanel5 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JButton NouvClient = new JButton();
  JButton Quitter = new JButton();
  /*************************
  private int reference;
  private double montant;
  private FenetreClient client;
  private Vector listeExemplaire;
  */
//********************

/*
  public FenetreCommande() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    /*
    listeExemplaire=new Vector();
    reference=r;
    montant=m;
    client=c;
    */

 /*   this.setSize(new Dimension(600, 256));
    jPanel2.setLayout(gridLayout1);
    jLabel1.setText("          REFERENCE DU PRODUIT");
    jLabel2.setText("          NOM DU PRODUIT");
    jLabel3.setText("          NumFactureITE");
    jLabel4.setText("          NOM DU CLIENT");
    jLabel5.setOpaque(false);
    jLabel5.setText("          CONTRAT");
    jLabel6.setRequestFocusEnabled(true);
    jLabel6.setText("          CAMPAGNE");
    jLabel7.setText("          TELEPHONE");
    jLabel8.setText("          MONTANT DE LA COMMANDE");
    jLabel9.setText("          MOYEN DE PAIEMENT");
    jLabel10.setText("         REFERENCE CLIENT");
    jPanel3.setLayout(gridLayout2);
    jPanel4.setLayout(gridLayout3);
    Valider.setSelectedIcon(null);
    Valider.setText("VALIDER");
    Valider.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Annuler_actionPerformed(e);
      }
    });

    Annuler.setText("ANNULER");
    Annuler.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        Annuler_actionPerformed(e);
      }
    });

    jPanel1.setLayout(gridLayout4);
    NomClient.setText("");
    NumFacture.setText("");
    Dossier.setText("");
    PdsBrut.setText("");
    TVA.setText("");
    MontantHT.setText("");
    Prestation.setText("");
    DeboursMOE.setText("");
    NbrBalles.setText("");
    Campagne.setText("");
    montCommande.setText("");
    this.setResizable(false);
    this.setSize(new Dimension(555, 315));
    jPanel1.setPreferredSize(new Dimension(680, 25));

    jPanel5.setLayout(borderLayout1);
    NouvClient.setText("NOUVEAU");
    Quitter.setText("QUITTER");
    this.getContentPane().add(jPanel1,  BorderLayout.SOUTH);
    this.getContentPane().add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jPanel3, null);
    jPanel3.add(jLabel1, null);
    jPanel3.add(jLabel2, null);
    jPanel3.add(jLabel3, null);
    jPanel3.add(jLabel4, null);
    jPanel3.add(jLabel5, null);
    jPanel3.add(jLabel6, null);
    jPanel3.add(jLabel7, null);
    jPanel3.add(jLabel8, null);
    jPanel3.add(jLabel9, null);
    jPanel3.add(jLabel10, null);
    jPanel2.add(jPanel4, null);
   // jPanel4.add(refProd, null);
    jPanel4.add(NomClient, null);
    jPanel4.add(NumFacture, null);
    jPanel4.add(Dossier, null);
    jPanel4.add(PdsBrut, null);
    jPanel4.add(TVA, null);
    jPanel4.add(Prestation, null);
    jPanel4.add(DeboursMOE, null);
    jPanel4.add(MontantHT, null);
   // jPanel4.add(Civil, null);
    jPanel4.add(NbrBalles, null);
    jPanel4.add(Campagne, null);
    jPanel4.add(montCommande, null);
    jPanel4.add(moyPaiement, null);
    jPanel4.add(jPanel5, null);
   // jPanel5.add(Dossier, BorderLayout.CENTER);
    jPanel5.add(NouvClient, BorderLayout.EAST);
    jPanel1.add(Valider, null);
    jPanel1.add(Annuler, null);
    jPanel1.add(Quitter, null);
  }

  public void Annuler_actionPerformed(ActionEvent e){
    this.dispose();
      //Accueil ac = new Accueil();
      //ac.show();
  }

  //annuler
  public void Annuler(ActionEvent e){
    Dossier.setText("");
    NomClient.setText("");
    PdsBrut.setText("");
    DeboursMOE.setText("");
   // Civil.setSelectedIndex(0);
   // Type.setSelectedIndex(0);
    Prestation.setText("");
    MontantHT.setText("");
    TVA.setText("");
  }

  //valider
  public void Valider(ActionEvent e){
    LireEtEcrire et = new LireEtEcrire();
    Format fr = new Format();
    String message;
    message= fr.devFormat(Dossier.getText())+"\t"+fr.devFormat(NomClient.getText())+"\t"+fr.devFormat(PdsBrut.getText())+"\t"+fr.devFormat(DeboursMOE.getText())+"\t"+"\t"+fr.devFormat(Prestation.getText())+"\t"+fr.devFormat(MontantHT.getText())+"\t"+fr.devFormat(TVA.getText());
    et.ecrire(message,"Commandes.xls");
    Annuler(e);
  }
/*
  public void setreference(int r){
    reference=r;
  }

  public int getReference(){
    return reference;
  }

  public void setMontant(double m){
    montant=m;
  }

  public double getMontant(){
    return montant;
  }
  public void setClient(FenetreClient c){
    client=c;
  }

  public void setLiteExemplaire(Exemplaire e){
    listeExemplaire.add(e);
  }

  public Vector getListeExemplaire(){
    return listeEmplaire;
  }
*/
//}

