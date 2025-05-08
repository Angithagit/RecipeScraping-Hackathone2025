package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class AllCategoryWisePage  {

	private WebDriver driver;

    
    public AllCategoryWisePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(linkText="Categories")
    private WebElement categoriesdropdown;
    
    @FindBy(linkText="View All Category")
    private WebElement viewallcategories;
    
    @FindBy(linkText="Healthy Indian Recipes")
    private WebElement healthyindianrecipes;
    
    public void clickCategoriesDropDown() {
    	categoriesdropdown.click();
    }
	
    public void clickViewAllCategories() {
    	viewallcategories.click();
    }
    
    public void clickHealthyIndianRecipes() {
    	healthyindianrecipes.click();
    }

}
