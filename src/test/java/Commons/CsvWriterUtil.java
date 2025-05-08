package Commons;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriterUtil {
	private static FileWriter writer;

    public static void initialize(String filePath) throws IOException {
        writer = new FileWriter(filePath);
        writer.append("Category ID,Category URL,Recipe ID,Recipe Name,Recipe URL\n");
    }

    public static void writeRow(int categoryId, String categoryUrl, int recipeId, String recipeName, String recipeUrl) throws IOException {
        writer.append(categoryId + ",");
        writer.append(categoryUrl + ",");
        writer.append(recipeId + ",");
        writer.append("\"" + recipeName.replace("\"", "\"\"") + "\",");
        writer.append(recipeUrl + "\n");
    }

    public static void close() throws IOException {
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }
}
