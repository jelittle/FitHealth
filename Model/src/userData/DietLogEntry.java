package userData;

import java.util.Date;
import java.util.List;

enum MealType {
    Breakfast, Lunch, Dinner, Snack
}

//enum FoodGroup {
//    Grains, Fruits, Vegetables, Protein, Dairy
//}

class NutrientInfo {
    private int calories;
    private double proteins;
    private double vitamins;

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }


    public double getVitamins() {
        return vitamins;
    }
    public void setVitamins(double vitamins) {
        this.vitamins = vitamins;
    }

}

public class DietLogEntry {

    private int Dietid;
    private String name = "";
    private double quantity = 0;
    private String foodGroup;
//    private NutrientInfo nutrientInfo;

    private int calories;
    private double proteins;
    private double vitamins;

    public DietLogEntry(int Dietid, String name, double quantity, String foodGroup, int calories, double proteins, double vitamins) {
        this.Dietid = Dietid;
        this.name = name;
        this.quantity = quantity;
        this.foodGroup = foodGroup;
        this.calories = calories;
        this.proteins = proteins;
        this.vitamins = vitamins;
    }

    public int getDietId() {
        return Dietid;
    }

    public void setDietId(int DietId) {
        this.Dietid = DietId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getFinalCalories() {
        return (getQuantity() / 100) * getCalories();
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFinalProteins() {
        return (getQuantity() / 100) * getProteins();
    }

    public double getVitamins() {
        return vitamins;
    }

    public void setVitamins(double vitamins) {
        this.vitamins = vitamins;
    }

    public double getFinalVitamins() {
        return (getQuantity() / 100) * getVitamins();
    }

}
