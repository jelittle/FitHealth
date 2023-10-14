package userData;

/**
 * userData.ExerciseLog holds a single entry period of exercise.contains everything needed to
 * display the exercise log graph and calculates calories burned.
 *
 *
 */
public class ExerciseLog {
    private final int id;
    private final int StartTime;
    private final int endTime;
    private final int duration;
    private  float met;
    private String Exercise;
    private String intensity;
    private User user;
    private int userid;
    private int CaloriesBurned;
    private final int metid;
    public ExerciseLog(int id,int StartTime, int EndTime, String Exercise, String intensity, float met, int metid,int userid){
        this.StartTime = StartTime;
        this.endTime = EndTime;
        this.duration = EndTime-StartTime;
        this.Exercise = Exercise;
        this.intensity = intensity;
        this.met = met;
        this.metid = metid;
        this.id =id;
        this.userid=userid;
    }
    public ExerciseLog(int id, int userid, int startTime, int endTime, int metid){
        this.id = id;
        this.userid = userid;
        this.StartTime = startTime;
        this.endTime = endTime;
        this.metid = metid;
        this.duration = endTime - startTime;

    }



    public int getStartTime() {
        return StartTime;
    }

    public int getDuration() {
        return duration;
    }

    public double getMet() {
        return met;
    }

    public String getExercise() {
        return Exercise;
    }

    public String getIntensity() {
        return intensity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCaloriesBurned() {
        return CaloriesBurned;
    }
    public int getUserId() {
        return userid;
    }

    public long getEndTime() {
        return StartTime + duration;
    }
    public int getMetId() {
        return metid;
    }
    public int getId() {
        return id;
    }
//    public void setCaloriesBurned(int caloriesBurned) {
//        CaloriesBurned = caloriesBurned;
//    }
}
