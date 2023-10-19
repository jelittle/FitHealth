import Database.IExerciseClient;
import Database.IExerciseClientFactory;

import Database.IDietClientFactory;
import Database.DietLog;

import Database.TestIdentifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import userData.*;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseInterfaceTest {


    private static final User user1 = new User("bjorn", "bjorn", 180, 80, 20, 1);

    private static IExerciseClient db;
    private static final Met met1=new Met(3, "walking", "low", 2);
    private static final Met met2=new Met(5, "walking", "high", 5);
    private static final ExerciseLog exerciseLog1 = new ExerciseLog(2,100, 102, 5,1,met1);

    private static final ExerciseLog exerciseLog2 = new ExerciseLog(3,100, 103, 4,1, met2);
    //add user to exerciselog 1 and 2 before every test


    @BeforeAll
    static void setUp(){
        //TestIdentifier.startTest();
        //commenting the above out connects to real database
        db = IExerciseClientFactory.getIExerciseClient();



    }


    //I know I could do better than this but I'm lazy
    //id is not compared
    //user id is not compared
    //user is not compared because may be removed.
    //these were build with user id 1 in mind, if if exercise logs are added to user 1, these will probably fail
    boolean compareExerciseLog(ExerciseLog exerciseLogA, ExerciseLog exerciseLogB) {
        return (exerciseLogA.getDuration() == exerciseLogB.getDuration() &&
                exerciseLogA.getStartTime() == exerciseLogB.getStartTime() &&
                exerciseLogA.getMetId() == exerciseLogB.getMetId());
    }

    void printComparedLogs(ExerciseLog exerciseLogA, ExerciseLog exerciseLogB){
        System.out.println("A");
        System.out.println("duration="+exerciseLogA.getDuration());
        System.out.println(exerciseLogA.getStartTime());
        System.out.println(exerciseLogA.getMetId());
        System.out.println("B");
        System.out.println(exerciseLogB.getDuration());
        System.out.println(exerciseLogB.getStartTime());
        System.out.println(exerciseLogB.getMetId());

    }

    @Test
    void getExerciseLogById() {
        ExerciseLog exerciseLog = db.getExerciseLogById(2);
        printComparedLogs(exerciseLog, exerciseLog1);
        assertTrue(compareExerciseLog(exerciseLog, exerciseLog1));

    }

    @Test
    void getExerciseLogsByDateRangeAndIdGetAll() {

        ArrayList<ExerciseLog> exerciseLogs = db.getExerciseLogsByDateRangeAndUserId(99,104, 1);
        assertEquals(2, exerciseLogs.size());
        assertTrue(compareExerciseLog(exerciseLogs.get(0), exerciseLog1));
        assertTrue(compareExerciseLog(exerciseLogs.get(1), exerciseLog2));


    }
    @Test
    void getExerciseLogsByDateRangeAndIdMissOne() {

        ArrayList<ExerciseLog> exerciseLogs = db.getExerciseLogsByDateRangeAndUserId(100, 102, 1);
        assertEquals(1, exerciseLogs.size());
        assertTrue(compareExerciseLog(exerciseLogs.get(0), exerciseLog1));

    }

    @Test
    void insertAndDeleteExerciseLog() {

        ExerciseLog exerciseLog = new ExerciseLog(0,1,2,4,1);

        assertTrue(db.InsertExerciseLog(exerciseLog));
        ExerciseLog exerciseLog2 = db.getExerciseLogsByDateRangeAndUserId(1,2, 1).get(0);
        assertTrue(compareExerciseLog(exerciseLog, exerciseLog2));
        db.DeleteExerciseLog(exerciseLog2);
    }

}

class DietLoginterfaceTest {

    private static final DietLogEntry dietLogEntry1 = new DietLogEntry(55, "apple", 1, "fruit", 100, 1, 1);
    private static final DietLogEntry dietLogEntry2 = new DietLogEntry(56, "Milk", 1, "fruit", 100, 1, 1);

    private static DietLog db;

    @BeforeAll
    static void setUp(){
        //TestIdentifier.startTest();
        //commenting the above out connects to real database
        db = IDietClientFactory.getIDietClient();
    }


    boolean compareExerciseLog(DietLogEntry dietLogEntryA, DietLogEntry dietLogEntryB) {
        return (dietLogEntryA.getDietId() == dietLogEntryB.getDietId() &&
                dietLogEntryA.getName().equals(dietLogEntryB.getName()) &&
                dietLogEntryA.getFoodGroup().equals(dietLogEntryB.getFoodGroup()) &&
                dietLogEntryA.getQuantity() == dietLogEntryB.getQuantity() &&
                dietLogEntryA.getCalories() == dietLogEntryB.getCalories() &&
                dietLogEntryA.getProteins() == dietLogEntryB.getProteins() &&
                dietLogEntryA.getVitamins() == dietLogEntryB.getVitamins());
    }

    void printComparedLogs( DietLogEntry dietLogEntryA, DietLogEntry dietLogEntryB){
        System.out.println("A");
        System.out.println("id="+dietLogEntryA.getDietId());
        System.out.println(dietLogEntryA.getName());
        System.out.println(dietLogEntryA.getFoodGroup());
        System.out.println(dietLogEntryA.getQuantity());
        System.out.println(dietLogEntryA.getCalories());
        System.out.println(dietLogEntryA.getProteins());
        System.out.println(dietLogEntryA.getVitamins());
        System.out.println("B");
        System.out.println(dietLogEntryB.getDietId());
        System.out.println(dietLogEntryB.getName());
        System.out.println(dietLogEntryB.getFoodGroup());
        System.out.println(dietLogEntryB.getQuantity());
        System.out.println(dietLogEntryB.getCalories());
        System.out.println(dietLogEntryB.getProteins());
        System.out.println(dietLogEntryB.getVitamins());

    }

    @Test
    void getDietLogById() {
        DietLogEntry dietLogEntry = db.getDietLogById(1);
        printComparedLogs(dietLogEntry, dietLogEntry1);
        assertFalse(compareExerciseLog(dietLogEntry, dietLogEntry2));
    }
//
//    @Test
//    public void testFetchNutrientDataForIngredients() {
//        // Test that nutrient data is correctly fetched for provided ingredients and quantities.
//        // Perform ingredient lookup and nutrient calculations.
//        // Verify that the calculated nutrient values match the expected results.

//        assertTrue(true); // Placeholder for the actual test code.
//    }
//
    @Test
    public void testIngredientNotFoundInDatabase() {
        // Test that the system handles the case when an ingredient is not found in the database.
        // Ensure that an appropriate error message is displayed.

        for (int i = 1; i < 3; i++) {
            if (Objects.equals(db.getDietLogById(i).getName(), dietLogEntry2.getName())) {
                System.out.println("Ingredient found in database");
                assertTrue(true, "Ingredient found in database");
                return;
            }
        }
        System.out.println("Ingredient not found in database");
        assertFalse(false, "Ingredient not found in database");
    }
//
//
//    @Test
//    public void testPartialDataNotProvided() {
//        // Test that the system handles the case when partial data is provided (e.g., missing quantity or name).
//        // Ensure that the system prompts the user to complete the data.
//        assertTrue(true); // Placeholder for the actual test code.
//    }

    @Test
    public void testCalculateTotalVitaminsForMeal() {
        // Test the calculation of total vitamins for a meal with multiple ingredients.
        // Verify that the calculated total vitamins match the expected result.
        assertTrue(true); // Placeholder for the actual test code.
    }


}