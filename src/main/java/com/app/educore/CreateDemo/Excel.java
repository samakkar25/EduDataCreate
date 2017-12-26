package com.app.educore.CreateDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	
	public static String excelPath = "C:\\Users\\vikas.tyagi\\Desktop\\testdata3.xlsx";
	public static File excelFile = new File(excelPath);
	public static XSSFWorkbook excelBook;
	public static XSSFSheet sheet;
	public static HashMap<String, Object> sheetvalues = new HashMap<String, Object>();
	public static FileInputStream fis;
	
	public static void loadExcel() throws FileNotFoundException {
		
		if (!excelFile.exists()) {
			System.out.println("file dont exist");
		}
		
		fis = new FileInputStream(excelPath);
		
	}
	
	
	public static void loadData() throws Exception {
		
		loadExcel();
		
		excelBook = new XSSFWorkbook(fis);
		Iterator<XSSFSheet> sheets;
		sheets = excelBook.iterator();
		Row row;
		Cell cell = null;
		while (sheets.hasNext()) {
			
			HashMap<Integer, Object> rowvalues = new HashMap<Integer, Object>();
			
			sheet = sheets.next();
			Iterator<Row> rows = sheet.iterator();
			String sheetName = sheet.getSheetName();
			int iRow = 1;
			while (rows.hasNext()) {
				row = rows.next();
				
				HashMap<Integer, String> cellvalues = new HashMap<Integer, String>();
				
				Iterator<Cell> cells = row.iterator();
				int iCol = 1;
				while (cells.hasNext()) {
					cell = cells.next();
					
					switch(cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						//return cell.getStringCellValue();
					//case Cell.CELL_TYPE_STRING:
						//return cell.getStringCellValue();			
					}
					String cellValue = cell.getStringCellValue();
					cellvalues.put(iCol, cellValue);
					iCol++;
				}
				rowvalues.put(iRow, cellvalues);
				iRow++;
			}
			sheetvalues.put(sheetName, rowvalues);
		}
		
		closeExcel();
	}
	
	public static void closeExcel() throws IOException {
		
		fis.close();
	
	}

	
	
	

	public static int getrowcount(String sheetName) throws Exception {
		
		if (!excelFile.exists()) {
			System.out.println("file dont exist");
		}
		
		FileInputStream fis = new FileInputStream(excelPath);
		
		excelBook = new XSSFWorkbook(fis);
		XSSFSheet sheet = excelBook.getSheet(sheetName);
		fis.close();
		return sheet.getLastRowNum();
	}
	
	
	@SuppressWarnings("unchecked")
	public static String getdata(String sheetName, int rowIndex, int colIndex) throws Exception {
		
		Map<Integer, Object> sheetvalue = (Map<Integer, Object>) sheetvalues.get(sheetName);
		Map<Integer, Object> rowvalue = (Map<Integer, Object>) sheetvalue.get(rowIndex);
		String value = (String) rowvalue.get(colIndex);
		
		return value;
		
	}
	
	public Excel(String sheetName) throws Exception {
		
		if (!excelFile.exists()) {
			System.out.println("file dont exist");
		}
		
		FileInputStream fis = new FileInputStream(excelPath);
		/*
		if (excelFile.getName().toLowerCase().endsWith(".xlsx")) {
			excelBook = new XSSFWorkbook(fis);
		} else {
			excelBook = new HSSFWorkbook(fis);
		}
		*/
		excelBook = new XSSFWorkbook(fis);
		sheet = excelBook.getSheet(sheetName);
		
		fis.close();
		
	}
	
	
	public String getdata(int rowIndex, int colIndex) throws Exception {
	
		Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
		switch(cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return cell.getStringCellValue() + "";
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();			
		}
		
		return null;
		
	}

	
	public void save() throws Exception {
		FileOutputStream fos = new FileOutputStream(excelPath);
		excelBook.write(fos);
		fos.close();
	}
	
	
	public void setdata(String sheetName, int rowIndex, int colIndex, String value) throws Exception {
		String excelPath = "C:\\Users\\vikas.tyagi\\Desktop\\testdata.xlsx";
		File excelFile = new File(excelPath);
		
		if (!excelFile.exists()) {
			System.out.println("file dont exist");
		}
		
		FileInputStream fis = new FileInputStream(excelPath);
		
		/*if (excelFile.getName().toLowerCase().endsWith(".xlsx")) {
			excelBook = new XSSFWorkbook(fis);
		} else {
			excelBook = new HSSFWorkbook(fis);
		}*/
		excelBook = new XSSFWorkbook(fis);
		fis.close();
		
		/*sheet = excelBook.getSheet(sheetName);
		Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(value);
		
		FileOutputStream fos = new FileOutputStream(excelPath); // saves xls file to
		excelBook.write(fos);
		fos.close();*/
	}
}
