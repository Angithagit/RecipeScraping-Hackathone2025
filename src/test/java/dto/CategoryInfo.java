package dto;

public class CategoryInfo {
    private String url;
    private String categoryName;
	private int recipeCount;
    private int categoryId;

    public CategoryInfo(String url, String categoryName, int recipeCount, int categoryId) {
        this.url = url;
        this.recipeCount = recipeCount;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getUrl() {
        return url;
    }

    public int getRecipeCount() {
        return recipeCount;
    }

    public int getCategoryId() {
        return categoryId;
    }
    
    public String getCategoryName() {
		return categoryName;
	}
}
