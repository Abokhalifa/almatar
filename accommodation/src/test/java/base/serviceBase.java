package base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class serviceBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public static String responseBody;
	public String empID = "51838";
	public Logger logger;
	public Faker faker = new Faker();
	
	@BeforeClass
	public void setup()
	{
		
		logger = Logger.getLogger("accommodationAPIs");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
		
	}

	

}
