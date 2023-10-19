package Database;

public class IDietClientFactory {
    //build a static factory class to return the correct client

    public static DietLog getIDietClient() {
        if(TestIdentifier.isTest()) {
            return new IdietLogClientTest();
        }
        else {
            return new IDietLogClient();
        }
    }
}
