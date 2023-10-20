import userData.User;

import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
//        User Bob = new User();
//        Bob.setName("Bobert");
//        Bob.setPassword("Password");
//        System.out.println(Bob.getName());
//        System.out.println(Bob.getPassword());
        // System.out.println("---------------");
        // UserInfo.printAll(Bob);

        ArrayList<User> userList = new ArrayList<>();
        User Bob = new User("Bobert", "Password", "Male", 154, 76.3f, 29, 1);
        userList.add(Bob);
        userList.add(new User("Bobby", "No", "Male", 172, 56.3f, 24, 2));
//        System.out.println("---------------");
        MenuNavigation.startScreen(userList);

        // System.out.println("---------------");
        // UserInfo.startScreen(userList);
        // Scanner reader = new Scanner(System.in);
        // int action = reader.nextInt();
        // if (action == 4)
        //     System.out.println("W");


        
        // reader.close();

        // Scanner scany = new Scanner(System.in);
        // int nums;
        // String words;
        // nums = Integer.valueOf(scany.nextLine());
        // words = scany.nextLine();
        // System.out.println(nums + words);
    }



} 