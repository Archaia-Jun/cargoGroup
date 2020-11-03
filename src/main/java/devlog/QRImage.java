package devlog;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QRImage extends JFrame {
    public static void main(String[] args) {
        new QRImage();
    }

    public QRImage() {
        this.setTitle("Picture Application");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel panel1 = new JPanel();
        ImageIcon pic = new ImageIcon("JD.png");
        panel1.add(new JLabel(pic));
        this.add(panel1);
        this.pack();
        this.setVisible(true);
    }
}

