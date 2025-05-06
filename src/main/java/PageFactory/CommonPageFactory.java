package PageFactory;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Commons.BrowserFactory;
import Commons.LoggerLoad;

public class CommonPageFactory {

	protected WebDriver driver;

	public CommonPageFactory() {

		this.driver = BrowserFactory.getdriverinstance(); // Initialize driver
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String handleAlert() {

		String alertText = "";
		try {
			// Wait for the alert to be present (optional but recommended)
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.alertIsPresent());

			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
			alert.accept();
			LoggerLoad.info("Alert accepted");

		} catch (NoAlertPresentException e) {
			// Alert was not present as expected
			LoggerLoad.error("Alert was not present.");
			// Add assertions to verify application state when no alert is present.
			// Example
			e.getMessage();// fail the test.

		} catch (Exception e) {
			// Some other error occurred
			LoggerLoad.error("An error occurred: ");
			e.getMessage(); // fail the test.

		}
		System.out.println("Alert accepted");
		return alertText;

	}

}
