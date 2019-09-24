package tests;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.testBase;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.Method;
import utilities.excel;
import utilities.miscellaneous;

public class TC01_addEmployee2 extends testBase {
	
	public static HashMap map = new HashMap();
	public static JSONObject requestParams = new JSONObject(); 
	
	
	@BeforeClass
	public void prepareForTest() throws InterruptedException
	{
		logger.info("******Starting TC01_addEmployee*******");
		RestAssured.baseURI = "http://restapi.demoqa.com";
		httpRequest = RestAssured.given();		
		httpRequest.header("Content-Type","application/json");
		Thread.sleep(3);
	
	}
	
	
	@Test(dataProvider = "emplyeeDataProviderExcel")
	public void addEmployeeRecord(String FirstName , String LastName, String UserName, String Password, String Email)
	{
			
		requestParams.put("FirstName", FirstName);
		requestParams.put("LastName", LastName);
		requestParams.put("UserName", UserName);
		requestParams.put("Password", Password);
		requestParams.put("Email", Email);
		
		httpRequest.body(requestParams.toString());
		response = httpRequest.request(Method.POST,"/customer/register");
		
		String responseBody = response.getBody().asString();
			
		
		Assert.assertEquals(responseBody.contains("OPERATION_SUCCESS"),true);
	}
	
	@Test(dataProvider = "emplyeeDataProviderExcel")
	public void addEmployeeRecordHashMap(String FirstName , String LastName, String UserName, String Password, String Email)
	{
		map.put("FirstName", FirstName);
		map.put("LastName", LastName);
		map.put("UserName", UserName);
		map.put("Password", Password);
		map.put("Email", Email);
		
		httpRequest.body(map);
		response = httpRequest.request(Method.POST,"/customer/register");
		
		String responseBody = response.getBody().asString();
			
		
		Assert.assertEquals(responseBody.contains("OPERATION_SUCCESS"),true);
		
			

		
	}
	
	@DataProvider(name = "emplyeeDataProviderStatic")
	String[][] emplyeeDataProvider() throws IOException
	{
	
		
		
		String [][] emplyeeData = {{"Ahmed","Abokhalifa","A"+miscellaneous.generateRandomNumber(),"test123","aa@gg"+miscellaneous.generateRandomNumber()+".net"},
				                   {"Ahmed","Abokhalifa","A"+miscellaneous.generateRandomNumber(),"test123","aa@gg"+miscellaneous.generateRandomNumber()+".net"}};
		return emplyeeData;
		
	}
	
	@DataProvider(name = "emplyeeDataProviderExcel")
	String[][] emplyeeDataProviderExcel() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/resources/empData.xlsx";
		int rowCount = excel.getRowCount(path, "empData");
		int colCount = excel.getCellCount(path, "empData",1);
		String emplyeeData [][] = new String[rowCount][colCount];
		for (int i=1; i<=rowCount;i++)
		{
			for (int j =0 ;j<colCount;j++)
			{
				emplyeeData[i-1][j]=excel.getCellData(path, "empData", i, j)+miscellaneous.generateRandomNumber();
						
			}
			
			
		}
	    System.out.println(emplyeeData[0][0]);
	    System.out.println(emplyeeData.length);
	    
	
		return emplyeeData;
		
	}
	

	
	
	
	
	

}
