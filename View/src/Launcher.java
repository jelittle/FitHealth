import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;




public class Launcher extends JFrame {

    public static int userId = 1;

    // User Profile Info
    
    private static String sex;
    private static LocalDate dateOfBirth;
    private static Double height;
    private static Double weight;

    // Diet Info
    private static LocalDate dateOfMeal;
    private static String mealType;
    private static List<IngredientData> ingredientData; 

    // Exercise Info
    private static LocalDate dateOfExercise;
    private static Double exerciseDuration;
    private static String exerciseType;
    private static String exerciseIntensity;

    // Calorie Intake/Exercise (Start-Stop Dates)
    private static LocalDate visualizeCEStartDate;
    private static LocalDate visualizeCEEndDate;

    // Daily Nutrient Intake (Start-Stop Dates)
    private static LocalDate visualizeDEStartDate;
    private static LocalDate visualizeDEEndDate;

    // Fat Loss (Start-Stop Dates)
    private static LocalDate visualizeFLEndDate;

    // Calorie and Energy
    static HashMap<String, Integer> calorieIntakeMap = new HashMap<>();
    static HashMap<String, Integer> energyExpenditureMap = new HashMap<>();


    private static JPanel cards;
    private static JFrame frame;
    private static CardLayout cardLayout;


    // ---------- Profile Info ----------
    public static String getSex() {
        return sex;
    }

    public static LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public static Double getUserHeight() {
        return height;
    }

    public static Double getUserWeight() {
        return weight;
    }

    public static void setSex(String s) {
        sex = s;
        System.out.println("User sex: " + sex);
    }

    public static void setDateOfBirth(LocalDate dob) {
        dateOfBirth = dob;
        System.out.println("User date of birth: " + dateOfBirth);
    }

    public static void setUserHeight(Double h) {
        height = h;
        System.out.println("User height: " + height);
    }

    public static void setUserWeight(Double h) {
        weight = h;
        System.out.println("User weight: " + weight);
    }

    // ---------- Diet Info ----------
    public static LocalDate getDateOfMeal() {
        return dateOfMeal;
    }

    public static String getMealType() {
        return mealType;
    }

    public static List<IngredientData> getIngredientData() {
        return ingredientData;
    }

    public static void setDateOfMeal(LocalDate date) {
        dateOfMeal = date;
        System.out.println("Date of meal: " + dateOfMeal);
    }

    public static void setMealType(String m) {
        mealType = m;
        System.out.println("Meal type: " + mealType);
    }

    public static void setIngredientData(List<IngredientData> data) {        
        ingredientData = data;
        System.out.println("User ingredient data: " + ingredientData);
    }

    // ---------- Exercise Info ----------

    public static LocalDate getDateOfExercise() {
        return dateOfExercise;
    }

    public static Double getExerciseDuration() {
        return exerciseDuration;
    }

    public static String getExerciseType() {
        return exerciseType;
    }

    public static String getExerciseIntensity() {
        return exerciseIntensity;
    }

    public static void setDateOfExercise(LocalDate date) {
        dateOfExercise = date;
        System.out.println("Date of exercise: " + dateOfExercise);
    }

    public static void setExerciseDuration(Double d) { //In minutes
        exerciseDuration = d;
        System.out.println("Exercise duration: " + exerciseDuration);
    }

    public static void setExerciseType(String t) {
        exerciseType = t;
        System.out.println("Exercise type: " + mealType);
    }

    public static void setExerciseIntensity(String i) {
        exerciseIntensity = i;
        System.out.println("Exercise intensity: " + exerciseIntensity);
    }

    // ---------- Calorie Intake/Exercise (Start/Stop Dates) ----------
    public static LocalDate getCEStartDate() {
        return visualizeCEStartDate;
    }

    public static LocalDate getCEEndDate() {
        return visualizeCEEndDate;
    }

    public static void setCEStartDate(LocalDate date) {
        visualizeCEStartDate = date;
        System.out.println("Start date: " + visualizeCEStartDate);
    }

    public static void setCEEndDate(LocalDate date) {
        visualizeCEEndDate = date;
        System.out.println("End date: " + visualizeCEEndDate);
    }

    // ---------- Daily Nutrient Intake (Start/Stop Dates) ----------
    public static LocalDate getDNStartDate() {
        return visualizeDEStartDate;
    }

    public static LocalDate getDNEndDate() {
        return visualizeDEEndDate;
    }

    public static void setDNStartDate(LocalDate date) {
        visualizeDEStartDate = date;
        System.out.println("Start date: " + visualizeDEStartDate);
    }

    public static void setDNEndDate(LocalDate date) {
        visualizeDEEndDate = date;
        System.out.println("End date: " + visualizeDEEndDate);
    }

    // ---------- Fat Loss (Start/Stop Dates) ----------

    public static LocalDate getFLEndDate() {
        return visualizeFLEndDate;
    }

    public static void setFLEndDate(LocalDate date) {
        visualizeFLEndDate = date;
        System.out.println("End date: " + visualizeFLEndDate);
    }

    // ---------- Methods ----------

    public static void createCEMaps() {

        calorieIntakeMap.put("2023-11-21", 2200);
        energyExpenditureMap.put("2023-11-21", 1800);

    }

    // TEST

    public static void setEnergyExpenditure() {

    }

    // ---------- END ----------
    private static void initialize() {
        frame = new JFrame();
        frame.setTitle("FitHealth");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Create instances of the screen classes
        MainMenu mainMenu = new MainMenu(cardLayout, cards);
        MainFrameFitHealth mainFrameFitHealth = new MainFrameFitHealth(cardLayout, cards);
        ProfileDataEntryPanel profileDataEntryPanel = new ProfileDataEntryPanel(cardLayout, cards);
        DietDataEntryPanel dietDataEntryPanel = new DietDataEntryPanel(cardLayout, cards);
        ExerciseDataEntryPanel exerciseDataEntryPanel = new ExerciseDataEntryPanel(cardLayout, cards);
//        VisualizeCE visualizeCE = new VisualizeCE(cardLayout, cards);
        VisualizeDN visualizeDN = new VisualizeDN(cardLayout, cards);
        VisualizeFL visualizeFL = new VisualizeFL(cardLayout, cards);
        VisualizeCFG visualizeCFG = new VisualizeCFG(cardLayout, cards);

        // Add the screens to the CardLayout
        cards.add(mainFrameFitHealth, "MainFrameFitHealth");
        cards.add(profileDataEntryPanel, "ProfileDataEntryPanel");
        cards.add(dietDataEntryPanel, "DietDataEntryPanel");
        cards.add(mainMenu, "MainMenu");
        cards.add(exerciseDataEntryPanel, "ExerciseDataEntryPanel");
//        cards.add(visualizeCE, "VisualizeCE");
        cards.add(visualizeDN, "VisualizeDN");
        cards.add(visualizeFL, "VisualizeFL");
        cards.add(visualizeCFG, "VisualizeCFG");

        frame.add(cards);
        cardLayout.show(cards, "FitHealth");


    }

    private static void display(){
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println("Nimbus Look and Feel not available");
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initialize();
                display();
            }
        });
    }
}


