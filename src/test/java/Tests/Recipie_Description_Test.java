package Tests;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import PageFactory.Recipie_Description_Page;

public class Recipie_Description_Test extends Recipie_Description_Page{
	@BeforeMethod
	public void initPageObjects() throws InterruptedException, IOException {
		
		  driver.manage().window().maximize();
	      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	

		  @Test(priority = 1)
			public void selectcategory() throws InterruptedException, IOException {
			  String url = "https://www.tarladalal.com/stir-fried-mixed-vegetables-in-butter-1788r";
			recipie_description(url);
			  recipie_method(url);
			  recipie_nutrient_values(url);
		  }


				        
						 public String recipie_method(String url) throws InterruptedException, IOException {
							 String method = null;
					   driver.navigate().to(url);
					   JavascriptExecutor js = (JavascriptExecutor) driver;
					   js.executeScript("document.body.style.zoom='25%'");
					   js.executeScript("window.scrollBy(0, 500)");
					   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
					   try {
					   WebElement element = wait.until(ExpectedConditions.elementToBeClickable(recipie_method_link));
					   element.click();
					   method= recipie_method_details.getText();
	                    // Check if the description is not empty or null
	                    if (method != null && !method.isEmpty()) {
	                        System.out.println("Method for element: " + method);
	                    } else {
	                        System.out.println("Method :NULL");
	                    }
	                    return method;
					   } catch (Exception e1) {
				            // If the first element is not clickable, try the second one
				            try {
				                WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(recipie_method_link2));
				                element2.click();  // Click the second element
				                //System.out.println("Clicked on recipie_method_link2");
				            } catch (Exception e2) {
				                System.out.println("Method1 : NULL");
				            }
				      
				            method= recipie_method_details.getText();
		                    // Check if the description is not empty or null
		                    if (method != null && !method.isEmpty()) {
		                        System.out.println("Method for element: " + method);
		                    } else {
		                        System.out.println("Method :NULL");
		                    }
		                    return method;
				        }
				 }
						 public String recipie_description(String url) throws InterruptedException, IOException {
					        	String description = null;
					        	driver.navigate().to(url);
					       	    JavascriptExecutor js = (JavascriptExecutor) driver;
					        	js.executeScript("document.body.style.zoom='25%'");
						        js.executeScript("window.scrollBy(0, 500)");
						        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						        try {
						        	
						        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Description_link));
						        element.click();
						        description= Description_Details.getText();
						        if (description != null && !description.isEmpty()) {
			                        System.out.println("Description for element: " + description);
			                    } else {
			                        System.out.println("Description :NULL");
			                    }
			                    return description;
						        
						        } catch (Exception e1) {
						            // If the first element is not clickable, try the second one
						            try {
						                WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(Description_link2));
						                element2.click();  // Click the second element
						                //System.out.println("Clicked on Description_link2");
						            } catch (Exception e2) {
						                System.out.println("Description1 : NULL");
						            }
						      
						       // WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(Description_link2));

						       // element2.click();
						        //Description_link.click();
			                     description= Description_Details.getText();
			                    // Check if the description is not empty or null
			                    if (description != null && !description.isEmpty()) {
			                        System.out.println("Description for element: " + description);
			                    } else {
			                        System.out.println("Description :NULL");
			                    }
			                    return description;
						        }
						 }
						 public String recipie_nutrient_values(String url) throws InterruptedException, IOException {
					        	String nutri_value = null;

					        	driver.navigate().to(url);
					        	JavascriptExecutor js = (JavascriptExecutor) driver;
					        	 js.executeScript("document.body.style.zoom='25%'");
						        js.executeScript("window.scrollBy(0, 500)");
						        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						        try
						        {
						        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(nutrient_value_link));
						        element.click();
						        nutri_value= nutrient_value_details.getText();
			                    // Check if the description is not empty or null
			                    if (nutri_value != null && !nutri_value.isEmpty()) {
			                        System.out.println("Nutrient Value for element: " + nutri_value);
			                    } else {
			                        System.out.println("Nutrient Value :NULL");
			                    }
			                    return nutri_value;
						        // nutrient_value_link.click();
						        } catch (Exception e1) {
						        	// If the first element is not clickable, try the second one
						            try {
						                WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(nutrient_value_link2));
						                element2.click();  // Click the second element
						                
						            } catch (Exception e2) {
						                System.out.println("Nutrient Value1 : NULL");
						            }
				                   nutri_value= nutrient_value_details.getText();
				                    // Check if the description is not empty or null
				                    if (nutri_value != null && !nutri_value.isEmpty()) {
				                        System.out.println("Nutrient Value for element: " + nutri_value);
				                    } else {
				                        System.out.println("Nutrient Value :NULL");
				                    }
				                    return nutri_value;
						        }
						 }
						 }
						       