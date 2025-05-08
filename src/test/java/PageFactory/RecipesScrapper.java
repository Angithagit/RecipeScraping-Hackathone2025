package PageFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Commons.BrowserFactory;
import dto.RecipeUrlInfo;

public class RecipesScrapper {

	protected WebDriver driver;

	public RecipesScrapper() {
		this.driver = BrowserFactory.getdriverinstance(); // Initialize driver
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
	}

	@FindBy(xpath = "//div[contains(@class,'recipe-block')]")
	private List<WebElement> recipeBlocks;

	public Map<Integer, RecipeUrlInfo> scrapeRecipeCategories() {
		Map<Integer, RecipeUrlInfo> recipeMap = new HashMap<>();

		for (WebElement block : recipeBlocks) {
			try {
				// 1. Category name
				String recipeName = block.findElement(By.xpath(".//h5/a")).getText().trim();

				// 2. Category URL (relative -> full)
				String relativeUrl = block.findElement(By.xpath(".//h5/a")).getAttribute("href");
				String fullUrl = relativeUrl.startsWith("http") ? relativeUrl
						: "https://www.tarladalal.com" + relativeUrl;

//				// 3. Recipe count
//				String recipeText = block.findElement(By.xpath(".//p[contains(text(),'Recipes')]")).getText().trim();
//
//				int recipeCount = Integer.parseInt(recipeText.split(" ")[0]);

				// 4. Extract categoryId from URL using regex
				String digits = relativeUrl.replaceAll(".*-(\\d+)\\D*$", "$1");
				int recipeId = Integer.parseInt(digits);

				RecipeUrlInfo recipeUrlInfo = new RecipeUrlInfo(recipeId, fullUrl, recipeName);
				recipeMap.put(recipeId, recipeUrlInfo);

			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.err.println("Skipping block due to missing element: " + e.getMessage());
			} catch (NumberFormatException e) {
				System.err.println("Skipping block due to invalid category ID or recipe count: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("Unexpected error: " + e.getMessage());
			}
		}
		return recipeMap;
	};

}
