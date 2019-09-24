package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import base.testBase;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;

public class TC02_authentication extends testBase {
	
	@BeforeClass
	public void prepareForTest() throws InterruptedException
	{
		logger.info("******Starting TC02_authentication*******");
		RestAssured.baseURI = "http://restapi.demoqa.com";
		httpRequest = RestAssured.given();
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");	
		RestAssured.authentication= authScheme;	
		httpRequest.header("Content-Type","application/json");
		response = httpRequest.request(Method.GET,"/authentication/CheckForAuthentication");
		
		Thread.sleep(3);
	
	}
	
	
	@Test
	void authenticate()
	{
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: "+responseBody);
	    int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
	
	}
	
	
	
	
	

}
