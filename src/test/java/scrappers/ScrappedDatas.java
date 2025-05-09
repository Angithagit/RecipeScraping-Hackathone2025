package scrappers;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import PageFactory.ScrappedDatas_Test;

public class ScrappedDatas extends ScrappedDatas_Test {
	
	public ScrappedDatas(WebDriver driver) {
		super(driver);
		
	}


	public String recipie_method(String url, WebDriver driver) throws InterruptedException, IOException {
		String method = null;
		driver.navigate().to(url);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='25%'");
		js.executeScript("window.scrollBy(0, 500)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(recipie_method_link));
			element.click();
			method = recipie_method_details.getText();
			// Check if the description is not empty or null
			if (method != null && !method.isEmpty()) {
				System.out.println("Method for element: " + method);
			} else {
				System.out.println("Method :NULL");
			}
			return method;
		} catch (Exception e1) {
			
			try {
				WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(recipie_method_link2));
				element2.click(); // Click the second element
			
			} catch (Exception e2) {
				System.out.println("Method1 : NULL");
			}

			method = recipie_method_details.getText();
		
			if (method != null && !method.isEmpty()) {
				System.out.println("Method for element: " + method);
			} else {
				System.out.println("Method :NULL");
			}
			return method;
		}
	}

	public String recipie_description(String url, WebDriver driver) throws InterruptedException, IOException {
		String description = null;
		driver.navigate().to(url);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='25%'");
		js.executeScript("window.scrollBy(0, 500)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Description_link));
			element.click();
			description = Description_Details.getText();
			if (description != null && !description.isEmpty()) {
				System.out.println("Description for element: " + description);
			} else {
				System.out.println("Description :NULL");
			}
			return description;

		} catch (Exception e1) {
			
			try {
				WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(Description_link2));
				element2.click();
				
			} catch (Exception e2) {
				System.out.println("Description1 : NULL");
			}

			
			description = Description_Details.getText();
			
			if (description != null && !description.isEmpty()) {
				System.out.println("Description for element: " + description);
			} else {
				System.out.println("Description :NULL");
			}
			return description;
		}
	}

	public String recipie_nutrient_values(String url, WebDriver driver) throws InterruptedException {
	    String nutri_value = null;

	    driver.navigate().to(url);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("document.body.style.zoom='25%'");
	    js.executeScript("window.scrollBy(0, 500)");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(nutrient_value_link));
	        element.click();
	    } catch (Exception e1) {
	        try {
	            WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(nutrient_value_link2));
	            element2.click();
	        } catch (Exception e2) {
	            System.out.println("❌ Both nutrient links not found or clickable.");
	            return "NULL";
	        }
	    }

	 
	    try {
	        WebElement nutrientElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rcpnuts")));
	        nutri_value = nutrientElement.getText();
	        System.out.println("✅ Nutrient Value: " + nutri_value);
	    } catch (Exception e) {
	        System.out.println("⚠️ Nutrient Value Element Not Found.");
	        return "NULL";
	    }

	    return nutri_value;
	}


}
