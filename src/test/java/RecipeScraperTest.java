import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class RecipeScraperTest {

    WebDriver driver;
    Connection conn;

    @BeforeMethod
	@BeforeClass
    public void setUp() throws Exception {
        // Setup JDBC Connection
        String url = "jdbc:postgresql://localhost:5432/your_db_name";
        String user = "your_username";
        String password = "your_password";
        conn = DriverManager.getConnection(url, user, password);

        // Setup WebDriver
        driver = new ChromeDriver();
    }

    @Test
    public void scrapeCategories() throws Exception {
        driver.get("https://www.tarladalal.com/RecipeCategories.aspx");

        List<WebElement> categoryLinks = driver.findElements(By.cssSelector("a.nrecipetitle"));

        for (WebElement cat : categoryLinks) {
            String name = cat.getText();
            String catUrl = cat.getAttribute("href");

            // Navigate to get number of recipes
            driver.navigate().to(catUrl);
            WebElement countElement = driver.findElement(By.xpath("//span[contains(@id,'ctl00_cntrightpanel_lblRecipestitle')]"));
            String countText = countElement.getText(); // e.g., "123 recipes"
            int numberOfRecipes = extractNumber(countText);

            // Insert into database
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO recipe_categories (category_name, category_url, number_of_recipes) VALUES (?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, catUrl);
            stmt.setInt(3, numberOfRecipes);
            stmt.executeUpdate();

            System.out.println("Inserted: " + name + " - " + numberOfRecipes);
            driver.navigate().back();
        }
    }

    private int extractNumber(String text) {
        try {
            return Integer.parseInt(text.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    @AfterMethod
	@AfterClass
    public void tearDown() throws Exception {
        if (conn != null) conn.close();
        if (driver != null) driver.quit();
    }
}
