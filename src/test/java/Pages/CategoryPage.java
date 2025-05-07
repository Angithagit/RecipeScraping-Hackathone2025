package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryPage {
	WebDriver driver;

	public CategoryPage(WebDriver driver) {
		this.driver = driver;
	}

	public void selectCategory(String categoryName) {
		List<WebElement> categories = driver.findElements(By.xpath("//div[@class='rcc_recipelist']//a"));
		for (WebElement cat : categories) {
			if (cat.getText().toLowerCase().contains(categoryName.toLowerCase())) {
				cat.click();
				break;
			}
		}
	}

	public List<WebElement> getRecipeLinks() {
		return driver.findElements(By.xpath("//div[@class='rcc_recipelist']//a"));
	}

}
