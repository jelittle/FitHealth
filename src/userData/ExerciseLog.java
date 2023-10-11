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
    private final int duration;
    private final double met;
    private final String Exercise;
    private final String intensity;
    private User user;
    private int CaloriesBurned;
    private final int metid;
    public ExerciseLog(int id,int StartTime, int duration, String Exercise, String intensity, double met, int metid){
        this.StartTime = StartTime;
        this.duration = duration;
        this.Exercise = Exercise;
        this.intensity = intensity;
        this.met = met;
        this.metid = metid;
        this.id =id;


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
        return user.getID();
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
