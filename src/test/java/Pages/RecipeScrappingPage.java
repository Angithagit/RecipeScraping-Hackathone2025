package Pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecipeScrappingPage {

	WebDriver driver;

	public RecipeScrappingPage(WebDriver driver) {
		this.driver = driver;
	}

	public List<String> getIngredients() {
		return driver.findElements(By.xpath("//div[@id='rcpinglist']//li")).stream().map(e -> e.getText().toLowerCase())
				.collect(Collectors.toList());
	}

	public String getRecipeName() {
		return driver.findElement(By.tagName("h1")).getText();
	}

}
