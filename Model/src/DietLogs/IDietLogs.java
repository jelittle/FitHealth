package DietLogs;

import java.util.ArrayList;

public interface IDietLogs {

    public void addDietLogByUserId(int userId, DietLogEntry dietLogEntry);
    public void updateDietLogByUserId( DietLogEntry dietLogEntry);
    public void deleteDietLogByUserId( DietLogEntry dietLogEntry);

    public ArrayList<DietLogEntry> getDietLogsByDateRangeAndUserId(long startDate, long EndDate, int userId);



}
