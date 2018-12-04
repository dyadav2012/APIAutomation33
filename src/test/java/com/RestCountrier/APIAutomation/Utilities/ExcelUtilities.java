package com.RestCountrier.APIAutomation.Utilities;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtilities {

	private static final String testDataFile = PropertiesUtilities.readProperty("Configuration.Properties", "testData");

	// Read test data from variable sheetName
	public static String[][] readTestData(String sheetName) {
		File inputWorkbook = new File(testDataFile);
		Workbook workbook;
		String[][] testData = null;
		try {
			workbook = Workbook.getWorkbook(inputWorkbook);
			// Get the sheet required
			Sheet sheet = workbook.getSheet(sheetName);

			// Get No of columns

			int noOfColumns = sheet.getColumns();

			// get no of rows

			int noOfRows = sheet.getRows();

			testData = new String[noOfColumns][noOfRows - 1];

			// reading row from 1st as 0th row is header

			for (int j = 0; j < noOfColumns; j++) {
				for (int i = 1; i < noOfRows; i++) {
					Cell cell = sheet.getCell(j, i);
					testData[j][i - 1] = cell.getContents();
				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testData;
	}

	// Write Test data into existing excel sheet

	public static void writeResult(String sheetName, int column, int row, String contents) {

		try {

			Workbook wb = Workbook.getWorkbook(new File(testDataFile));

			WritableWorkbook writeWbk = Workbook.createWorkbook(new File("D:\temp.xls"), wb);
			WritableSheet sheet = writeWbk.getSheet(sheetName);

			// Creating a object of label before writing into cell

			Label label = new Label(column, row, contents);

			// adding cell to sheet
			
			sheet.addCell(label);
			
			// writing into Workbook
			
			writeWbk.write();
			
			// closing workbooks
			
			writeWbk.close();
			wb.close();
			writeWbk.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
