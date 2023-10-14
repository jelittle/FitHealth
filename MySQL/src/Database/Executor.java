package Database;

import userData.ExerciseLog;
import userData.User;

import java.sql.*;
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
            float met;
            int metid;
            int id;
            int userId;

            while (resultSet.next()) {
                id= resultSet.getInt("exercise_log.id");
                startTime = resultSet.getInt("starttime");
                endTime = resultSet.getInt("endtime");
                intensity = resultSet.getString("intensity");
                Exercise = resultSet.getString("exercise_type");
                met = resultSet.getFloat("met");
                metid= resultSet.getInt("met.id");
                userId= resultSet.getInt("exercise_log.userid");

                ExerciseLog temp = new ExerciseLog(id,startTime, endTime, Exercise, intensity, met,metid,userId);
                exercises.add((T) temp);
            }

            return exercises;
        }
    }


    static class SettingsExecutor extends Executor {
        @Override
        <T> ArrayList<T> buildObjects(ResultSet resultSet) throws SQLException {
            return null;
        }
    }

    static class UserExecutor extends Executor {

        @Override
        <T> ArrayList<T> buildObjects(ResultSet resultSet) throws SQLException {
            ArrayList<T> users = new ArrayList<>();

            String name;
            String password;
            int height;
            float weight;
            int age;
            int id;
            while(resultSet.next()) {

                name = resultSet.getString("username");
                password = resultSet.getString("password");
                height = resultSet.getInt("height");
                weight = resultSet.getFloat("weight");
                age = resultSet.getInt("age");
                id = resultSet.getInt("id");

                User temp = new User(name, password, height, weight, age, id);
                users.add((T) temp);
            }

            // Your existing code...

            return users;
        }
    }


    static class DietLogExecutor extends Executor {
        @Override
        <T> ArrayList<T> buildObjects(ResultSet resultSet) throws SQLException {
            return null;
        }
    }
}

