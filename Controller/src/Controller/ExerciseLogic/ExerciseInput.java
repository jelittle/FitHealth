package Controller.ExerciseLogic;

import ExerciseLogs.ExerciseLog;

import java.util.ArrayList;

/**
 * This class manages exercise log input and delete, builds exerciseLog objects from front end and passes them to dataManager
 * gives exercise options and intensity options for log input
 */
class ExerciseInput {
    private DataManager dataManager= DataManager.getInstance();
    /**
     * Builds an exercise log object from the given parameters
     * @param sTime
     * @param eTime
     * @param exercise
     * @param intensity
     * @param userId
     * @return
     * @throws IllegalArgumentException
     */
    private ExerciseLog buildExerciseLog(int sTime, int eTime, String exercise, String intensity,int userId)throws IllegalArgumentException{
        int metId=0;
        metId = dataManager.getMetId(exercise, intensity).getid();
        if(metId==0)
            throw new IllegalArgumentException("Invalid exercise or intensity");

        return new ExerciseLog(0, sTime, eTime, metId, userId);

    }
    void insertExerciseLog(int sTime, int eTime, String exercise, String intensity,int userId) throws IllegalArgumentException{

            dataManager.addExerciseLog(buildExerciseLog(sTime,eTime,exercise,intensity,userId));



    }
    void deleteExerciseLog(int userId,int exerciseId) throws IllegalArgumentException{

            dataManager.DeleteExerciseLog(exerciseId,userId);


    }
    ArrayList<String> getExerciseOptions(){
        return dataManager.getExerciseList();
    }

    /**
     * Returns the intensity options for a given exercise
     * @param exercise
     * @return
     */
    ArrayList<String> getIntensityOptions(String exercise){
    return dataManager.getIntensityList(exercise);
    }
}
