package devlog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintPreview extends JFrame implements Runnable
{
    public static TablePrinterDemo.PrintPreview.TextUtils TextUtils;
    protected JScrollPane displayArea;
    protected int m_wPage;
    protected int m_hPage;
    protected int width;
    protected int height;
    protected Printable m_target;
    protected JComboBox m_cbScale;
    protected PreviewContainer m_preview;
    protected static PageFormat pp_pf = null;
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
    private static boolean bScallToFitOnePage = false;
    private String msg;
    JTable table;
    static TablePrinter.HeadBandPrintable header;
    static TablePrinter.HeadBandPrintable footer;


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
                    PagePreview pp = new PagePreview(width, height, img);
                    m_preview.add(pp);
                    break;
                } else
                if (m_target.print(g, pp_pf, pageIndex) !=  Printable.PAGE_EXISTS)
                    break;
                PagePreview pp = new PagePreview(width, height, img);
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
        m_preview = new PreviewContainer();
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
            BufferedImage imageg = ImageIO.read(getClass().getResource("../icones/logog1.png"));
            BufferedImage imaged = ImageIO.read(getClass().getResource("../icones/logod1.png"));
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                graphics.drawImage(imageg, (int)pageFormat.getImageableX()-30, (int)pageFormat.getImageableY()+10, null);
                graphics.setFont(Font.decode("Arial-BOLD-20"));
                TextUtils.drawString(graphics, headerField.getText(),
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
                TextUtils.drawString(graphics, "Page " + (pageIndex+1),new Rectangle2D.Double(pageFormat.getImageableX(),
                                pageFormat.getImageableY(),pageFormat.getImageableWidth(),pageFormat.getImageableHeight()),
                        SwingConstants.CENTER, SwingConstants.CENTER);
                return Printable.NO_SUCH_PAGE;
            }


            public double getHeight() {
                return 20;
            }

        };


    }
    private static void printTable(JTable table, TablePrinter.HeadBandPrintable header, TablePrinter.HeadBandPrintable footer) {

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
            Logger.getLogger(PrintPreview.class.getName()).log(Level.SEVERE, null, ex);
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
            if (!(comps[k] instanceof PagePreview))
                continue;
            PagePreview pp = (PagePreview)comps[k];
            pp.setScaledSize(width, height);
        }
        m_preview.doLayout();
        m_preview.getParent().getParent().validate();

    }

    static class PreviewContainer extends JPanel
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

    static class PagePreview extends JPanel
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

    private static class TextUtils {
        public static void drawString(Graphics graphics, String s, Rectangle2D.Double aDouble, int center, int center1) {
        }
    }
}
