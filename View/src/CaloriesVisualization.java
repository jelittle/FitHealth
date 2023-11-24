import DietLogs.DietLogEntry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import Controller.DietLogic.DietLogic;
import Controller.DietLogic.IDietLogic;
import Controller.ExerciseLogic.ExerciseLogic;
import Controller.ExerciseLogic.IExerciseLogic;

public class CaloriesVisualization extends JPanel {

    static ArrayList<HashMap> dailyExercise = new ArrayList<>();
    static IExerciseLogic exerciseLogic = new ExerciseLogic();
    
    static ArrayList<DietLogEntry> dailyNutrient = new ArrayList<>();
    static IDietLogic dietLogic = new DietLogic();

    public CaloriesVisualization(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception {

        dailyExercise = exerciseLogic.getExerciseByDateRange(userId, startDate, EndDate);
        dailyNutrient = dietLogic.mealsByDateRange(startDate, EndDate, userId);

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Energy Expenditure Over Time");

        for (HashMap<String, String> nutrientMap : dailyExercise) {
            String caloriesBurned = nutrientMap.get("caloriesBurned");
            String startDate2 = nutrientMap.get("startDate");

            double caloriesBurned2 = Double.parseDouble(caloriesBurned);
            double startDate3 = Double.parseDouble(startDate2);

            series1.add(startDate3, caloriesBurned2);
        }

        XYSeries series2 = new XYSeries("Calorie Intake Over Time");

        for (HashMap<String, String> nutrientMap : dailyExercise) {
            // String calorieIntake = nutrientMap.get("caloriesBurned");
            // String startDate2 = nutrientMap.get("startDate");

            // double calorieIntake2 = Double.parseDouble(calorieIntake);
            // double startDate3 = Double.parseDouble(startDate2);

            // series1.add(startDate3, calorieIntake2);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Calorie Intake and Energy Expenditure Over Time",
                "Time",
                "Amount of Calories",
                dataset,
                PlotOrientation.VERTICAL,
                true,   
                true,   
                false   
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        this.add(chartPanel);
    }
}
