package Database;

import org.junit.jupiter.api.Test;
import userData.ExerciseLog;
import userData.Met;
import userData.User;

import java.util.ArrayList;

class TestDatabaseTest {
    TestDatabase db =new TestDatabase();

    @Test
    void getObjectListById() {
        db.getObjectListByUserId("user",1);
        db.getObjectListByUserId("exercise",1);
    }

    @Test
    void getTable() {

        ArrayList<User> userTable = (ArrayList<User>) db.getTable("user");
        ArrayList<ExerciseLog> exerciseTable = (ArrayList<ExerciseLog>) db.getTable("exercise");
        ArrayList<DietLog> dietTable = (ArrayList<DietLog>) db.getTable("diet");
        //for every user in the user table
        for (User user: userTable){
            System.out.println(user.getName());
            //for every execercise log with id==user.getID()
            for (ExerciseLog exerciseLog: exerciseTable){
                if(exerciseLog.getUserId()==user.getID()){
                    Met met=(Met) db.getTableEntityById("met",exerciseLog.getMetId());
                    System.out.println(met.exercise() );
                }
            }
        }



    }
}