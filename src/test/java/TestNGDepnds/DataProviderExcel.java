package TestNGDepnds;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import HpaDependables.ExcelUtility;

public class DataProviderExcel {
	
	@DataProvider(name = "shiftData")
	public Object[][] getShiftData() throws IOException {
		String excelPath = "src/test/resources/HpaDataSheet.xlsx";
		String sheetName = "Sheet1";
		ExcelUtility excel = new ExcelUtility(excelPath);
		
		
		 int rows =excel.getRowCount(sheetName);
		 
		 int cols =excel.getTotalColCount(sheetName);
		
		 Object[][] data =  new Object[rows - 1][cols];
		 
		 for (int i = 1; i < rows; i++) {
			 
			    for (int j = 0; j < cols; j++) {
			    	
			        String cellValue = excel.getCellData(sheetName, i, j);  // always String

			     
			        if ((j == 1 || j == 2 || j == 5 || j == 6) && !cellValue.isEmpty()) {
			            data[i - 1][j] = Integer.parseInt(cellValue);
			        } else {
			            data[i - 1][j] = cellValue;
			        }
			    }
			}
		
		 return data;
	}
}
