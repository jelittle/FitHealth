import Database.IExerciseClientFactory;
import Database.UserClient;
import userData.ExerciseLog;
import userData.User;

import java.util.ArrayList;

/**
 * logic class for Exercise
 * @version 1.0
 * Will be changed in code refactor, temporarily being used to maintain basic structure
 * interact with this class via ExerciseWindow
 */
public class ExerciseController
{
    private static Database.IExerciseClient exerciseTable;
    private static Database.UserClient userTable;
    private  ArrayList<ExerciseLog> activeExercises;
    private  User client;



    public ExerciseController()
    {
        //connect
        exerciseTable= IExerciseClientFactory.getIExerciseClient(true);
        //ideally wont be in next version, will have userid from login
        userTable= new UserClient();
        getPresetUser();
    }
    /**
     * get userlist for terminal,wont be in final version
     * @return array of users and names
     */
    private void getPresetUser(){
       client =userTable.getUserById(1);


    }

    public String getClientName(){
        return client.getName();
    }

    /**
     * set exercise log for a user by date range
     * Arraylist should be in form, year,month,day,hour,minute
     * @param startDate
     * @param EndDate
     */
    private void setActiveExercisesByDateRange(ArrayList<Integer> startDate, ArrayList<Integer> EndDate){
        //convert array of year month day to unix time
        int startUnixTime=0;
        try{
            int year=startDate.get(0);
            int month=startDate.get(1);
            int day=startDate.get(2);
            int hour=startDate.get(3);
            int minute=startDate.get(4);

            startUnixTime=getUnixTime.getUnixTime(year,month,day,hour,minute);
        }catch(Exception e){
            throw new IllegalArgumentException("startDate must be in form year,month,day,hour,minute");
        }
        int endUnixTime=0;

        try{
            int year=EndDate.get(0);
            int month=EndDate.get(1);
            int day=EndDate.get(2);
            int hour=EndDate.get(3);
            int minute=EndDate.get(4);

            endUnixTime=getUnixTime.getUnixTime(year,month,day,hour,minute);
        }catch(Exception e){
            throw new IllegalArgumentException("EndDate must be in form year,month,day,hour,minute");
        }
        //get exercise log from db

        activeExercises=exerciseTable.getExerciseLogsByDateRangeAndUserId(client.getID(),startUnixTime,endUnixTime);
    }

    /**
     * these functions were thrown up quickly to work, not final code
     * returns id's for all exercises
     * @returns Exercises
     */
    public ArrayList<Integer> getActiveExerciseIds() {
        if(activeExercises==null) {
            throw new IllegalArgumentException("activeExercises must be set before calling this function");
        }
        ArrayList<Integer> exercises = new ArrayList<>();
        for (ExerciseLog exerciseLog : activeExercises) {
            exercises.add(exerciseLog.getId());
        }
        return exercises;
    }
    /**
     * these functions were thrown up quickly to work, not final code
     * return name for all active Exercises
     * @returns ExerciseNames
     *
     */
    public ArrayList<String> getActiveExerciseNames() {
        if(activeExercises==null) {
            throw new IllegalArgumentException("activeExercises must be set before calling this function");
        }
        ArrayList<String> exercises = new ArrayList<>();
        for (ExerciseLog exerciseLog : activeExercises) {
            exercises.add(exerciseLog.getExercise());
        }
        return exercises;
    }
    /**
     * same as above
     * calculates calories burned for all active exercises
     * @returns calories burned
     */
     public ArrayList<Integer> getCaloriesBurned(){
         if(activeExercises==null) {
             throw new IllegalArgumentException("activeExercises must be set before calling this function");
         }
        ArrayList<Integer> caloriesBurned = new ArrayList<>();
        for (ExerciseLog exerciseLog : activeExercises) {
            caloriesBurned.add(exerciseLog.getCaloriesBurned());
        }
        return caloriesBurned;
     }
     public ArrayList<Integer> getDuration(){
         if(activeExercises==null) {
             throw new IllegalArgumentException("activeExercises must be set before calling this function");
         }
        ArrayList<Integer> duration = new ArrayList<>();
        for (ExerciseLog exerciseLog : activeExercises) {
            duration.add(exerciseLog.getDuration());
        }
        return duration;
     }
     public ArrayList<String> getExerciseIntesity(){
         if(activeExercises==null) {
             throw new IllegalArgumentException("activeExercises must be set before calling this function");
         }
        ArrayList<String> intensity = new ArrayList<>();
        for (ExerciseLog exerciseLog : activeExercises) {
            intensity.add(exerciseLog.getIntensity());
        }
        return intensity;
     }



}
