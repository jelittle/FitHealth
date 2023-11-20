package Controller.ExerciseLogic;

import Database.IExerciseClient;
import Database.IExerciseClientFactory;
import ExerciseLogs.ExerciseLog;
import ExerciseLogs.Met;

import java.util.ArrayList;

/**
 * dataManager class manages data for the exercise logic class
 * it is a singleton class
 * uses connector classes to talk to the database.
 */
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
    private boolean checkValidId(int Exerciseid,int userId) {

        return exerciseTable.getExerciseLogById(Exerciseid).getUserId() == userId;
    }
    ArrayList<ExerciseLog> getExerciseLogbyDateRangeandUserId(int startUnixTime,int endUnixTime,int UserId) {
       ArrayList<ExerciseLog> logs= exerciseTable.getExerciseLogsByDateRangeAndUserId(startUnixTime,endUnixTime,UserId);

       for (ExerciseLog log: logs) {
            log.setMet(exerciseTable.getMetById(log.getMetId()));
        }
       return logs;
    }
    void DeleteExerciseLog(int exerciseId,int userId) throws IllegalArgumentException{

            if (!checkValidId(exerciseId,userId)){
                throw new IllegalArgumentException("ExerciseId and userId do not relate.");
            }


            exerciseTable.DeleteExerciseLog(exerciseTable.getExerciseLogById(exerciseId));



    }

     void addExerciseLog(ExerciseLog exerciseLog) throws IllegalArgumentException {
        int id = exerciseTable.InsertExerciseLog(exerciseLog);
        if (id == 0) {
            throw new IllegalArgumentException("ExerciseLog not added");

        }
    }
    ArrayList<String> getExerciseList(){
        return exerciseTable.getMetExercises();
    }

    ArrayList<String> getIntensityList(String exercise){
        return exerciseTable.getMetIntensities(exercise);
    }

    public Met getMetId(String exercise, String intensity) {
        return exerciseTable.getMetbyExerciseAndIntensity(exercise,intensity);
    }
}
