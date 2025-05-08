package tests;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import Pages.Recipepage;
import base.Base;
import excelreader.ExcelReader;
import utils.DataBaseClass2;
import utils.DataBaseclass;
import utils.ProgressTracker;

public class GettingAllDetails extends Base {

//	 private void dismissAdPopupIfPresent(WebDriver driver) {
//	        try {
//	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//	            WebElement closeAd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Close'] | //div[contains(text(),'Close')] | //div[@role='dialog']//button")));
//	            if (closeAd.isDisplayed()) {
//	                closeAd.click();
//	                System.out.println("✅ Ad popup dismissed.");
//	                Thread.sleep(500);
//	            }
//	        } catch (Exception e) {
//	            // No popup present, continue silently
//	        }
//	    }

	    @Test
	    public void scrapeBasedOnCategoryFilters() {
	        Set<String> eliminate = ExcelReader.getColumnValues("LFVELIMINATION", 0).stream().map(String::toLowerCase).collect(Collectors.toSet());
	        Set<String> add = ExcelReader.getColumnValues("LFVELIMINATION", 1).stream().map(String::toLowerCase).collect(Collectors.toSet());
	        Set<String> optionalAdd = ExcelReader.getColumnValues("LFVELIMINATION", 2).stream().map(String::toLowerCase).collect(Collectors.toSet());
	        Set<String> recipesToAvoid = ExcelReader.getColumnValues("LFVELIMINATION", 3).stream().map(String::toLowerCase).collect(Collectors.toSet());
	        Set<String> optionalRecipes = ExcelReader.getColumnValues("LFVELIMINATION", 4).stream().map(String::toLowerCase).collect(Collectors.toSet());

	        List<Map<String, String>> FoodCategoryData = ExcelReader.readCategoryData();
	        Set<String> uniqueCategories = new LinkedHashSet<>();
	        for (Map<String, String> row : FoodCategoryData) {
	            String foodCategory = row.getOrDefault("foodcategory", "").trim().toLowerCase().replaceAll(" +", "+");
	            if (!foodCategory.isEmpty()) {
	                uniqueCategories.add(foodCategory);
	            }
	        }

	        System.out.println("Unique categories: " + uniqueCategories);

	        boolean resetProgress = true;
	        int lastSavedIndex = resetProgress ? 0 : ProgressTracker.read("last_index.txt");

	        if (!resetProgress && lastSavedIndex >= uniqueCategories.size()) {
	            System.out.println("All categories already scraped. Resetting progress.");
	            ProgressTracker.save("last_index.txt", 0);
	            lastSavedIndex = 0;
	        }

	        int index = 0;
	        for (String foodcategory : uniqueCategories) {
	            System.out.println("Processing category: " + foodcategory + " | Index: " + index);
	            if (index < lastSavedIndex) {
	                index++;
	                continue;
	            }

	            try {
	                Recipepage page = new Recipepage(driver);
	                page.searchIcon();
	                page.searchField().sendKeys(foodcategory);
	                page.clickSearchbutton();
	                
	                Set<String> recipeUrls = new LinkedHashSet<>();
					List<WebElement> linkElements = driver
							.findElements(By.xpath("//div[@class='col-sm-6 col-md-4 col-lg-3']//a[@href]"));
					for (WebElement element : linkElements) {
						recipeUrls.add(element.getAttribute("href"));
					}

					for (String recipeUrl : recipeUrls) {
						System.out.println("printing url: " + recipeUrl);
						try {
							driver.get(recipeUrl);
						    String name = page.getRecipeName();
	                        List<String> ingredientsList = page.getIngredients();
	                        String ingredients = String.join(", ", ingredientsList).toLowerCase();
	                        String prepTime = page.getPreparationTime();
	                        String cookTime = page.getCookingTime();
	                        String noOfServings = page.getNoOfServings();
	                        String tags = page.getTags();
	                        String description = page.getRecipeDescription();
//	                        String methods = page.getRecipeMethods();
//	                        String nutrientValues = page.getNutrientValues();
	                        String recipeurl = driver.getCurrentUrl();

	                        boolean hasEliminate = eliminate.stream().anyMatch(ingredients::contains);
	                        boolean matchesToAvoid = recipesToAvoid.stream().anyMatch(description::contains);
	                        boolean hasAdd = add.stream().anyMatch(ingredients::contains);
	                        boolean hasOptionalAdd = optionalAdd.stream().anyMatch(ingredients::contains);
	                        boolean isOptionalRecipe = optionalRecipes.stream().anyMatch(description::contains);

	                        if (hasEliminate || matchesToAvoid) {
	                            DataBaseClass2.insertIntoEliminatedTable(name, foodcategory, ingredients, prepTime, cookTime,
	                                    noOfServings, description, tags, recipeurl);
	                        } else if (hasAdd || hasOptionalAdd) {
	                            DataBaseClass2.insertIntoAddedTable(name, foodcategory, ingredients, prepTime, cookTime,
	                                    noOfServings, description, tags, recipeurl);
	                        } else if (isOptionalRecipe) {
	                            DataBaseClass2.insertIntoSafeRecipeTable(name, foodcategory, ingredients, prepTime, cookTime,
	                                    noOfServings, description, tags, recipeurl);
	                        } else {
	                            DataBaseClass2.OtherRecipes(name, foodcategory, ingredients, prepTime, cookTime,
	                                    noOfServings, description, tags, recipeurl);
	                        }

	                        
	                    } catch (Exception e) {
	                        System.out.println("Error navigating or scraping: " + e.getMessage());
	                        break;
	                    }
	                }

	                ProgressTracker.save("last_index.txt", index);
	                index++;

	            } catch (Exception e) {
	                System.out.println("❌ Category error: " + foodcategory + " | " + e.getMessage());
	            }
	        }
	    }
}

	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                

