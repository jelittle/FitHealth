package userData;

import Database.DietLog;
import Database.IDietClientFactory;

import java.util.ArrayList;

public class DietLogs {
    private User user;
    private ArrayList<DietLogEntry> dietLogs = new ArrayList<>();
    private static final DietLog db = IDietClientFactory.getIDietLogClient();

    DietLogs(User user){
        this.user = user;
    }

    ArrayList<DietLogEntry> GetDietLogsByDateRangeAndUserId(long startDate, long EndDate, int userId){
        dietLogs =db.getDietLogsByDateRangeAndUserId(startDate, EndDate, userId);
        return dietLogs;

    }
}
