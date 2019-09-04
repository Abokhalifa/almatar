package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import base.testBase;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.PreemptiveAuthSpec;
import io.restassured.specification.RequestSpecification;

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
