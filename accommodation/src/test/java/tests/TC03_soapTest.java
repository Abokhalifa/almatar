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


public class TC03_soapTest extends testBase {
	
	
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
		System.out.println(response + " @Before");
		xmlFileInput.close();
		



	}

	@Test(enabled =true)
	public void traverseXML() throws InterruptedException
	{
		String responseBody = response.getBody().asString();


		//String name = with(XML).get("shopping.category.item[0].name");

		httpRequest.config(RestAssuredConfig.config()
				.xmlConfig(XmlConfig.xmlConfig()
						.with()
						.namespaceAware(false)
						.declareNamespace("soap-env", "http://schemas.xmlsoap.org/soap/envelope/")
						.declareNamespace("eb", "http://www.ebxml.org/namespaces/messageHeader")
						.declareNamespace("wsse", "http://schemas.xmlsoap.org/ws/2002/12/secext")))
		.post()
		.then()
		.statusCode(200)
		.assertThat()
		.body(tokenPath,containsString("Shared/IDL:IceSess"))
		.log();
		
		XmlPath xmlPath= new XmlPath(responseBody);//Converting string into xml path to assert
		String token=xmlPath.getString(tokenPath);
		String pcc = xmlPath.getString(pccPath);
		String partyIDType = xmlPath.getString(partyIDTypePath);
		String conversationID = xmlPath.getString(conversationIDPath);
		System.out.println(token);
		System.out.println(pcc);
		System.out.println(partyIDType);
		System.out.println(conversationID);
	}
	
//	
//	public <T> T valueOf (final String name, final String path) {
//		if (!StringUtils.isEmpty (name)) {
//			final String root = "Envelope.Body.%s";
//			testBase.response.then ()
//				.root (String.format (root, name));
//		}
//		final XmlPath xmlPath = XmlPath.with (this.response.asString ());
//		if (StringUtils.isBlank (path)) {
//			return null;
//		}
//		try {
//			return xmlPath.get (path);
//		}
//		catch (final Exception e) {
//			final String message = "Soap Response value parsing failed for [%s] expression.";
//			fail (SoapResponseParsingFailedError.class, format (message, path), e);
//		}
//		return null;
//	}
	 
	
	




}
