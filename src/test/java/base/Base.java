package base;

import java.util.Properties;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import DriverFactory.DriverFactory;
import utils.ConfigReader;

public class Base {
    public WebDriver driver;

    private DriverFactory driverFactory = new DriverFactory();
    private static Properties prop1;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        prop1 = ConfigReader.initializeprop();
        System.out.println("Before Suite: Config properties initialized.");
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional("") String browser) {
        try {
            if (browser == null || browser.trim().isEmpty()) {
                browser = prop1.getProperty("browser");
                System.out.println("Using default browser from properties: " + browser);
            }

            driverFactory.initializeBrowser(browser);
            driver = DriverFactory.getDriver();
            driver.manage().deleteAllCookies();

            String url = prop1.getProperty("URL");
            boolean loaded = false;

            for (int attempt = 1; attempt <= 2; attempt++) {
                try {
                    System.out.println("🔄 Attempting to load homepage (try " + attempt + "): " + url);
                    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
                    driver.get(url);
                    Thread.sleep(2000);
                    loaded = true;
                    break;
                } catch (Exception e) {
                    System.out.println("⚠️ Homepage load attempt failed: " + e.getMessage());
                    if (attempt == 2) {
                        driver.navigate().to("about:blank");
                        System.out.println("🛑 Redirected to about:blank after failed attempts");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error in setup(): " + e.getMessage());
            throw e;
        }
    }

    public void setup() {
        String browser = prop1.getProperty("browser");
        setup(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult result) {
    	driver.quit();
    }

    public static WebDriver getDriver() {
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver instance is null. Ensure setup() is called before tests.");
        }
        return driver;
    }
}
