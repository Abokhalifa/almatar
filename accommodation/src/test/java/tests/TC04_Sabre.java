package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.testBase;
import io.restassured.*;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import static io.restassured.RestAssured.given;
import io.restassured.config.XmlConfig;
import static org.hamcrest.Matchers.*;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.path.xml.XmlPath.CompatibilityMode;
import com.jayway.restassured.path.xml.XmlPath;

public class TC04_Sabre extends testBase {

	String tokenPath = "Envelope.Header.Security.BinarySecurityToken[-1].text()";
	String pccPath = "Envelope.Header.MessageHeader.CPAId.text()"; 
	String partyIDTypePath = "Envelope.Header.MessageHeader.From.PartyId.@type";
	String conversationIDPath = "Envelope.Header.MessageHeader.ConversationId.text()";

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
	public void authenticateSabre()
	{
		given().
		when().
		post("").
		then().
		assertThat().
		body(tokenPath,containsString("Shared/IDL:IceSess"));
	}




}

