import userData.*;
import Database.DietLog;
import Database.IDietClientFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DietInterfaceTest {



//    private static final DietLogEntry dietLogEntry1 = new DietLogEntry(55, "apple", 1, "fruit", 100, 1, 1);
//    private static final DietLogEntry dietLogEntry2 = new DietLogEntry(56, "Milk", 100, );

    private static DietLog db;

    @BeforeAll
    static void setUp(){
        //TestIdentifier.startTest();
        //commenting the above out connects to real database
        db = IDietClientFactory.getIDietLogClient();
    }


//    boolean compareDietLog(DietLogEntry dietLogEntryA, DietLogEntry dietLogEntryB) {
//        return (dietLogEntryA.getDietId() == dietLogEntryB.getDietId() &&
//                dietLogEntryA.getName().equals(dietLogEntryB.getName()) &&
//                dietLogEntryA.getFoodGroup().equals(dietLogEntryB.getFoodGroup()) &&
//                dietLogEntryA.getStartTime() == dietLogEntryB.getStartTime() &&
//                dietLogEntryA.getEndTime() == dietLogEntryB.getEndTime() &&
//                dietLogEntryA.getUserId() == dietLogEntryB.getUserId() );
//    }
//
//    void printComparedLogs( DietLogEntry dietLogEntryA, DietLogEntry dietLogEntryB){
//
//        System.out.println("DietId: " + dietLogEntryA.getDietId() + " " + dietLogEntryB.getDietId());
//        System.out.println("Name: " + dietLogEntryA.getName() + " " + dietLogEntryB.getName());
//        System.out.println("FoodGroup: " + dietLogEntryA.getFoodGroup() + " " + dietLogEntryB.getFoodGroup());
//        System.out.println("StartTime: " + dietLogEntryA.getStartTime() + " " + dietLogEntryB.getStartTime());
//        System.out.println("EndTime: " + dietLogEntryA.getEndTime() + " " + dietLogEntryB.getEndTime());
//        System.out.println("UserId: " + dietLogEntryA.getUserId() + " " + dietLogEntryB.getUserId());
//
//    }
//
//    boolean calculateTotalN(DietLogEntry dietLogEntryA, DietLogEntry dietLogEntryB) {
//        return (  dietLogEntryA.getFinalCalories() == (dietLogEntryB.getCalories() * (dietLogEntryB.getQuantity() / 100))
//        && dietLogEntryA.getFinalProteins() == (dietLogEntryB.getProteins() * (dietLogEntryB.getQuantity() / 100))
//        && dietLogEntryA.getFinalVitamins() == (dietLogEntryB.getVitamins() * (dietLogEntryB.getQuantity() / 100))
//        );
//    }
//
//    @Test
//    void getDietLogById() {
//        DietLogEntry dietLogEntry1 = db.getDietLogById(1);
//        printComparedLogs(dietLogEntry1, dietLogEntry1);
//        assertTrue(compareDietLog(dietLogEntry1, dietLogEntry1));
//
//    }
////
//    @Test
//    public void testFetchNutrientDataForIngredients() {
//        // Test that nutrient data is correctly fetched for provided ingredients and quantities.
//        // Perform ingredient lookup and nutrient calculations.
//        // Verify that the calculated nutrient values match the expected results.
//        for (int i = 1; i < 3; i++) {
//
//            if (Objects.equals(db.getDietLogById(i).getName(), dietLogEntry2.getName())) {
//                dietLogEntry2.setCalories(db.getDietLogById(i).getCalories());
//                dietLogEntry2.setProteins(db.getDietLogById(i).getProteins());
//                dietLogEntry2.setVitamins(db.getDietLogById(i).getVitamins());
//                assertTrue(calculateTotalN(dietLogEntry2, db.getDietLogById(i))); // Placeholder for the actual test code.
//            }
//        }
//    }
//
//    @Test
//    public void testIngredientNotFoundInDatabase() {
//        // Test that the system handles the case when an ingredient is not found in the database.
//        // Ensure that an appropriate error message is displayed.
//
//        for (int i = 1; i < 3; i++) {
//            if (Objects.equals(db.getDietLogById(i).getName(), dietLogEntry2.getName())) {
//                System.out.println("Ingredient found in database");
//                assertTrue(true, "Ingredient found in database");
//                return;
//            }
//        }
//        System.out.println("Ingredient not found in database");
//        assertFalse(false, "Ingredient not found in database");
//    }
////
////
//    @Test
//    public void testPartialDataNotProvided() {
//        // Test that the system handles the case when partial data is provided (e.g., missing quantity or name).
//        // Ensure that the system prompts the user to complete the data.
//
//        assertTrue(dietLogEntry1.getName().length() != 0 || dietLogEntry1.getQuantity() != 0); // Placeholder for the actual test code.
//    }


}