package Database;

import userData.ExerciseLog;
import userData.Met;
import userData.User;

import java.util.ArrayList;

/**
 * Class holds Test Table for testing purposes
 * doesnt replace the actual database, and has limited options in exercise and meal options
 * but is used to test the program
* */
public class TestDatabase {
   private static boolean initialized = false;

    UserTable userTable = new UserTable();
    ExerciseTable exerciseTable = new ExerciseTable();
    DietTable dietTable = new DietTable();
    MetStaticTable metTable = new MetStaticTable();


    public TestDatabase() {
        if (!initialized) {
            userTable.bulkCreate();
            exerciseTable.BulkCreateforUser(1);
            exerciseTable.BulkCreateforUser(2);
            exerciseTable.BulkCreateforUser(3);
            exerciseTable.BulkCreateforUser(4);
            //do user table stuff
            initialized = true;
        }

    }

    /**
     *
     * Todo: check if theres a way to pass functions in java, would simplify this code a lot
     * @param table
     * @param id
     * @return
     * @param <T>
     */
    <T> ArrayList<T> getObjectListByUserId(String table , int id) {
        return switch (table) {
            case  "user"-> userTable.getByUserId(id);
            case "exercise_log" -> exerciseTable.getByUserId(id);
            case "diet_log" -> dietTable.getByUserId(id);
            default -> throw new IllegalArgumentException("Table not found");
            };
        }
        ArrayList<?> getTable(String table){
            return switch (table) {
                case "user" -> userTable.getTable();
                case "exercise_log" -> exerciseTable.getTable();
                case "diet_log" ->  dietTable.getTable();
                case "met" -> metTable.getTable();
                default -> throw new IllegalArgumentException("Table not found");
            };
        }

        Object getTableEntityById(String table, int id){
            return switch (table) {
                case "user"-> userTable.getById(id);
                case "exercise_log" -> exerciseTable.getById(id);
                case "diet_log" -> dietTable.getById(id);
                case "met" -> metTable.getById(id);
                default -> throw new IllegalArgumentException("Table not found");
            };
        }
        void InsertTableEntity(String table, Object object){
            switch (table) {
                case "user" -> userTable.add((User) object);
                case "exercise_log" -> exerciseTable.add((ExerciseLog) object);
                case "diet_log" -> dietTable.add((DietLog) object);
                case "met" -> metTable.add((Met) object);
                default -> throw new IllegalArgumentException("Table not found");
            };
        }
        void UpdateTableEntity(String table, Object object){
            switch (table) {
                case "user" -> userTable.updateTable((User) object);
                case "exercise_log" -> exerciseTable.updateTable((ExerciseLog) object);
                case "diet" -> dietTable.updateTable((DietLog) object);
                case "met" -> metTable.updateTable((Met) object);
                default -> throw new IllegalArgumentException("Table not found");
            };
        }
        void DeleteTableEntity(String table, Object object){
            switch (table) {
                case "user" -> userTable.deleteEntity((User) object);
                case "exercise_log" -> exerciseTable.deleteEntity((ExerciseLog) object);
                case "diet_log" -> dietTable.deleteEntity((DietLog) object);
                case "met" -> metTable.deleteEntity((Met) object);
                default -> throw new IllegalArgumentException("Table not found");
            };
        }

    }














