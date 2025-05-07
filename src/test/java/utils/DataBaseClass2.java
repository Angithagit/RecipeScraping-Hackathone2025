package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseClass2 {

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/tarladalal", "postgres", "Sql@2025");
	}

	public static void OtherRecipes(String name, String foodCategory, String ingredients, String prepTime,
			String cookTime, String noOfServings, String description, 
			String tags, String url) {
		String sql = "INSERT INTO otherrecipes (recipe_name, food_category, ingredients, preparation_time, cooking_time, no_Of_Servings, description, tags, recipe_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		insert(sql, name, foodCategory, ingredients, prepTime, cookTime, noOfServings, description, tags, url);
	}

	public static void insertIntoEliminatedTable(String name, String foodCategory, String ingredients, String prepTime,
			String cookTime, String noOfServings, String description, 
			String tags, String url) {
		String sql = "INSERT INTO LFVELIMINATED (recipe_name, food_category, ingredients, preparation_time, cooking_time, no_Of_Servings, description, tags, recipe_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		insert(sql, name, foodCategory, ingredients, prepTime, cookTime, noOfServings, description,  tags, url);
	}

	public static void insertIntoAddedTable(String name, String foodCategory, String ingredients, String prepTime,
			String cookTime, String noOfServings, String description, 
			String tags, String url) {
		String sql = "INSERT INTO LFVTOADD (recipe_name, food_category, ingredients, preparation_time, cooking_time, no_Of_Servings, description, tags, recipe_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		insert(sql, name, foodCategory, ingredients, prepTime, cookTime, noOfServings, description, tags, url);
	}

	public static void insertIntoSafeRecipeTable(String name, String foodCategory, String ingredients, String prepTime,
			String cookTime, String noOfServings, String description, 
			String tags, String url) {
		String sql = "INSERT INTO saferecipes (recipe_name, food_category, ingredients, preparation_time, cooking_time, no_Of_Servings, description,tags, recipe_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		insert(sql, name, foodCategory, ingredients, prepTime, cookTime, noOfServings, description,  tags, url);
	}

	private static void insert(String query, String name, String category, String ingredients, String prep, String cook,
	        String noOfServings, String description, String tags, String url) {
	    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, name);
	        stmt.setString(2, category);
	        stmt.setString(3, ingredients);
	        stmt.setString(4, prep);
	        stmt.setString(5, cook);
	        stmt.setString(6, noOfServings);
	        stmt.setString(7, description);
//	        stmt.setString(8, methods);
//	        stmt.setString(9, nutrientvalues);
	        stmt.setString(8, tags);
	        stmt.setString(9, url);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("error while inserting into DataBase: " + e.getMessage());
	    }
	}
}
