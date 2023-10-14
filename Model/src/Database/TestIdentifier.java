package Database;

/**
 * Created by Joshua Little
 * Allows database to be set to test mode, which runs fake methods instead of connecting to the database
 */
public class TestIdentifier {
    static boolean test = false;
    public static void startTest() {
        test = true;
    }
    public void endTest() {
        test = false;
    }
    public static boolean isTest() {
        return test;
    }
}
