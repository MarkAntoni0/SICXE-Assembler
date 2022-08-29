import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExtractData {
	public static String[][] readExcel(String fileName, String sheetName) {

		String[][] dataTable = null;

		try {

			// Create a file input stream to read Excel workbook and worksheet
			File xlFile = new File("./SICXE-Code/" + fileName);
			FileInputStream fis = new FileInputStream(xlFile);
			XSSFWorkbook xlWB = new XSSFWorkbook(fis);
			XSSFSheet xlSheet = xlWB.getSheet(sheetName);

			// Get the number of rows and columns
			int numRows = xlSheet.getLastRowNum() + 1;
			int numCols = xlSheet.getRow(0).getLastCellNum();

			// Create double array data table - rows x cols
			// We will return this data table
			dataTable = new String[numRows][numCols];

			// For each row, create a HSSFRow, then iterate through the "columns"
			// For each "column" create an HSSFCell to grab the value at the specified cell
			// (i,j)
			for (int i = 0; i < numRows; i++) {
				XSSFRow xlRow = xlSheet.getRow(i);
				for (int j = 0; j < numCols; j++) {
					XSSFCell xlCell = xlRow.getCell(j);
					dataTable[i][j] = xlCell.toString();
				}
			}
			
			//close the workbook once done
			xlWB.close();
		} catch (Exception e) {
			System.out.println("Error when reading xlsx file:" + e.getMessage());
		}

		return dataTable;
	}
}
