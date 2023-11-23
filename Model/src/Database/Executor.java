package Database;

import DietLogs.Ingredient;
import DietLogs.MealIngredients;
import DietLogs.NutrientInfo;
import ExerciseLogs.ExerciseLog;
import ExerciseLogs.Met;
import userData.*;
import DietLogs.DietLogEntry;

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

            //check if column intensity exists
            try{
                resultSet.findColumn("intensity");}
           catch (SQLException e){
                ArrayList<String> temp = new ArrayList<>();
                while (resultSet.next()) {
                    temp.add( resultSet.getString("exercise_type"));

                }
                return (ArrayList<T>) temp;
            }
            try{resultSet.findColumn("exercise_type"); }catch (SQLException e){

                ArrayList<String> temp = new ArrayList<>();
                while (resultSet.next()) {
                    temp.add( resultSet.getString("intensity"));

                }
                return (ArrayList<T>) temp;
            }
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

            ArrayList<T> dietLogs = new ArrayList<>();
            int Dietid;
            String name;
            String foodGroup;
            int dateTime;
            int userId;

            while (resultSet.next()) {
                Dietid = resultSet.getInt("diet_log.id");
                name = resultSet.getString("name");
                foodGroup = resultSet.getString("food_group");
                dateTime = resultSet.getInt("datetime");
                userId = resultSet.getInt("diet_log.userid");

                DietLogEntry temp = new DietLogEntry(Dietid, name, foodGroup, dateTime, userId);
                dietLogs.add((T) temp);
            }

            return dietLogs;

        }
    }

    static class IngredientExecutor extends Executor {
        @Override
        <T> ArrayList<T> buildObjects(ResultSet resultSet) throws SQLException {
            ArrayList<T> ingredients = new ArrayList<>();

           int ingredientId;
           String ingredientName;
           String foodGroup;

              while (resultSet.next()) {
                ingredientId = resultSet.getInt("id");
                ingredientName = resultSet.getString("name");
                foodGroup = resultSet.getString("food_group");

                Ingredient temp = new Ingredient(ingredientId, ingredientName, foodGroup);
                ingredients.add((T) temp);
              }

                return ingredients;
        }
    }

    static class MealIngredientExecutor extends Executor {
        @Override
        <T> ArrayList<T> buildObjects(ResultSet resultSet) throws SQLException {
            ArrayList<T> mealIngredients = new ArrayList<>();

            int mealId;
            int ingredientId;
            float quantity;

            while (resultSet.next()) {
                mealId = resultSet.getInt("mealid");
                ingredientId = resultSet.getInt("ingredientid");
                quantity = resultSet.getFloat("quantity");

                MealIngredients temp = new MealIngredients(mealId, ingredientId, quantity);
                mealIngredients.add((T) temp);
            }

            return mealIngredients;
        }
    }

    static class NutrientInfoExecutor extends Executor {
        @Override
        <T> ArrayList<T> buildObjects(ResultSet resultSet) throws SQLException {
            ArrayList<T> nutrientInfo = new ArrayList<>();

            int nutrientId;
            int ingredientId;
            String nutrientName;
            float nutrientValue;

            while (resultSet.next()) {
                nutrientId = resultSet.getInt("nutrientid");
                ingredientId = resultSet.getInt("ingredientid");
                nutrientName = resultSet.getString("nutrientname");
                nutrientValue = resultSet.getFloat("nutrientvalue");

                NutrientInfo temp = new NutrientInfo(nutrientId, ingredientId, nutrientName, nutrientValue);
                nutrientInfo.add((T) temp);
            }

            return nutrientInfo;
        }
    }

}

