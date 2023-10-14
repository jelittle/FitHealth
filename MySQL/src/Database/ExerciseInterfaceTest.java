package Database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import userData.ExerciseLog;
import userData.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExerciseInterfaceTest {

    //this is depricated, was following the existing database rows, were deleted
    private static final User user1 = new User("Joshua", "password", 180, 80, 20, 1);
    private static final ExerciseInterface db = new ExerciseLogClient();
    private static final ExerciseLog exerciseLog1 = new ExerciseLog(2,100, 101, "walking","low" , 2, 3,1);


    private static final ExerciseLog exerciseLog2 = new ExerciseLog(3,100, 101, null,null , 0, 4,1);
    //add user to exerciselog 1 and 2 before every test
    @BeforeAll
    static void setUp(){
        exerciseLog1.setUser(user1);
        exerciseLog2.setUser(user1);



    }


    //I know I could do better than this but I'm lazy
    //id is not compared
    //user id is not compared
    //user is not compared because may be removed.
    //these were build with user id 1 in mind, if if exercise logs are added to user 1, these will probably fail
    boolean compareExerciseLog(ExerciseLog exerciseLogA, ExerciseLog exerciseLogB){
        return (exerciseLogA.getDuration() == exerciseLogB.getDuration() &&
                exerciseLogA.getStartTime() == exerciseLogB.getStartTime() &&
                exerciseLogA.getMetId() == exerciseLogB.getMetId());



    }
    @Test
    void getExerciseLogById() {
        ExerciseLog exerciseLog = db.getExerciseLogById(2);

        assertTrue(compareExerciseLog(exerciseLog, exerciseLog1));

    }

    @Test
    void getExerciseLogsByDateRangeAndIdGetAll() {

        ArrayList<ExerciseLog> exerciseLogs = db.getExerciseLogsByDateRangeAndUserId(696106011, 1996106012, 1);
        assertEquals(2, exerciseLogs.size());
        assertTrue(compareExerciseLog(exerciseLogs.get(0), exerciseLog1));
        assertTrue(compareExerciseLog(exerciseLogs.get(1), exerciseLog2));

    }
    @Test
    void getExerciseLogsByDateRangeAndIdMissOne() {

        ArrayList<ExerciseLog> exerciseLogs = db.getExerciseLogsByDateRangeAndUserId(1696106012, 1700000000, 1);
        assertEquals(1, exerciseLogs.size());
        assertTrue(compareExerciseLog(exerciseLogs.get(0), exerciseLog2));

    }

    @Test
    void insertAndDeleteExerciseLog() {

        ExerciseLog exerciseLog = new ExerciseLog(0,1796106011, 1320, "walking","low" , 2, 3,1);
        exerciseLog.setUser(user1);
        assertTrue(db.InsertExerciseLog(exerciseLog));
        ExerciseLog exerciseLog2 = db.getExerciseLogsByDateRangeAndUserId(1796106011, 1796107331, 1).get(0);
        assertTrue(compareExerciseLog(exerciseLog, exerciseLog2));
        db.DeleteExerciseLog(exerciseLog2.getId());
    }


}