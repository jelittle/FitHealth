package Controller.DietLogic;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDietLogic {


    public HashMap<String, ArrayList<String>> mealsByDateRange(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception;

    public HashMap<String, Float> AverageDailyNutrientInfo(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception;

    public HashMap<String, Float> PercentagesOfNutrients(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId)throws Exception;

    public void addMeal(String mealName, String mealType, ArrayList<Integer> dateTime, int userId) throws Exception;

    public void deleteMeal(int mealId) throws Exception;

    public void addIngredient(int mealId, String ingredientName, float quantity) throws Exception;

    public void deleteIngredient(int mealId, int ingredientId) throws Exception;

}
