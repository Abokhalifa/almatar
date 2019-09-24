package services;

import static org.hamcrest.Matchers.containsString;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.path.xml.XmlPath.CompatibilityMode;

import base.serviceBase;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.XmlConfig;
import io.restassured.http.Method;

public class sabreAuthenticate extends serviceBase{
	
	//Locators

	
	@BeforeClass
	public void prepareForTest() throws InterruptedException, IOException
	{
		logger.info("******Starting TC03_SOAP test*******");
		RestAssured.baseURI = "https://sws-crt.cert.havail.sabre.com";
		httpRequest = RestAssured.given();
		String xmlFilepath = System.getProperty("user.dir")+"/src/test/resources/authenticateSabreRequest.xml";
		FileInputStream xmlFileInput = new FileInputStream(new File(xmlFilepath));
		//XmlPath xmlPath = new XmlPath(CompatibilityMode.HTML,xmlFileInput);
		httpRequest.body(IOUtils.toString(xmlFileInput,"UTF-8"));
		httpRequest.header("Content-Type","text/xml");
		response = httpRequest.request(Method.POST,"");
		xmlFileInput.close();
		
		httpRequest.config(RestAssuredConfig.config()
				.xmlConfig(XmlConfig.xmlConfig()
						.with()
						.namespaceAware(false)
						.declareNamespace("soap-env", "http://schemas.xmlsoap.org/soap/envelope/")
						.declareNamespace("eb", "http://www.ebxml.org/namespaces/messageHeader")
						.declareNamespace("wsse", "http://schemas.xmlsoap.org/ws/2002/12/secext")))
		.post();
		
		responseBody = response.getBody().asString();

    }
	

	
	@Test(enabled =true)
	public void getTokenValue() throws InterruptedException
	{		
		XmlPath tokenXMLPath= new XmlPath(responseBody);//Converting string into xml path to assert
		String token=tokenXMLPath.getString("Envelope.Header.Security.BinarySecurityToken.text()");
		System.out.println(token);
		
		
		
		
	}
	

}
