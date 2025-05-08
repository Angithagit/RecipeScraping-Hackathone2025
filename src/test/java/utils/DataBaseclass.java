package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseclass {
	private static final String URL = "jdbc:postgresql://localhost:5432/tarladalal";
	private static final String USER = "postgres"; // e.g., "postgres"
	private static final String PASSWORD = "Sql@2025"; // your PostgreSQL password

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public static void insertRecipe(String name, String category, String ingredients, String prepTime, String cookTime,
			String tags, String url) {
		String sql = "INSERT INTO foodcategory(recipe_name, food_category, ingredients, preparation_time, cooking_time, tags, url) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.setString(2, category);
			stmt.setString(3, ingredients);
			stmt.setString(4, prepTime);
			stmt.setString(5, cookTime);
			stmt.setString(6, tags);
			stmt.setString(7, url);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
