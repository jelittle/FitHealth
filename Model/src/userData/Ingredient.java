package userData;

public class Ingredient {

    private int IngredientId;
    private String IngredientName;

    public Ingredient(int IngredientId, String IngredientName) {
        this.IngredientId = IngredientId;
        this.IngredientName = IngredientName;
    }

    public int getIngredientId() {
        return IngredientId;
    }

    public void setIngredientId(int ingredientId) {
        IngredientId = ingredientId;
    }

    public String getIngredientName() {
        return IngredientName;
    }

    public void setIngredientName(String ingredientName) {
        IngredientName = ingredientName;
    }
}
