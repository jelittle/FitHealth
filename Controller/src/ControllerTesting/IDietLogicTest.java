package ControllerTesting;

import Controller.DietLogic.*;
import Controller.UnixTime;
import DietLogs.DietLogEntry;
import Database.*;
import DietLogs.Ingredient;
import DietLogs.MealIngredients;
import DietLogs.NutrientInfo;


import java.util.ArrayList;
import java.util.HashMap;


public class IDietLogicTest {

    DietLogic dietLogic = new DietLogic();
    DietLog dietTable = IDietClientFactory.getIDietLogClient();


    @org.junit.jupiter.api.Test
    void addMeal() {
        try {

            ArrayList<Integer> date = new ArrayList<Integer>();
            date.add(2020);
            date.add(1);
            date.add(2);
            date.add(1);
            date.add(1);
            HashMap<Integer, String> meal = dietLogic.addMeal("Hot Dog", "Lunch", date, 1);

            for (Integer key : meal.keySet()) {
                System.out.println("mealId: " + key + " mealName: " + meal.get(key));
            }


        } catch (Exception e) {
            System.out.println("addMeal failed");
        }
    }

    @org.junit.jupiter.api.Test
    void addIngredient() {
        try {

            HashMap<String, Float> ingredient =  dietLogic.addIngredient(4, "Brown Rice", 2);

            for (String key : ingredient.keySet()) {
                System.out.println("ingredientName: " + key + " quantity: " + ingredient.get(key));
            }
        } catch (Exception e) {
            System.out.println("addIngredient failed");
        }
    }

    @org.junit.jupiter.api.Test
    void deleteIngredient() {
        try {
            dietLogic.deleteIngredient(4, "Brown Rice");
            System.out.println("ingredientName: " + "Brown Rice" + " is deleted");
        } catch (Exception e) {
            System.out.println("deleteIngredient failed");
        }
    }

    @org.junit.jupiter.api.Test
    void deleteMeal() {

        HashMap<Integer, String> meal = new HashMap<>();
        try {
            ArrayList<Integer> date = new ArrayList<Integer>();
            date.add(2020);
            date.add(1);
            date.add(2);
            date.add(1);
            date.add(1);
            meal = dietLogic.addMeal("noodle", "Breakfast", date, 1);

        } catch (Exception e) {
            System.out.println("deleteMeal failed");
        }

        try {
            for (Integer key : meal.keySet()) {
                dietLogic.deleteMeal(key);
                System.out.println(" mealName: " + meal.get(key) + " is deleted");
            }
        }
        catch (Exception e) {
            System.out.println("deleteMeal failed");
        }

    }

    @org.junit.jupiter.api.Test
    void getAllIngredientsAvailable() {
        try {
            ArrayList<String> ingredients = dietLogic.getAllIngredientsAvailable();
            for (String ingredient : ingredients) {
                System.out.println("ingredient: " + ingredient);
            }
        } catch (Exception e) {
            System.out.println("getAllIngredientsAvailable failed");
        }
    }


    @org.junit.jupiter.api.Test
    void mealsByDateRange() {
        try {
            ArrayList<Integer> startDate = new ArrayList<Integer>();
            startDate.add(2020);
            startDate.add(1);
            startDate.add(2);
            startDate.add(1);
            startDate.add(1);
            ArrayList<Integer> endDate = new ArrayList<Integer>();
            endDate.add(2021);
            endDate.add(1);
            endDate.add(2);
            endDate.add(1);
            endDate.add(1);
            ArrayList<DietLogEntry> meals = dietLogic.mealsByDateRange(startDate, endDate, 1);
            for (DietLogEntry meal : meals) {
                System.out.println("meal: " + meal.getName());
            }
        } catch (Exception e) {
            System.out.println("mealsByDateRange failed");
        }
    }

    @org.junit.jupiter.api.Test
    void alignmentWithCanadaFoodGuide() {
        try {
            ArrayList<Integer> startDate = new ArrayList<Integer>();
            startDate.add(2020);
            startDate.add(1);
            startDate.add(2);
            startDate.add(1);
            startDate.add(1);
            ArrayList<Integer> endDate = new ArrayList<Integer>();
            endDate.add(2021);
            endDate.add(1);
            endDate.add(2);
            endDate.add(1);
            endDate.add(1);
            HashMap<String, Float> alignment = dietLogic.alignmentWithCanadaFoodGuide(startDate, endDate, 1);
            for (String key : alignment.keySet()) {
                System.out.println("nutrient: " + key + " quantity: " + alignment.get(key));
            }
        } catch (Exception e) {
            System.out.println("alignmentWithCanadaFoodGuide failed");
        }
    }

    @org.junit.jupiter.api.Test
    void AverageDailyNutrientInfo() {
        try {
            ArrayList<Integer> startDate = new ArrayList<Integer>();
            startDate.add(2020);
            startDate.add(1);
            startDate.add(2);
            startDate.add(1);
            startDate.add(1);
            ArrayList<Integer> endDate = new ArrayList<Integer>();
            endDate.add(2021);
            endDate.add(1);
            endDate.add(2);
            endDate.add(1);
            endDate.add(1);
            HashMap<String, Float> averageDailyNutrientInfo = dietLogic.AverageDailyNutrientInfo(startDate, endDate, 1);
            for (String key : averageDailyNutrientInfo.keySet()) {
                System.out.println("nutrient: " + key + " quantity: " + averageDailyNutrientInfo.get(key));
            }
        } catch (Exception e) {
            System.out.println("AverageDailyNutrientInfo failed");
        }
    }

    @org.junit.jupiter.api.Test
    void PercentagesOfNutrients() {
        try {
            ArrayList<Integer> startDate = new ArrayList<Integer>();
            startDate.add(2020);
            startDate.add(1);
            startDate.add(2);
            startDate.add(1);
            startDate.add(1);
            ArrayList<Integer> endDate = new ArrayList<Integer>();
            endDate.add(2021);
            endDate.add(1);
            endDate.add(2);
            endDate.add(1);
            endDate.add(1);
            HashMap<String, Float> percentagesOfNutrients = dietLogic.PercentagesOfNutrients(startDate, endDate, 1);
            for (String key : percentagesOfNutrients.keySet()) {
                System.out.println("nutrient: " + key + " quantity: " + percentagesOfNutrients.get(key));
            }
        } catch (Exception e) {
            System.out.println("PercentagesOfNutrients failed");
        }
    }




}

