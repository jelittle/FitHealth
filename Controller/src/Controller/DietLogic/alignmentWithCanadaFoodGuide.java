package Controller.DietLogic;

import DietLogs.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;

public class alignmentWithCanadaFoodGuide {
    public static final String VEGETABLES_FRUITS = "Vegetables and Fruits";
    public static final String GRAIN_PRODUCTS = "Grain Products";
    public static final String MILK_ALTERNATIVES = "Milk and Alternatives";
    public static final String MEAT_ALTERNATIVES = "Meat and Alternatives";

    public HashMap<String, Float> averageOfFoodGroups(int totalIngredient, ArrayList<Ingredient> ingredients) {
        HashMap<String, Float> foodGroupPercentages = new HashMap<>();
        foodGroupPercentages.put(VEGETABLES_FRUITS, 0f);
        foodGroupPercentages.put(GRAIN_PRODUCTS, 0f);
        foodGroupPercentages.put(MILK_ALTERNATIVES, 0f);
        foodGroupPercentages.put(MEAT_ALTERNATIVES, 0f);

        for (Ingredient ingredient : ingredients) {
            updateFoodGroupCount(ingredient, foodGroupPercentages);
        }

        foodGroupPercentages.put(VEGETABLES_FRUITS, foodGroupPercentages.get(VEGETABLES_FRUITS) / totalIngredient);
        foodGroupPercentages.put(GRAIN_PRODUCTS, foodGroupPercentages.get(GRAIN_PRODUCTS) / totalIngredient);
        foodGroupPercentages.put(MILK_ALTERNATIVES, foodGroupPercentages.get(MILK_ALTERNATIVES) / totalIngredient);
        foodGroupPercentages.put(MEAT_ALTERNATIVES, foodGroupPercentages.get(MEAT_ALTERNATIVES) / totalIngredient);

        return foodGroupPercentages;

    }

    private void updateFoodGroupCount(Ingredient ingredient, HashMap <String, Float> foodGroupCounts) {
        switch (ingredient.getFoodGroup()) {
            case VEGETABLES_FRUITS ->
                    foodGroupCounts.put(VEGETABLES_FRUITS, foodGroupCounts.getOrDefault(VEGETABLES_FRUITS, 0f) + 1);
            case GRAIN_PRODUCTS ->
                    foodGroupCounts.put(GRAIN_PRODUCTS, foodGroupCounts.getOrDefault(GRAIN_PRODUCTS, 0f) + 1);
            case MILK_ALTERNATIVES ->
                    foodGroupCounts.put(MILK_ALTERNATIVES, foodGroupCounts.getOrDefault(MILK_ALTERNATIVES, 0f) + 1);
            case MEAT_ALTERNATIVES ->
                    foodGroupCounts.put(MEAT_ALTERNATIVES, foodGroupCounts.getOrDefault(MEAT_ALTERNATIVES, 0f) + 1);
        }
    }

    public static ServingsCalculator createCalculator(int age) {
        if (age <= 13) {
            return new ServingsCalculatorForChildren();
        } else if (age <= 18) {
            return new ServingsCalculatorForTeens();
        } else {
            return new ServingsCalculatorForAdults();
        }
    }

    public float determineAge(int age, String foodGroup) {
        ServingsCalculator servingsCalculator = createCalculator(age);
        return servingsCalculator.calculateServings(age, foodGroup);
    }



}

