package ControllerTesting;

import Controller.ExerciseLogic.ExerciseLogic;
import Controller.ExerciseLogic.IExerciseLogic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IExerciseLogicTest {
    private IExerciseLogic exerciseLogic= new ExerciseLogic();





    @org.junit.jupiter.api.Test
    void GetExercises_CorrectDate() {
        ArrayList<Integer> startDate = new ArrayList<>();
        startDate.add(2020);
        startDate.add(11);
        startDate.add(1);
        startDate.add(0);
        startDate.add(0);
        ArrayList<Integer> endDate = new ArrayList<>();
        endDate.add(2023);
        endDate.add(11);
        endDate.add(30);
        endDate.add(23);
        endDate.add(59);
        exerciseLogic.getExerciseByDateRange(1,startDate,endDate);

    }

    @org.junit.jupiter.api.Test
    void getGraphData() {
    }

    @org.junit.jupiter.api.Test
    void deleteExerciseLog() {
    }

    @org.junit.jupiter.api.Test
    void updateExerciseLog() {
    }

    @org.junit.jupiter.api.Test
    void addExerciseLog() {
    }

    @org.junit.jupiter.api.Test
    void getExerciseTypes() {
    }

    @org.junit.jupiter.api.Test
    void getIntensityOptions() {
    }

    @org.junit.jupiter.api.Test
    void predictFatLoss() {
    }
}