import java.sql.*;
import Database.Connector;
import Database.Manager;

public class Main {
    private static Connection connection=null;
    public static void main(String arg[])
    {
        try {

//            Connector connector = new Connector();
//            connection = connector.connect();

//            Statement statement;
//            statement = connection.createStatement();
//            ResultSet resultSet;
//            resultSet = statement.executeQuery(
//                    "select * from user");
//            int code;
//            String title;
//            while (resultSet.next()) {
//                code = resultSet.getInt("id");
//                title = resultSet.getString("firstname").trim();
//                System.out.println("Name : " + code
//                        + "\nTitle : " + title);
//            }
//            resultSet.close();
//            statement.close();
//            connection.close();
            Manager manager = new Manager();
            manager.getRecord("exercise_log", new String[]{"*"}, new String[]{});
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }


    // function ends
} // class ends