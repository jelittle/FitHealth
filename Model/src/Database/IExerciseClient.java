package Database;

import ExerciseLogs.ExerciseLog;
import ExerciseLogs.Met;
import userData.User;

import java.util.ArrayList;

/**
 * Created by Joshua Little
 * Interface between anything requiring ExerciseLog objects within the Database
 */

public interface IExerciseClient {
    ExerciseLog getExerciseLogById(int id);

    ArrayList<ExerciseLog> getExerciseLogsByDateRangeAndUserId(long startDate, long EndDate, int userId);

    int InsertExerciseLog(ExerciseLog exerciseLog);

    void DeleteExerciseLog(ExerciseLog exerciseLog);

    public Met getMetById(int id);

    ArrayList<Met> getMetTable();
}
class ExerciseLogClient implements IExerciseClient {
        static Manager manager;

        public ExerciseLogClient() {
            if (manager == null)
                manager = new Manager();
        }

        public ExerciseLog getExerciseLogById(int id) {

            ExerciseLog exerciseLog = manager.getRecord("exercise_log", null, new String[]{"id = " + id});
            //impossible to get more than 1 with id
            exerciseLog.setUserWeight(manager.getRecord("user", null, new String[]{"id = " + exerciseLog.getUserId()}));
            exerciseLog.setMet(manager.getRecord("met", null, new String[]{"id = " + exerciseLog.getMetId()}));

            return exerciseLog;


        }

        public ArrayList<ExerciseLog> getExerciseLogsByDateRangeAndUserId(long startDate, long EndDate, int userId) {
            ArrayList<ExerciseLog> array = manager.getRecordsSql("exercise_log", "SELECT * FROM exercise_log JOIN met ON exercise_log.metid = met.id WHERE exercise_log.starttime >= " + startDate + " AND exercise_log.endtime <= " + EndDate
                    + " AND userid=" + userId);

            User user =manager.getRecord("user", null, new String[]{"id = " + userId});
            float weight=user.getWeight();
            for(ExerciseLog e: array){
                e.setUserWeight(weight);
                e.setMet(manager.getRecord("met", null, new String[]{"id = " + e.getMetId()}));
            }
            return array;
        }

        public int InsertExerciseLog(ExerciseLog exerciseLog) {
            String[] columns = {"userid", "starttime", "endtime", "metid"};
            String[] values = {Integer.toString(exerciseLog.getUserId()), Long.toString(exerciseLog.getStartTime()), Long.toString(exerciseLog.getEndTime()), Integer.toString(exerciseLog.getMetId())};
            manager.insertRecord("exercise_log", columns, values);
            //return id of inserted record
            return manager.getRecord("exercise_log", null, new String[]{"userid = " + exerciseLog.getUserId(), "starttime = " + exerciseLog.getStartTime(), "endtime = " + exerciseLog.getEndTime(), "metid = " + exerciseLog.getMetId()});
        }

        public void DeleteExerciseLog(ExerciseLog exerciseLog) {

                manager.deleteRecord("exercise_log", new String[]{"id = " + exerciseLog.getId()});

        }

        /**
         * @param id
         * @return
         */
        @Override
        public Met getMetById(int id) {
            return  manager.getRecord("met", null, new String[]{"id = " + id});

        }

    /**
     * @return
     */
    @Override
    public ArrayList<Met> getMetTable() {
        return null;
    }

}
class ExerciseLogTestClient implements IExerciseClient{
    private static TestDatabase db;
    public ExerciseLogTestClient() {
       if (db == null)
           db = new TestDatabase();
    }

    public    ExerciseLog getExerciseLogById(int id) {
        return (ExerciseLog) db.getTableEntityById("exercise_log", id);
    }

    public ArrayList<ExerciseLog> getExerciseLogsByDateRangeAndUserId(long startDate, long endDate, int userId) {
        ArrayList<Object> array=  db.getObjectListByUserId("exercise_log", userId);
        ArrayList<ExerciseLog> exerciseLogs = new ArrayList<>();
        for(Object o: array){
            ExerciseLog e = (ExerciseLog) o;
            if(e.getStartTime() >= startDate && e.getEndTime() <= endDate)
                e.setMet((Met) db.getTableEntityById("met", e.getMetId()));
                e.setUserWeight(((User) db.getTableEntityById("user", e.getUserId())).getWeight());
                exerciseLogs.add(e);
        }
        return exerciseLogs;

    }

    public int InsertExerciseLog(ExerciseLog exerciseLog) {
        db.InsertTableEntity("exercise_log", exerciseLog);
        ArrayList<ExerciseLog> table= db.getObjectListByUserId("exercise_log", exerciseLog.getUserId());
        return table.get(table.size()-1).getId();

    }

    public void DeleteExerciseLog(ExerciseLog exerciseLog) {
        db.DeleteTableEntity("exercise_log", db.getTableEntityById("exercise_log", exerciseLog.getId()));



    }

    /**
     * @param id
     * @return
     */
    @Override
    public Met getMetById(int id) {
        return null;
    }
    public ArrayList<Met> getMetTable(){
        return (ArrayList<Met>) db.getTable("met");
    }
}




