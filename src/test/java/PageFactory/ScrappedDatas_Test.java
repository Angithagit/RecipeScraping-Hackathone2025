package PageFactory;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
public class ScrappedDatas_Test {
	//String URL = "https://www.tarladalal.com/";

    public static WebDriver driver;

	 
    // Use Page Factory to locate elements


    @FindBy(xpath = "/html/body/header/nav/div/div/div[2]/ul/li[4]/a")
    WebElement categories;
    @FindBy(xpath ="/html/body/header/nav/div/div/div[2]/ul/li[4]/ul/li[9]/a")
    WebElement view_all_categories;
    @FindBy(xpath ="/html/body/header/nav/div/div/div[2]/ul/li[4]/ul/li[1]/a")
	protected
    WebElement healthy_indian_recipies;
    @FindBy(xpath ="/html/body/header/nav/div/div/div[3]/a[1]/i")
    WebElement searchicon;	    
    @FindBy(xpath ="//*[@id=\"ad_position_box\"]")
	protected
    WebElement adElement;
    @FindBy(xpath ="//*[@id=\"dismiss-button\"]")
	protected 
    WebElement add_btn_close;

    @FindBy(xpath = "/html/body/div[5]")
    WebElement searchcontainer;

    @FindBy(xpath = "/html/body/div[1]/div/div[1]/div[2]/form")
    WebElement search_textbox;


    @FindBy(xpath = "/html/body/div[3]/div/div[1]/div[2]/form/div/button")
    WebElement search_button;

    @FindBy(xpath = "/html/body/main/section/div[3]/div/div/div")
    WebElement  recipeLinks;

    @FindBy(xpath = "/html/body/main/section/div[3]/div/div/div/div[1]/div[1]/div[2]/div/div[2]/a")
	public
    WebElement  vitb12;

    @FindBy(xpath = "//a[@href='/paneer-masala-2404r']")
	public
    WebElement  paneer_masala;

    @FindBy(xpath = "/html/body/main/section/div[3]/div/div[1]/div")
	protected
    WebElement  all_paneer;

    @FindBy(xpath = "/html/body/main/section/div/div/div[1]/div[8]/div[1]/table/tbody/tr[2]/td/a")
   	public WebElement  icredients;


    @FindBy(xpath = "//*[@id=\"shopify-section-coupon-subscription-theme-footer\"]/div[4]/div[1]/span[2]")
   	public WebElement  newwindow;

    @FindBy(xpath = "/html/body/main/section/div/div/div[1]/div[8]/div[1]/table/tbody/tr[3]/td/a")
   	public WebElement  recipie_method_link;
    @FindBy(xpath = " /html/body/main/section/div/div/div[1]/div[7]/div[1]/table/tbody/tr[3]/td/a")
   	public WebElement  recipie_method_link2;

    @FindBy(xpath = "//*[@id=\"methods\"]")
   	public WebElement  recipie_method_details;

    @FindBy(xpath = "/html/body/main/section/div/div/div[1]/div[8]/div[1]/table/tbody/tr[2]/td/a")
   	public WebElement ingredients_link;
    @FindBy(xpath = "//*[@id=\"ingredients\"]")
   	public WebElement ingredients_to_add;
    @FindBy(xpath = "/html/body/main/section/div/div/div[1]/div[8]/div[1]/table/tbody/tr[1]/td/a")
   	public WebElement Description_link;
    @FindBy(xpath = "/html/body/main/section/div/div/div[1]/div[7]/div[1]/table/tbody/tr[1]/td/a")
   	public WebElement Description_link2;

    @FindBy(xpath = "//*[@id=\"aboutrecipe\"]")
   	public WebElement Description_Details;
    @FindBy(xpath = "/html/body/main/section/div/div/div[1]/div[8]/div[1]/table/tbody/tr[7]/td/a")
   	public WebElement nutrient_value_link;
    @FindBy(xpath = "/html/body/main/section/div/div/div[1]/div[7]/div[1]/table/tbody/tr[8]/td/a")
   	public WebElement nutrient_value_link2;


    @FindBy(xpath = "//*[@id=\"rcpnuts\"]")
   	public WebElement nutrient_value_details;



    @FindBy(xpath = "/html/body/main/section/div[1]/div/div/h2")
   	public
       WebElement  justclick;



    @FindBy(xpath = "/html/body/main/section")
	public WebElement  fullpage;
    public void add_url_remove() 
	 {
    	 String currentUrl = driver.getCurrentUrl();
		 String newUrl = currentUrl.split("#")[0];
		   driver.get(newUrl);			

	 }
    public void CLICKcategories() 
	 {

    categories.click();
	 }
    public void CLICKview_all_categories() 
	 {
    view_all_categories.click();

	 }
    public void CLICKsearch_icon() 
	 {

    searchicon.click();

	 }

    public void close_Adverisement() {

    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

          try {
              // Check if an alert is present (e.g., advertisement pop-up)
              wait.until(ExpectedConditions.alertIsPresent());
             driver.switchTo().frame(adElement);
             add_btn_close.click();
              driver.switchTo().defaultContent();

          } catch (Exception e) {
              System.out.println("Advertisement iframe or close button not found, proceeding without closing ad.");
          }
    }

    // Launch the URL
    public static void launchUrl() {
        driver.get("https://m.tarladalal.com");
    }

    // Close the browser after the test
    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}
