import Database.UserTestClient;
import org.junit.jupiter.api.Test;
import userData.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTestClientTest {
    UserTestClient db = new UserTestClient();

    User userA = new User("Lenny", "12345", "Male",156, 82.4F, 26,2);

    boolean compareUsers(User userA, User userB){
        if (userA.getName().equals(userB.getName()) && userA.getPassword().equals(userB.getPassword()) &&
                userA.getHeight() == userB.getHeight() && userA.getWeight() == userB.getWeight() &&
                userA.getAge() == userB.getAge())
            return true;
        else
            return false;
    }

    @Test
    void getUserById() {
        db.updateUser(userA);
        User userB = db.getUserById(2);
        assertTrue(compareUsers(userA, userB));
    }

    @Test
    void setAndDeleteUser() {
        User userC = new User("Tods", "Logs", "Male", 320, 6f, 14, 5);
        db.setUser(userC);
        User userD = db.getUserByUserName(userC.getName());
        assertTrue(compareUsers(userC, userD));
        db.deleteUser(userD);
        assertTrue(!db.checkforExistingUsername(userC.getName()));

    }

    @Test
    void updateUser() {
        User userC = new User("Ali", "Logs", "Female", 320, 6f, 14, 2);
        db.updateUser(userC);
        User userD = db.getUserByUserName(userC.getName());
        assertTrue(compareUsers(userC, userD));
    }

    @Test
    void getUserByUserName() {
        User userB = db.getUserByUserName("Lenny");
        assertTrue(compareUsers(userA, userB));
    }

    @Test
    void checkforExistingUsername() {
        db.updateUser(userA);
        assertTrue(db.checkforExistingUsername("Lenny"));
        assertTrue(!db.checkforExistingUsername("LJKGKUV<H"));
    }

}