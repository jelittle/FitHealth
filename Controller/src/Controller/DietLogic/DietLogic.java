package Controller.DietLogic;

import Controller.UnixTime;
import DietLogs.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DietLogic implements IDietLogic{

    private DietDataManager db = DietDataManager.getInstance();
    private ActiveMealLogs activeMealLogs;
    private ActiveIngredient activeIngredient;
    private DietInput dietInput;

    public DietLogic() {

        dietInput = new DietInput();
        activeMealLogs = new ActiveMealLogs();
        activeIngredient = new ActiveIngredient();
    }

    @Override
    public HashMap<String, ArrayList<String>> mealsByDateRange(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception{
        if (!(dietInput.isDateValid(startDate) && dietInput.isDateValid(EndDate))) {
            throw new Exception("Invalid date");
        }

        int startUnixTime = dietInput.fromArrayListToUnixTime(startDate);
        int endUnixTime = dietInput.fromArrayListToUnixTime(EndDate);


        ArrayList<DietLogEntry> dietLogEntries = activeMealLogs.GetActiveDietLogsByDateRangeAndUserId(startUnixTime, endUnixTime, userId);

        HashMap<String, ArrayList<String>> meals = new HashMap<>();

        for (DietLogEntry dietLogEntry : dietLogEntries) {
            ArrayList<MealIngredients> mealIngredients = activeIngredient.GetActiveMealIngredientsByMealId(dietLogEntry.getDietId());

        }

// not finished
        return null;
    }

    @Override
    public HashMap<String, Float> AverageDailyNutrientInfo(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception {
        if (!(dietInput.isDateValid(startDate) && dietInput.isDateValid(EndDate))) {
            throw new Exception("Invalid date");
        }

        int totalDays = dietInput.totalDays(startDate, EndDate);

        int startUnixTime = dietInput.fromArrayListToUnixTime(startDate);
        int endUnixTime = dietInput.fromArrayListToUnixTime(EndDate);

        ArrayList<DietLogEntry> dietLogEntries = activeMealLogs.GetActiveDietLogsByDateRangeAndUserId(startUnixTime, endUnixTime, userId);
        ArrayList<MealIngredients> mealIngredients = new ArrayList<>();

        for (DietLogEntry dietLogEntry : dietLogEntries) {
            mealIngredients.addAll(activeIngredient.GetActiveMealIngredientsByMealId(dietLogEntry.getDietId()));
        }

        ArrayList<NutrientInfo> nutrientInfos = new ArrayList<>();

        for (MealIngredients mealIngredient : mealIngredients) {
            nutrientInfos.addAll(db.getNutrientInfoByIngredientId(mealIngredient.getIngredientId()));
        }

        HashMap<String, Float> nutrientInfo = new HashMap<>();

        for (NutrientInfo info : nutrientInfos) {
            if (nutrientInfo.containsKey(info.getNutrientName())) {
                nutrientInfo.put(info.getNutrientName(), nutrientInfo.get(info.getNutrientName()) + info.getNutrientValue());
            } else {
                nutrientInfo.put(info.getNutrientName(), info.getNutrientValue());
            }
        }

        for (String key : nutrientInfo.keySet()) {
            nutrientInfo.put(key, nutrientInfo.get(key) / totalDays);
        }

        return nutrientInfo;

    }

    @Override
    public HashMap<String, Float> PercentagesOfNutrients(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception {

        HashMap<String, Float> nutrientInfo = AverageDailyNutrientInfo(startDate, EndDate, userId);

        float totalNutrients = 0;

        for (String key : nutrientInfo.keySet()) {
            totalNutrients = totalNutrients + nutrientInfo.get(key);
        }

        HashMap<String, Float> percentageNutrients = new HashMap<>();

        for (String key : nutrientInfo.keySet()) {
            percentageNutrients.put(key, (nutrientInfo.get(key) / totalNutrients) * 100);
        }

        return percentageNutrients;

    }

    @Override
    public void addMeal(String mealName, String mealType, ArrayList<Integer> dateTime, int userId) throws Exception {
        if (!(dietInput.isDateValid(dateTime))) {
            throw new Exception("Invalid date");
        }

        if (!(dietInput.isMealTypeValid(mealType))) {
            throw new Exception("Invalid meal type");
        }

        int dateUnixTime = dietInput.fromArrayListToUnixTime(dateTime);

        dietInput.addDietLog(mealName, mealType, dateUnixTime, userId);
    }

    @Override
    public void deleteMeal(int mealId) throws Exception {

        if (db.getDietLogById(mealId) == null) {
            throw new Exception("Invalid meal id");
        }

        db.deleteDietLog(db.getDietLogById(mealId));
    }

    @Override
    public void addIngredient(int mealId, String ingredientName, float quantity ) throws Exception {
        if (ingredientName.equals("")) {
            throw new IllegalArgumentException("ingredientName is empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }
        if (db.getDietLogById(mealId) == null) {
            throw new Exception("Invalid meal id");
        }

        int ingredientId = db.getIngredientIdByName(ingredientName);

        if (db.getIngredientIdByName(ingredientName) == 0) {
            throw new Exception("Ingredient does not exists");
        }

        db.addMealIngredients(new MealIngredients(0, ingredientId, quantity));

    }

    @Override
    public void deleteIngredient(int mealId, int ingredientId) throws Exception {
        if (db.getDietLogById(mealId) == null) {
            throw new Exception("Invalid meal id");
        }
        if (db.getIngredientById(ingredientId) == null) {
            throw new Exception("Invalid ingredient id");
        }

        db.deleteMealIngredients(mealId, ingredientId);

    }
}
