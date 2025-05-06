package dto;

public class RecipeUrlInfo {

	private String url;
	private String recipeName;
	private int recipeId;

	public RecipeUrlInfo(int recipeId,String url, String recipeName) {
		this.url = url;
		this.recipeId = recipeId;
		this.recipeName = recipeName;
	}

	public String getUrl() {
		return url;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

}
