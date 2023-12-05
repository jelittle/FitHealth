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

        if (dateTime.size() != 5) {return false;}

        return isInRange(dateTime.get(0), 1900, 2200) &&
                isInRange(dateTime.get(1), 1, 12) &&
                isInRange(dateTime.get(2), 1, 31) &&
                isInRange(dateTime.get(3), 0, 23) &&
                isInRange(dateTime.get(4), 0, 59);
    }

    private static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public boolean isMealTypeValid(String mealType) {
        return mealType.equals("Breakfast") || mealType.equals("Lunch") ||
                mealType.equals("Dinner") || mealType.equals("Snack");
    }


}
