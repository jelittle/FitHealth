package Controller.ExerciseLogic;
import java.util.ArrayList;
import java.util.HashMap;

public interface IExerciseLogic {
//    public Command

    public ArrayList<HashMap> getExerciseByDateRange(int userId, ArrayList<Integer> startDate, ArrayList<Integer> endDate);
    public ArrayList<HashMap> getGraphData(int userId,ArrayList<Integer> startDate, ArrayList<Integer> endDate);
    public void deleteExerciseLog(int userId,int logId) throws Exception;
    public void updateExerciseLog(int userId,int logId) throws Exception;
    public void addExerciseLog(int userId,int logId) throws Exception;
    public ArrayList<String> getExerciseTypes();
    public ArrayList<String> getIntensityOptions(int metId);
    public int predictFatLoss(int userId,ArrayList<Integer> predictionDate);
}