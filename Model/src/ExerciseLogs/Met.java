package ExerciseLogs;

public record Met(int id, String exercise, String intensity, float metValue) {
    public int getid() {
        return id;
    }
    public String getExercise() {
        return exercise;
    }
}


