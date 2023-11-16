//import Database.IExerciseClient;
//import Database.IExerciseClientFactory;
//import Database.TestIdentifier;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import ExerciseLogs.ExerciseLog;
//import ExerciseLogs.Met;
//import userData.User;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//
//    class ExerciseInterfaceTest {
//
//
//    private static final User user1 = new User("bjorn", "bjorn", 180, 80, 20, 1);
//
//    private static IExerciseClient db;
//    private static final Met met1=new Met(3, "walking", "low", 2);
//    private static final Met met2=new Met(5, "walking", "high", 5);
//    private static final ExerciseLog exerciseLog1 = new ExerciseLog(2,100, 102, 5,1,met1);
//
//    private static final ExerciseLog exerciseLog2 = new ExerciseLog(3,100, 103, 4,1, met2);
//    //add user to exerciselog 1 and 2 before every test
//    @BeforeAll
//    static void setUp(){
//        TestIdentifier.startTest();
//        //commenting the above out connects to real database
//        db = IExerciseClientFactory.getIExerciseClient();
//
//
//
//    }
//
//
//    //I know I could do better than this but I'm lazy
//    //id is not compared
//    //user id is not compared
//    //user is not compared because may be removed.
//    //these were build with user id 1 in mind, if if exercise logs are added to user 1, these will probably fail
//    boolean compareExerciseLog(ExerciseLog exerciseLogA, ExerciseLog exerciseLogB) {
//        return (exerciseLogA.getDuration() == exerciseLogB.getDuration() &&
//                exerciseLogA.getStartTime() == exerciseLogB.getStartTime() &&
//                exerciseLogA.getMetId() == exerciseLogB.getMetId());
//    }
//
//    void printComparedLogs(ExerciseLog exerciseLogA, ExerciseLog exerciseLogB){
//        System.out.println("A");
//        System.out.println("duration="+exerciseLogA.getDuration());
//        System.out.println(exerciseLogA.getStartTime());
//        System.out.println(exerciseLogA.getMetId());
//        System.out.println("B");
//        System.out.println(exerciseLogB.getDuration());
//        System.out.println(exerciseLogB.getStartTime());
//        System.out.println(exerciseLogB.getMetId());
//
//
//
//    }
//
//    @Test
//    void getExerciseLogById() {
//        ExerciseLog exerciseLog = db.getExerciseLogById(2);
//        printComparedLogs(exerciseLog, exerciseLog1);
//        assertTrue(compareExerciseLog(exerciseLog, exerciseLog1));
//
//    }
//
//    @Test
//    void getExerciseLogsByDateRangeAndIdGetAll() {
//
//        ArrayList<ExerciseLog> exerciseLogs = db.getExerciseLogsByDateRangeAndUserId(99,104, 1);
//        assertEquals(2, exerciseLogs.size());
//        assertTrue(compareExerciseLog(exerciseLogs.get(0), exerciseLog1));
//        assertTrue(compareExerciseLog(exerciseLogs.get(1), exerciseLog2));
//
//
//    }
//    @Test
//    void getExerciseLogsByDateRangeAndIdMissOne() {
//
//        ArrayList<ExerciseLog> exerciseLogs = db.getExerciseLogsByDateRangeAndUserId(100, 102, 1);
//        assertEquals(1, exerciseLogs.size());
//        assertTrue(compareExerciseLog(exerciseLogs.get(0), exerciseLog1));
//
//    }
//
//    @Test
//    void insertAndDeleteExerciseLog() {
//
//        ExerciseLog exerciseLog = new ExerciseLog(0,1,2,4,1);
//
//        assertInt(db.InsertExerciseLog(exerciseLog));
//        ExerciseLog exerciseLog2 = db.getExerciseLogsByDateRangeAndUserId(1,2, 1).get(0);
//        assertTrue(compareExerciseLog(exerciseLog, exerciseLog2));
//        db.DeleteExerciseLog(exerciseLog2);
//    }
//
//
//}
