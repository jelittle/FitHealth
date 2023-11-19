import Database.UserTestClient;
import org.testng.annotations.Test;
import userData.User;
import static org.junit.jupiter.api.Assertions.assertTrue;
import MenuNavigation.*;

public class TestCases {

    UserTestClient db = new UserTestClient();
    MenuNavigation currentMenu;

    User userA = new User("Lenny", "12345", "Male",156, 82.4F, 26,2);

    boolean compareUsers(User userA, User userB){
        if (userA.getName().equals(userB.getName()) && userA.getPassword().equals(userB.getPassword()) &&
                userA.getHeight() == userB.getHeight() && userA.getWeight() == userB.getWeight() &&
                userA.getAge() == userB.getAge())
            return true;
        else
            return false;
    }

    @Test
    void homeScreenNavigation(){
        currentMenu = new StartScreen();
        currentMenu = ((StartScreen) currentMenu).login();
        currentMenu = ((LoginScreen) currentMenu).login(userA.getName(), userA.getPassword());
        assertTrue(currentMenu instanceof HomeScreen);
    }

    @Test
    void newUser(){
        currentMenu = new StartScreen();
        currentMenu = ((StartScreen) currentMenu).newUser("Tods", "Logs", "Male", 320, 6f, 14);
        assertTrue(db.checkforExistingUsername("Tods"));
        db.deleteUser(db.getUserByUserName("Tods"));
    }

    @Test
    void profileEdit(){
        currentMenu = new StartScreen();
        currentMenu = ((StartScreen) currentMenu).login();
        currentMenu = ((LoginScreen) currentMenu).login(userA.getName(), userA.getPassword());
        currentMenu = ((HomeScreen) currentMenu).settings();
        ((SettingsScreen) currentMenu).changeWeight(72.3F);
        assertTrue(db.getUserByUserName("Lenny").getWeight() == 72.3F);
        ((SettingsScreen) currentMenu).changeWeight(82.4F);
    }

}
