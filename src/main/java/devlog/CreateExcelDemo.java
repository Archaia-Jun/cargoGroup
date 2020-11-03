package devlog;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CreateExcelDemo extends AfficheCommandes {

    // génère un fichier excel avec des champs

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public static void main(String[] args) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");

        List<Commande> list = CommandeDAO.listCommandes();

        int rownum = 0;
        Cell cell;
        Row row;

        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        // EmpNo
        cell = row.createCell(0, CellType.NUMERIC);
        cell.setCellValue("Numéro Facture");
        cell.setCellStyle(style);
        // EmpName
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Nom Client");
        cell.setCellStyle(style);
        // Salary
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Dossier");
        cell.setCellStyle(style);
        // Grade
        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue("Poids Brut");
        cell.setCellStyle(style);
        // Bonus
        cell = row.createCell(4, CellType.NUMERIC);
        cell.setCellValue("Debours / MO");
        cell.setCellStyle(style);


        // EmpNo
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Prestation");
        cell.setCellStyle(style);
        // EmpName
        cell = row.createCell(6, CellType.NUMERIC);
        cell.setCellValue("Montant HT");
        cell.setCellStyle(style);
        // Salary
        cell = row.createCell(7, CellType.NUMERIC);
        cell.setCellValue("TVA");
        cell.setCellStyle(style);
        // Grade
        cell = row.createCell(8, CellType.NUMERIC);
        cell.setCellValue("Montant TVA");
        cell.setCellStyle(style);
        // Bonus
        cell = row.createCell(9, CellType.NUMERIC);
        cell.setCellValue("Montant TTC");
        cell.setCellStyle(style);

        // EmpNo
        cell = row.createCell(10, CellType.NUMERIC);
        cell.setCellValue("AIB");
        cell.setCellStyle(style);
        // EmpName
        cell = row.createCell(11, CellType.NUMERIC);
        cell.setCellValue("Net A Payer");
        cell.setCellStyle(style);


        // Data
        for (Commande emp : list) {
            rownum++;
            row = sheet.createRow(rownum);

            // EmpNo (A)
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(emp.getNumFacture());
            // EmpName (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(emp.getNomClient());
            // Salary (C)
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(emp.getDossier());
            // Grade (D)
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(emp.getPoidsBrut());
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(emp.getDebooursMOE());
            // EmpName (B)
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(emp.getPrestation());
            // Salary (C)
            cell = row.createCell(6, CellType.NUMERIC);
            cell.setCellValue(emp.getMontantHT());
            // Grade (D)
            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellValue(emp.getTVA());
            cell = row.createCell(8, CellType.NUMERIC);
            cell.setCellValue(emp.getMontantTVA());
            // EmpName (B)
            cell = row.createCell(9, CellType.NUMERIC);
            cell.setCellValue(emp.getMontantTTC());
            // Salary (C)
            cell = row.createCell(10, CellType.NUMERIC);
            cell.setCellValue(emp.getAIB());
            // Salary (C)
            cell = row.createCell(11, CellType.NUMERIC);
            cell.setCellValue(emp.getNetAPayer());

            // Salary (C)
           // cell = row.createCell(12, CellType.NUMERIC);
           // cell.setCellValue(emp.getAIB());
            // Salary (C)
           // cell = row.createCell(13, CellType.NUMERIC);
           // cell.setCellValue(emp.getNetAPayer());


            // Bonus (E)
            //String formula = "0.1*C" + (rownum + 1) + "*D" + (rownum + 1);
           // cell = row.createCell(4, CellType.FORMULA);
           // cell.setCellFormula(formula);


        }
        File file = new File("C:/Users/LENOVO/IdeaProjects/bccLog/employees.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }

}
// pour mettre une formue dans une case
// // Create Cell type of FORMULA
//cell = row.createCell(rowIndex, CellType.FORMULA);
//
//// Set formula
//cell.setCellFormula("SUM(C2:C4)");