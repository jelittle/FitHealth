package Database;

import DietLogs.DietLogEntry;
import DietLogs.MealIngredients;
import DietLogs.Ingredient;
import DietLogs.NutrientInfo;

import java.util.ArrayList;

//Interface between anything requiring DietLog objects within the Database
public interface DietLog{

    public DietLogEntry getDietLogById(int id);
    public  int getDietLogIdByName(String name);
    public boolean setDietLog(DietLogEntry dietLog);
    public void updateDietLog(DietLogEntry dietLog);
    public void deleteDietLog(DietLogEntry dietLog);
    ArrayList<DietLogEntry> getDietLogsByDateRangeAndUserId(long startDate, long EndDate, int userId);


    public Ingredient getIngredientById(int IngredientId);

    public int getIngredientIdByName(String IngredientName);


    public ArrayList<NutrientInfo> getAllNutrientInfoByIngredientId(int ingredientId);

    public MealIngredients getMealIngredientsById(int mealId, int ingredientId);
    public ArrayList<MealIngredients> getMealIngredientsTable(int mealId);
    public boolean setMealIngredients(int mealId, int ingredientId, float quantity);
    public boolean deleteMealIngredients(int mealId, int ingredientId);
}

class IDietLogClient implements DietLog {
    static Manager manager;

    public IDietLogClient() {
        if (manager == null)
            manager = new Manager();
    }

    public DietLogEntry getDietLogById(int id) {

        DietLogEntry dietLog = manager.getRecord("diet_log", null, new String[]{"id = " + id});
        //impossible to get more than 1 with id
        dietLog.setUserId(manager.getRecord("user", null, new String[]{"id = " + dietLog.getUserId()}));

        return dietLog;
    }

    public int getDietLogIdByName(String name) {
        DietLogEntry dietLog = manager.getRecord("diet_log", null, new String[]{"name = " + name});
        return dietLog.getDietId();
    }

    @Override
    public boolean setDietLog(DietLogEntry dietLog) {

        String[] columns = {"name", "foodgroup", "userid", "datetime"};

        String[] values = {dietLog.getName(), dietLog.getFoodGroup(), Integer.toString(dietLog.getUserId()), Integer.toString(dietLog.getDateTime())};

        return manager.insertRecord("diet_log", columns, values);
    }

    @Override
    public void updateDietLog(DietLogEntry dietLog) {

        String[] columns = {"id", "name", "foodgroup", "userid", "datetime"};

        String[] values = {Integer.toString(dietLog.getDietId()), dietLog.getName(), dietLog.getFoodGroup(), Integer.toString(dietLog.getUserId()), Integer.toString(dietLog.getDateTime())};

        manager.updateRecord("diet_log", columns, values, new String[]{"id = " + dietLog.getDietId()});

    }


    @Override
    public void deleteDietLog(DietLogEntry dietLog) {
        manager.deleteRecord("diet_log", new String[]{"id = " + dietLog.getDietId()});
    }

    @Override
    public ArrayList<DietLogEntry> getDietLogsByDateRangeAndUserId(long startDate, long EndDate, int userId) {
            ArrayList<DietLogEntry> array = manager.getRecordsSql("diet_log", "SELECT * FROM diet_log WHERE datetime >= " + startDate + " AND datetime <= " + EndDate
                    + " AND userid=" + userId);
            return array;
    }

    @Override
    public Ingredient getIngredientById(int id) {

        Ingredient ingredient = manager.getRecord("ingredient", null, new String[]{"id = " + id});

        return ingredient;

    }

    @Override
    public int getIngredientIdByName(String name) {
        Ingredient ingredient = manager.getRecord("ingredient", null, new String[]{"name = " + name});
        return ingredient.getIngredientId();
    }


    @Override
    public ArrayList<NutrientInfo> getAllNutrientInfoByIngredientId(int ingredientId) {

            ArrayList<NutrientInfo> nutrientInfos = manager.getRecordsSql("nutrientinfo", "SELECT * FROM nutrientinfo WHERE ingredientid = " + ingredientId);

            return nutrientInfos;
    }

    @Override
    public MealIngredients getMealIngredientsById(int mealId, int ingredientId) {

        MealIngredients mealIngredient = manager.getRecord("mealingredient", null, new String[]{"mealid = " + mealId, "ingredientid = " + ingredientId});

        return mealIngredient;
    }

    @Override
    public ArrayList<MealIngredients> getMealIngredientsTable(int mealId) {

        ArrayList<MealIngredients> mealIngredients = manager.getRecordsSql("mealingredient", "SELECT * FROM mealingredient WHERE mealid = " + mealId);

        return mealIngredients;
    }

    @Override
    public boolean setMealIngredients(int mealId, int ingredientId, float quantity) {
        String[] columns = {"mealid", "ingredientid", "quantity"};
        String[] values = {Integer.toString(mealId), Integer.toString(ingredientId), Float.toString(quantity)};
        return manager.insertRecord("mealingredient", columns, values);
    }

    @Override
    public boolean deleteMealIngredients(int mealId, int ingredientId) {
        return manager.deleteRecord("mealingredient", new String[]{"mealid = " + mealId, "ingredientid = " + ingredientId});
    }

}


class IdietLogClientTest implements DietLog {

    private static TestDatabase db;

    public IdietLogClientTest() {
        if (db == null)
            db = new TestDatabase();
    }


    @Override
    public DietLogEntry getDietLogById(int id) {
        return null;
    }

    @Override
    public int getDietLogIdByName(String name) {
        return 0;
    }

    @Override
    public boolean setDietLog(DietLogEntry dietLog) {
        return false;
    }

    @Override
    public void updateDietLog(DietLogEntry dietLog) {

    }

    @Override
    public void deleteDietLog(DietLogEntry dietLog) {

    }

    @Override
    public ArrayList<DietLogEntry> getDietLogsByDateRangeAndUserId(long startDate, long EndDate, int userId) {
        return null;
    }

    @Override
    public Ingredient getIngredientById(int IngredientId) {
        return null;
    }

    @Override
    public int getIngredientIdByName(String IngredientName) {
        return 0;
    }

    @Override
    public ArrayList<NutrientInfo> getAllNutrientInfoByIngredientId(int ingredientId) {
        return null;
    }

    @Override
    public MealIngredients getMealIngredientsById(int mealId, int ingredientId) {
        return null;
    }

    @Override
    public ArrayList<MealIngredients> getMealIngredientsTable(int mealId) {
        return null;
    }

    @Override
    public boolean setMealIngredients(int mealId, int ingredientId, float quantity) {
        return false;
    }

    @Override
    public boolean deleteMealIngredients(int mealId, int ingredientId) {
        return false;
    }


}