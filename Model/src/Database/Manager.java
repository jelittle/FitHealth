package Database;

import java.sql.Connection;
import java.util.ArrayList;


/**
 * Created by Joshua Little
 * Manages connections and builds sql statements for the Executor execute.
 * used by Interface classes to get data from the database
 * if test is true, executor will connect to test executor with fakemethods
 * abstract class allows pseudo Bridge pattern, limited by executor being more work than building directly connecting with testdatabase
 * Todo:
 * - add batch methods?
 * - add more error handling, if connection fails, print in console and swap to overloaded test methods.
 *
 */
abstract class DatabaseManager{
    abstract <T> T getRecord(String table, String[] columns, String[] conditions);
    abstract <T> ArrayList<T> getRecords(String table, String[] columns, String[] conditions);
    abstract void updateRecord(String table, String[] columns, String[] Values, String[] conditions);
    abstract boolean deleteRecord(String table, String[] conditions);
    abstract boolean insertRecord(String table, String[] columns, String[] Values );


}
class Manager extends DatabaseManager {
    private static Connection connection;
    private static final boolean test = false;

    public Manager(){
        if(connection ==null) {
            Connector connector = new Connector();
            connection = connector.connect();
        }
    }
     <T> T getRecord(String table, String[] columns, String[] conditions){
        //create sql statement using tables, columns, and conditions
        Executor executor= getExecutor(table);

        String sql = QueryBuilder.sqlSelectBuilder(table, columns, conditions);

        ArrayList<T> Array=executor.processRequest(sql, connection);

        if(Array.size()>1){
            throw new RuntimeException("Too many records returned");
        }
        if(Array.size()==0){
            return null;
        }

        return Array.get(0);
    }

     <T> ArrayList<T> getRecords(String table, String[] columns, String[] conditions){
        Executor executor= getExecutor(table);

        String sql = QueryBuilder.sqlSelectBuilder(table, columns, conditions);

        return executor.processRequest(sql, connection);
    }
     <T> ArrayList<T>  getRecordsSql(String table, String sql){
        Executor executor= getExecutor(table);


        return executor.processRequest(sql, connection);
    }
    void updateRecord(String table, String[] columns, String[] Values, String[] conditions){
        Executor executor= getExecutor(table);

        String sql = QueryBuilder.sqlUpdateBuilder(table, columns, Values, conditions);
        executor.processUpdate(sql, connection);

    }
    boolean deleteRecord(String table, String[] conditions){
        Executor executor= getExecutor(table);

        String sql = QueryBuilder.sqlDeleteBuilder(table, conditions);
        try {
            executor.processUpdate(sql, connection);
        }catch (Exception e){
            return false;
        }
        return true;

    }
    boolean insertRecord(String table, String[] columns, String[] Values ){
        Executor executor= getExecutor(table);

        String sql = QueryBuilder.sqlInsertBuilder(table, columns, Values);

        try {
            executor.processUpdate(sql, connection);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return true;

    }

    //finds the correct executor for the columns
    private Executor getExecutor(String table){
        switch (table){
            case "user":
                return new Executor.UserExecutor();
            case "diet_log":
                return new Executor.DietLogExecutor();
            case "exercise_log":
                return new Executor.ExerciseExecutor();
            case "settings":
                return new Executor.SettingsExecutor();
            case "met":
                return new Executor.MetExecutor();
            case "Ingredient":
                return new Executor.IngredientExecutor();
            case "MealIngredient":
                return new Executor.MealIngredientExecutor();
            case "NutrientInfo":
                return new Executor.NutrientInfoExecutor();
            default:
                return null;
        }
    }


}

