package Controller.DietLogic;

import Controller.UnixTime;

import java.util.ArrayList;

public class DaysCalculation {

    public int totalDays(ArrayList<Integer> startDate, ArrayList<Integer> endDate) {
        int totalDays = 1;

        totalDays += calculateDaysDifference(startDate.get(2), endDate.get(2), "day");
        totalDays += calculateDaysDifference(startDate.get(1), endDate.get(1), "month");
        totalDays += calculateDaysDifference(startDate.get(0), endDate.get(0), "year");

        return totalDays;
    }

    private int calculateDaysDifference(int start, int end, String unit) {
        switch (unit) {
            case "day" -> {
                return calculateDayDifference(start, end);
            }
            case "month" -> {
                return calculateMonthDifference(start, end);
            }
            case "year" -> {
                return calculateYearDifference(start, end);
            }
            default -> throw new IllegalArgumentException("Invalid unit: " + unit);
        }
    }

    private int calculateDayDifference(int start, int end) {
        return Math.abs(end - start);
    }

    private int calculateMonthDifference(int start, int end) {
        return Math.abs(end - start) * 30;
    }

    private int calculateYearDifference(int start, int end) {
        return Math.abs(end - start) * 365;
    }

    public int fromArrayListToUnixTime(ArrayList<Integer> dateTime) {

        int dateUnixTime;

        try{
            int year=dateTime.get(0);
            int month=dateTime.get(1);
            int day=dateTime.get(2);
            int hour=dateTime.get(3);
            int minute=dateTime.get(4);

            dateUnixTime= UnixTime.getUnixTime(year,month,day,hour,minute);

        }catch(Exception e){
            throw new IllegalArgumentException("startDate must be in form year,month,day,hour,minute");
        }

        return dateUnixTime;
    }

}
