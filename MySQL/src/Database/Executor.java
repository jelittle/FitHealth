package Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Joshua Little
 * Abstract class handles all Database Queries
 * Uses Template Method Design Pattern to returned objects
 *
 */
abstract class Executor {


    // public abstract void execute(String sql);
    //build object is a submethod of execute
    //returns any object
    <T> T processRequest(String sql, Connection connection){
        try {
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }

//            int code;
//            String title;
//            while (resultSet.next()) {
//                code = resultSet.getInt("id");
//                title = resultSet.getString("firstname").trim();
//                System.out.println("Name : " + code
//                        + "\nTitle : " + title);
//            }
            resultSet.close();
            statement.close();
//            connection.close();


            //return buildObjects(resultSet);
            return null;
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }
    int processInsert(String sql,Statement statement, Connection connection){
     return 0;
    }
    boolean processUpdate(String sql,Statement statement, Connection connection){
     return false;
    }




    abstract <T> ArrayList<T> buildObjects(ResultSet resultSet) throws SQLException;


    public static class ExerciseExecutor extends Executor {
        <T> ArrayList<T> buildObjects(ResultSet resultSet) throws SQLException {
            ArrayList<T> exercises = new ArrayList<>();
            int startTime;
            int endTime;
            int duration;
            while (resultSet.next()) {
                startTime = resultSet.getInt("starttime");
                endTime = resultSet.getInt("endtime");
                duration=startTime-endTime;

            }
        return null;
        }
    }

    public static class SettingsExecutor{
    }

    public static class UserExecutor{

    }

    public static class DietLogExecutor {
    }
}
