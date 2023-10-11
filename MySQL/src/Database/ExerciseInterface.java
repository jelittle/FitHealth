package Database;
import userData.ExerciseLog;

import java.util.ArrayList;

/**
 * Created by Joshua Little
 * Interface between anything requiring ExerciseLog objects within the Database
 */
public class ExerciseInterface {
    static Manager manager;
    public ExerciseInterface(){
        if(manager == null)
            manager = new Manager();
    }

    public ExerciseLog getExerciseLogById(int id){
        ArrayList<ExerciseLog> array= manager.getRecordsSql("exercise_log","SELECT * FROM exercise_log JOIN met ON exercise_log.metid = met.id WHERE exercise_log.id = " + id);

       //impossible to get more than 1 with id
        return array.get(0);


    }
    public ArrayList<ExerciseLog> getExerciseLogsByDateRangeAndUserId(long startDate, long EndDate, int userId){
        ArrayList<ExerciseLog> array= manager.getRecordsSql("exercise_log","SELECT * FROM exercise_log JOIN met ON exercise_log.metid = met.id WHERE exercise_log.starttime >= " + startDate + " AND exercise_log.endtime <= " + EndDate
                +" AND userid="+userId);
        return array;
    }

    public boolean InsertExerciseLog(ExerciseLog exerciseLog){
        String[] columns = {"userid", "starttime", "endtime", "metid"};
        String[] values = {Integer.toString(exerciseLog.getUserId()), Long.toString(exerciseLog.getStartTime()), Long.toString(exerciseLog.getEndTime()), Integer.toString(exerciseLog.getMetId())};

        return manager.insertRecord("exercise_log", columns, values);
    }
    public boolean DeleteExerciseLog(int id){
        return manager.deleteRecord("exercise_log", new String[]{"id = " + id});
    }

}

