package DietLogs;

public class MealIngredients {

    private int mealId;
    private int ingredientId;
    private float quantityValue;

    public MealIngredients(int mealId, int ingredientId, float quantity) {
        this.mealId = mealId;
        this.ingredientId = ingredientId;
        this.quantityValue = quantity;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public float getQuantityValue() {
        return quantityValue;
    }

    public void setQuantityValue(float quantityValue) {
        this.quantityValue = quantityValue;
    }

}
