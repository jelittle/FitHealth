package MenuNavigation;

import userData.User;
public class HomeScreen extends MenuNavigation{
    User user;

    HomeScreen(User user){
        this.user = user;
    }
    public SettingsScreen settings(){
        return new SettingsScreen(user);
    }

    public StartScreen logout(){
        return new StartScreen();
    }

}
