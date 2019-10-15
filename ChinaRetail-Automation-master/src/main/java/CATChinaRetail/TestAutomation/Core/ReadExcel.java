package CATChinaRetail.TestAutomation.Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	static int ActualRowNum = 0;
	static String SheetName = null;

	public static void SheetName(String InputSheetName) {
		SheetName = InputSheetName;
	}

	@SuppressWarnings("resource")
	public static int TestCaseName(String TestCaseName) throws IOException

	{
		File file = new File("./src/main/resources/DataSheet.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook currentWorkbook = null;
		currentWorkbook = new XSSFWorkbook(inputStream);
		Sheet currentSheet = currentWorkbook.getSheet(SheetName);
		int rowCount = currentSheet.getLastRowNum() - currentSheet.getFirstRowNum();
		int i = 1;

		for (i = 1; i < rowCount + 1; i++) {
			Row row = currentSheet.getRow(i);
			if (row.getCell(0).getStringCellValue().equals(TestCaseName)) {
				ActualRowNum = i;
				break;
			} else {
				continue;
			}
		}

		
		inputStream.close();
		return ActualRowNum;
	}

	@SuppressWarnings("resource")
	public static String ReadColumn(String ColumnName) throws IOException {

		File file = new File("./src/main/resources/DataSheet.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook currentWorkbook = null;
		currentWorkbook = new XSSFWorkbook(inputStream);
		Sheet currentSheet = currentWorkbook.getSheet(SheetName);
		Row row = currentSheet.getRow(0);
		int j = 0;
		for (j = 0; j < row.getLastCellNum(); j++) {

			if (row.getCell(j).getStringCellValue().equals(ColumnName)) {
				break;
			}

		}

		row = currentSheet.getRow(ActualRowNum);
		String rowValue = row.getCell(j).getStringCellValue();
		
		inputStream.close();
		return rowValue;

	}

}
