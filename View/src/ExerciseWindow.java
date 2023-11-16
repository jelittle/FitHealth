import Controller.ExerciseController;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Exercise window class for front end
 * will not be in terminal by deliverable two, no ui for deliverable 1
 * this class holds more functionality class than a normal window should have, but it was built as a temporary class and will
 * be changed
 * //todo
 *
 */
public class ExerciseWindow {
    private static ExerciseController controller;
    private static Scanner reader = new Scanner(System.in);
    private static ArrayList<Integer> startDate;
    private static ArrayList<Integer> endDate;
    private static boolean DateRangeSet= false;

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
            System.out.println("4. show calories burned per day graph");
            System.out.println("5.set Date Range");
            System.out.println("6. Exit");
            //read user response
            int action = Integer.valueOf(reader.nextLine());

            switch (action) {
                case 1:
                    viewExerciseLogs();
                    break;
                case 2:
                    addExerciseLog();
                    break;
                case 3:
                    deleteExerciseLog();
                    break;
                case 4:
                    showGraph();
                    break;
                case 5:
                    setDateRange();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }

    }
    private static void deleteExerciseLog(){
        System.out.println("enter id of exercise log to delete");
        int id = Integer.valueOf(reader.nextLine());
        //check if id is valid
        if(controller.DeleteExerciseLog(id)){
            System.out.println("exercise log deleted");
            return;
        }
        System.out.println("invalid id");
        }

    private static void showGraph(){
        if(!DateRangeSet){
            setDateRange();
            DateRangeSet=true;
        }
        controller.getGraph();



    }
    private static void addExerciseLog() {
        getExerciseInput();


    }
    private static void getExerciseInput(){


        while(true) {

            try {

                System.out.println("enter start date");
                ArrayList<Integer> startDate = GetDateTime();
                System.out.println("enter end date");
                ArrayList<Integer> endDate = GetDateTime();
                System.out.println("enter exercise name");
                String exerciseName = getExerciseChoice();
                System.out.println("enter intensity");
                System.out.println("1. low");
                System.out.println("2. medium");
                System.out.println("3. high");
                System.out.println("4. very high");
                String intensity;
                switch (reader.nextLine()) {
                    case "1":
                        intensity = "low";
                        break;
                    case "2":
                        intensity = "medium";
                        break;
                    case "3":
                        intensity = "high";
                        break;
                    case "4":
                        intensity = "very high";
                        break;
                    default:
                        throw new IllegalArgumentException("invalid input");
                }
                System.out.println(controller.getMetId(exerciseName, intensity));
                controller.addExerciseLog(startDate, controller.getMetId(exerciseName, intensity),endDate);
                break;
            } catch (Exception e) {

                System.out.println("invalid input, try again");
            }

        }


    }
    private static String getExerciseChoice(){
        ArrayList<String> exercises = controller.getExerciseTypes();
        for(int i=0;i<exercises.size();i++){
            System.out.println(i+1+". "+exercises.get(i));
        }
        System.out.println("enter exercise number");
        int exerciseNumber = Integer.valueOf(reader.nextLine());
        return exercises.get(exerciseNumber-1);
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
    private static void setDateRange() {

        getDates();
        controller.setActiveExercisesByDateRange(startDate, endDate);

    }

    private static void viewExerciseLogs() {
        if(!DateRangeSet){
            setDateRange();
            DateRangeSet=true;
        }


        ArrayList<Integer> ids = controller.getActiveExerciseIds();
        ArrayList<String> names = controller.getActiveExerciseNames();
        ArrayList<Integer> calories = controller.getActiveExerciseCalories();
        ArrayList<Integer> durations = controller.getActiveExerciseDuration();
        ArrayList<Integer> startTimes = controller.getActiveExerciseStartTimes();
        ArrayList<String> intensity = controller.getExerciseIntensity();
        //prep start time


        //for each loop that loops through ids

        System.out.println("unix timestamp logic in code but not implemented");
        for (int i = 0; i < ids.size(); i++) {

            System.out.println("id: " + ids.get(i) + " name: " + names.get(i) + " calories: " + calories.get(i) + " duration(in seconds): " + durations.get(i) + " start time(unix timestamp): " + startTimes + " intensity: " + intensity.get(i));
        }
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
                startDate.add(1970);
                startDate.add(1);
                startDate.add(1);
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
        System.out.println("Enter your start date");
        startDate = GetDateTime();
        System.out.println("Enter your end date");
        endDate = GetDateTime();






    }
    private static ArrayList<Integer> GetDateTime() {
        while (true) {
            try {


                System.out.println("year:");

                int year = Integer.valueOf(reader.nextLine());
                if (year < 0) {
                    throw new IllegalArgumentException("year must be greater than 0");
                }

                System.out.println("month:");
                int month = Integer.valueOf(reader.nextLine());
                if (month > 12 || month < 1) {
                    throw new IllegalArgumentException("month must be between 1 and 12");
                }
                ;
                System.out.println("day:");
                int day = Integer.valueOf(reader.nextLine());
                if (day > 31 || day < 1) {
                    throw new IllegalArgumentException("day must be between 1 and 31");
                }

                System.out.println("hour:");
                int hour = Integer.valueOf(reader.nextLine());
                if (hour > 23 || hour < 0) {
                    throw new IllegalArgumentException("hour must be between 0 and 23");
                }

                System.out.println("minute:");
                int minute = Integer.valueOf(reader.nextLine());
                if (minute > 59 || minute < 0) {
                    throw new IllegalArgumentException("minute must be between 0 and 59");
                }
                ArrayList<Integer> dateTime = new ArrayList<>();
                dateTime.add(year);
                dateTime.add(month);
                dateTime.add(day);
                dateTime.add(hour);
                dateTime.add(minute);
                return dateTime;


            } catch (Exception e) {
                System.out.println("Invalid input, try again");

            }
        }

    }

}






