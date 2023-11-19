package MenuNavigation;
//import Database.UserClient;
import Database.UserTestClient;
//import Database.UserClient;
import java.util.Scanner;

public class LoginScreen extends MenuNavigation{
//    LoginScreen(){
//        Scanner reader = new Scanner(System.in);
//        System.out.println("Enter Username or \"Cancel\"");
//        String name = reader.nextLine();
//        if (name.equals("Cancel"))
//            cancel();
//        System.out.println("Enter Password");
//        String password = reader.nextLine();
//
//        if (passwordCheck(name, password))
//            new HomeScreen();
//        else {
//            System.out.println("Incorrect Username or Password\n");
//            new LoginScreen();
//        }
//    }


    //        UserClient user = new UserClient();
    UserTestClient user = new UserTestClient();

    public MenuNavigation login(String name, String password){
        if (passwordCheck(name, password))
            return new HomeScreen(user.getUserByUserName(name));
        else
            return new LoginScreen();
    }
    protected boolean passwordCheck(String name, String password){
        //        UserClient user = new UserClient();
        UserTestClient user = new UserTestClient();
        if (user.checkforExistingUsername(name))
            return (user.getUserByUserName(name).getPassword().equals(password));
        else
            return false;
    }

    public void cancel(){
        new StartScreen();
    }
}
