package baseClass;


import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import Commons.BrowserFactory;


public class BaseClass {


 protected WebDriver driver; // Class level


 @Parameters("browser")
 @BeforeMethod(alwaysRun = true)
 public void setUp(String browser) throws IOException {
	 System.out.println("First step");
  BrowserFactory bf = new BrowserFactory();
  bf.browsersetup(browser);
  driver = BrowserFactory.getdriverinstance();
  driver.manage().window().maximize(); // Ensure window is full screen
 }


 @AfterMethod(alwaysRun = true)
 public void tearDown() {
  driver.quit();
 }
 




}