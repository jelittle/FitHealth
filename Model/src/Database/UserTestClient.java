package Database;

import userData.User;

/**
 * temporary class to allow testing of ExerciseLogController, dont know if will be in final
 * TODO: remove this class
 */
public class UserTestClient implements UserInterface {
    private static TestDatabase db;
    public UserTestClient(){
        if (db == null)
            db = new TestDatabase();
    }
    public User getUserById(int id) {
        User user = (User) db.getTableEntityById("user", id);
        if (user == null)
            throw new RuntimeException("User not found");
        return user;
    }

    public void setUser(User user) {
        db.userTable.add(user);
    }

    public void setUser(String name, String password, String sex, int height, float weight, int age) {
        User user = new User(name, password, sex, height, weight, age, db.userTable.getTable().size());
        db.userTable.add(user);
    }

    public void updateUser(User user) {
        db.userTable.updateTable(user);

    }

    public User getUserByUserName(String userName) {
        User user = (User) db.userTable.getByName(userName);
        if (user == null)
            throw new RuntimeException("User not found");
        else
            return user;
    }

    public boolean checkforExistingUsername(String userName) {
        try {
            return db.userTable.getByName(userName) != null;
        }
        catch (Exception e){
            return false;
        }
    }

    public void deleteUser(User user) {
        db.userTable.deleteEntity(user);
    }
}
