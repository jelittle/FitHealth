package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Manager {
    private Connection connection;
    public Manager(){
        Connector connector = new Connector();
        connection = connector.connect();
    }
    public void getRecord(String Table, String[] Columns, String[] Conditions){
        //create sql statement using table, columns, and conditions
        Executor executor= getExecutor(Table);
        String sql = "SELECT ";

        // Add columns to the SQL statement
        for(int i = 0; i < Columns.length; i++){
            sql += Columns[i];
            if(i < Columns.length - 1){
                sql += ", ";
            }
        }

        // Add table to the SQL statement
        sql += " FROM " + Table;

        // Add conditions to the SQL statement
        if(Conditions.length > 0){
            sql += " WHERE ";
            for(int i = 0; i < Conditions.length; i++){
                sql += Conditions[i];
                if(i < Conditions.length - 1){
                    sql += " AND ";
                }
            }
        }

        executor.processRequest(sql, connection);

        System.out.println(sql);
    }

    void getRecords(){}
    void getRecordsSql(String Table, String sql){



    }
    void updateRecord(){}
    void deleteRecord(){}
    void insertRecord(){}
    //finds the correct executor for the table
    private Executor getExecutor(String Table){
        switch (Table){
//            case "user":
//                return new Executor.UserExecutor();
//            case "diet_log":
//                return new Executor.DietLogExecutor();
            case "exercise_log":
                return new Executor.ExerciseExecutor();
//            case "settings":
//                return new Executor.SettingsExecutor();
            default:
                return null;
        }
    }



}
