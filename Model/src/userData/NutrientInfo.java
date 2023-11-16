package userData;

public class NutrientInfo {

    private int nutrientId;
    private int ingredientId;
    private String nutrientName;
    private float nutrientValue;

    public NutrientInfo(int nutrientId, int ingredientId, String nutrientName, float nutrientValue) {
        this.nutrientId = nutrientId;
        this.ingredientId = ingredientId;
        this.nutrientName = nutrientName;
        this.nutrientValue = nutrientValue;
    }

    public int getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(int nutrientId) {
        this.nutrientId = nutrientId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    public float getNutrientValue() {
        return nutrientValue;
    }

    public void setNutrientValue(float nutrientValue) {
        this.nutrientValue = nutrientValue;
    }

}
