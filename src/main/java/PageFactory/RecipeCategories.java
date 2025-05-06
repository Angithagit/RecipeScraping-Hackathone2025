package PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dto.CategoryInfo;

public class RecipeCategories extends CommonPageFactory {

	@FindBy(xpath = "//div[contains(@class,'recipe-block')]")
	private List<WebElement> recipeBlocks;

	@FindBy(xpath = "//div[@class='col-sm-6 col-md-4 col-lg-3']/div//a") // Change based on actual HTML
	List<WebElement> categoryLinks;

	@FindBy(xpath = "//div[@class='col-sm-6 col-md-4 col-lg-3']/div//a[text()='Vitamin B12 Cobalamin Rich Recipes']")
	List<WebElement> recipeCards;

	public void getCategoryData() {

		List<WebElement> categories = recipeBlocks();

	}

	public List<WebElement> recipeBlocks() {

		return recipeBlocks;
	}

	public Map<Integer, CategoryInfo> scrapeRecipeCategories() {
		Map<Integer, CategoryInfo> categoryMap = new HashMap<>();

		for (WebElement block : recipeBlocks) {
			try {
				// 1. Category name
				String categoryName = block.findElement(By.xpath(".//h5/a")).getText().trim();

				// 2. Category URL (relative -> full)
				String relativeUrl = block.findElement(By.xpath(".//h5/a")).getAttribute("href");
				String fullUrl = relativeUrl.startsWith("http") ? relativeUrl
						: "https://www.tarladalal.com" + relativeUrl;

				// 3. Recipe count
				String recipeText = block.findElement(By.xpath(".//p[contains(text(),'Recipes')]")).getText().trim();

				int recipeCount = Integer.parseInt(recipeText.split(" ")[0]);

				// 4. Extract categoryId from URL using regex
				String digits = relativeUrl.replaceAll(".*-(\\d+)\\D*$", "$1");
				int categoryId = Integer.parseInt(digits);

				CategoryInfo categoryInfo = new CategoryInfo(fullUrl, categoryName, recipeCount, categoryId);
				categoryMap.put(categoryId, categoryInfo);

			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.err.println("Skipping block due to missing element: " + e.getMessage());
			} catch (NumberFormatException e) {
				System.err.println("Skipping block due to invalid category ID or recipe count: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("Unexpected error: " + e.getMessage());
			}
		}
		return categoryMap;
	};

}