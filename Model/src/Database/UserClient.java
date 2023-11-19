package Database;

import userData.User;

public class UserClient implements UserInterface {
    //Todo: check if manager can be final
    static Manager manager;

    public UserClient() {
        if (manager == null)
            manager = new Manager();
    }


    public User getUserById(int id) {
        String[] columns = {};
        User user = manager.getRecord("user", columns, new String[]{"id = " + id});
        return user;
    }

    public void setUser(User user) {
        String[] columns = { "username", "password", "height", "weight", "age"};
        String[] values = {"'" + user.getName() + "'", "'" + user.getPassword() + "'",  Integer.toString(user.getHeight()),
                Float.toString(user.getWeight()) ,  Integer.toString(user.getAge()) };
        manager.insertRecord("user", columns, values);
    }

    public void setUser(String name, String password, String sex, int height, float weight, int age) {
        String[] columns = { "username", "password", "height", "weight", "age"};
        String[] values = {"'" + name + "'", "'" + password + "'",  Integer.toString(height),
                Float.toString(weight),  Integer.toString(age)};
        manager.insertRecord("user", columns, values);
    }

    public void updateUser(User user) {
        //columns= columns you want to change
        //values= new values
        //condition= where to change
        String[] columns = {"username", "password", "height", "weight", "age"};
        String[] values = {"'" + user.getName() + "'", "'" + user.getPassword() + "'", Integer.toString(user.getHeight()),
                Float.toString(user.getWeight()), Integer.toString(user.getAge())};
        manager.updateRecord("user", columns, values, new String[]{"id = " + user.getID()});
    }

    public User getUserByUserName(String userName) {
        String[] columns = {};
        userName = "'" + userName + "'";
        User user = manager.getRecord("user", columns, new String[]{"username = " + userName});
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public boolean checkforExistingUsername(String userName) {
        String[] columns = {};
        userName = "'" + userName + "'";
        User user = manager.getRecord("user", columns, new String[]{"username = " + userName});
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteUser(User user) {
        manager.deleteRecord("user", new String[]{"id = " + user.getID()});
    }
}



