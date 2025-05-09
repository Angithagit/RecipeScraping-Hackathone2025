package Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Commons.BrowserFactory;
import Commons.ConfigReader;
import Commons.LoggerLoad;
import PageFactory.RecipesScrapper;
import PageFactory.ScrappedDatas_Test;
import baseClass.BaseClass;
import dto.RecipeUrlInfo;
import scrappers.FilterAllergytables;
import scrappers.FilterLowCarbRecipe;
import scrappers.FilterLowFatRecipe;
import scrappers.FilterfoodRecipecategory;
import scrappers.ScrappedDatas;
import utils.DataBaseclass;


public class RecipeScrapper extends BaseClass{
	
	@BeforeClass
	public void clearTablesBeforeTest() {
		DataBaseclass.truncateAllTables();
	}

	@Test(priority = 1)
	public static void recipeDataInfo() throws IOException, InterruptedException {

		
		WebDriver driver = BrowserFactory.getdriverinstance();
		ScrappedDatas SD = new ScrappedDatas(driver);
		FilterLowFatRecipe lfdiet = new FilterLowFatRecipe();
		FilterLowCarbRecipe lcdiet = new FilterLowCarbRecipe();
		FilterAllergytables allergy = new FilterAllergytables();
		ScrappedDatas_Test scdata = new ScrappedDatas_Test(driver);

		driver.get(ConfigReader.indianRecipeUrl());

		RecipesScrapper recipesScrapper = new RecipesScrapper();
		Map<Integer, RecipeUrlInfo> recipeMap = recipesScrapper.scrapeRecipeCategories();
		for (Map.Entry<Integer, RecipeUrlInfo> entry : recipeMap.entrySet()) {
			RecipeUrlInfo url_Info = entry.getValue();
			String value = url_Info.getUrl();
			String recipiemethod = SD.recipie_method(value, driver);
			String recipiedescription = SD.recipie_description(value, driver);
			String recipie_nutrient_values = SD.recipie_nutrient_values(value, driver);
			List<String> liststringingredients = scdata.getIngredients();
			String ingredients = String.join(", ", liststringingredients).toLowerCase();
			System.out.println(ingredients);

			String LFtablename = lfdiet.recipecheckLowFatdiet(ingredients, recipiemethod);
			System.out.println(LFtablename);
			LoggerLoad.info("TABLE NAME: " + LFtablename);

			String LCtablename = lcdiet.recipecheckLowCarbdiet(ingredients, recipiemethod);
			System.out.println(LCtablename);
			LoggerLoad.info("TABLE NAME: " + LCtablename);

			String recipename = url_Info.getRecipeName();
			String prepTime = scdata.getPreparationTime();
			String cookTime = scdata.getCookingTime();
			String noOfServings = scdata.getNoOfServings();
			String tags = scdata.getTags();
			String url = value;

			FilterfoodRecipecategory filtercategory = new FilterfoodRecipecategory();

			String foodcategory = filtercategory.getfoodcategory(ingredients);

			System.out.println(foodcategory);

			String recipecategory = filtercategory.getrecipecategory(recipename);

			System.out.println(recipecategory);

			if (LFtablename.equals("LFV Added")) {
				DataBaseclass.insertIntoTable("Low_fat_diet_add", recipename, foodcategory, recipecategory, ingredients, prepTime, cookTime,
						noOfServings, recipiedescription, recipiemethod, recipie_nutrient_values, tags, url);
			} else if (LFtablename.equals("LFV eliminated")) {
				DataBaseclass.insertIntoTable("Low_fat_diet_elimination", recipename, foodcategory, recipecategory, ingredients, prepTime, cookTime,
						noOfServings, recipiedescription, recipiemethod, recipie_nutrient_values, tags, url);
			} else if (LFtablename.equals("LF Nonvegan Added")) {
				DataBaseclass.insertIntoTable("Low_fat_diet_Non_Vegan", recipename, foodcategory, recipecategory, ingredients, prepTime, cookTime,
						noOfServings, recipiedescription, recipiemethod, recipie_nutrient_values, tags, url);
			}

			if (LCtablename.equals("LCHF Added")) {
				DataBaseclass.insertIntoTable("Low_Carb_High_fat_add", recipename, foodcategory, recipecategory, ingredients, prepTime, cookTime,
						noOfServings, recipiedescription, recipiemethod, recipie_nutrient_values, tags, url);
			} else {
				DataBaseclass.insertIntoTable("Low_Carb_High_fat_elimination", recipename, foodcategory, recipecategory, ingredients, prepTime,
						cookTime, noOfServings, recipiedescription, recipiemethod, recipie_nutrient_values, tags, url);
			}

			ArrayList<String> Allergytables = allergy.allergylist(ingredients);
			if (Allergytables.size()>0) {
				for (String allergytable : Allergytables) {
					LoggerLoad.info("TABLE NAME: " + allergytable);
					System.out.println(allergytable);
					String dbTable = allergytable.substring(0, 1).toUpperCase()
							+ allergytable.substring(1).toLowerCase().replace(" ", "_") + "_Allergy";
					DataBaseclass.insertIntoTable(dbTable, recipename,foodcategory, recipecategory, ingredients, prepTime, cookTime, noOfServings,
							recipiedescription, recipiemethod, recipie_nutrient_values, tags, url);
				}
			}
		}
	}
}




