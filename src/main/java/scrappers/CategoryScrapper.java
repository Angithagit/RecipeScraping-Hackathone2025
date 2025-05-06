package scrappers;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import Commons.BrowserFactory;
import PageFactory.RecipeCategories;
import dto.CategoryInfo;

public class CategoryScrapper {
	public static void main(String[] args) throws IOException {
		BrowserFactory bf = new BrowserFactory();
		bf.browsersetup("chrome");

		WebDriver driver = BrowserFactory.getdriverinstance();

		driver.get("https://www.tarladalal.com/recipe-category/");

		RecipeCategories recipeCategories = new RecipeCategories();

		Map<Integer, CategoryInfo> categoryMap = recipeCategories.scrapeRecipeCategories();
		for (Map.Entry<Integer, CategoryInfo> entry : categoryMap.entrySet()) {
			Integer categoryId = entry.getKey();
			CategoryInfo info = entry.getValue();

			System.out.println("Category ID   : " + categoryId);
			System.out.println("Category URL  : " + info.getUrl());
			System.out.println("Recipe Count  : " + info.getRecipeCount());
			System.out.println("-------------------------------------------");
		}
		driver.quit();
	}
}
