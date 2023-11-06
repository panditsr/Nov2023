package com.automation.tests.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiXlsxFileUtility {

	private static XSSFWorkbook workbook=null;
     
		public static Object  readSingledatafromxlsxfile(File path,String sheetName,int rowNum,int cellNum)  {
			Object data=null;
			
			//properties pro=new properties();
			//FileInputStream fi=new FileInputStream(new File(System.getProperty("user.dir")+ "/dataexcel/Spreadsheet_java.xlsx")	);
			 try {
				workbook=new XSSFWorkbook(path);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			XSSFSheet sheet= workbook.getSheet(sheetName);
			XSSFRow row= sheet.getRow(rowNum);
			XSSFCell cell= row.getCell(cellNum);
			
			if (cell.getCellType()==CellType.NUMERIC)
				data=(Object)Double.valueOf(cell.getNumericCellValue());
			else if (cell.getCellType()== CellType.STRING)
				data=(Object)cell.getStringCellValue();
			 try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return data;
	}
		
		public static  Object[][] readAlldatafromxlsxfileA(String path,String sheetname) throws IOException  {
			

			
			FileInputStream fi=new FileInputStream(new File(path));
			XSSFWorkbook workbook=new XSSFWorkbook(fi); //.xlsx
			XSSFSheet sheet= workbook.getSheet("sheetname");
			int rowcount= sheet.getLastRowNum()+1;
			int columncount = sheet.getRow(0).getLastCellNum();
			
			Object[][]data =new Object[rowcount][columncount];
			Iterator<Row>rowit=sheet.rowIterator();
			int i=0,j=0;
			while(rowit.hasNext()) {
				Row row=rowit.next();
				Iterator<Cell>cellit=row.cellIterator();
				j=0;
				while(cellit.hasNext()) {
					Cell cell=cellit.next();
		
			if (cell.getCellType()==CellType.NUMERIC) {
				data[i][j] =cell.getNumericCellValue();}
				
			else if (cell.getCellType()== CellType.STRING) {
				data[i][j] =cell.getStringCellValue();
				
				}
				j++;
			}
			i++;
			}
		return data;



		}

			
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
	     

}
}