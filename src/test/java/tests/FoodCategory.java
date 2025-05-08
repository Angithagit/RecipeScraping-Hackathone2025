package tests;

import base.Base;
import excelreader.ExcelReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import Pages.Recipepage;

import utils.DataBaseclass;
import utils.ProgressTracker;

import java.util.*;

public class FoodCategory extends Base {

	@Test
	public void scrapeBasedOnCategoryFilters() {

		List<Map<String, String>> FoodCategoryData = ExcelReader.readCategoryData();
		Set<String> uniqueCategories = new LinkedHashSet<>();

		for (Map<String, String> row : FoodCategoryData) {
			String foodCategory = row.getOrDefault("foodcategory", "").trim().replaceAll(" +", "+");
			if (!foodCategory.isEmpty()) {
				uniqueCategories.add(foodCategory);
			}
		}

		int lastSavedIndex = ProgressTracker.read("last_index.txt");
		if (lastSavedIndex >= uniqueCategories.size()) {
		    System.out.println("All categories already scraped. Resetting progress.");
		    ProgressTracker.save("last_index.txt", 0);
		    lastSavedIndex = 0;
		}

		int index = 0;
		
		for (String foodcategory : uniqueCategories) {
			if (index < lastSavedIndex) {
				index++;
				continue;
			}

			try {
				Recipepage page = new Recipepage(driver);
				page.searchIcon();
				page.searchField().sendKeys(foodcategory);
				page.clickSearchbutton();

				Set<String> recipeUrls = new LinkedHashSet<>();
				List<WebElement> linkElements = driver
						.findElements(By.xpath("//div[@class='col-sm-6 col-md-4 col-lg-3']//a[@href]"));
				for (WebElement element : linkElements) {
					recipeUrls.add(element.getAttribute("href"));
				}

				for (String recipeUrl : recipeUrls) {
					try {
						driver.get(recipeUrl);
						String recipeName = page.getRecipeName();
						List<String> ingredientList = page.getIngredients();
						String ingredients = String.join(", ", ingredientList);
						String preparationTime = page.getPreparationTime();
						String cookingTime = page.getCookingTime();
						String tags = page.getTags();

						DataBaseclass.insertRecipe(recipeName, foodcategory, ingredients, preparationTime, cookingTime,
								tags, recipeUrl);
					} catch (Exception e) {
						System.out.println("❌ Error scraping/inserting recipe: " + e.getMessage());
					}
				}

				ProgressTracker.save("last_index.txt", index);
				index++;

			} catch (Exception e) {
				System.out.println("❌ Error processing category: " + foodcategory + " | " + e.getMessage());
			}
		}
	}
}
