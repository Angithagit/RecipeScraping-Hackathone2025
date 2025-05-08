package Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Commons.BrowserFactory;
import Commons.ConfigReader;
import Commons.CsvWriterUtil;
import Commons.LoggerLoad;
import PageFactory.RecipesScrapper;
import PageFactory.ScrappedDatas_Test;
import dto.RecipeUrlInfo;
import scrappers.FilterAllergytables;
import scrappers.FilterLowCarbRecipe;
import scrappers.FilterLowFatRecipe;
import scrappers.ScrappedDatas;


public class RecipeScrapper {

@Test(priority = 1)

	public static void recipeDataInfo(Integer categoryId, String url) throws IOException, InterruptedException {

		BrowserFactory bf = new BrowserFactory();
		bf.browsersetup("chrome");
		WebDriver driver = BrowserFactory.getdriverinstance();
		ScrappedDatas SD=new ScrappedDatas();
		FilterLowFatRecipe lfdiet = new FilterLowFatRecipe();
		FilterLowCarbRecipe lcdiet = new FilterLowCarbRecipe();
		FilterAllergytables allergy = new FilterAllergytables();
		ScrappedDatas_Test scdata = new ScrappedDatas_Test();

		driver.get(ConfigReader.indianRecipeUrl());

		RecipesScrapper recipesScrapper = new RecipesScrapper();

		Map<Integer, RecipeUrlInfo> recipeMap = recipesScrapper.scrapeRecipeCategories();
		for (Map.Entry<Integer, RecipeUrlInfo> entry : recipeMap.entrySet()) 
		{
			RecipeUrlInfo url_Info=entry.getValue();
			String value =url_Info.getUrl();
			String recipiemethod=SD.recipie_method(value, driver);
			String recipiedescription=SD.recipie_description(value, driver);
			String recipie_nutrient_values=SD.recipie_nutrient_values(value, driver);
			List<String> liststringingredients = scdata.getIngredients();
			String ingredients = String.join(", ", liststringingredients).toLowerCase();

	
			 String LFtablename = lfdiet.recipecheckLowFatdiet(ingredients, recipiemethod);
			 LoggerLoad.info("TABLE NAME: "+LFtablename);
			//Make a call to the appropriate class.method() to connect to postgresql and insert into above said Low fat table
			 
			 String LCtablename = lcdiet.recipecheckLowCarbdiet(ingredients, recipiemethod);
			 LoggerLoad.info("TABLE NAME: "+LCtablename);
			//Make a call to the appropriate class.method() to connect to postgresql and insert into above said Low carbs table
			 
			 ArrayList<String> Allergytables= allergy.allergylist(ingredients);
			 if(Allergytables.size()>0) {
				 for(String allergytable:Allergytables) {
					 LoggerLoad.info("TABLE NAME: "+allergytable);
					 //Make a call to the appropriate class.method() to connect to postgresql and insert into above said allergy table
				 										}
			//CsvWriterUtil.writeRow(categoryId, info.getUrl(), recipeId, info.getRecipeName(), info.getUrl());
		}
		
	}
}
}