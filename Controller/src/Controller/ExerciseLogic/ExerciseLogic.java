package Controller.ExerciseLogic;

import Controller.UnixTime;

import java.util.ArrayList;
import java.util.HashMap;


public class ExerciseLogic implements IExerciseLogic{

    public ArrayList<HashMap> getExerciseByDateRange(int userId, ArrayList<Integer> startDate, ArrayList<Integer> endDate) {
        int StartTime= UnixTime.getUnixTime(startDate);
        int EndTime= UnixTime.getUnixTime(endDate);
        System.out.println(StartTime);
        System.out.println(EndTime);

        return null;

    }

    public ArrayList getGraphData(int userId, ArrayList<Integer> startDate, ArrayList<Integer> endDate) {
        return null;
    }

    public void deleteExerciseLog(int userId, int logId) throws Exception {

    }

    public void updateExerciseLog(int userId, int logId) throws Exception {

    }

    public void addExerciseLog(int userId, int logId) throws Exception {

    }

    public ArrayList<String> getExerciseTypes() {
        return null;
    }

    public ArrayList<String> getIntensityOptions(int metId) {
        return null;
    }
    public int predictFatLoss(int userId, ArrayList<Integer> predictionDate) {
        return 0;
    }
}
