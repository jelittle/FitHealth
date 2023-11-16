package ExerciseLogs;

import java.util.ArrayList;

public interface IExerciseLog {
    public ArrayList<ExerciseLog> getExerciseLogsByDateRangeAndUserId(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId);
    public void addExerciseLogbyUserId(ExerciseLog exerciseLog,int UserId);
    public void updateExerciseLog(ExerciseLog exerciseLog);
    public void deleteExerciseLog(ExerciseLog exerciseLog);
    
}
