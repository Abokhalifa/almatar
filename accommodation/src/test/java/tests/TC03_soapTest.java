package tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.baseTest;
import io.restassured.*;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import static io.restassured.RestAssured.given;
import com.jayway.restassured.config.*;
import io.restassured.config.XmlConfig;
import static org.hamcrest.Matchers.*;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.path.xml.XmlPath.CompatibilityMode;

public class TC03_soapTest extends baseTest {
	
	@BeforeClass
	public void prepareForTest() throws InterruptedException, IOException
	{
		logger.info("******Starting TC03_SOAP test*******");
		RestAssured.baseURI = "https://sws-crt.cert.havail.sabre.com";
		httpRequest = RestAssured.given();
		String xmlFilepath = System.getProperty("user.dir")+"/src/test/resources/authenticateSabreRequest.xml";
		FileInputStream xmlFileInput = new FileInputStream(new File(xmlFilepath));
		 XmlPath xmlPath = new XmlPath(CompatibilityMode.HTML,xmlFileInput);
		httpRequest.body(IOUtils.toString(xmlFileInput,"UTF-8"));
		httpRequest.header("Content-Type","text/xml");
		response = httpRequest.request(Method.POST,"");
		xmlFileInput.close();
		
		
	
	}
	
	
	
	@Test
	public void checkResponseBody() throws InterruptedException
	{
		logger.info("******Checking Response Body*******");
		String responseBody = response.getBody().asString();
		logger.info("Response body ==> " + responseBody);
		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains("Token"),true);
	
	}
	
	@Test
	public void traverseXML() throws InterruptedException
	{
		

		
		//String name = with(XML).get("shopping.category.item[0].name");
		
		httpRequest.config(RestAssuredConfig.config()
				.xmlConfig(XmlConfig.xmlConfig()
				.with()
				.namespaceAware(true)
				.declareNamespace("soap-env", "http://schemas.xmlsoap.org/soap/envelope/")
				.declareNamespace("eb", "http://www.ebxml.org/namespaces/messageHeader")
				.declareNamespace("wsse", "http://schemas.xmlsoap.org/ws/2002/12/secext")))
				.post()
				.then()
				.statusCode(200)
				.assertThat()
				.body("Envelope.Header.Security.BinarySecurityToken",contains("Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTD!ICESMSLB\\/CRT.LB!-"));
		
	
	
	}
	
	
	

}
