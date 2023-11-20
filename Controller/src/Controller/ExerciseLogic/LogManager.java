package Controller.ExerciseLogic;

import ExerciseLogs.ExerciseLog;

import java.util.ArrayList;
import java.util.HashMap;

//create singleton class
/**
 * Created Joshua Little on 15/11/2023
 * Description:
 * singleton class managess Exerciselogs for the 'session'
 * in a real world scenario, this would probably be held by a session object
 */
public class LogManager {
    private static LogManager instance = null;
    private static DataManager dataManager = DataManager.getInstance();
    private LogManager() {
    }
    public static LogManager getInstance() {
        if (instance == null) {
            instance = new LogManager();
        }
        return instance;
    }
    public ArrayList<HashMap> getExerciseLogbyDateRange(int userId, int startTime, int endTime){

        ArrayList<ExerciseLog> logs =dataManager.getExerciseLogbyDateRangeandUserId(startTime, endTime, userId);
        ArrayList<HashMap> returnList = new ArrayList<>();

        for (ExerciseLog log: logs) {
            HashMap<String,String> hash =new HashMap<>();
            hash.put("Id",Integer.toString(log.getId()));
            hash.put("caloriesBurned",Integer.toString(log.getCaloriesBurned()));
            hash.put("exerciseType",log.getMet().getExercise());
            hash.put("intensity",log.getIntensity());
            hash.put("startTime",Integer.toString(log.getStartTime()));
            hash.put("endTime",Integer.toString(log.getEndTime()));
            returnList.add(hash);
        }


        return returnList;
    }

}

