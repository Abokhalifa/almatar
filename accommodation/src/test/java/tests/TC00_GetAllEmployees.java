package tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.testBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC00_GetAllEmployees extends testBase {
	
	@BeforeClass
	public void getAllEmployees() throws InterruptedException
	{
		logger.info("******Starting TC00_getAllEmployees*******");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(3);
	
	}
	
	
	@Test
	public void checkResponseBody() throws InterruptedException
	{
		logger.info("******Checking Response Body*******");
		String responseBody = response.getBody().asString();
		logger.info("Response body ==> " + responseBody);
		System.out.println(responseBody);
		Assert.assertTrue(responseBody!=null);
	
	}
	
	
	
	

	

}
