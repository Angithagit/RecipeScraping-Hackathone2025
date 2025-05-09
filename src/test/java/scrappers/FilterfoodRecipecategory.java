package scrappers;

import java.util.ArrayList;
import java.util.Arrays;

public class FilterfoodRecipecategory {
	
	public String getfoodcategory(String ingredient) {
		String foodcategory=null;
		boolean iseggiterian = false;
		boolean isjainfood = true;
		boolean isvegan = true;
		
		ArrayList<String> nonvegchecklist = new ArrayList<>(Arrays.asList("fish","chicken","meat","mutton","ham","sausage","tuna","sardines","salmon","mackerel","sardines"));
		ArrayList<String> nonveganchecklist = new ArrayList<>(Arrays.asList("milk","yogurt","curd","butter","ghee","cheese","paneer","malai","raitha","dahi"));
		ArrayList<String> nonjainchecklist = new ArrayList<>(Arrays.asList("potato","onion","garlic","mushrooms","cauli","carrot","beet","yeast","ginger","suran","shatavari","radish","vinegar"));
		
		
		boolean isnonveg=false;
		for(String nonveg:nonvegchecklist) {
			if(ingredient.contains(nonveg))
				isnonveg = true;
		}
		
		if(ingredient.contains("egg"))
			iseggiterian = true;
		
		for(String nonjainfood:nonjainchecklist) {
			if(ingredient.contains(nonjainfood))
				isjainfood = false;
		}
		
		for(String nonveganlist:nonveganchecklist) {
			if(ingredient.contains(nonveganlist))
				isvegan = false;
		}
		
		if(isnonveg == true)
			foodcategory = "Non Vegetarian";
		else if(iseggiterian == true)
			foodcategory = "Eggitarian";
		else if(isvegan == false && isjainfood == false)
			foodcategory = "Vegeterian"; 
		else if(isvegan == true && isjainfood == true)
			foodcategory = "Vegan Jain food";
		else if(isvegan == true && isjainfood == false)
			foodcategory = "Vegan";
		else if(isvegan == false && isjainfood == true)
			foodcategory = "Jain food";
		
		return foodcategory;
		
	}
	
	public String getrecipecategory(String recipename) {
		String recipecategory=null;
		boolean isbreakfast = false;
		boolean islunch = false;
		boolean isdinner = false;
		boolean issnack = false;
		boolean isdessert = false;
		
		ArrayList<String> breakfastchecklist = new ArrayList<>(Arrays.asList("Breakfast","idly","chutney","dosa","chilla","puri","semiya","upma","puttu","appe","oat","smoothie","bread","muesli","poha","pancake","jam","uttapam","pongal","toast","sandwich"));
		ArrayList<String> lunchchecklist = new ArrayList<>(Arrays.asList("Lunch","rice","roti","curry","sambar","pulav","biriyani"));
		ArrayList<String> dinnerchecklist = new ArrayList<>(Arrays.asList("Dinner","Korma","parotta","paratha","salad","rolls","pasta","dumplings","kebab","casserole"));
		ArrayList<String> snackchecklist = new ArrayList<>(Arrays.asList("Snack","chakli","panki","vada","pakora","pakoda","street food","chaat","samosa","nugget"));
		ArrayList<String> dessertchecklist = new ArrayList<>(Arrays.asList("Dessert","kheer","cake","payasam","sweet","malpua","lapsi","rasgulla","meetha"));
		
		
		for(String breakfast:breakfastchecklist) {
			if(recipename.toLowerCase().contains(breakfast.toLowerCase()))
				isbreakfast = true;
		}
		
		for(String lunch:lunchchecklist) {
			if(recipename.toLowerCase().contains(lunch.toLowerCase()))
				islunch = true;
		}
		
		for(String dinner:dinnerchecklist) {
			if(recipename.toLowerCase().contains(dinner.toLowerCase()))
				isdinner = true;
		}
		
		for(String snack:snackchecklist) {
			if(recipename.toLowerCase().contains(snack.toLowerCase()))
				issnack = true;
		}
		
		for(String dessert:dessertchecklist) {
			if(recipename.toLowerCase().contains(dessert.toLowerCase()))
				isdessert = true;
		}
		
		if(isbreakfast == true)
			recipecategory = "Breakfast";
		else if(islunch == true)
			recipecategory = "Lunch";
		else if(isdinner == true)
			recipecategory = "Dinner"; 
		else if(issnack == true)
			recipecategory = "Snack";
		else if(isdessert == true)
			recipecategory = "Dessert";
		else 
			recipecategory = "Other";
		
		return recipecategory;
		
	}

}
