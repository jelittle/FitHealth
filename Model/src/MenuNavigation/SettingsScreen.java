package MenuNavigation;

import Database.UserTestClient;
import userData.User;

public class SettingsScreen extends MenuNavigation{

    User user;
    UserTestClient userClient = new UserTestClient();
    SettingsScreen(User user){
        this.user = user;
    }
    public StartScreen deleteProfile(){
        userClient.deleteUser(userClient.getUserByUserName(user.getName()));
        user = null;
        return new StartScreen();
    }

    public void changeUsername(String name){
        user.setName(name);
        userClient.updateUser(user);
    }
    public void changePassword(String password){
        user.setPassword(password);
        userClient.updateUser(user);
    }
    public void changeSex(String sex){
        user.setSex(sex);
        userClient.updateUser(user);
    }
    public void changeHeight(int height){
        user.setHeight(height);
        userClient.updateUser(user);
    }
    public void changeWeight(float weight){
        user.setWeight(weight);
        userClient.updateUser(user);
    }
    public void changeAge(int age){
        user.setAge(age);
        userClient.updateUser(user);
    }
}
