package Database;

class  QueryBuilder {



    static String sqlSelectBuilder(String table, String[] columns, String[] Conditions){
        //create sql statement using table, columns, and conditions

        String sql = "SELECT ";

        // Add columns to the SQL statement
        // If no columns are specified, select all columns
        if(columns == null)
            sql += "*";

        else if(columns.length == 0){
            sql += "*";
        }else
        for(int i = 0; i < columns.length; i++){
            sql += columns[i];
            if(i < columns.length - 1){
                sql += ", ";
            }
        }

        // Add table to the SQL statement
        sql += " FROM " + table;

        // Add conditions to the SQL statement
        if(Conditions.length > 0){
            sql += " WHERE ";
            for(int i = 0; i < Conditions.length; i++){
                //if condition i is number, don't add quotes

                sql += Conditions[i];
                if(i < Conditions.length - 1){
                    sql += " AND ";
                }
            }
        }

        return sql;
    }
    static String sqlInsertBuilder(String table,String[] columns, String[] values){
        String sql = "INSERT INTO " + table + " (";

        // Add columns to the SQL statement
        for(int i = 0; i < columns.length; i++){
            sql += columns[i];
            if(i < columns.length - 1){
                sql += ", ";
            }
        }

        sql += ") VALUES (";

        // Add values to the SQL statement
        for(int i = 0; i < values.length; i++){
            sql += values[i];
            if(i < values.length - 1){
                sql += ", ";
            }
        }

        sql += ")";

        return sql;
    }
    static String sqlUpdateBuilder(String table, String[] columns, String[] values, String[] Conditions){
        String sql = "UPDATE " + table + " SET ";

        // Add columns to the SQL statement
        for(int i = 0; i < columns.length; i++){
            sql += columns[i] + " = " + values[i];
            if(i < columns.length - 1){
                sql += ", ";
            }
        }

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

        return sql;
    }

     static String sqlDeleteBuilder(String table, String[] conditions) {
        String sql = "DELETE FROM " + table;

        // Add conditions to the SQL statement
        if(conditions.length > 0){
            sql += " WHERE ";
            for(int i = 0; i < conditions.length; i++){
                sql += conditions[i];
                if(i < conditions.length - 1){
                    sql += " AND ";
                }
            }
        }

        return sql;
    }

}
    