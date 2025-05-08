package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Categories")
	WebElement categories;

	@FindBy(linkText = "View All Category")
	WebElement viewAll;

	public void openAllCategories() {
		categories.click();
		viewAll.click();
	}

}
