package Controller.DietLogic;

import DietLogs.MealIngredients;

import java.util.ArrayList;

public class ActiveIngredient {

    private DietDataManager db = DietDataManager.getInstance();

    private ArrayList<MealIngredients> ActiveIngredients = new ArrayList<>();

    public ArrayList<MealIngredients> GetActiveMealIngredientsByMealId(int mealId){
        ActiveIngredients =db.getMealIngredientsByMealId(mealId);
        return ActiveIngredients;
    }

    public void addMealIngredients(int mealId, int ingredientId, float quantity){
        db.addMealIngredients(mealId, ingredientId, quantity);
    }

    public void deleteMealIngredients(int mealId, int ingredientId){
        db.deleteMealIngredients(mealId, ingredientId);
    }

    public ArrayList<MealIngredients> getActiveIngredients() {
        return ActiveIngredients;
    }

}
