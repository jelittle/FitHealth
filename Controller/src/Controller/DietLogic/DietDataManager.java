package Controller.DietLogic;

import Database.DietLog;
import Database.IDietClientFactory;
import DietLogs.*;

import java.util.ArrayList;


public class DietDataManager {

    private static DietDataManager instance = null;
    private static DietLog dietTable;

    private DietDataManager() {
        dietTable = IDietClientFactory.getIDietLogClient();
    }

    public static DietDataManager getInstance() {
        if (instance == null) {
            instance = new DietDataManager();
        }
        return instance;
    }

//    meal
    ArrayList<DietLogEntry> getDietLogbyDateRangeandUserId(int startUnixTime,int endUnixTime,int UserId) {
       return dietTable.getDietLogsByDateRangeAndUserId(startUnixTime,endUnixTime,UserId);
    }

    public void addDietLog(DietLogEntry dietLogEntry){
        dietTable.setDietLog(dietLogEntry);
    }

    public DietLogEntry getDietLogById(int dietLogId){
        return dietTable.getDietLogById(dietLogId);
    }

    public int getDietLogIdByName(String dietLogName ){
        return dietTable.getDietLogIdByName(dietLogName );
    }

    public void deleteDietLog(DietLogEntry dietLogEntry){
        dietTable.deleteDietLog(dietLogEntry);
    }

//    mealingredient
    ArrayList<MealIngredients> getMealIngredientsByMealId(int mealId){
        return dietTable.getMealIngredientsTable(mealId);
    }

    public void addMealIngredients(int mealId, int ingredientId, float quantity){
        dietTable.setMealIngredients(mealId, ingredientId, quantity);
    }

    public void deleteMealIngredients(int mealId, int ingredientId){
        dietTable.deleteMealIngredients(mealId, ingredientId);
    }

//    nutrientinfo
    ArrayList<NutrientInfo> getNutrientInfoByIngredientId(int ingredientId){
        return dietTable.getAllNutrientInfoByIngredientId(ingredientId);
    }

//    ingredient
    Ingredient getIngredientById(int ingredientId){
        return dietTable.getIngredientById(ingredientId);
    }

    public int getIngredientIdByName(String ingredientName){
        return dietTable.getIngredientIdByName(ingredientName);
    }

    public ArrayList<Ingredient> getAllIngredientsAvailable(){
        return dietTable.getAllIngredientsAvailable();
    }


}
