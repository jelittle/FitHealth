package ControllerTesting;

import Controller.DietLogic.*;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//tetsting for IDietLogic
//test cases:

public class IDietLogicTesting {

    public static void main(String[] args) {

        IDietLogic dietLogic = new DietLogic();

        //test case 1: test get All Ingredients Available

        ArrayList<String> allIngredientsAvailable = new ArrayList<>();
        try {
            allIngredientsAvailable = dietLogic.getAllIngredientsAvailable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String ingredient : allIngredientsAvailable) {
            System.out.println(ingredient);
        }

    }



}
