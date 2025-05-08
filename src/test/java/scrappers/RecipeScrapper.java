package scrappers;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import Commons.BrowserFactory;
import Commons.ConfigReader;
import Commons.CsvWriterUtil;
import PageFactory.RecipesScrapper;
import dto.RecipeUrlInfo;
import tests.SampleGetAllDetails;

public class RecipeScrapper {

	public static void main(String[] args) throws IOException {
		BrowserFactory bf = new BrowserFactory();
		bf.browsersetup("chrome");

		WebDriver driver = BrowserFactory.getdriverinstance();

		driver.get(ConfigReader.indianRecipeUrl());

		RecipesScrapper recipesScrapper = new RecipesScrapper();

		Map<Integer, RecipeUrlInfo> recipeMap = recipesScrapper.scrapeRecipeCategories();
		for (Map.Entry<Integer, RecipeUrlInfo> entry : recipeMap.entrySet()) {
			Integer recipeId = entry.getKey();
			RecipeUrlInfo info = entry.getValue();

//			System.out.println("Recipe ID   : " + recipeId);
//			System.out.println("Recipe Name  : " + info.getRecipeName());
			System.out.println("Recipe URL  : " + info.getUrl());
			System.out.println("-------------------------------------------");
		}
		
//		driver.quit();
	}

	public static void recipeDataInfo(Integer categoryId, String url) throws IOException {

		BrowserFactory bf = new BrowserFactory();
		bf.browsersetup("chrome");
		WebDriver driver = BrowserFactory.getdriverinstance();

//		driver.get("https://www.tarladalal.com/recipes/category/Vitamin-B12-Cobalamin-Rich-Foods/");
		driver.get(ConfigReader.indianRecipeUrl());

		RecipesScrapper recipesScrapper = new RecipesScrapper();

		Map<Integer, RecipeUrlInfo> recipeMap = recipesScrapper.scrapeRecipeCategories();
		for (Map.Entry<Integer, RecipeUrlInfo> entry : recipeMap.entrySet()) {
			Integer recipeId = entry.getKey();
			RecipeUrlInfo info = entry.getValue();
//			System.out.println("Recipe ID   : " + recipeId);
//			System.out.println("Recipe Name  : " + info.getRecipeName());
			System.out.println("Recipe URL  : " + info.getUrl());
//			System.out.println("-------------------------------------------");
			// CsvWriterUtil.writeRow(categoryId, info.getUrl(), recipeId, info.getRecipeName(), info.getUrl());
		}
		SampleGetAllDetails.scrapeRecipeData(driver, recipeMap);
	}
}
