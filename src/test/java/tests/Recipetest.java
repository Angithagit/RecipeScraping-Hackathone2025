package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Pages.Recipepage;
import base.Base;
import excelreader.ExcelReader;

public class Recipetest extends Base {

	@Test
	public void scrapeBasedOnCategoryFilters() {
		Set<String> eliminate = ExcelReader.getColumnValues("LFVElimination", 0);
		Set<String> add = ExcelReader.getColumnValues("LFVElimination", 1);
		Set<String> avoid = ExcelReader.getColumnValues("LFVElimination", 3);

		List<Map<String, String>> categoryData = ExcelReader.readCategoryData();

		for (Map<String, String> row : categoryData) {
			// Fetching each category separately
			String foodCategories = row.getOrDefault("foodcategory", "").trim().replaceAll(" +", "+");
//            String cuisineCategory = row.getOrDefault("cuisinecategory", "").trim().replaceAll(" +", "+");
//            String recipeCategory = row.getOrDefault("recipecategory", "").trim().replaceAll(" +", "+");
//            String subCategory = row.getOrDefault("subcategory", "").trim().replaceAll(" +", "+");

			// If you want to form different search URLs per category:
			List<String> searchQueries = List.of(foodCategories);

			for (String foodcategory : searchQueries) {
				if (foodcategory.isEmpty())
					continue;
				
		   Recipepage page = new Recipepage(driver);
		   page.searchIcon();
		   page.searchField().sendKeys(foodcategory);
		   page.clickSearchbutton();
		   List<WebElement> linkElements = driver.findElements(
				    By.xpath("//div[@class='col-sm-6 col-md-4 col-lg-3']//a[@href]"));
		   System.out.println("printing linkelements" + linkElements);

				List<String> recipeUrls = new ArrayList<>();
				for (WebElement element : linkElements) {
				    recipeUrls.add(element.getAttribute("href"));
				}

				System.out.println("Found " + recipeUrls.size() + " recipes");
     			for (String recipeUrl : recipeUrls) {
				    try {
				        driver.get(recipeUrl);
				        
						List<String> ingredients = page.getIngredients();

						boolean hasEliminate = ingredients.stream().anyMatch(eliminate::contains);
						boolean hasAvoid = ingredients.stream().anyMatch(avoid::contains);
						boolean hasAdd = ingredients.stream().anyMatch(add::contains);
//                        boolean hasBadProcessing = !row.getOrDefault("foodprocessing toavoid", "").isEmpty() &&
//                            ingredients.stream().anyMatch(ingredient -> 
//                                ingredient.contains(row.get("foodprocessing toavoid"))
//                            );

						if (hasEliminate || hasAvoid || !hasAdd) {
							System.out.println("Skipped: " + page.getRecipeName());
							continue;
						}

						System.out.println("Recipe Name" + page.getRecipeName());
						System.out.println("Category: " + page.getRecipeCategory());
						System.out.println("Cuisine: " + foodcategory);
						System.out.println("Ingredients: " + ingredients);
						System.out.println("Preparation Time: " + page.getPreparationTime());
						System.out.println("Cooking Time: " + page.getCookingTime());
						System.out.println("Tags: " + page.getTags());
						
					} catch (Exception e) {
						System.out.println("Error: " + e.getMessage());
					}
					driver.navigate().back();
				}
			}
		}
	}
}
		 
		
	
				
				
				
				
				
				
				
				
				
				
				
				
				
				
//



//				String searchUrl = "https://www.tarladalal.com/recipesearch/?query=" + query;
//				System.out.println("Searching: " + searchUrl);
//				driver.get(searchUrl);



//
//				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//				wait.until(ExpectedConditions.presenceOfElementLocated(
//				    By.xpath("//div[contains(@class,'col-sm-6')]")));
//				
//				List<WebElement> recipeLinks = driver.findElements(
//						By.xpath("//div[contains(@class,'col-sm-6')]"));
//
//				for (int i = 0; i < recipeLinks.size(); i++) {
//					try {
//						recipeLinks = driver.findElements(
//								By.xpath("//div[contains(@class,'recipe-block')]//a[contains(@href, '/recipe-')]"));
//						String recipeUrl = recipeLinks.get(i).getAttribute("href");
//						driver.get(recipeUrl);
//			


				
//				List<WebElement> linkElements = driver.findElements(
//					    By.xpath("//div[contains(@class,'recipe-block')]//a[contains(@href, '/recipe-')]"));
//
//					List<String> recipeUrls = new ArrayList<>();
//					for (WebElement element : linkElements) {
//					    recipeUrls.add(element.getAttribute("href"));
//					}
//
//					System.out.println("🔗 Found " + recipeUrls.size() + " recipes");
//
//					// Now loop over URLs (no stale element risk)
//					for (String recipeUrl : recipeUrls) {
//					    try {
//					        driver.get(recipeUrl);




//
//						Recipepage page = new Recipepage(driver);
//						List<String> ingredients = page.getIngredients();
//
//						boolean hasEliminate = ingredients.stream().anyMatch(eliminate::contains);
//						boolean hasAvoid = ingredients.stream().anyMatch(avoid::contains);
//						boolean hasAdd = ingredients.stream().anyMatch(add::contains);
////                        boolean hasBadProcessing = !row.getOrDefault("foodprocessing toavoid", "").isEmpty() &&
////                            ingredients.stream().anyMatch(ingredient -> 
////                                ingredient.contains(row.get("foodprocessing toavoid"))
////                            );
//
//						if (hasEliminate || hasAvoid || !hasAdd) {
//							System.out.println("Skipped: " + page.getRecipeName());
//							continue;
//						}
//
//						System.out.println("Recipe Name" + page.getRecipeName());
//						System.out.println("Category: " + page.getRecipeCategory());
//						System.out.println("Cuisine: " + page.getFoodCategory());
//						System.out.println("Ingredients: " + ingredients);
//
//					} catch (Exception e) {
//						System.out.println("Error: " + e.getMessage());
//					}
//					driver.navigate().back();
//				}
//			}
//		}
//	}

		
