package Database;
import userData.ExerciseLog;

import java.util.ArrayList;

/**
 * Created by Joshua Little
 * Interface between anything requiring ExerciseLog objects within the Database
 */

interface ExerciseInterface {
    ExerciseLog getExerciseLogById(int id);
    ArrayList<ExerciseLog> getExerciseLogsByDateRangeAndUserId(long startDate, long EndDate, int userId);
    boolean InsertExerciseLog(ExerciseLog exerciseLog);
    boolean DeleteExerciseLog(int id);
}

