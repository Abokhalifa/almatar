package base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class testBase {



		protected static RequestSpecification httpRequest;
		protected static Response response;
		protected String empID = "51838";
		protected Logger logger;
		protected Faker faker = new Faker();
		protected int excelRowCount =0;
		protected int excelColCount = 0;
		protected String [][]inputBody = new String [excelRowCount][excelColCount];
		
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


