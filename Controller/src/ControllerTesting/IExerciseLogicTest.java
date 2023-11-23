package ControllerTesting;

import Controller.ExerciseLogic.ExerciseLogic;
import Controller.ExerciseLogic.IExerciseLogic;

import java.util.ArrayList;
import java.util.HashMap;

class IExerciseLogicTest {
    private IExerciseLogic exerciseLogic= new ExerciseLogic();





    @org.junit.jupiter.api.Test
    void GetExercisesByDateRange(){
        ArrayList<Integer> startDate= new ArrayList<Integer>();
        startDate.add(1969);
        startDate.add(1);
        startDate.add(2);
        startDate.add(1);
        startDate.add(1);
        ArrayList<Integer> endDate= new ArrayList<Integer>();
        endDate.add(2025);
        endDate.add(1);
        endDate.add(2);
        endDate.add(1);
        endDate.add(1);

        ArrayList<HashMap> exerciseLogs= exerciseLogic.getExerciseByDateRange(1,startDate,endDate);
        // print list
        System.out.println("exerciseLogs: " + exerciseLogs);

    }


    @org.junit.jupiter.api.Test
    void addAndDeleteExerciseLog() {
        ArrayList<Integer> startDate= new ArrayList<Integer>();
        startDate.add(2020);
        startDate.add(1);
        startDate.add(2);
        startDate.add(1);
        startDate.add(1);
        ArrayList<Integer> endDate= new ArrayList<Integer>();
        endDate.add(2021);
        endDate.add(1);
        endDate.add(2);
        endDate.add(1);
        endDate.add(1);

        try {
            exerciseLogic.addExerciseLog(startDate,endDate,"Running","high",1);

            ArrayList<HashMap> exerciseLogs= exerciseLogic.getExerciseByDateRange(1,startDate,endDate);
        // print list
             System.out.println("exerciseLogs: " + exerciseLogs);

            exerciseLogic.deleteExerciseLog(1,23);
            exerciseLogs= exerciseLogic.getExerciseByDateRange(1,startDate,endDate);
            // print list
            System.out.println("exerciseLogs: " + exerciseLogs);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }





    @org.junit.jupiter.api.Test
    void getExerciseTypes() {

        System.out.println(exerciseLogic.getExerciseOptions());
    }

    @org.junit.jupiter.api.Test
    void getIntensityOptions() {

        System.out.println(exerciseLogic.getIntensityOptions("running"));
    }

    @org.junit.jupiter.api.Test
    void predictFatLoss() {
        ArrayList<Integer> startDate= new ArrayList<Integer>();
        startDate.add(1969);
        startDate.add(1);
        startDate.add(2);
        startDate.add(1);
        startDate.add(1);
        ArrayList<Integer> endDate= new ArrayList<Integer>();
        endDate.add(2025);
        endDate.add(1);
        endDate.add(2);
        endDate.add(1);
        endDate.add(1);
        try {
            System.out.println(exerciseLogic.predictFatLoss(1, endDate));
        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
}