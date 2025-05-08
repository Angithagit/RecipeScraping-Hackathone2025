package tests;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Commons.BrowserFactory;
import Commons.ConfigReader;
import Commons.CsvWriterUtil;
import PageFactory.RecipesScrapper;
import dto.RecipeUrlInfo;
import scrappers.ScrappedDatas;


public class RecipeScrapper {

@Test(priority = 1)

	public static void recipeDataInfo(Integer categoryId, String url) throws IOException, InterruptedException {

		BrowserFactory bf = new BrowserFactory();
		bf.browsersetup("chrome");
		WebDriver driver = BrowserFactory.getdriverinstance();
ScrappedDatas SD=new ScrappedDatas();

		driver.get(ConfigReader.indianRecipeUrl());

		RecipesScrapper recipesScrapper = new RecipesScrapper();

		Map<Integer, RecipeUrlInfo> recipeMap = recipesScrapper.scrapeRecipeCategories();
		for (Map.Entry<Integer, RecipeUrlInfo> entry : recipeMap.entrySet()) {
			RecipeUrlInfo url_Info=entry.getValue();
			String value =url_Info.getUrl();
String recipiemethod=SD.recipie_method(value, driver);
String recipiedescription=SD.recipie_description(value, driver);
String recipie_nutrient_values=SD.recipie_nutrient_values(value, driver);

	
			//CsvWriterUtil.writeRow(categoryId, info.getUrl(), recipeId, info.getRecipeName(), info.getUrl());
		}
		
	}
}
