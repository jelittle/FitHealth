package Controller;

import java.util.ArrayList;

import Database.DietLog;
import Database.IDietClientFactory;
import Database.UserTestClient;
import DietLogs.Ingredient;
import DietLogs.MealIngredients;
import DietLogs.NutrientInfo;
import userData.*;
import DietLogs.*;

import java.util.HashMap;

public class DietController {

    private static DietLog dietTable;
    private static UserTestClient userTable;

    private ArrayList<DietLogEntry> ActiveddietLogs = new ArrayList<>();

    private ArrayList<Ingredient> Activedingredients = new ArrayList<>();
    private ArrayList<MealIngredients> ActivedmealIngredients = new ArrayList<>();

    private ArrayList<NutrientInfo> ActivednutrientInfo = new ArrayList<>();

    private User client;

    private int startUnixTime=0;
    private int endUnixTime=0;

    private int startYear=0;
    private int startMonth =0;
    private int startDay=0;

    private int endYear=0;
    private int endMonth =0;
    private int endDay=0;


    public DietController() {
        dietTable = IDietClientFactory.getIDietLogClient();
        userTable= new UserTestClient();
    }

    public void getCurrentUser () {
        client = userTable.getUserById(2);
    }

    public void setActiveDietLogsByDateRange(ArrayList<Integer> startDate, ArrayList<Integer> EndDate){
        //convert array of year month day to unix time

        startYear=startDate.get(0);
        startMonth=startDate.get(1);
        startDay=startDate.get(2);

        endYear=EndDate.get(0);
        endMonth=EndDate.get(1);
        endDay=EndDate.get(2);

        try{
            int year=startDate.get(0);
            int month=startDate.get(1);
            int day=startDate.get(2);
            int hour=startDate.get(3);
            int minute=startDate.get(4);

            startUnixTime= UnixTime.getUnixTime(year,month,day,hour,minute);

        }catch(Exception e){
            throw new IllegalArgumentException("startDate must be in form year,month,day,hour,minute");
        }

        try{
            int year=EndDate.get(0);
            int month=EndDate.get(1);
            int day=EndDate.get(2);
            int hour=EndDate.get(3);
            int minute=EndDate.get(4);

            endUnixTime=UnixTime.getUnixTime(year,month,day,hour,minute);
        }catch(Exception e){
            throw new IllegalArgumentException("EndDate must be in form year,month,day,hour,minute");
        }

        ActiveddietLogs = dietTable.getDietLogsByDateRangeAndUserId(startUnixTime, endUnixTime, client.getID());
    }

    public ArrayList<Integer> getActiveddietLogsId() {
        if (ActiveddietLogs == null) {
            throw new NullPointerException("ActiveddietLogs is null");
        }

        ArrayList<Integer> ids = new ArrayList<>();

        for (DietLogEntry dietLog : ActiveddietLogs) {
            ids.add(dietLog.getDietId());
        }
        return ids;
    }

    public ArrayList<String> getActiveddietLogsName() {
        if (ActiveddietLogs == null) {
            throw new NullPointerException("ActiveddietLogs is null");
        }

        ArrayList<String> names = new ArrayList<>();

        for (DietLogEntry dietLog : ActiveddietLogs) {
            names.add(dietLog.getName());
        }
        return names;
    }


    public void setActiveddietLogsMealIngredients() {
        if (ActiveddietLogs == null) {
            throw new NullPointerException("ActiveddietLogs is null");
        }
        ArrayList<Integer> dietLogsId = getActiveddietLogsId();
        ArrayList<ArrayList<MealIngredients>> ingredients = new ArrayList<>();

        for (int dietLog : dietLogsId) {
            ingredients.add(dietTable.getMealIngredientsTable(dietLog));
        }

        for (int i = 0; i < ingredients.size(); i++) {
            ActivedmealIngredients.addAll(ingredients.get(i));
        }
    }

    public void setActiveddietLogsIngredients() {
        if (ActivedmealIngredients == null) {
            throw new NullPointerException("ActivedmealIngredients is null");
        }
        for (MealIngredients mealIngredients : ActivedmealIngredients) {
            Activedingredients.add(dietTable.getIngredientById(mealIngredients.getIngredientId()));
        }
    }

