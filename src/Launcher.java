import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Launcher {

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

    // Start-Stop Dates
    private static LocalDate startDate;
    private static LocalDate endDate;

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

    public static Double getHeight() {
        return height;
    }

    public static Double getWeight() {
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

    public static void setHeight(Double h) {
        height = h;
        System.out.println("User height: " + height);
    }

    public static void setWeight(Double h) {
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

    public static void setDietData(List<IngredientData> data) {
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

    // ---------- Start/Stop Dates ----------
    public static LocalDate getStartDate() {
        return startDate;
    }

    public static LocalDate getEndDate() {
        return endDate;
    }

    public static void setStartDate(LocalDate date) {
        startDate = date;
        System.out.println("Start date: " + startDate);
    }

    public static void setEndDate(LocalDate date) {
        endDate = date;
        System.out.println("End date: " + endDate);
    }

    // ---------- END ----------

    private static void initialize() {
        frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setTitle("FitHealth");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Create instances of the screen classes
        MainMenu mainMenu = new MainMenu(cardLayout, cards);
        MainFrameFitHealth mainFrameFitHealth = new MainFrameFitHealth(cardLayout, cards);
        ProfileDataEntryPanel profileDataEntryPanel = new ProfileDataEntryPanel(cardLayout, cards);
        DietDataEntryPanel dietDataEntryPanel = new DietDataEntryPanel(cardLayout, cards);
        ExerciseDataEntryPanel exerciseDataEntryPanel = new ExerciseDataEntryPanel(cardLayout, cards);
        VisualizeCE visualizeCE = new VisualizeCE(cardLayout, cards);
        VisualizeDN visualizeDN = new VisualizeDN(cardLayout, cards);

        // Add the screens to the CardLayout
        cards.add(mainFrameFitHealth, "MainFrameFitHealth");
        cards.add(profileDataEntryPanel, "ProfileDataEntryPanel");
        cards.add(dietDataEntryPanel, "DietDataEntryPanel");
        cards.add(mainMenu, "MainMenu");
        cards.add(exerciseDataEntryPanel, "ExerciseDataEntryPanel");
        cards.add(visualizeCE, "VisualizeCE");
        cards.add(visualizeDN, "VisualizeDN");

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
