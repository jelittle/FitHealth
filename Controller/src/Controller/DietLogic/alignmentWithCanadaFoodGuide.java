package Controller.DietLogic;

import DietLogs.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;

public class alignmentWithCanadaFoodGuide {


    public HashMap<String, Float> averageOfFoodGroups(int totalIngredient, ArrayList<Ingredient> ingredients) {

        HashMap<String, Float> foodGroupPercentages = new HashMap<>();
        float vegetablesAndFruits = 0;
        float grainProducts = 0;
        float milkAndAlternatives = 0;
        float meatAndAlternatives = 0;
        float other = 0;
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getFoodGroup().equals("Vegetables and Fruits")) {
                vegetablesAndFruits = vegetablesAndFruits + 1;
            } else if (ingredient.getFoodGroup().equals("Grain Products")) {
                grainProducts = grainProducts + 1;
            } else if (ingredient.getFoodGroup().equals("Milk and Alternatives")) {
                milkAndAlternatives = milkAndAlternatives + 1;
            } else if (ingredient.getFoodGroup().equals("Meat and Alternatives")) {
                meatAndAlternatives = meatAndAlternatives + 1;
            } else {
                other= other + 1;
            }
        }
        foodGroupPercentages.put("Vegetables and Fruits", vegetablesAndFruits / totalIngredient);
        foodGroupPercentages.put("Grain Products", grainProducts / totalIngredient);
        foodGroupPercentages.put("Milk and Alternatives", milkAndAlternatives / totalIngredient);
        foodGroupPercentages.put("Meat and Alternatives", meatAndAlternatives / totalIngredient);
        foodGroupPercentages.put("Other", other / totalIngredient);

        return foodGroupPercentages;

    }

    public float determineAge(int age, String foodGroup) {

        if (age <= 13) {
            return servingsForKids(age, foodGroup);
        } else if (age <= 18) {
            return servingsForTeens(age, foodGroup);
        } else {
            return servingsForAdults(age, foodGroup);
        }

    }

    public float servingsForKids(int age, String foodGroup) {
        float servings = 0;
        if (age >= 2 && age <= 3) {
            switch (foodGroup) {
                case "Vegetables and Fruits":
                    servings = 4;
                    break;
                case "Grain Products":
                    servings = 3;
                    break;
                case "Milk and Alternatives":
                    servings = 2;
                    break;
                case "Meat and Alternatives":
                    servings = 1;
                    break;
                case "Other":
                    servings = 0;
                    break;
            }
        } else if (age >= 4 && age <= 8) {
            switch (foodGroup) {
                case "Vegetables and Fruits":
                    servings = 5;
                    break;
                case "Grain Products":
                    servings = 4;
                    break;
                case "Milk and Alternatives":
                    servings = 2;
                    break;
                case "Meat and Alternatives":
                    servings = 1 ;
                    break;
                case "Other":
                    servings = 0;
                    break;
            }
        } else if (age >= 9 && age <= 13) {
            switch (foodGroup) {
                case "Vegetables and Fruits":
                    servings = 6;
                    break;
                case "Grain Products":
                    servings = 6;
                    break;
                case "Milk and Alternatives":
                    servings = 3;
                    break;
                case "Meat and Alternatives":
                    servings = 1;
                    break;
                case "Other":
                    servings = 0;
                    break;
            }
        } else {
            throw new IllegalArgumentException("age must be between 2 and 13");
        }
        return servings;
    }

    public float servingsForTeens(int age,  String foodGroup) {

        float servings = 0;
        if (age >= 14 && age <= 18) {
            switch (foodGroup) {
                case "Vegetables and Fruits":
                    servings = 7;
                    break;
                case "Grain Products":
                    servings = 6;
                    break;
                case "Milk and Alternatives":
                    servings = 3;
                    break;
                case "Meat and Alternatives":
                    servings = 3;
                    break;
                case "Other":
                    servings = 0;
                    break;
            }
        } else {
            throw new IllegalArgumentException("age must be between 14 and 18");
        }
        return servings;
    }

    public float servingsForAdults (int age,  String foodGroup ) {
        float servings = 0;
        if (age >= 19 && age <= 50) {
            switch (foodGroup) {
                case "Vegetables and Fruits":
                    servings = 7;
                    break;
                case "Grain Products":
                    servings = 6;
                    break;
                case "Milk and Alternatives":
                    servings = 2;
                    break;
                case "Meat and Alternatives":
                    servings = 2;
                    break;
                case "Other":
                    servings = 0;
                    break;
            }
        } else {
            throw new IllegalArgumentException("age must be between 19 and 50");
        }
        return servings;
    }

}

