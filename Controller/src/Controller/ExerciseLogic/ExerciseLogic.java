package Controller.ExerciseLogic;

import Controller.UnixTime;

import java.util.ArrayList;
import java.util.HashMap;


public class ExerciseLogic implements IExerciseLogic {
    LogManager logManager = LogManager.getInstance();
    ExerciseInput ExerciseInput = new ExerciseInput();

    public ArrayList<HashMap> getExerciseByDateRange(int userId, ArrayList<Integer> startDate, ArrayList<Integer> endDate) {
        return logManager.getExerciseLogbyDateRange(userId, UnixTime.getUnixTime(startDate), UnixTime.getUnixTime(endDate));
    }

    public void deleteExerciseLog(int userId, int logId) throws Exception {

        ExerciseInput.deleteExerciseLog(userId, logId);

    }

    /**
     * @param startDate
     * @param endDate
     * @param Exercise
     * @param Intensity
     * @param userId
     * @throws Exception
     */
    @Override
    public void addExerciseLog(ArrayList<Integer> startDate, ArrayList<Integer> endDate, String Exercise, String Intensity, int userId) throws Exception {
        ExerciseInput.insertExerciseLog(UnixTime.getUnixTime(startDate), UnixTime.getUnixTime(endDate), Exercise, Intensity, userId);
    }


    /**
     * returns a list of exercise types
     *
     * @return ExerciseList
     */
    public ArrayList<String> getExerciseOptions() {
        return ExerciseInput.getExerciseOptions();
    }

    /**
     * Returns the intensity options for a given exercise
     *
     * @param exercise
     * @return
     */
    public ArrayList<String> getIntensityOptions(String exercise) {

        return ExerciseInput.getIntensityOptions(exercise);
    }

    /**
     *function to predict fat loss
     * @param userId
     * @param predictionDate

     * @return
     */
    @Override
    public int predictFatLoss(int userId, ArrayList<Integer> predictionDate) throws Exception {
        FatLossPredictor predictor = new FatLossPredictor();
        return predictor.predictFatLoss(userId, UnixTime.getUnixTime(predictionDate));

    }
}

