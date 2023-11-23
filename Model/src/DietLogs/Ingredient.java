package DietLogs;

public class Ingredient {

    private int IngredientId;
    private String IngredientName;

    private String foodGroup;

    public Ingredient(int IngredientId, String IngredientName, String foodGroup ) {
        this.IngredientId = IngredientId;
        this.IngredientName = IngredientName;
        this.foodGroup = foodGroup;
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

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }
}
