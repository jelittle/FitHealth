package Database;

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
    <T> ArrayList<T> getObjectListByUserId(String table , int id) {
        return switch (table) {
            case "user" -> userTable.getByUserId(id);
            case "exercise" -> exerciseTable.getByUserId(id);
            case "diet" -> dietTable.getByUserId(id);
            default -> throw new IllegalArgumentException("Table not found");
            };
        }
        ArrayList<?> getTable(String table){
            return switch (table) {
                case "user" -> userTable.getTable();
                case "exercise" -> exerciseTable.getTable();
                case "diet" ->  dietTable.getTable();
                case "met" -> metTable.getTable();
                default -> throw new IllegalArgumentException("Table not found");
            };
        }

        Object getTableEntityById(String table, int id){
            return switch (table) {
                case "user" -> userTable.getById(id);
                case "exercise" -> exerciseTable.getById(id);
                case "diet" -> dietTable.getById(id);
                case "met" -> metTable.getById(id);
                default -> throw new IllegalArgumentException("Table not found");
            };
        }

    }














