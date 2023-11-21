package ControllerTesting;

import Controller.DietLogic.*;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//tetsting for IDietLogic
//test cases:

public class IDietLogicTesting {

    private IDietLogic dietLogic = new DietLogic();

    @org.junit.jupiter.api.Test
    void inputCorrectedMeal(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception {
        //test case 1: input correct date
        ArrayList<Integer> startDate1 = new ArrayList<>();
        startDate1.add(2020);
        startDate1.add(11);
        startDate1.add(1);
        startDate1.add(0);
        startDate1.add(0);
        ArrayList<Integer> endDate1 = new ArrayList<>();
        endDate1.add(2023);
        endDate1.add(11);
        endDate1.add(30);
        endDate1.add(23);
        endDate1.add(59);
        dietLogic.mealsByDateRange(startDate1, endDate1, 1);

        for (int i = 0; i < startDate1.size(); i++) {
            System.out.println(startDate1.get(i));
        }
    }



}
