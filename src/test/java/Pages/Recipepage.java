package Pages;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Recipepage {

	private WebDriver driver;

	public Recipepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'container')]/div/div[3]//i[@class='fa fa-search primary search-show']")
	private WebElement searchicon;

	@FindBy(xpath = "//input[@placeholder='Search Here']")
	private WebElement searchfield;

	@FindBy(xpath = "//button[contains(@class,'input-group-text btn btn-main')]")
	private WebElement searchbutton;

//    @FindBy(xpath = "//h1[contains(@id,'lblRecipename')]")
//    private WebElement title;
	@FindBy(xpath = "//h4[@class='rec-heading']/span")
	private WebElement recipeTitle;

	@FindBy(xpath = "//span[contains(text(),'Category')]/following-sibling::a")
	private WebElement category;

	@FindBy(xpath = "//span[contains(text(),'Cuisine')]/following-sibling::a")
	private WebElement cuisine;

//    @FindBy(xpath = "//div[@id='rcpinglist']//li")
	@FindBy(xpath = "//div[@id='ingredients']//p")
	private List<WebElement> ingredients;

	@FindBy(xpath = "//div[contains(@class,'box-time')][1]/div/p/strong")
	private WebElement preparationtime;

	@FindBy(xpath = "//div[contains(@class,'box-time')][2]/div/p/strong")
	private WebElement cookingtime;

	@FindBy(xpath = "//div[contains(@class,'box-time')][4]/div/p/strong")
	private WebElement noofservings;
	
	@FindBy(xpath="//a[contains(@class,'scroll-link') and contains(@href,'#aboutrecipe')]/span[@class='float-end']/img[@class='img-fluid' and contains(@src,'down-arrow')]")
	
	private WebElement recipedescription;
	
	//@FindBy(xpath="//a[contains(@class,'scroll-link') and normalize-space(text())='Methods']/span[@class='float-end']/img[@class='img-fluid' and contains(@src,'down-arrow')]")
	@FindBy(xpath="//li[@id='procstepbystep13070']")
	private WebElement recipemethods;
	
	@FindBy(xpath="//a[contains(@class,'scroll-link') and normalize-space(text())='Nutrient values']/span[@class='float-end']/img[@class='img-fluid' and contains(@src,'down-arrow')]")
	private WebElement nutrientvalues;
	
	@FindBy(xpath = "//div[@class=\"col-md-12\"]/ul/li/a[not(descendant::i)]")
	private WebElement tags;

	@FindBy(linkText = "Categories")
	private WebElement categories;

	@FindBy(linkText = "Cuisine")
	private WebElement cuisine1;
	


	public void searchIcon() {
		searchicon.click();

	}

	public WebElement searchField() {
		return searchfield;
	}

	public void clickSearchbutton() {
		searchbutton.click();
	}

//    public String getRecipeName() {
//        return title.getText().trim();
//    }
	public String getRecipeName() {
		String fullText = recipeTitle.getText().trim();
		// Split by space and take first 3 words
		String[] words = fullText.split("\\s+");
		return String.join(" ", Arrays.copyOfRange(words, 0, 3));
	}

	public String getRecipeCategory() {
		return category.getText().trim();
	}

	public String getFoodCategory() {
		return cuisine.getText().trim();
	}

	public List<String> getIngredients() {
		return ingredients.stream().map(WebElement::getText)
				.map(i -> i.replaceAll("\\d|\\(|\\)|/|\\.|-", "").trim().toLowerCase()).collect(Collectors.toList());
	}

	public String getPreparationTime() {
		return preparationtime.getText();
	}

	public String getCookingTime() {
		return cookingtime.getText();
	}

	public String getNoOfServings() {
		return noofservings.getText();
	}
	
	public String getRecipeDescription() {
		return recipedescription.getText();
	}
	
	public String getRecipeMethods() {
		return recipemethods.getText();
	}
	
	public String getNutrientValues() {
		return nutrientvalues.getText();
	}

	public String getTags() {
		return tags.getText();
	}

	public void clickCategories() {
		categories.click();
	}

	public void clickCuisine() {
		cuisine1.click();
	}
}
