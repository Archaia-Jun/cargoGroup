package devlog;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class TablePrinter implements Printable {

    private JTable table;
    private HeadBandPrintable header;
    private HeadBandPrintable footer;
    private Printable tablePrintable;
    private JTable.PrintMode printMode;
    private int lastPage;


    public TablePrinter(JTable table, JTable.PrintMode printMode, HeadBandPrintable header, HeadBandPrintable footer) {
        this.table=table;
        this.header=header;
        this.footer=footer;
        this.printMode=printMode;
    }

    public void reset() {
        tablePrintable=null;
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        if ( tablePrintable==null || pageIndex<lastPage) {
            tablePrintable = this.table.getPrintable(printMode, null, null);
        }

        double headerHeight;
        if ( header!=null ) {
            headerHeight = Math.max(0, header.getHeight());
        }
        else {
            headerHeight = 0;
        }

        double footerHeight;
        if ( footer!=null ) {
            footerHeight = Math.max(0, footer.getHeight());
        }
        else {
            footerHeight = 0;
        }

        PageFormat tablePageFormat;
        if ( headerHeight!=0 || footerHeight!=0 ) {

            tablePageFormat = new HeaderFooterPageFormat(pageFormat, headerHeight, footerHeight);

        }
        else {
            tablePageFormat = pageFormat;
        }

        int page = tablePrintable.print(graphics, tablePageFormat, pageIndex);
        if (page==Printable.PAGE_EXISTS) {
            if ( header!=null ) {
                HeaderFooterPageFormat headerPageFormat = new HeaderFooterPageFormat(pageFormat, 0, tablePageFormat.getImageableHeight()+footerHeight);
                header.print(graphics, headerPageFormat, pageIndex);
            }
            if ( footer!=null ) {
                HeaderFooterPageFormat footerPageFormat = new HeaderFooterPageFormat(pageFormat, tablePageFormat.getImageableHeight()+headerHeight, 0);
                footer.print(graphics, footerPageFormat, pageIndex);
            }
        }
        return page;

    }

    private static class HeaderFooterPageFormat extends PageFormat {

        private PageFormat pageFormat;
        private double headerHeight;
        private double footerHeight;
        private Rectangle2D.Double imageable;

        public HeaderFooterPageFormat(PageFormat pageFormat, double headerHeight, double footerHeight) throws PrinterException {
            this.pageFormat = pageFormat;
            this.headerHeight = headerHeight;
            this.footerHeight = footerHeight;

            computeImageable();


        }

        private void computeImageable() throws PrinterException {
            double height = this.pageFormat.getImageableHeight()-headerHeight-footerHeight;
            if (height<0 ) {
                throw new PrinterException("Not enough space to print - format: " + this.pageFormat.getImageableHeight() + " - header: " + headerHeight + " - footer: " + footerHeight );
            }
            imageable = new Rectangle2D.Double(pageFormat.getImageableX(), pageFormat.getImageableY()+headerHeight, pageFormat.getImageableWidth(), height);
        }

        @Override
        public double getHeight() {
            return pageFormat.getHeight();
        }

        @Override
        public double getWidth() {
            return pageFormat.getWidth();
        }

        @Override
        public Paper getPaper() {
            return pageFormat.getPaper();
        }

        @Override
        public int getOrientation() {
            return pageFormat.getOrientation();
        }

        @Override
        public double[] getMatrix() {
            return pageFormat.getMatrix();
        }

        @Override
        public double getImageableX() {
            return imageable.getX();
        }

        @Override
        public double getImageableWidth() {
            return imageable.getWidth();
        }

        @Override
        public double getImageableY() {
            return imageable.getY();
        }

        @Override
        public double getImageableHeight() {
            return imageable.getHeight();
        }

        @Override
        public void setOrientation(int orientation) throws IllegalArgumentException {
            pageFormat.setOrientation(orientation);
            try {
                computeImageable();
            } catch (PrinterException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void setPaper(Paper paper) {
            pageFormat.setPaper(paper);
            try {
                computeImageable();
            } catch (PrinterException e) {
                throw new RuntimeException(e);
            }
        }

        public PageFormat getPageFormat() {
            return pageFormat;
        }

    }

    public static interface HeadBandPrintable extends Printable {

        double getHeight();

        int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException;

    }

}
