package HpaDependables;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private final String filepath;

    public ExcelUtility(String filepath) {
        this.filepath = filepath;
    }

    // Get total row count
    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(filepath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int lastRow = sheet.getLastRowNum();
            int count = 0;
            
            for(int i =0; i<=lastRow; i++) {
            	Row row = sheet.getRow(i);
            	  if (row != null && row.getCell(0) != null && 
                          !row.getCell(0).toString().trim().isEmpty()) {
                          count++;
            	
            }
            }
            return count;
        }
    }
    

    // Get total column count (from header row)
    public int getTotalColCount(String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(filepath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(0);
            return (row != null) ? row.getLastCellNum() : 0;
        }
    }

    // Get single cell data safely
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        try (FileInputStream fis = new FileInputStream(filepath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            if (row == null) return "";   // no such row
            Cell cell = row.getCell(colNum);
            if (cell == null) return "";  // no such cell

            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        }
    }
}
