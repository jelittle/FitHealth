package MenuNavigation;

import Database.UserTestClient;
//import Database.UserClient;
import Database.UserTestClient;
//import java.util.Scanner;

public class StartScreen extends MenuNavigation{
//    StartScreen(){
//        Scanner reader = new Scanner(System.in);
//        System.out.println("[1] Login\n[2] New User\n[3] Shut Down");
//        int action = Integer.valueOf(reader.nextLine());
//
//        switch(action){
//            case 1:
//                login();
//            case 2:
//                newUser();
//            case 3:
//                shutDown();
//        }
//    }
    public LoginScreen login(){
        return new LoginScreen();
    }
    public HomeScreen newUser(String name, String password, String sex, int height, float weight, int age){
        //        UserClient user = new UserClient();
        UserTestClient user = new UserTestClient();
        user.setUser(name, password, sex, height, weight, age);
        return new HomeScreen(user.getUserByUserName(name));
    }

    public void shutDown(){
        System.exit(0);
    }
}
