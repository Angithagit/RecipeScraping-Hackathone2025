package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Pages.Recipepage;
import base.Base;
import excelreader.ExcelReader;

public class CuisineCategory extends Base{
	@Test
	public void scrapeBasedOnCuisineCategory() {

		List<Map<String, String>> categoryData = ExcelReader.readCategoryData();

		for (Map<String, String> row : categoryData) {

			String cuisineCategories = row.getOrDefault("cuisinecategory", "").trim().replaceAll(" +", "+");
			List<String> searchQueries = List.of(cuisineCategories);

			for (String cuisinecategory : searchQueries) {
				if (cuisinecategory.isEmpty())
					continue;
				   Recipepage page = new Recipepage(driver);
				   page.clickCategories();
				   page.clickCuisine();
				   
				   
				   
				   List<WebElement> linkElements = driver
							.findElements(By.xpath("//div[@class='col-sm-6 col-md-4 col-lg-3']//a[@href]"));
					System.out.println("printing linkelements" + linkElements);

					List<String> recipeUrls = new ArrayList<>();
					for (WebElement element : linkElements) {
						recipeUrls.add(element.getAttribute("href"));
					}
//
//				 System.out.println("Found " + recipeUrls.size() + " recipes");
//					for (String recipeUrl : recipeUrls) {
//						try {
//							driver.get(recipeUrl);
//						}

			}
		}
	}
}