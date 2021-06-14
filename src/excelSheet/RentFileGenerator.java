package excelSheet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import parameters.RentInformation;

public class RentFileGenerator 
{
    // The headers for the file.
    private static final String[] titles = {"Name", "Previous", "Current","Difference", 
                            "Electricity Total", "Rent", "Water", "Members", "Total"};
   
    private static final String[] utilities = {"Electricity", "Water"};

    // Data for the Second Sheet.
    private static final int[] rates = {24, 150};
    
    public RentFileGenerator() throws FileNotFoundException, IOException 
    {
        // Creating the workbook.
        Workbook wb = new HSSFWorkbook();
        
        // Creating the sheet.
        Sheet sheet1 = wb.createSheet("Rent");
        Sheet sheet2 = wb.createSheet("Rates");
        
        // This is the style of the workbook.
        Map<String, CellStyle> styles = createStyles(wb);
        
        // This is for setting the headers on the Rent Sheet.
        Row headerRow = sheet1.createRow(0);
        for (int i = 0; i < titles.length; i++) 
        {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(titles[i]);
            headerCell.setCellStyle(styles.get("header"));
            sheet1.autoSizeColumn(i);
        }
        
        // Creating the header of the Rates Sheet.
        Row rateRow = sheet2.createRow(0);
        Cell rateCell = rateRow.createCell(0);
        rateCell.setCellValue("Rates");
        rateCell.setCellStyle(styles.get("header"));
        
        sheet2.addMergedRegion(CellRangeAddress.valueOf("A1:B1"));
 
        for (int i = 0; i < utilities.length; i++) 
        {
            Row utilityRow = sheet2.createRow(i + 1);
            Cell cell = utilityRow.createCell(0);
            cell.setCellValue(utilities[i]);
            
            cell = utilityRow.createCell(1);
            cell.setCellValue(rates[i]);
        }
        
        // This is the actual data.
        Row row;
        Cell cell;
        String formula;
        
        // Gets a new row.
        int rownum = sheet1.getLastRowNum() + 1;
        row = sheet1.createRow(rownum);

        for (int i = 0; i < titles.length; i++) 
        {
            // Creates a new row.
            cell = row.createCell(i);
            
            switch (i) 
            {
                case 0: 
                {
                    // Name
                    cell.setCellValue(RentInformation.getName());
                    break;
                }
                case 1: 
                {
                    // Previous Electricity
                    cell.setCellValue(RentInformation.getPreviousElectricity());
                    break;
                }
                case 2:
                {
                    // Current Electricity
                    cell.setCellValue(RentInformation.getCurrentElectricity());
                    break;
                }
                case 3:
                {
                    // Electric Difference
                    formula = String.format("C%s-B%s", rownum + 1, rownum + 1);
                    cell.setCellFormula(formula);
                    break;
                }
                case 4:
                {
                    // Electricity Total
                    formula = String.format("D%s*Rates!B2", rownum + 1);
                    cell.setCellFormula(formula);
                    break;
                }
                case 5:
                {
                    // Rent
                    cell.setCellValue(RentInformation.getRent());
                    break;
                }
                case 6:
                {
                    // Water
                    formula = String.format("H%s*Rates!B3", rownum + 1);
                    cell.setCellFormula(formula);
                    break;
                }
                case 7:
                {
                    // Members
                    cell.setCellValue(RentInformation.getMembers());
                    break;
                }
                default:
                {
                    // Total Bill
                    formula = "SUM(E2:G2)";
                    formula = String.format("SUM(E%s:G%s)", rownum + 1, rownum + 1);
                    cell.setCellFormula(formula);
                    break;
                }
            }
            
            sheet1.autoSizeColumn(i);
            cell.setCellStyle(styles.get("normal"));
        }       
        // This is the end of the actual data.
        
        // Creating the file.
        String file = "Renting Info.xls";
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();

        wb.close();
        
        // A popup indicating the file has been created.
        JOptionPane.showMessageDialog(null, "Your file has been generated.");
    }
    
        // Used to create the styles of respective cells to be stored in a HashMap.
        private static Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<>();
        
        // Style for the headers.
        CellStyle style;
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBold(true);
        headerFont.setFontName("Times New Roman");
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("header", style);
        
        // Style for the normal cells.
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        styles.put("normal", style);
        
        return styles;
    }
        
        private static CellStyle createBorderedStyle(Workbook wb){
        BorderStyle thin = BorderStyle.THIN;
        short black = IndexedColors.BLACK.getIndex();

        CellStyle style = wb.createCellStyle();
        style.setBorderRight(thin);
        style.setRightBorderColor(black);
        style.setBorderBottom(thin);
        style.setBottomBorderColor(black);
        style.setBorderLeft(thin);
        style.setLeftBorderColor(black);
        style.setBorderTop(thin);
        style.setTopBorderColor(black);
        return style;
    }
}
