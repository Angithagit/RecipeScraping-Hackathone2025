package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseclass {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/tarladalal", "postgres", "Sql@2025");
    }

    private static void insert(String query, String  recipename,  String foodcategory, String recipecategory, String ingredients, String prep, String cook,
            String noOfServings, String description, String method, String nutrientvalues, String tags, String url) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1,  recipename);
            stmt.setString(2, foodcategory);
            stmt.setString(3, recipecategory);
            stmt.setString(4, ingredients);
            stmt.setString(5, prep);
            stmt.setString(6, cook);
            stmt.setString(7, noOfServings);
            stmt.setString(8, description);
            stmt.setString(9, method);
            stmt.setString(10, nutrientvalues);
            stmt.setString(11, tags);
            stmt.setString(12, url);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while inserting into database: " + e.getMessage());
        }
    }

    public static void insertIntoTable(String tableName, String  recipename,  String foodcategory, String recipecategory, String ingredients, String method, String nutrientvalues, String prep, String cook,
            String noOfServings, String description, String tags, String url) {
        String sql = "INSERT INTO " + tableName + " (recipe_name, food_category, recipe_category, ingredients, preparation_time, cooking_time, no_of_servings, recipe_description, recipe_method, nutrient_values, tags, recipe_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
        insert(sql,  recipename, foodcategory, recipecategory, ingredients, prep, cook, noOfServings, description, method, nutrientvalues, tags, url);
    }
    public static void truncateAllTables() {
        String[] tables = {
            "Low_fat_diet_add",
            "Low_fat_diet_elimination",
            "Low_fat_diet_Non_Vegan",
            "Low_Carb_High_fat_add",
            "Low_Carb_High_fat_elimination",
            "Milk_Allergy",
            "Soy_Allergy",
            "Egg_Allergy",
            "Sesame_Allergy",
            "Nuts_Allergy",
            "Shell_Fish_Allergy",
            "Seafood_Allergy"
        };

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            for (String table : tables) {
                stmt.execute("TRUNCATE TABLE " + table + " RESTART IDENTITY CASCADE");
            }
            System.out.println("✅ All tables truncated successfully.");
        } catch (SQLException e) {
            System.out.println("❌ Error while truncating tables: " + e.getMessage());
        }
    }

}
