package Base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import Commons.BrowserFactory;
import Commons.ConfigReader;


public class baseclass {
	
	public BrowserFactory bf=new BrowserFactory();
	public static WebDriver driver;
	private String appurl;
	ConfigReader config = new ConfigReader();
	
		
	@Parameters({"browser"})
	@BeforeClass(alwaysRun = true)
	public void opendsalgo(String browser) throws IOException {
		driver =bf.browsersetup(browser);
		appurl = config.getappurl();
		driver.get(appurl);
	}
	
	@AfterClass(alwaysRun = true)
	public void teardown() {
		driver.quit();
	}
	

}
