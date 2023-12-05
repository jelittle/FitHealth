//package ControllerTesting;
//
//import Controller.DietLogic.*;
//import Controller.UnixTime;
//import DietLogs.DietLogEntry;
//import Database.*;
//import DietLogs.Ingredient;
//import DietLogs.MealIngredients;
//import DietLogs.NutrientInfo;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import static org.junit.jupiter.api.Assertions.*;
//
////tetsting for IDietLogic
////test cases:
//
//public class IDietLogicTesting {
//
//    public static void main(String[] args) {
//
//        DietLogic dietLogic = new DietLogic();
//        DietLog dietTable = IDietClientFactory.getIDietLogClient();
//
//        //test addMeal
//        try {
//
//            ArrayList<Integer> date = new ArrayList<Integer>();
//            date.add(2020);
//            date.add(1);
//            date.add(2);
//            date.add(1);
//            date.add(1);
//            HashMap<Integer, String> meal = dietLogic.addMeal("Laz", "Lunch", date, 1);
//            for (Integer key : meal.keySet()) {
//                System.out.println("mealId: " + key + " mealName: " + meal.get(key));
//            }
//        } catch (Exception e) {
//            System.out.println("addMeal failed");
//        }
//
//        //test addMeal
////        try {
////
////                ArrayList<Integer> date = new ArrayList<Integer>();
////                date.add(2021);
////                date.add(1);
////                date.add(2);
////                date.add(1);
////                date.add(1);
////                HashMap<Integer, String> meal = dietLogic.addMeal("Pasta", "Breakfast", date, 1);
////        }
////        catch (Exception e) {
////            System.out.println("addMeal failed");
////        }
////
////        //test addIngredient
////        try {
////            HashMap<String, Float> ingredient = dietLogic.addIngredient(4, "Milk", 44);
////            HashMap<String, Float> ingredient1 = dietLogic.addIngredient(6, "Egg", 11);
////            HashMap<String, Float> ingredient2 = dietLogic.addIngredient(7, "Apple", 33);
////        }
////        catch (Exception e) {
////            System.out.println("addIngredient failed");
////
////        }
////
////        try {
////            HashMap<String, Float> ingredient = dietLogic.addIngredient(8, "Milk", 44);
////            HashMap<String, Float> ingredient1 = dietLogic.addIngredient(4, "Egg", 11);
////            HashMap<String, Float> ingredient2 = dietLogic.addIngredient(7, "Apple", 33);
////        }
////        catch (Exception e) {
////            System.out.println("addIngredient failed");
////
////        }
//// fdnmsnrnje
////        try {
////
////            ArrayList<Integer> date = new ArrayList<Integer>();
////            date.add(2020);
////            date.add(1);
////            date.add(2);
////            date.add(1);
////            date.add(1);
////
////
////            ArrayList<Integer> date1 = new ArrayList<Integer>();
////            date1.add(2021);
////                date1.add(1);
////                date1.add(2);
////                date1.add(1);
////                date1.add(1);
////
////
////            HashMap<String, Float > allighment = dietLogic.alignmentWithCanadaFoodGuide(date, date1, 1);
////            for (String key : allighment.keySet()) {
////                System.out.println("nutrient: " + key + " amount: " + allighment.get(key));
////            }
////
////        }
////            catch (Exception e) {
////                System.out.println("addMeal mmmmm");
////        }
//
//
////        ArrayList<MealIngredients> mealIngredients = dietTable.getMealIngredientsTable(1);
////        for (MealIngredients mealIngredient : mealIngredients) {
////            System.out.println(mealIngredient.getMealId() + " " + mealIngredient.getIngredientId() );
////        }
//
//
//        //test addNutrientInfo
//
////            ArrayList<Integer> date = new ArrayList<Integer>();
////            date.add(2020);
////            date.add(1);
////            date.add(2);
////            date.add(1);
////            date.add(1);
////
////            int year=date.get(0);
////            int month=date.get(1);
////            int day=date.get(2);
////            int hour=date.get(3);
////            int minute=date.get(4);
////
////            long dateUnixTime = UnixTime.getUnixTime(year,month,day,hour,minute);
////
////
////            ArrayList<Integer> date1 = new ArrayList<Integer>();
////            date1.add(2021);
////            date1.add(1);
////            date1.add(2);
////            date1.add(1);
////            date1.add(1);
////
////            int year1=date1.get(0);
////            int month1=date1.get(1);
////            int day1=date1.get(2);
////            int hour1=date1.get(3);
////            int minute1=date1.get(4);
////
////            long dateUnixTime1 = UnixTime.getUnixTime(year1, month1, day1, hour1, minute1);
////
////            ArrayList<DietLogEntry> food = dietTable.getDietLogsByDateRangeAndUserId(dateUnixTime, dateUnixTime1, 1);
////
////            System.out.println(food.size());
////            for (DietLogEntry dietLogEntry : food) {
////                System.out.println(dietLogEntry.getName());
////            }
//
//
//
//    }
//}
