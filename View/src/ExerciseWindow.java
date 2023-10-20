import java.util.Scanner;

/**
 * Exercise window class for front end
 * will not be in terminal by deliverable two, no ui for deliverable 1
 *
 */
public class ExerciseWindow {
    private static ExerciseController controller;
    //create main class
    public static void main(String[] args) {
        ExerciseWindow window = new ExerciseWindow();
        //introduce this terminal window, tell user that a default user is already initated
        System.out.println("Welcome to the exercise window, a default user has been created for you");


    }
    ExerciseWindow() {
        //connect
        controller=new ExerciseController();
    }
    private static void MainMenu(){
        System.out.println("Please select an option");
        System.out.println("1. View exercise logs");
        System.out.println("2. Add exercise log");
        System.out.println("3. Delete exercise log");
        System.out.println("4. show graph with data");
        System.out.println("4. Exit");
        //read user response
        Scanner scanner = new Scanner(System.in);

    }






}
