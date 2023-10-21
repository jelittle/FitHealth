import Database.IExerciseClientFactory;
import Database.UserTestClient;
import userData.ExerciseLog;
import userData.Met;
import userData.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * logic class for Exercise
 * @version 1.0
 * Will be changed in code refactor, temporarily being used to maintain basic structure
 * interact with this class via ExerciseWindow
 */
public class ExerciseController
{
    private static Database.IExerciseClient exerciseTable;
    private static Database.UserTestClient userTable;
    private  ArrayList<ExerciseLog> activeExercises;
    private int startUnixTime=0;
    private int endUnixTime=0;
    private  User client;



    public ExerciseController()
    {
        //connect
        exerciseTable= IExerciseClientFactory.getIExerciseClient(true);
        //ideally wont be in next version, will have userid from login
        userTable= new UserTestClient();
        getPresetUser();

    }
    /**
     * get userlist for terminal,wont be in final version
     * @return array of users and names
     */
    private void getPresetUser(){
       client =userTable.getUserById(2);

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
    public void setActiveExercisesByDateRange(ArrayList<Integer> startDate, ArrayList<Integer> EndDate){
        //convert array of year month day to unix time

        try{
            int year=startDate.get(0);
            int month=startDate.get(1);
            int day=startDate.get(2);
            int hour=startDate.get(3);
            int minute=startDate.get(4);

            startUnixTime=UnixTime.getUnixTime(year,month,day,hour,minute);

        }catch(Exception e){
            throw new IllegalArgumentException("startDate must be in form year,month,day,hour,minute");
        }

        try{
            int year=EndDate.get(0);
            int month=EndDate.get(1);
            int day=EndDate.get(2);
            int hour=EndDate.get(3);
            int minute=EndDate.get(4);

            endUnixTime=UnixTime.getUnixTime(year,month,day,hour,minute);
        }catch(Exception e){
            throw new IllegalArgumentException("EndDate must be in form year,month,day,hour,minute");
        }
//        get exercise log from db

        activeExercises=exerciseTable.getExerciseLogsByDateRangeAndUserId(startUnixTime,endUnixTime,client.getID());

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
     public ArrayList<Integer> getActiveExerciseCalories(){
         if(activeExercises==null) {
             throw new IllegalArgumentException("activeExercises must be set before calling this function");
         }
        ArrayList<Integer> caloriesBurned = new ArrayList<>();
        for (ExerciseLog exerciseLog : activeExercises) {
            caloriesBurned.add(exerciseLog.getCaloriesBurned());
        }
        return caloriesBurned;
     }
     public ArrayList<Integer> getActiveExerciseDuration(){
         if(activeExercises==null) {
             throw new IllegalArgumentException("activeExercises must be set before calling this function");
         }
        ArrayList<Integer> duration = new ArrayList<>();
        for (ExerciseLog exerciseLog : activeExercises) {
            duration.add(exerciseLog.getDuration());
        }
        return duration;
     }
     public ArrayList<String> getExerciseIntensity(){
         if(activeExercises==null) {
             throw new IllegalArgumentException("activeExercises must be set before calling this function");
         }
        ArrayList<String> intensity = new ArrayList<>();
        for (ExerciseLog exerciseLog : activeExercises) {
            intensity.add(exerciseLog.getIntensity());
        }
        return intensity;
     }
     public ArrayList<Integer> getActiveExerciseStartTimes(){
         if(activeExercises==null) {
             throw new IllegalArgumentException("activeExercises must be set before calling this function");
         }
        ArrayList<Integer> startTimes = new ArrayList<>();
        for (ExerciseLog exerciseLog : activeExercises) {
            startTimes.add(exerciseLog.getStartTime());
        }
        return startTimes;
     }
     ArrayList<String> getExerciseTypes(){

         ArrayList<Met> met= exerciseTable.getMetTable();
            ArrayList<String> exerciseTypes = new ArrayList<>();
            for (Met m : met) {
                //if not already in the table
                if (!exerciseTypes.contains(m.exercise()))
                    exerciseTypes.add(m.exercise());
            }
         return exerciseTypes;
     }
     public int getMetId(String exerciseName, String intensity) {
         ArrayList<Met> met = exerciseTable.getMetTable();
         for (Met m : met) {
             if (m.exercise().equals(exerciseName) && m.intensity().equals(intensity)) {
                 return m.id();
             }
         }
         return 0;
     }
     public void addExerciseLog(ArrayList<Integer> startTime, int metId,ArrayList<Integer> endTime){
            int sTime=UnixTime.getUnixTime(startTime);
            int eTime=UnixTime.getUnixTime(endTime);
            System.out.println(sTime);
            System.out.println(eTime);
            System.out.println("metId");
            System.out.println(metId);
            ExerciseLog exerciseLog = new ExerciseLog(0,sTime,eTime,metId,client.getID());

            System.out.println(exerciseTable.InsertExerciseLog(exerciseLog));
        }

    /**
     * deletes exercise log by id if log is owned by user
     * @param id
     * @return
     */
    public boolean DeleteExerciseLog(int id){
        try {
            if (!checkValidId(id)){
            return false;
            }

            exerciseTable.DeleteExerciseLog(exerciseTable.getExerciseLogById(id));
        }catch(Exception e){
            throw new IllegalArgumentException("ExerciseId not found");
        }
        return true;

    }

    /**
     * checks if given id is owned by user
     * @param id
     * @return
     */
    private boolean checkValidId(int id) {

        return exerciseTable.getExerciseLogById(id).getUserId() == client.getID();
    }

    public void getGraph() {
        System.out.println(activeExercises.size());
        ExerciseVisualization graph = new ExerciseVisualization();
        ArrayList<Integer> intList = new ArrayList<>();
        ArrayList<Integer> Calories = new ArrayList<>();
        int earliest=0;
        int latest=0;
        for (ExerciseLog exerciseLog : activeExercises) {
            if (exerciseLog.getStartTime() < earliest || earliest == 0) {
                earliest = exerciseLog.getStartTime();
            }
            if (exerciseLog.getStartTime() > latest || latest == 0) {
                latest = exerciseLog.getStartTime();
            }
            if(intList.isEmpty()){
                intList.add(exerciseLog.getStartTime());
                Calories.add(exerciseLog.getCaloriesBurned());
            }
            for(int i = 0; i < intList.size(); i++) {

                if(UnixTime.unixIsSameDate(intList.get(i), exerciseLog.getStartTime())){
                    Calories.set(i, Calories.get(i) + exerciseLog.getCaloriesBurned());

                }

            }




        }

        for(int i=0;i<intList.size();i++){
            System.out.println(intList.get(i));
            System.out.println(Calories.get(i));
            graph.addData(new Date((long)intList.get(i)*1000), Calories.get(i));


        }

        graph.plotExerciseData(new Date((long)earliest*1000), new Date((long)(latest*1000)+86400000L));

    }
}
