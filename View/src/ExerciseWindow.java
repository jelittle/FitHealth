import java.util.ArrayList;
import java.util.Scanner;

/**
 * Exercise window class for front end
 * will not be in terminal by deliverable two, no ui for deliverable 1
 *
 */
public class ExerciseWindow {
    private static ExerciseController controller;
    private static Scanner reader = new Scanner(System.in);
    private static ArrayList<Integer> startDate;
    private static ArrayList<Integer> endDate;

    //create main class
    public static void main(String[] args) {
        ExerciseWindow window = new ExerciseWindow();
        //introduce this terminal window, tell user that a default user is already initated
        System.out.println("Welcome to the exercise window, a default user has been created for you");
        MainMenu();

    }

    ExerciseWindow() {
        //connect
        controller = new ExerciseController();
    }

    private static void MainMenu() {
        while (true) {


            System.out.println("Please select an option");
            System.out.println("1. View exercise logs");
            System.out.println("2. Add exercise log");
            System.out.println("3. Delete exercise log");
            System.out.println("4. show graph with data");
            System.out.println("5. Exit");
            //read user response
            int action = Integer.valueOf(reader.nextLine());

            switch (action) {
                case 1:
                    viewExerciseLogs();
                    break;
//                case 2:
//                    addExerciseLog();
//                    break;
//                case 3:
//                    deleteExerciseLog();
//                    break;
//                case 4:
//                    showGraph();
//                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }

    }

    private boolean checkValidDates() {
        if (startDate.get(0) > endDate.get(0)) {
            return false;
        }
        if (startDate.get(1) > endDate.get(1)) {
            return false;
        }
        if (startDate.get(2) > endDate.get(2)) {
            return false;
        }
        if (startDate.get(3) > endDate.get(3)) {
            return false;
        }
        if (startDate.get(4) > endDate.get(4)) {
            return false;
        }
        return true;
    }

    private static void viewExerciseLogs() {

        getDates();
        System.out.println(startDate.get(0));
        controller.setActiveExercisesByDateRange(startDate, endDate);
        ArrayList<Integer> ids = controller.getActiveExerciseIds();
        ArrayList<String> names = controller.getActiveExerciseNames();
        ArrayList<Integer> calories = controller.getActiveExerciseCalories();
        ArrayList<Integer> durations = controller.getActiveExerciseDuration();
        ArrayList<Integer> startTimes = controller.getActiveExerciseStartTimes();
        ArrayList<String> intensity = controller.getExerciseIntensity();


    }

    /**
     * sets start date in array in order of year,month,day,hour,minute
     *
     * @return
     */
    private static void getDates() {
        startDate = new ArrayList<>();
        endDate = new ArrayList<>();

        int intInput;
        System.out.println("Enter 1 if you would like to view the entire date range(only for testing), if not enter anything else");
        try {
            intInput = Integer.valueOf(reader.nextLine());
            if (intInput == 1) {
                startDate.add(0);
                startDate.add(0);
                startDate.add(0);
                startDate.add(0);
                startDate.add(0);
                endDate.add(2038);
                endDate.add(1);
                endDate.add(19);
                endDate.add(3);
                endDate.add(14);
                return;

            }
        }catch(Exception e){

        }


        setStartDate();
        setEndDate();




    }
    private static void setStartDate() {
        while (true) {
            try {

                System.out.println("Enter the start date in you date range");
                System.out.println("year:");

                int year = Integer.valueOf(reader.nextLine());
                if (year < 0) {
                    throw new IllegalArgumentException("year must be greater than 0");
                }
                startDate.add(year);
                System.out.println("month:");
                int month = Integer.valueOf(reader.nextLine());
                if (month > 12 || month < 1) {
                    throw new IllegalArgumentException("month must be between 1 and 12");
                }
                startDate.add(month);
                System.out.println("day:");
                int day = Integer.valueOf(reader.nextLine());
                if (day > 31 || day < 1) {
                    throw new IllegalArgumentException("day must be between 1 and 31");
                }
                startDate.add(day);
                System.out.println("hour:");
                int hour = Integer.valueOf(reader.nextLine());
                if (hour > 23 || hour < 0) {
                    throw new IllegalArgumentException("hour must be between 0 and 23");
                }
                startDate.add(hour);
                System.out.println("minute:");
                int minute = Integer.valueOf(reader.nextLine());
                if (minute > 59 || minute < 0) {
                    throw new IllegalArgumentException("minute must be between 0 and 59");
                }
                startDate.add(minute);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input, try again");

            }
        }

    }
    private static void setEndDate(){
        while(true){
            try {

                System.out.println("Enter the end date in your date range");
                System.out.println("year:");

                int year = Integer.valueOf(reader.nextLine());
                if(year<0){
                    throw new IllegalArgumentException("year must be greater than 0");
                }
                endDate.add(year);
                System.out.println("month:");
                int month = Integer.valueOf(reader.nextLine());
                if(month>12||month<1){
                    throw new IllegalArgumentException("month must be between 1 and 12");
                }
                endDate.add(month);
                System.out.println("day:");
                int day = Integer.valueOf(reader.nextLine());
                if(day>31||day<1){
                    throw new IllegalArgumentException("day must be between 1 and 31");
                }
                endDate.add(day);
                System.out.println("hour:");
                int hour = Integer.valueOf(reader.nextLine());
                if(hour>23||hour<0){
                    throw new IllegalArgumentException("hour must be between 0 and 23");
                }
                endDate.add(hour);
                System.out.println("minute:");
                int minute = Integer.valueOf(reader.nextLine());
                if(minute>59||minute<0){
                    throw new IllegalArgumentException("minute must be between 0 and 59");
                }
                endDate.add(minute);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input, try again");

            }
        }
    }
}






