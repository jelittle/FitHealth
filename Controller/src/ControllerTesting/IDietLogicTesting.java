package ControllerTesting;

import Controller.DietLogic.*;
import Controller.UnixTime;
import DietLogs.DietLogEntry;
import Database.*;
import DietLogs.Ingredient;
import DietLogs.MealIngredients;
import DietLogs.NutrientInfo;


import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

//tetsting for IDietLogic
//test cases:

public class IDietLogicTesting {

    public static void main(String[] args) {

        DietLogic dietLogic = new DietLogic();
        DietLog dietTable = IDietClientFactory.getIDietLogClient();

        //test addMeal
        try {

            ArrayList<Integer> date = new ArrayList<Integer>();
            date.add(2020);
            date.add(1);
            date.add(2);
            date.add(1);
            date.add(1);


            ArrayList<Integer> date1 = new ArrayList<Integer>();
            date1.add(2021);
                date1.add(1);
                date1.add(2);
                date1.add(1);
                date1.add(1);


            HashMap<String, Float > allighment = dietLogic.alignmentWithCanadaFoodGuide(date, date1, 1);
            for (String key : allighment.keySet()) {
                System.out.println("nutrient: " + key + " amount: " + allighment.get(key));
            }

        }
            catch (Exception e) {
                System.out.println("addMeal mmmmm");
        }
    }
}
