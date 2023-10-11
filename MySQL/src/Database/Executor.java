package Database;

import userData.ExerciseLog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Joshua Little
 * Abstract class handles all Database Queries
 * Uses Template Method Design Pattern to returned objects
 *Overloaded methods connect to fake database for testing
 * Todo: built fake database and test methods,
 * Todo: build rest of executor methods
 *
 *
 */
abstract class Executor {


    // public abstract void execute(String sql);
    //build object is a submethod of execute
    //returns any object
    <T> ArrayList<T> processRequest(String sql, Connection connection){
        try {
            //execute request
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(sql);
            //build object

            ArrayList<T> ret = buildObjects(resultSet);


            resultSet.close();
            statement.close();


            return ret;

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

    }


    void processUpdate(String sql, Connection connection) {
        try {
            Statement statement = connection.createStatement();
            int success = statement.executeUpdate(sql);
            if (success == 0) {
                throw new RuntimeException("Update failed");
            }

            statement.close();

        } catch (Exception exception) {
            System.out.println(exception);
        }


    }


    abstract <T> ArrayList<T> buildObjects(ResultSet resultSet) throws SQLException;



    static class ExerciseExecutor extends Executor {
        <T> ArrayList<T> buildObjects(ResultSet resultSet) throws SQLException {
            ArrayList<T> exercises = new ArrayList<>();
            int startTime;
            int endTime;
            int duration;
            String intensity;
            String Exercise;
            int met;
            int metid;
            int id;

            while (resultSet.next()) {
                id= resultSet.getInt("exercise_log.id");
                startTime = resultSet.getInt("starttime");
                endTime = resultSet.getInt("endtime");
                duration = endTime-startTime;
                intensity = resultSet.getString("intensity");
                Exercise = resultSet.getString("exercise_type");
                met = resultSet.getInt("met");
                metid= resultSet.getInt("met.id");

                ExerciseLog temp = new ExerciseLog(id,startTime, duration, Exercise, intensity, met,metid);
                exercises.add((T) temp);
            }

            return exercises;
        }
    }


    static class SettingsExecutor {
    }

    static class UserExecutor {

    }

    static class DietLogExecutor {
    }
}

