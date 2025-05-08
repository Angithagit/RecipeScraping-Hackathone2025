package tests;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Pages.CategoryPage;
import Pages.HomePage;
import Pages.RecipeScrappingPage;
import base.Base;
import excelreader.ExcelReader;

public class RecipeScraperTest extends Base {

    @Test
    public void scrapeRecipes() {
        Set<String> eliminate = ExcelReader.getColumnValues("LFVELIMINATION", 0);
        Set<String> add = ExcelReader.getColumnValues("LFVELIMINATION", 1);
        Set<String> avoid = ExcelReader.getColumnValues("LFVELIMINATION", 3);

        HomePage home = new HomePage(driver);
        home.openAllCategories();

        CategoryPage category = new CategoryPage(driver);
        category.selectCategory("Low Calorie"); // Can loop through your Excel categories

        List<WebElement> recipes = category.getRecipeLinks();
        for (WebElement recipe : recipes) {
            String url = recipe.getAttribute("href");
            driver.get(url);

            RecipeScrappingPage page = new RecipeScrappingPage(driver);
            List<String> ingredients = page.getIngredients();

            boolean containsEliminate = ingredients.stream().anyMatch(ing -> eliminate.contains(ing));
            boolean containsAvoid = ingredients.stream().anyMatch(ing -> avoid.contains(ing));
            boolean containsAdd = ingredients.stream().anyMatch(ing -> add.contains(ing));

            if (!containsEliminate && !containsAvoid && containsAdd) {
                System.out.println("✅ Good Recipe: " + page.getRecipeName());
                // You can save this to DB or export it
            }
        }
    }
}


