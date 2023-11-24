public class IngredientData {
    private String ingredient;
    private String amount;

    public IngredientData(String ingredient, String amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    // Getters
    public String getIngredient() {
        return ingredient;
    }

    public String getAmount() {
        return amount;
    }



    @Override
    public String toString() {
        if (ingredient == null){}

        return "Ingredient: " + ingredient + ", Amount: " + amount;
    }

    
}