//	                new WebDriverWait(driver, Duration.ofSeconds(5))
//	                        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//	                                By.xpath("//div[@class='col-sm-6 col-md-4 col-lg-3']//a[@href]")));
//
//	                List<WebElement> recipeLinks = driver.findElements(By.xpath("//div[@class='col-sm-6 col-md-4 col-lg-3']//a[@href]"));
//
//	                while (!recipeLinks.isEmpty()) {
//	                    try {
//	                        dismissAdPopupIfPresent(driver);
//
//	                        WebElement recipeLink = recipeLinks.get(0);
//	                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", recipeLink);
//	                        Thread.sleep(500);
//	                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", recipeLink);
//
//	                        new WebDriverWait(driver, Duration.ofSeconds(5))
//	                                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1")));
//
//	                        page = new Recipepage(driver);
//	                        String name = page.getRecipeName();
//	                        List<String> ingredientsList = page.getIngredients();
//	                        String ingredients = String.join(", ", ingredientsList).toLowerCase();
//	                        String prepTime = page.getPreparationTime();
//	                        String cookTime = page.getCookingTime();
//	                        String noOfServings = page.getNoOfServings();
//	                        String tags = page.getTags();
//	                        String description = page.getRecipeDescription();
//	                        String methods = page.getRecipeMethods();
//	                        String nutrientValues = page.getNutrientValues();
//	                        String recipeUrl = driver.getCurrentUrl();
//
//	                        boolean hasEliminate = eliminate.stream().anyMatch(ingredients::contains);
//	                        boolean matchesToAvoid = recipesToAvoid.stream().anyMatch(description::contains);
//	                        boolean hasAdd = add.stream().anyMatch(ingredients::contains);
//	                        boolean hasOptionalAdd = optionalAdd.stream().anyMatch(ingredients::contains);
//	                        boolean isOptionalRecipe = optionalRecipes.stream().anyMatch(description::contains);
//
//	                        if (hasEliminate || matchesToAvoid) {
//	                            DataBaseClass2.insertIntoEliminatedTable(name, foodcategory, ingredients, prepTime, cookTime,
//	                                    noOfServings, description, methods, nutrientValues, tags, recipeUrl);
//	                        } else if (hasAdd || hasOptionalAdd) {
//	                            DataBaseClass2.insertIntoAddedTable(name, foodcategory, ingredients, prepTime, cookTime,
//	                                    noOfServings, description, methods, nutrientValues, tags, recipeUrl);
//	                        } else if (isOptionalRecipe) {
//	                            DataBaseClass2.insertIntoSafeRecipeTable(name, foodcategory, ingredients, prepTime, cookTime,
//	                                    noOfServings, description, methods, nutrientValues, tags, recipeUrl);
//	                        } else {
//	                            DataBaseClass2.OtherRecipes(name, foodcategory, ingredients, prepTime, cookTime,
//	                                    noOfServings, description, methods, nutrientValues, tags, recipeUrl);
//	                        }
//
//	                        driver.navigate().back();
//
//	                        new WebDriverWait(driver, Duration.ofSeconds(5))
//	                                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//	                                        By.xpath("//div[@class='col-sm-6 col-md-4 col-lg-3']//a[@href]")));
//
//	                        recipeLinks = driver.findElements(By.xpath("//div[@class='col-sm-6 col-md-4 col-lg-3']//a[@href]"));
//	                        if (!recipeLinks.isEmpty()) recipeLinks.remove(0);
//
//	                    } catch (Exception e) {
//	                        System.out.println("Error navigating or scraping: " + e.getMessage());
//	                        break;
//	                    }
//	                }
//
//	                ProgressTracker.save("last_index.txt", index);
//	                index++;
//
//	            } catch (Exception e) {
//	                System.out.println("❌ Category error: " + foodcategory + " | " + e.getMessage());
//	            }
//	        }
//	    }
//}
