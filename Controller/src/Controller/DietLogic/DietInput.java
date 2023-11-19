package Controller.DietLogic;

import Controller.UnixTime;
import DietLogs.*;

import java.util.ArrayList;

public class DietInput {

    private DietDataManager db = DietDataManager.getInstance();

    public void addDietLog(String mealName, String mealType, int dateTime, int userId) throws Exception {

        if (!isMealTypeValid(mealType)) {
            throw new Exception("Invalid meal type");
        }
        DietLogEntry dietLogEntry = new DietLogEntry(0, mealName, mealType, dateTime, userId);
        db.addDietLog(dietLogEntry);
    }

    public boolean isDateValid(ArrayList<Integer> dateTime) {

        if (dateTime.size() != 5) {
            return false;
        }
        int year = dateTime.get(0);
        int month = dateTime.get(1);
        int day = dateTime.get(2);
        int hour = dateTime.get(3);
        int minute = dateTime.get(4);

        if (!isInRange(year, 1900, 2200) ||
                !isInRange(month, 1, 12) ||
                !isInRange(day, 1, 31) ||
                !isInRange(hour, 0, 23) ||
                !isInRange(minute, 0, 59)) {
            return false;
        }

        return true;
    }

    private static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public boolean isMealTypeValid(String mealType) {
        return mealType.equals("Breakfast") || mealType.equals("Lunch") || mealType.equals("Dinner") || mealType.equals("Snack");
    }

    public int fromArrayListToUnixTime(ArrayList<Integer> dateTime) {

        int dateUnixTime;

        try{
            int year=dateTime.get(0);
            int month=dateTime.get(1);
            int day=dateTime.get(2);
            int hour=dateTime.get(3);
            int minute=dateTime.get(4);

            dateUnixTime= UnixTime.getUnixTime(year,month,day,hour,minute);

        }catch(Exception e){
            throw new IllegalArgumentException("startDate must be in form year,month,day,hour,minute");
        }

        return dateUnixTime;
    }

    public void addMealIngredients(int mealId, int ingredientId, float quantity) throws Exception {
        MealIngredients mealIngredients = new MealIngredients(mealId, ingredientId, quantity);
        db.addMealIngredients(mealIngredients);
    }

    public int totalDays(ArrayList<Integer> startDate, ArrayList<Integer> EndDate) {


        int startYear = startDate.get(0);
        int startMonth = startDate.get(1);
        int startDay = startDate.get(2);

        int endYear = EndDate.get(0);
        int endMonth = EndDate.get(1);
        int endDay = EndDate.get(2);

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


}
