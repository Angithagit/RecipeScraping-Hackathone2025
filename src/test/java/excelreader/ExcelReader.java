package excelreader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

	private static final String INGREDIENTS_FILE = "src/test/java/utils/IngredientsAndComorbiditiestestdata.xlsx";
	private static final String CATEGORY_FILE = "src/test/java/utils/Recipefilterstestdata.xlsx";

	public static Set<String> getColumnValues(String sheetName, int colIndex) {
		Set<String> values = new HashSet<>();
		try (Workbook workbook = new XSSFWorkbook(new FileInputStream(INGREDIENTS_FILE))) {
			Sheet sheet = workbook.getSheet(sheetName);
			for (Row row : sheet) {
				Cell cell = row.getCell(colIndex);
				if (cell != null) {
					String value = cell.toString().trim().toLowerCase();
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return values;
	}

	public static List<Map<String, String>> readCategoryData() {
		List<Map<String, String>> data = new ArrayList<>();
		try (Workbook workbook = new XSSFWorkbook(new FileInputStream(CATEGORY_FILE))) {
			Sheet sheet = workbook.getSheet("Food Category");
			Row headerRow = sheet.getRow(0);
			int numCols = headerRow.getLastCellNum();

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row == null)
					continue;

				Map<String, String> rowMap = new HashMap<>();
				for (int j = 0; j < numCols; j++) {
					Cell keyCell = headerRow.getCell(j);
					Cell valueCell = row.getCell(j);
					if (keyCell != null && valueCell != null) {
						rowMap.put(keyCell.getStringCellValue().trim().toLowerCase(),
								valueCell.toString().trim().toLowerCase());
					}
				}
				data.add(rowMap);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
