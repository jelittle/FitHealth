package userData;

import Database.IExerciseClient;
import Database.IExerciseClientFactory;

import java.util.ArrayList;

/**
 * class interfaces with the database to get exercise logs
 * manages exercise logs for user class
 * Client class for controller
 *
 *
 *
 */

class ExerciseLogs {
    private  User user;
    private ArrayList<ExerciseLog> exerciseLogs = new ArrayList<>();

    private static final IExerciseClient db = IExerciseClientFactory.getIExerciseClient();

    ExerciseLogs(User user){
        this.user = user;
    }
    ArrayList<ExerciseLog> GetExerciseLogsByDateRangeAndUserId(long startDate, long EndDate, int userId){
        exerciseLogs =db.getExerciseLogsByDateRangeAndUserId(startDate, EndDate, userId);
        return exerciseLogs;

    }
}
