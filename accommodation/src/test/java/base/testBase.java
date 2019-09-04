package base;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class testBase {



		public static RequestSpecification httpRequest;
		public static Response response;
		public String empID = "51838";
		public Logger logger;
		public Faker faker = new Faker();
		
		public testBase()
		{
			
//		    try {
//		    	String pathToConfigFile = System.getProperty("user.dir")+"config.properties";
//	            Properties props = new Properties();
//	            props.load(getClass().getClassLoader().getResourceAsStream(pathToConfigFile));
//
//	            //Rest Assured config
//	            RestAssured.baseURI = props.getProperty("api.uri");
//	            RestAssured.port = Integer.valueOf(props.getProperty("api.port"));
//	        } catch (IOException ex) {
//	            ex.printStackTrace();
//	        }
			
		}
		
		
		
		
		@BeforeClass
		public void setup()
		{
			
			logger = Logger.getLogger("accommodationAPIs");
			PropertyConfigurator.configure("log4j.properties");
			logger.setLevel(Level.DEBUG);
			
		}

	}


