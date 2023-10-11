/**
 * ExerciseLog holds a single entry period of exercise.contains everything needed to
 * display the exercise log graph and calculates calories burned.
 *
 *
 */
public class ExerciseLog {

    private int StartTime;
    private int duration;
    private int met;
    private String Exercise;
    private String intensity;
    private User user;
    private int CaloriesBurned;
    public ExerciseLog(int StartTime,int duration,String Exercise, String intensity){
        this.StartTime = StartTime;
        this.duration = duration;
        this.Exercise = Exercise;
        this.intensity = intensity;
        this.met = met;
        this.CaloriesBurned = CaloriesBurned;
    }

    public int getStartTime() {
        return StartTime;
    }

    public int getDuration() {
        return duration;
    }

    public int getMet() {
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

//    public void setCaloriesBurned(int caloriesBurned) {
//        CaloriesBurned = caloriesBurned;
//    }
}
