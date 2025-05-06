package Base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import Commons.BrowserFactory;
import Commons.ConfigReader;

public class BaseClass {

	public BrowserFactory bf = new BrowserFactory();
	public static WebDriver driver;
	private String appurl;
	ConfigReader config = new ConfigReader();

	public void opendsalgo(String browser) throws IOException {
		driver = bf.browsersetup(browser);
		appurl = config.getappurl();
		driver.get(appurl);
	}

	public void teardown() {
		driver.quit();
	}

}
