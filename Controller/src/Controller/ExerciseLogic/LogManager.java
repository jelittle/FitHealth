package Controller.ExerciseLogic;

import ExerciseLogs.ExerciseLog;

import java.util.ArrayList;

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
    public ArrayList<ExerciseLog> getExerciseLogbyDateRange() {
        return null;
    }
}

