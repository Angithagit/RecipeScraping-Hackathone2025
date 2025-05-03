package Commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	String [] [] ingredient = new String [100] [100];
	ArrayList<String> retreiveditem = new ArrayList<String>();
	int i = 0,j=0;

public ArrayList<String> readExcelSheet(int rowvalue, int colvalue, String sheetname) throws IOException {
	
		String path = System.getProperty("user.dir")+"/src/test/resources/testdata/IngredientsAndComorbidities-ScrapperHackathon.xlsx";
		File Excelfile = new File(path);
		
		FileInputStream Fis = new FileInputStream(Excelfile);
		XSSFWorkbook workbook = new XSSFWorkbook(Fis);
		XSSFSheet sheet = workbook.getSheet(sheetname);
		
		Iterator<Row> row = sheet.rowIterator();
		
		while(row.hasNext()) {
			
			Row currRow = row.next();
			Iterator<Cell> cell = currRow.cellIterator();
			
			while(cell.hasNext()) {
				Cell currCell = cell.next();
				i=currCell.getRowIndex();
				j=currCell.getColumnIndex();				
				ingredient[i][j] = currCell.getStringCellValue();
			}
		}
		workbook.close();
		retreiveditem.add(ingredient[rowvalue][colvalue]);
		return retreiveditem;
	}

public ArrayList<String> LFV_checklist(int rownumber,int columnnumber) throws IOException {	
	ArrayList<String> checkitem = new ArrayList<String>();
	String sheetname = "Final list for LFV Elimination";
	checkitem = readExcelSheet(rownumber, columnnumber, sheetname);
	LoggerLoad.info("Ingredient check on:"+checkitem);
	return checkitem;	
}

public ArrayList<String> LCHF_checklist(int rownumber,int columnnumber) throws IOException {
	ArrayList<String> checkitem = new ArrayList<String>();	
	String sheetname = "Final list for LCHFElimination";
	checkitem = readExcelSheet(rownumber, columnnumber, sheetname);
	LoggerLoad.info("Ingredient check on:"+checkitem);
	return checkitem;
}

public ArrayList<String> Allergy_list(int rownumber,int columnnumber) throws IOException {
	ArrayList<String> checkitem = new ArrayList<String>();	
	String sheetname = "Filter -1 Allergies - Bonus Poi";
	checkitem = readExcelSheet(rownumber, columnnumber, sheetname);
	LoggerLoad.info("Ingredient check on:"+checkitem);
	return checkitem;
}



}