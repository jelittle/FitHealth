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

    private alignmentWithCanadaFoodGuide alignmentWithCanadaFoodGuide;

    public DietLogic() {

        dietInput = new DietInput();
        activeMealLogs = new ActiveMealLogs();
        activeIngredient = new ActiveIngredient();
        alignmentWithCanadaFoodGuide = new alignmentWithCanadaFoodGuide();
    }

    @Override
    public HashMap<String, ArrayList<String>> mealsByDateRange(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception{
        if (!(dietInput.isDateValid(startDate) && dietInput.isDateValid(EndDate))) {
            throw new Exception("Invalid date");
        }

        int startUnixTime = dietInput.fromArrayListToUnixTime(startDate);
        int endUnixTime = dietInput.fromArrayListToUnixTime(EndDate);


        ArrayList<DietLogEntry> dietLogEntries = activeMealLogs.GetActiveDietLogsByDateRangeAndUserId(startUnixTime, endUnixTime, userId);

        HashMap<String, ArrayList<MealIngredients>> mealIngredients = new HashMap<>();

        for (DietLogEntry dietLogEntry : dietLogEntries) {
            mealIngredients.put(dietLogEntry.getName(), activeIngredient.GetActiveMealIngredientsByMealId(dietLogEntry.getDietId()));
        }

        HashMap<String, ArrayList<Ingredient>> mealWithIngredient = new HashMap<>();

        for (String key : mealIngredients.keySet()) {
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for (MealIngredients mealIngredient : mealIngredients.get(key)) {
                ingredients.add(db.getIngredientById(mealIngredient.getIngredientId()));
            }
            mealWithIngredient.put(key, ingredients);
        }





// not finishe
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

        nutrientInfo.replaceAll((k, v) -> nutrientInfo.get(k) / totalDays);

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

    private ArrayList<MealIngredients> getMealIngredients(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception {

        if (!(dietInput.isDateValid(startDate) && dietInput.isDateValid(EndDate))) {
            throw new Exception("Invalid date");
        }

        int startUnixTime = dietInput.fromArrayListToUnixTime(startDate);
        int endUnixTime = dietInput.fromArrayListToUnixTime(EndDate);

        ArrayList<DietLogEntry> dietLogEntries = activeMealLogs.GetActiveDietLogsByDateRangeAndUserId(startUnixTime, endUnixTime, userId);

        ArrayList<MealIngredients> mealIngredients = new ArrayList<>();

        for (DietLogEntry dietLogEntry : dietLogEntries) {
            mealIngredients.addAll(activeIngredient.GetActiveMealIngredientsByMealId(dietLogEntry.getDietId()));
        }

        return mealIngredients;
    }

    public HashMap<String, Float> averagePercentagesOfFoodGroupss(int mealId) throws Exception {

        ArrayList<MealIngredients> mealIngredients =  db.getMealIngredientsByMealId(mealId);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        int totalIngredients = 0;

        for (MealIngredients mealIngredient : mealIngredients) {
            ingredients.add(db.getIngredientById(mealIngredient.getIngredientId()));
            totalIngredients = totalIngredients + 1;
        }
        HashMap<String, Float> averagefoodGroups = alignmentWithCanadaFoodGuide.averageOfFoodGroups(totalIngredients, ingredients);

        return averagefoodGroups;

    }

    @Override
    public HashMap<Integer, String > addMeal(String mealName, String mealType, ArrayList<Integer> dateTime, int userId) throws Exception {
        if (!(dietInput.isDateValid(dateTime))) {
            throw new Exception("Invalid date");
        }

        if (!(dietInput.isMealTypeValid(mealType))) {
            throw new Exception("Invalid meal type");
        }

        int dateUnixTime = dietInput.fromArrayListToUnixTime(dateTime);

        dietInput.addDietLog(mealName, mealType, dateUnixTime, userId);

        int mealId = db.getDietLogIdByName(mealName);

        HashMap<Integer, String> meal = new HashMap<>();
        meal.put(mealId, mealName);

        return meal;
    }

    @Override
    public void deleteMeal(int mealId) throws Exception {

        if (db.getDietLogById(mealId) == null) {
            throw new Exception("Invalid meal id");
        }

        db.deleteDietLog(db.getDietLogById(mealId));
    }

    @Override
    public HashMap<String, Float> addIngredient(int mealId, String ingredientName, float quantity ) throws Exception {
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

        if (ingredientId== 0) {
            throw new Exception("Ingredient does not exists");
        }

        db.addMealIngredients(mealId, ingredientId, quantity);

        HashMap<String, Float> ingredient = new HashMap<>();
        ingredient.put(ingredientName, quantity);

        return ingredient;

    }

    @Override
    public void deleteIngredient(int mealId, String ingredientName) throws Exception {
        if (db.getDietLogById(mealId) == null) {
            throw new Exception("Invalid meal id");
        }

        if (ingredientName.equals("")) {
            throw new IllegalArgumentException("ingredientName is empty");
        }

        if (db.getIngredientIdByName(ingredientName) == 0) {
            throw new Exception("Ingredient does not exists");
        }

        int ingredientId = db.getIngredientIdByName(ingredientName);

        db.deleteMealIngredients(mealId, ingredientId);

    }

    @Override
    public ArrayList<String> getAllIngredientsAvailable() throws Exception {
        ArrayList<Ingredient> ingredients = db.getAllIngredientsAvailable();
        ArrayList<String> ingredientsName = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            ingredientsName.add(ingredient.getIngredientName());
        }

        return ingredientsName;
    }

    @Override
    public HashMap<String, Float> alignmentWithCanadaFoodGuide(int mealId, int userId) throws Exception {
        if (db.getDietLogById(mealId) == null) {
            throw new Exception("Invalid meal id");
        }

        HashMap<String, Float> averagefoodGroups = averagePercentagesOfFoodGroupss(mealId);

        float recomendedVegetablesAndFruits = alignmentWithCanadaFoodGuide.determineAge(userId, "Vegetables and Fruits");
        float recomendedGrainProducts = alignmentWithCanadaFoodGuide.determineAge(userId, "Grain Products");
        float recomendedMilkAndAlternatives = alignmentWithCanadaFoodGuide.determineAge(userId, "Milk and Alternatives");
        float recomendedMeatAndAlternatives = alignmentWithCanadaFoodGuide.determineAge(userId, "Meat and Alternatives");

        HashMap<String, Float> alignment = new HashMap<>();

        for (String key : averagefoodGroups.keySet()) {
            if (key.equals("Vegetables and Fruits")) {
                alignment.put(key, averagefoodGroups.get(key) / recomendedVegetablesAndFruits * 100);
            } else if (key.equals("Grain Products")) {
                alignment.put(key, averagefoodGroups.get(key) / recomendedGrainProducts * 100);
            } else if (key.equals("Milk and Alternatives")) {
                alignment.put(key, averagefoodGroups.get(key) / recomendedMilkAndAlternatives * 100);
            } else if (key.equals("Meat and Alternatives")) {
                alignment.put(key, averagefoodGroups.get(key) / recomendedMeatAndAlternatives * 100);
            } else {
                alignment.put(key, averagefoodGroups.get(key));
            }
        }

        return alignment;
    }


}
