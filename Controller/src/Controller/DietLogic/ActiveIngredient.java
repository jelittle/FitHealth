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

    public void addMealIngredients(MealIngredients mealIngredients){
        db.addMealIngredients(mealIngredients);
    }

    public void deleteMealIngredients(MealIngredients mealIngredients){
        db.deleteMealIngredients(mealIngredients);
    }

    public ArrayList<MealIngredients> getActiveIngredients() {
        return ActiveIngredients;
    }

}
