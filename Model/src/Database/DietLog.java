package Database;

import userData.DietLogEntry;

//Interface between anything requiring DietLogEntry objects within the Database
public interface DietLog{

    public DietLog getDietLogById(int id);
    public boolean setDietLog(DietLogEntry dietLog);
    public void updateDietLog(DietLogEntry dietLog);
    public void deleteDietLog(DietLogEntry dietLog);

}

class IDietLogClient implements DietLog {
    static Manager manager;

    public IDietLogClient() {
        if (manager == null)
            manager = new Manager();
    }

    public DietLog getDietLogById(int id) {

        //impossible to get more than 1 with id

        return manager.getRecord("diet_log", null, new String[]{"id = " + id});
    }

    @Override
    public boolean setDietLog(DietLogEntry dietLog) {

        String[] columns = {"id", "name", "foodgroup", "amount", "calories", "proteins", "vitamin"};

        String[] values = {Integer.toString(dietLog.getDietId()), dietLog.getName(), dietLog.getFoodGroup(), Double.toString(dietLog.getQuantity()), String.valueOf(dietLog.getCalories()), String.valueOf(dietLog.getProteins()), String.valueOf(dietLog.getVitamins())};

        return manager.insertRecord("diet_log", columns, values);

    }

    @Override
    public void updateDietLog(DietLogEntry dietLog) {

        String[] columns = {"id", "name", "foodgroup", "amount", "calories", "proteins", "vitamin"};

        String[] values = {Integer.toString(dietLog.getDietId()), dietLog.getName(), dietLog.getFoodGroup(), Double.toString(dietLog.getQuantity()), String.valueOf(dietLog.getCalories()), String.valueOf(dietLog.getProteins()), String.valueOf(dietLog.getVitamins())};

        manager.updateRecord("diet_log", columns, values, new String[]{"id = " + dietLog.getDietId()});

    }


    @Override
    public void deleteDietLog(DietLogEntry dietLog) {
        manager.deleteRecord("diet_log", new String[]{"id = " + dietLog.getDietId()});
    }

}


class IdietLogClientTest implements DietLog {

    private static TestDatabase db;

    public IdietLogClientTest() {
        if (db == null)
            db = new TestDatabase();
    }

    @Override
    public DietLog getDietLogById(int id) {
        return (DietLog) db.getTableEntityById("diet_log", id);
    }

    @Override
    public boolean setDietLog(DietLogEntry dietLog) {
        db.InsertTableEntity("diet_log", dietLog);
        return true;
    }

    @Override
    public void updateDietLog(DietLogEntry dietLog) {
        db.UpdateTableEntity("diet_log", dietLog);
    }

    @Override
    public void deleteDietLog(DietLogEntry dietLog) {
        db.DeleteTableEntity("diet_log", db.getTableEntityById("diet_log", dietLog.getDietId()));
    }
}