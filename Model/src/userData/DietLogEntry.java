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
    private String name;
    private double quantity;
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

    public double getFinalCalories() {
        return (getQuantity() / 100) * getCalories();
    }

    public double getProteins() {
        return proteins;
    }

    public double getFinalProteins() {
        return (getQuantity() / 100) * getProteins();
    }

    public double getVitamins() {
        return vitamins;
    }

    public double getFinalVitamins() {
        return (getQuantity() / 100) * getVitamins();
    }

}


//class DietLog {
//    public static void main(String[] args) {
//        // Create ingredients with their nutrient information
//        NutrientInfo tomatoNutrients = new NutrientInfo();
//        tomatoNutrients.setCalories(22);
//        tomatoNutrients.setProteins(0.9);
//        tomatoNutrients.setCarbs(4.8);
//
//        Ingredient tomato = new Ingredient("Tomato", 100.0, FoodGroup.Vegetables, tomatoNutrients);
//
//        // Create a DietLogEntry for breakfast
//        DietLogEntry breakfastEntry = new DietLogEntry(22, new Date(), MealType.Breakfast, List.of(tomato));
//
//        // Calculate and print the total calories for the breakfast entry
//        int totalCalories = breakfastEntry.getCalories();
//        System.out.println("Total Calories for Breakfast: " + totalCalories);
//
//        // You can continue to add more ingredients, meals, and functionality as needed.
//    }
//}
