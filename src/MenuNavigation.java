import userData.User;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuNavigation {
    static Scanner reader = new Scanner(System.in);

    private static void userMenu(User user, ArrayList<User> userList){
        int action;
        System.out.println("[1] Name: " + user.getName() +  "\n[2] Password: " + user.getPassword() + "\n[3] Height: " + user.getHeight() + 
        " cm\n[4] Weight: " + user.getWeight()/1000 + "kg\n[5] Age: " + user.getAge() + "\n[6] ID: " + user.getID() + "\n[7] Delete Profile" + "\n[8] Logout");
        action = Integer.valueOf(reader.nextLine());
        switch(action){
            case 1: 
                System.out.println("Enter New Name"); 
                user.setName(reader.nextLine());   
                break;
            case 2: 
                System.out.println("Enter New Password");
                user.setPassword(reader.nextLine()); 
                break;
            case 3: 
                System.out.println("Enter New Height");
                user.setHeight(Integer.valueOf(reader.nextLine()));
                break;
            case 4: 
                System.out.println("Enter New Weight");
                user.setWeight(Float.valueOf(reader.nextLine()));
                break;
            case 5: 
                System.out.println("Enter New Age");
                user.setAge(Integer.valueOf(reader.nextLine())); 
                break;
            case 6: 
                System.out.println("Enter New ID");
                user.setID(Integer.valueOf(reader.nextLine())); 
                break;
            case 7: 
                System.out.println("Enter Password");
                if (passwordCheck(user, reader.nextLine())){
                    userList.remove(user);
                    startScreen(userList);
                } else 
                    userMenu(user, userList);
            case 8: startScreen(userList); break;
        }
        userMenu(user, userList);
    }

    public static void startScreen(ArrayList<User> userList){
        int action;
        int i;

        for (i = 0; i < userList.size(); i++){
            System.out.println("[" + (i+1) + "] " + userList.get(i).getName());
        }
        System.out.println("[" + (i + 1) + "] New User");
        System.out.println("[" + (i + 2) + "] Shut Down");

        action = Integer.valueOf(reader.nextLine());

        if (action == i + 2){
            System.exit(0);
        } else if (action == i + 1){
            System.out.println("Enter Name");
            String name = reader.nextLine();
            User newUser = new User(name);
            userList.add(newUser);
            MenuNavigation.userMenu(userList.get(action - 1), userList);
        } else{
            
            if (!userList.get(action - 1).getPassword().equals("")){
                System.out.println("Enter Password");
                String password = reader.nextLine();
                if (passwordCheck(userList.get(action - 1), password))
                    MenuNavigation.userMenu(userList.get(action - 1), userList);
                else {
                    startScreen(userList);
                }
            } else
                MenuNavigation.userMenu(userList.get(action - 1), userList);
        }
        
    }

    public static boolean passwordCheck(User user, String password){
        if (user.getPassword().equals(password))
            return true;
        else {
            System.out.println("Incorrect password, try again or enter BACK to cancel");
            password = reader.nextLine();
            if (!password.equals("BACK"))  
                passwordCheck(user, password);
            return false;
        }
    }
}