    public ArrayList<Ingredient> getActivedingredients() {
        if (Activedingredients == null) {
            throw new NullPointerException("Activedingredients is null");
        }
        return Activedingredients;
    }

    public void setActivednutrientInfo() {
        if (Activedingredients == null) {
            throw new NullPointerException("Activedingredients is null");
        }
        for (Ingredient ingredient : Activedingredients) {
            ActivednutrientInfo.addAll(dietTable.getAllNutrientInfoByIngredientId(ingredient.getIngredientId()));
        }
    }

    public ArrayList<NutrientInfo> getActivednutrientInfo() {
        if (ActivednutrientInfo == null) {
            throw new NullPointerException("ActivednutrientInfo is null");
        }
        return ActivednutrientInfo;
    }

    public int getTotalDays() {
        if (ActiveddietLogs == null) {
            throw new NullPointerException("ActiveddietLogs is null");
        }

        int totalDays = 1;

        if(!(Math.abs( startDay - endDay) == 0)) {
            totalDays = totalDays + (Math.abs(endDay - startDay));
        }

        if(!(Math.abs( startMonth - endMonth) == 0)) {
            totalDays = totalDays + (Math.abs(endMonth - startMonth) * 30);
        }

        if(!(Math.abs( startYear - endYear) == 0)) {
            totalDays = totalDays + (Math.abs(endYear - startYear) * 365);
        }

        return totalDays;
    }

    public float getTotalCalories() {
        if (ActivednutrientInfo == null) {
            throw new NullPointerException("ActivednutrientInfo is null");
        }

        float totalCalories = 0;

        for (NutrientInfo nutrientInfo : ActivednutrientInfo) {
            if (nutrientInfo.getNutrientName().equals("Calories")) {
                totalCalories = totalCalories +  nutrientInfo.getNutrientValue();
            }
        }

        return totalCalories;
    }

    public HashMap<String, Float> avrageDailyNutrients() {
        if (ActivednutrientInfo == null) {
            throw new NullPointerException("ActivednutrientInfo is null");
        }

        HashMap<String, Float> avrageDailyNutrients = new HashMap<>();

        for (NutrientInfo nutrientInfo : ActivednutrientInfo) {
            if (avrageDailyNutrients.containsKey(nutrientInfo.getNutrientName())) {
                avrageDailyNutrients.put(nutrientInfo.getNutrientName(), avrageDailyNutrients.get(nutrientInfo.getNutrientName()) + (int) nutrientInfo.getNutrientValue());
            } else {
                avrageDailyNutrients.put(nutrientInfo.getNutrientName(), nutrientInfo.getNutrientValue());
            }
        }

        for (String key : avrageDailyNutrients.keySet()) {
            avrageDailyNutrients.put(key, avrageDailyNutrients.get(key) / getTotalDays());
        }

        return avrageDailyNutrients;
    }

    public HashMap<String, Float> percentageDailyNutrients() {
        if (ActivednutrientInfo == null) {
            throw new NullPointerException("ActivednutrientInfo is null");
        }

        HashMap<String, Float> avrageDailyNutrients = avrageDailyNutrients();
        float totalNutrients = 0;

        for (String key : avrageDailyNutrients.keySet()) {
            totalNutrients = totalNutrients + avrageDailyNutrients.get(key);
        }

        HashMap<String, Float> percentageDailyNutrients = new HashMap<>();

        for (String key : avrageDailyNutrients.keySet()) {
            percentageDailyNutrients.put(key, (avrageDailyNutrients.get(key) / totalNutrients) * 100);
        }

        return percentageDailyNutrients;
    }

    //kkkkkkkkkdddddd
    public void addDietLog(String name, String foodGroup, int dateTime, int userid) {
        DietLogEntry dietLog = new DietLogEntry(0, name, foodGroup, dateTime, userid);
        dietTable.setDietLog(dietLog);
    }

    public void addMealIngredients(int mealId, int ingredientId, int quantity) {
        dietTable.setMealIngredients(mealId, ingredientId, quantity);
    }
//    kkkdsssmdmsmevnxnv

}
