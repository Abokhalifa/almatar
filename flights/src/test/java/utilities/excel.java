package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excel {
	
	public static FileInputStream fi; 
	public static FileOutputStream fo; 
	public static XSSFWorkbook wb; 
	public static XSSFSheet ws; 
	public static XSSFRow row;
	public static XSSFCell cell; 
	
	public static int getRowCount(String xlFile, String xlSheet) throws IOException
	{
		System.out.println("Good Evening!!");
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		int rowCount =ws.getLastRowNum();
		wb.close();	
		fi.close();
		return rowCount;
	}
	public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException
	{
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		int cellCount =row.getLastCellNum();
		wb.close();	
		fi.close();
		return cellCount;
	}
	
	public static String getCellData(String xlFile, String xlSheet, int rowNum,int column) throws IOException
	{
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		cell = row.getCell(column);
		String data;
		
		try
		{
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		}
		
		catch(Exception e)
		{
			data = "";
		}
		
		wb.close();	
		fi.close();
		return data;
	}
	
	public static void setCellData(String xlFile, String xlSheet, int rowNum,int column,String data) throws IOException
	{
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		cell =row.createCell(column);
		cell.setCellValue(data);
		fo=new FileOutputStream(xlFile);
		wb.write(fo);
		wb.close();	
		fi.close();
		fo.close();
	}
	
	


}
