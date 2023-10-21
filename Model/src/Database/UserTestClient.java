package Database;

import userData.User;

/**
 * temporary class to allow testing of ExerciseLogController, dont know if will be in final
 * TODO: remove this class
 */
public class UserTestClient {
    private static TestDatabase db;
    public UserTestClient(){
        if (db == null)
            db = new TestDatabase();
    }
    public User getUserById(int id) {
        return (User) db.getTableEntityById("user", id);

    }
}
