package Controller.ExerciseLogic;
import java.util.ArrayList;
import java.util.HashMap;

public interface IExerciseLogic {
//    public Command
//todo:calories

    public ArrayList<HashMap> getExerciseByDateRange(int userId, ArrayList<Integer> startDate, ArrayList<Integer> endDate);
    public void deleteExerciseLog(int userId,int logId) throws Exception;
    public void addExerciseLog(ArrayList<Integer> startDate, ArrayList<Integer> endDate,String Exercise,String Intensity,int userId) throws Exception;
    public ArrayList<String> getExerciseOptions();
    public ArrayList<String> getIntensityOptions(String exercise);
    public int predictFatLoss(int userId,ArrayList<Integer> predictionDate) throws Exception;
}