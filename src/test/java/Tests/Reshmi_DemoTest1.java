package Tests;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import Commons.LoggerLoad;
import Scrapper.FilterAllergytables;
import Scrapper.FilterLowCarbRecipe;
import Scrapper.FilterLowFatRecipe;

public class Reshmi_DemoTest1 {

	@Test
 public void recipecheck() throws IOException {
		FilterLowFatRecipe lfdiet = new FilterLowFatRecipe();
		FilterLowCarbRecipe lcdiet = new FilterLowCarbRecipe();
		FilterAllergytables allergy = new FilterAllergytables();
		
	
		String ingredients = "1 cup quick cooking rolled oats, 1 tbsp finely chopped walnuts (akhrot), 1 tbsp finely chopped almonds (badam), 2 tbsp sesame seeds (til),  tsp ghee, 2 tbsp chopped jaggery (gur) 1/2 tsp cardamom (elaichi) powder 2 tbsp low fat milk , 99.7% fat-free";
		String method = "To make oats and mixed nuts ladoo, heat a broad non-stick pan, add the oats and dry roast on a medium flame for 3 minutes. Remove and keep aside to cool completely. Heat the same broad non-stick pan, add the sesame seeds and dry roast them on a medium flame for 2 minutes. Keep aside to cool completely. Heat the ghee and jaggery in the same broad non-stick pan, mix well and cook on a slow flame for 1 minute, while stirring continuously. Transfer the jaggery mixture into a flat thali and allow it to cool slightly.Add the roasted oats, roasted sesame seeds, alnuts, almonds and cardamom powder and mix very well.Add the milk and mix very well.Divide the mixture into 8 equal portions and roll out each portion into a round ball.Serve the oats and mixed nuts ladoo immediately.";
		
		
		
		LoggerLoad.info("INGREDIENTS: "+ingredients);
		LoggerLoad.info("COOKING METHOD: "+method);
	
	 
	 String LFtablename = lfdiet.recipecheckLowFatdiet(ingredients, method);
	 LoggerLoad.info("TABLE NAME: "+LFtablename);
	//Make a call to the appropriate class.method() to connect to postgresql and insert into above said Low fat table
	 
	 String LCtablename = lcdiet.recipecheckLowCarbdiet(ingredients, method);
	 LoggerLoad.info("TABLE NAME: "+LCtablename);
	//Make a call to the appropriate class.method() to connect to postgresql and insert into above said Low carbs table
	 
	 ArrayList<String> Allergytables= allergy.allergylist(ingredients);
	 if(Allergytables.size()>0) {
		 for(String allergytable:Allergytables) {
			 LoggerLoad.info("TABLE NAME: "+allergytable);
			 //Make a call to the appropriate class.method() to connect to postgresql and insert into above said allergy table
		 }
	 }
 
 }
}
