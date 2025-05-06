package PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dto.CategoryInfo;

public class CategoriesScrapper extends CommonPageFactory {

	@FindBy(xpath = "//div[contains(@class,'recipe-block')]")
	private List<WebElement> recipeBlocks;

	
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
				String urlPath = relativeUrl.replaceAll("^.*recipes-for-(.*?)-\\d+.*$", "$1");

				String fullUrl = "https://www.tarladalal.com/recipes/category/" + urlPath;

				// 3. Recipe count
//				String recipeText = block.findElement(By.xpath(".//p[contains(text(),'Recipes')]")).getText().trim();
//
//				int recipeCount = Integer.parseInt(recipeText.split(" ")[0]);

				// 4. Extract categoryId from URL using regex
				String digits = relativeUrl.replaceAll(".*-(\\d+)\\D*$", "$1");
				int categoryId = Integer.parseInt(digits);

				CategoryInfo categoryInfo = new CategoryInfo(fullUrl, categoryName, 0, categoryId);
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