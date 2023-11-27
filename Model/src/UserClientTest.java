import Database.UserClient;
import org.junit.jupiter.api.Test;
import userData.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserClientTest {
    UserClient db = new UserClient();

    User userA = new User("Joshua", "joshua", 300, 10f, 0, 2);

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
        User userB = db.getUserById(2);
        System.out.println(userB.getName());
        System.out.println(userB.getPassword());
        System.out.println(userB.getHeight());
        System.out.println(userB.getWeight());
        System.out.println(userB.getAge());
        assertTrue(compareUsers(userA, userB));
    }

    @Test
    void setAndDeleteUser() {

        User userB = new User("Tods", "Logs", 320, 6f, 14, 3);
        System.out.println("setting user"+ userB.getName());
        db.setUser(userB);
        User userD = db.getUserByUserName(userB.getName());
        assertTrue(compareUsers(userB, userD));
        db.deleteUser(userD);
        System.out.println("deleting user"+ userD.getName());
        assertTrue(!db.checkforExistingUsername(userB.getName()));

    }

    @Test
    void updateUser() {
        User original = db.getUserById(2);
        User userC = new User("Tods", "Logs", 320, 6f, 14, 2);
        db.updateUser(userC);
        User userD = db.getUserByUserName(userC.getName());
        assertTrue(compareUsers(userC, userD));
        db.updateUser(original);
        userD = db.getUserByUserName("Joshua");
        assertTrue(compareUsers(original, userD));
    }

    @Test
    void getUserByUserName() {
        User userB = db.getUserByUserName("Joshua");
        assertTrue(compareUsers(userA, userB));
    }

    @Test
    void checkforExistingUsername() {
        System.out.println("checking for existing username");
        assertTrue(db.checkforExistingUsername("Joshua"));
    }

}