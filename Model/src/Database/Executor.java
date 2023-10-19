package Database;

import userData.*;
import userData.DietLogEntry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

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


            int metid;
            int id;
            int userId;

            while (resultSet.next()) {
                id= resultSet.getInt("exercise_log.id");
                startTime = resultSet.getInt("starttime");
                endTime = resultSet.getInt("endtime");
                metid= resultSet.getInt("metid");
                userId= resultSet.getInt("exercise_log.userid");

                ExerciseLog temp = new ExerciseLog(id,startTime, endTime,metid,userId);
                exercises.add((T) temp);
            }

            return exercises;
        }
    }
    static class MetExecutor extends Executor {
        @Override
        <T> ArrayList<T> buildObjects(ResultSet resultSet) throws SQLException {
            ArrayList<Met> temp = new ArrayList<Met>();
            while(resultSet.next()){
                String exercise = resultSet.getString("exercise_type");
                String intensity = resultSet.getString("intensity");
                float met = resultSet.getFloat("met");
                int id = resultSet.getInt("id");
                temp.add( new Met(id, exercise,intensity,met));

            }
            return (ArrayList<T>) temp;
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

//            ArrayList<T> dietLogs = new ArrayList<>();
//            int id;
//            int userId;
//            int calories;
//            double proteins;
//            double carbs;
//            Map<VitaminType, Double> vitamins;
//            Map<String, Double> otherNutrients;
//            while(resultSet.next()){
//                id = resultSet.getInt("id");
//                userId = resultSet.getInt("userid");
//                calories = resultSet.getInt("calories");
//                proteins = resultSet.getDouble("proteins");
//                carbs = resultSet.getDouble("carbs");
//                vitamins = (Map<VitaminType, Double>) resultSet.getObject("vitamins");
//                otherNutrients = (Map<String, Double>) resultSet.getObject("othernutrients");
//                NutrientInfo nutrientInfo = new NutrientInfo();
//                nutrientInfo.setCalories(calories);
//                nutrientInfo.setProteins(proteins);
//                nutrientInfo.setCarbs(carbs);
//                nutrientInfo.setVitamins(vitamins);
//                nutrientInfo.setOtherNutrients(otherNutrients);
//                DietLog temp = new DietLog(id, userId, nutrientInfo);
//                dietLogs.add((T) temp);
//            }
//            return dietLogs;
            return null;
        }
    }
}

