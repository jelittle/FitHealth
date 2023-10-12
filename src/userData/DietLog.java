package userData;

import java.util.Date;
import java.util.List;
import java.util.Map;

enum MealType {
    Breakfast, Lunch, Dinner, Snack
}

enum FoodGroup {
    Grains, Fruits, Vegetables, Protein, Dairy
}

enum VitaminType {
    VitaminA, VitaminB, VitaminC, VitaminD
}


class NutrientInfo {
    private int calories;
    private double proteins;
    private double carbs;
    private Map<VitaminType, Double> vitamins;
    private Map<String, Double> otherNutrients;

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

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public Map<VitaminType, Double> getVitamins() {
        return vitamins;
    }

    public void setVitamins(Map<VitaminType, Double> vitamins) {
        this.vitamins = vitamins;
    }

    public Map<String, Double> getOtherNutrients() {
        return otherNutrients;
    }

    public void setOtherNutrients(Map<String, Double> otherNutrients) {
        this.otherNutrients = otherNutrients;
    }
}

class Ingredient {
    private String name;
    private double quantity;
    private FoodGroup foodGroup;
    private NutrientInfo nutrientInfo;

    public Ingredient(String name, double quantity, FoodGroup foodGroup, NutrientInfo nutrientInfo) {
        this.name = name;
        this.quantity = quantity;
        this.foodGroup = foodGroup;
        this.nutrientInfo = nutrientInfo;
    }

    public NutrientInfo getNutrientInfo() {
        return nutrientInfo;
    }

    // Getters for name, quantity, and foodGroup



}

class DietLogEntry {
    private Date date;
    private MealType mealType;
    private List<Ingredient> ingredients;

    public DietLogEntry(Date date, MealType mealType, List<Ingredient> ingredients) {
        this.date = date;
        this.mealType = mealType;
        this.ingredients = ingredients;
    }

    public int getCalories() {
        int totalCalories = 0;
        for (Ingredient ingredient : ingredients) {
            totalCalories += ingredient.getNutrientInfo().getCalories();
        }
        return totalCalories;
    }

    public NutrientInfo getNutrientBreakdown() {
        NutrientInfo totalNutrients = new NutrientInfo();
        for (Ingredient ingredient : ingredients) {
            // Add the nutrient info of each ingredient to the total
            // You need to implement the addition of nutrient info here
        }
        return totalNutrients;
    }
}

public class DietLog {
    public static void main(String[] args) {
        // Create ingredients with their nutrient information
        NutrientInfo tomatoNutrients = new NutrientInfo();
        tomatoNutrients.setCalories(22);
        tomatoNutrients.setProteins(0.9);
        tomatoNutrients.setCarbs(4.8);

        Ingredient tomato = new Ingredient("Tomato", 100.0, FoodGroup.Vegetables, tomatoNutrients);

        // Create a DietLogEntry for breakfast
        DietLogEntry breakfastEntry = new DietLogEntry(new Date(), MealType.Breakfast, List.of(tomato));

        // Calculate and print the total calories for the breakfast entry
        int totalCalories = breakfastEntry.getCalories();
        System.out.println("Total Calories for Breakfast: " + totalCalories);

        // You can continue to add more ingredients, meals, and functionality as needed.
    }
}
