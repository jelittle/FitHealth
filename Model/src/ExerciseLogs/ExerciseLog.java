package ExerciseLogs;

/**
 * ExerciseLogs.ExerciseLog holds a single entry period of exercise.contains everything needed to
 * display the exercise log graph and calculates calories burned.
 *
 *
 */
public class ExerciseLog {
    private final int id;
    private float userWeight;
    private int startTime;
    private int endTime;
    private int duration;

    private Met met=null;

    private int userid;
    private int CaloriesBurned=0;
    private int metid;
    public ExerciseLog(int id,int startTime, int endTime, int metid,int userid){
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = endTime-startTime;
        this.metid = metid;
        this.id =id;
        this.userid=userid;
    }

    public ExerciseLog(int id, int startTime, int endTime, int metid, int userid, Met met) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = endTime-startTime;
        this.metid = metid;
        this.id =id;
        this.userid=userid;
        this.met = met;

    }

    public ExerciseLog(ExerciseLog Exerciselog){
        this.startTime = Exerciselog.startTime;
        this.endTime = Exerciselog.endTime;
        this.duration = Exerciselog.duration;
        this.metid = Exerciselog.metid;
        this.id =Exerciselog.id;
        this.userid=Exerciselog.userid;
        this.met = Exerciselog.met;
    }


    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMet(Met met) {
        this.met = met;
    }
    public Met getMet() {
        return met;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public double getMetValue() {
        return met.metValue();
    }

    public String getExercise() {
        return met.exercise();
    }

    public String getIntensity() {
        return met.intensity();
    }

    public int getCaloriesBurned() {
        if(CaloriesBurned==0){
            CaloriesBurned=(int)((getMetValue()*3.5*userWeight*getDuration()/60)/200);
        }
        return CaloriesBurned;
    }
    public int getUserId() {
        return userid;
    }

    public int getEndTime() {
        return startTime + duration;
    }
    public int getMetId() {
        return metid;
    }
    public int getId() {
        return id;
    }
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setMetId(int metId) {
        metid = metId;
    }

    public void setUserId(int userId) {
        userid = userId;
    }
    public void setUserWeight(float userWeight) {
        this.userWeight = userWeight;
    }
}
