package Controller.DietLogic;

import DietLogs.DietLogEntry;

import java.util.ArrayList;

public class ActiveMealLogs {

    private DietDataManager db = DietDataManager.getInstance();

    private ArrayList<DietLogEntry> ActiveddietLogs = new ArrayList<>();

    public ArrayList<DietLogEntry> GetActiveDietLogsByDateRangeAndUserId(int startDate, int EndDate, int userId){
        ActiveddietLogs =db.getDietLogbyDateRangeandUserId(startDate, EndDate, userId);
        return ActiveddietLogs;
    }

    public void addDietLog(DietLogEntry dietLogEntry){
        db.addDietLog(dietLogEntry);
    }

    public void deleteDietLog(DietLogEntry dietLogEntry){
        db.deleteDietLog(dietLogEntry);
    }


    public ArrayList<DietLogEntry> getActiveddietLogs() {
        return ActiveddietLogs;
    }

}
