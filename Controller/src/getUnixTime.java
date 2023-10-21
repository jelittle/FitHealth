import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

class UnixTime {
    static int getUnixTime(int year, int month, int day, int hour, int minute) {
        // Create a Calendar object
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Convert to Unix time
        return (int) (calendar.getTimeInMillis() / 1000L);
    }
    static int getUnixTime(ArrayList<Integer> dateTime) {
        // Create a Calendar object
        if(dateTime.size()!=5){
            throw new IllegalArgumentException("dateTime must be in form year,month,day,hour,minute");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(dateTime.get(0), dateTime.get(1) - 1, dateTime.get(2), dateTime.get(3), dateTime.get(4), 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Convert to Unix time
        return (int) (calendar.getTimeInMillis() / 1000L);
    }
    static int[] fromUnixTime(int unixTime){
        // Create a Calendar object
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis((long) unixTime * 1000L);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Extract date and time components
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Months are zero-based in Java
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new int[]{year, month, day, hour, minute};
    }
    static boolean unixIsSameDate(int first, int last){
        int[] firstDate=fromUnixTime(first);
        int[] lastDate=fromUnixTime(last);
        for(int i=0;i<3;i++){
            if(firstDate[i]!=lastDate[i]){
                return false;
            }
        }
        return true;

    }
}