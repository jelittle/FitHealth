package Database;

import userData.ExerciseLog;
import userData.User;

import java.util.ArrayList;

abstract class Table {
    abstract ArrayList<?> getTable();
    abstract Object getById(int id);

    abstract void add(Object object);
}
abstract class LogTable extends Table{
    abstract <T> ArrayList<T> getByUserId(int id);

    abstract void BulkCreateforUser(int Id);
}


class UserTable extends Table {
    private static int userId = 1;
    private static boolean bulkcreateUsed = false;
    private static ArrayList<User> userList = new ArrayList<>();

    void add(String name, String password, int height, float weight, int age) {
        userList.add(new User(name, password, height, weight, age, userId++));
    }
    void bulkCreate(){
        if(!bulkcreateUsed) {
            userList.add(new User("Bob", "Password", 183, 72.3F, 23, userId++));
            userList.add(new User("Lenny", "12345", 156, 82.4F, 26, userId++));
            userList.add(new User("John", "No", 166, 72.1F, 23, userId++));
            userList.add(new User("Todington", "y4gbp87bqt", 201, 7230.45F, 23, userId++));
            bulkcreateUsed = true;
        }
        System.out.println("BulkCreate already used");
    }

    ArrayList<User> getTable() {
        return userList;
    }

    /**
     * @param id
     * @return
     */
    @Override
    Object getById(int id) {
        for (User user : userList) {
            if (user.getID() == id) {
                return user;
            }
        }
        throw new IllegalArgumentException("User not found");

    }


    public <T> ArrayList<T> getByUserId(int id) {
        ArrayList<User> returnList = new ArrayList<>();
        for (User user : userList) {
            if (user.getID() == id) {
                returnList.add(user);
            }
        }
        return (ArrayList<T>) returnList;
    }

    @Override
    void add(Object object) {
        userList.add((User) object);
    }
}

class ExerciseTable extends LogTable {
    private static int exerciseId = 1;
    private static ArrayList<ExerciseLog> exerciseList = new ArrayList<>();

    void add(int userid, int metid, int duration, int intensity) {
        exerciseList.add(new ExerciseLog(exerciseId++, userid, metid, duration, intensity));
    }
    void BulkCreateforUser(int Id){
        exerciseList.add(new ExerciseLog(exerciseId++, Id, 1, 2, 2));
        exerciseList.add(new ExerciseLog(exerciseId++, Id, 2, 3, 8));
        exerciseList.add(new ExerciseLog(exerciseId++, Id, 3, 4, 10));
        exerciseList.add(new ExerciseLog(exerciseId++, Id, 4, 5, 15));
    }

    ArrayList<ExerciseLog> getTable() {
        return exerciseList;
    }

    /**
     * @param id
     * @return
     */
    @Override
    Object getById(int id) {
        for (ExerciseLog exerciseLog : exerciseList) {
            if (exerciseLog.getId() == id) {
                return exerciseLog;
            }
        }
        throw new IllegalArgumentException("Exercise not found");
    }


    @Override
    <T> ArrayList<T> getByUserId(int id) {
        ArrayList<ExerciseLog> returnList = new ArrayList<>();
        for (ExerciseLog exerciseLog : exerciseList) {
            if (exerciseLog.getUserId() == id) {
                returnList.add(exerciseLog);
            }
        }
        return (ArrayList<T>) returnList;
    }

    @Override
    void add(Object object) {
        exerciseList.add((ExerciseLog) object);
    }

}

class MetStaticTable extends Table {
    private static int metId = 1;
    static final ArrayList<met> metList = new ArrayList<>();

    MetStaticTable() {
        if (metList.size() == 0) {
            metList.add(new met(metId++, 1, 1, 2.0F));
            metList.add(new met(metId++, 1, 2, 3.5F));
            metList.add(new met(metId++, 1, 3, 5.0F));
            metList.add(new met(metId++, 1, 4, 8.0F));
            metList.add(new met(metId++, 2, 1, 6.0F));
            metList.add(new met(metId++, 2, 2, 11.50F));
            metList.add(new met(metId++, 2, 3, 14.0F));
            metList.add(new met(metId++, 2, 4, 20.0F));
            metList.add(new met(metId++, 3, 1, 5.0F));
            metList.add(new met(metId++, 3, 2, 7.0F));
            metList.add(new met(metId++, 3, 3, 9.0F));
            metList.add(new met(metId++, 3, 4, 13.0F));
            metList.add(new met(metId++, 4, 1, 5.0F));
            metList.add(new met(metId++, 4, 2, 8.0F));
            metList.add(new met(metId++, 4, 3, 12.0F));
            metList.add(new met(metId++, 4, 4, 15.0F));
        }

    }

    ArrayList<met> getTable() {
        return metList;
    }

    /**
     * @param id
     * @return
     */
    @Override
    Object getById(int id) {
        for (met met : metList) {
            if (met.id == id) {
                return met;
            }
        }
        throw new IllegalArgumentException("Met not found");
    }

    @Override
    void add(Object object) {
        metList.add((met) object);
    }

    //only used in test database
    class met {
        int id;
        String exercise;

        public String getExercise() {
            return exercise;
        }

        public String getIntensity() {
            return intensity;
        }

        public float getMet() {
            return met;
        }

        String intensity;
        float met;

        met(int id, int exercise, int intensity, float met) {
            this.id = id;
            this.exercise = getExerciseFromTable(exercise);
            this.intensity = getIntensityFromTable(intensity);
            this.met = met;
        }
    }

    private String getIntensityFromTable(int id) {
        //get intensity from table
        switch (id) {
            case 1:
                return "low";
            case 2:
                return "medium";
            case 3:
                return "high";
            case 4:
                return "very high";
        }
        throw new IllegalArgumentException("Invalid Intensity ID");
    }

    private String getExerciseFromTable(int id) {
        //get exercise from table
        switch (id) {
            case 1:
                return "walking";
            case 2:
                return "running";
            case 3:
                return "swimming";
            case 4:
                return "cycling";
        }
        throw new IllegalArgumentException("Invalid Exercise ID");
    }

}

class DietTable extends LogTable{


    @Override
    ArrayList<?> getTable() {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    Object getById(int id) {
        return null;
    }

    @Override
    void add(Object object) {

    }

    @Override
    <T> ArrayList<T> getByUserId(int id) {
        return null;
    }

    @Override
    void BulkCreateforUser(int Id) {

    }
}