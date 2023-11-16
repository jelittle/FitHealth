package Database;

public class IDietClientFactory {
    //build a static factory class to return the correct client


    public static DietLog getIDietLogClient() {
        if(TestIdentifier.isTest()) {
            return new IdietLogClientTest();
        }
        else {
            return new IDietLogClient();
        }
    }
    public static DietLog getIDietLogClient(boolean isTest) {
        System.out.println("isTest: " + isTest);
        if(isTest) {
            TestIdentifier.startTest();

            return new IdietLogClientTest();
        }
        else {
            return new IDietLogClient();
        }
    }

//    public static DietLog getIDietClient() {
//        if(TestIdentifier.isTest()) {
//            return new IdietLogClientTest();
//        }
//        else {
//            return new IDietLogClient();
//        }
//    }
}
