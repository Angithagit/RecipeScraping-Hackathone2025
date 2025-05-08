package tests;

import org.testng.annotations.Test;

import Pages.AllCategoryWisePage;
import Pages.CategoryPage;
import base.Base;

public class AllCategorywiseTest extends Base{

@Test	
public void scrapingcategories() {
	AllCategoryWisePage allcategorywisepage = new AllCategoryWisePage(driver);
	allcategorywisepage.clickCategoriesDropDown();
	allcategorywisepage.clickHealthyIndianRecipes();
//	   CategoryPage category = new CategoryPage(driver);
//       category.selectCategory("Low Calorie"); // Can loop over multiple if needed
//
//       List<WebElement> recipes = category.getRecipeLinks();
//       for (WebElement recipe : recipes) {
//           String url = recipe.getAttribute("href");
//           driver.get(url);
//
//           RecipePage page = new RecipePage(driver);
//           List<String> ingredients = page.getIngredients();
//           String name = page.getRecipeName();
//           String ingredientsText = String.join(", ", ingredients);
//
//           boolean containsEliminate = ingredients.stream().anyMatch(ing -> eliminate.contains(ing));
//           boolean containsAvoid = ingredients.stream().anyMatch(ing -> avoid.contains(ing));
//           boolean containsAdd = ingredients.stream().anyMatch(ing -> add.contains(ing));
//
//           if (containsEliminate || containsAvoid) {
//               System.out.println("❌ Eliminated Recipe: " + name);
//               DBUtil.insertEliminated(name, url, ingredientsText);
//           } else if (containsAdd) {
//               System.out.println("✅ Add Recipe: " + name);
//               DBUtil.insertAdd(name, url, ingredientsText);
//           } else {
//               System.out.println("⚠️ Skipped: " + name);
//           }
//       }
//   }
}

}
