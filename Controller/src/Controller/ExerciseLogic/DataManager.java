package Controller.ExerciseLogic;

import Database.IExerciseClient;
import Database.IExerciseClientFactory;
import ExerciseLogs.ExerciseLog;

import java.util.ArrayList;

public class DataManager {
    private static DataManager instance = null;
    private static IExerciseClient exerciseTable;
    private DataManager() {
        exerciseTable = IExerciseClientFactory.getIExerciseClient();

    }
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
    ArrayList<ExerciseLog> getExerciseLogbyDateRangeandUserId(int startUnixTime,int endUnixTime,int UserId) {
       return exerciseTable.getExerciseLogsByDateRangeAndUserId(startUnixTime,endUnixTime,UserId);
    }

}
