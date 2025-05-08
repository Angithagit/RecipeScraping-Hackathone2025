
package DriverFactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public WebDriver initializeBrowser(String browser) {
		if (browser == null || browser.isEmpty()) {
			throw new IllegalArgumentException("Browser name must not be null or empty.");
		}

		try {
			switch (browser.toLowerCase()) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("headless");
				
				tlDriver.set(new ChromeDriver());
				break;
//			case "chrome":
//				ChromeOptions chromeOptions = new ChromeOptions();
//				chromeOptions.addArguments("--headless=new");
//				chromeOptions.addArguments("--disable-gpu");
//				chromeOptions.addArguments("--disable-extensions");
//				chromeOptions.addArguments("--disable-popup-blocking");
//				chromeOptions.addArguments("--blink-settings=imagesEnabled=false"); // Optional: don't load images
//				chromeOptions.addArguments("--no-sandbox");
//				chromeOptions.addArguments("--remote-allow-origins=*");
//				tlDriver.set(new ChromeDriver(chromeOptions));
//				break;
			case "firefox":
				// FirefoxOptions firefoxOptions = new FirefoxOptions();
				// firefoxOptions.addArguments("headless");
				tlDriver.set(new FirefoxDriver());
				break;
			case "edge":
				// EdgeOptions edgeOptions = new EdgeOptions();
				// edgeOptions.addArguments("headless");
				tlDriver.set(new EdgeDriver());
				break;
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
			}
		} catch (Exception e) {
			System.err.println("Error initializing browser: " + e.getMessage());
			throw e;
		}

		WebDriver driver = getDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

		return driver;
	}

	public static WebDriver getDriver() {
		WebDriver driver = tlDriver.get();
		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized. Did you call initializeBrowser?");
		}
		return driver;
	}

	public static void removeDriver() {
		WebDriver driver = tlDriver.get();
		if (driver != null) {
			driver.quit();
			tlDriver.remove();
		}

		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /T");
		} catch (Exception ignored) {
		}
	}

}