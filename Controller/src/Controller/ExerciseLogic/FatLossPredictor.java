package Controller.ExerciseLogic;

import Controller.UnixTime;
import ExerciseLogs.ExerciseLog;
import userData.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * class handles fat loss predictions
 *
 */
class FatLossPredictor {
    private DataManager dataManager;
    FatLossPredictor() {
        dataManager = DataManager.getInstance();

    }
    /**
     * predicts fat loss for a given user, based on past 6 months of logs
     * @param userId
     * @param predictionDate
     * @return
     */
    public int predictFatLoss(int userId, int predictionDate ) throws Exception {
        ArrayList<Integer> endTime = getToday();
        //hard copy end time to start time
        ArrayList<Integer> startTime = new ArrayList<>();
        for (int i = 0; i < endTime.size(); i++) {
            startTime.add(endTime.get(i));
        }
        //set start time to 6 months ago
        if (startTime.get(1) < 6) {
            startTime.set(1, startTime.get(1) + 12);
            startTime.set(0, startTime.get(0) - 1);
        }
        startTime.set(1, startTime.get(1) - 6);
            //get variables
            float calories = getaverageCalories(userId, startTime, endTime);
            float burned = getaverageBurnedCalories(userId, startTime, endTime);
            float bmr = getBmr(userId);
            //find difference between between prediction date and today
            int diff = predictionDate - UnixTime.getUnixTime(getToday());
            //convert unix duration(end-start) to days
            //conditional, if diff is less than 1 day, set to 1 day
            if(diff<86400){
                diff=86400;
            }
            diff = diff / 86400;

            float DailyChange= (calories - burned) - bmr;

            //calculate fat loss
            float fatLoss = (DailyChange * diff) / 7700;//equation from use case 6

            return (int) fatLoss;
    }

    private float getaverageCalories(int userId, ArrayList<Integer> startTime, ArrayList<Integer> endTime ) throws IllegalArgumentException {

        HashMap<String,Float> averageCalories = dataManager.getNutritionLogbyDateRangeandUserId(startTime,endTime,userId);
        try {

            return averageCalories.get("Calories");
        }
        catch (Exception e){
            throw new IllegalArgumentException("No Meals Logged");
        }

    }
    /**
     * gets average calories burned per Day for bmr calculations
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    private float getaverageBurnedCalories(int userId, ArrayList<Integer> startTime, ArrayList<Integer> endTime ) {
        ArrayList<ExerciseLog> logs= dataManager.getExerciseLogbyDateRangeandUserId(UnixTime.getUnixTime(startTime), UnixTime.getUnixTime(endTime), userId);

        float burned=0;
        int start=0;
        int end=0;
        for (ExerciseLog log: logs) {
            if (log.getStartTime()<start){
                start=log.getStartTime();
            }
            if (log.getEndTime()>end){
                end=log.getEndTime();
            }
            burned+= (float) (log.getCaloriesBurned());
        }
        //convert unix duration(end-start) to days
        System.out.println("start: "+start);
        System.out.println("end: "+end);
        int diff= end-start;
        if(diff<86400){
            diff=86400;
        }


        burned= burned/((float) diff /86400);
        return burned;
    }

    /**
     * gets today's date
     * @return
     */
    private ArrayList<Integer> getToday(){
        ArrayList<Integer> today= new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        today.add(calendar.get(Calendar.YEAR));
        today.add(calendar.get(Calendar.MONTH)+1);
        today.add(calendar.get(Calendar.DAY_OF_MONTH)+1);
        today.add(0);
        today.add(0);
        return today;
    }
    private float getBmr(int userId){
        User user=dataManager.getUserById(userId);
        return user.getBMR();
    }

}
