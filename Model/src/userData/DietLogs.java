package userData;

import Database.DietLog;
import Database.IDietClientFactory;

import java.util.ArrayList;

public class DietLogs {
    private User user;
    private ArrayList<DietLog> dietLogs = new ArrayList<>();
    private static final DietLog db = IDietClientFactory.getIDietClient();

    DietLogs(User user){
        this.user = user;
    }
//    ArrayList<DietLog> GetDietLogsByDateRangeAndUserId(long startDate, long EndDate, int userId){
//        dietLogs =db.getDietLogsByDateRangeAndUserId(startDate, EndDate, userId);
//        return dietLogs;
//
//    }
    public ArrayList<DietLog> getDietLogs() {
        return dietLogs;
    }

}
