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

public class CaloriesExerciseVisualization extends JPanel {

    static ArrayList<HashMap> dailyExercise = new ArrayList<>();
    static IExerciseLogic exerciseLogic = new ExerciseLogic();
    
    static HashMap<String, Float> dailyNutrient = new HashMap<>();
    static IDietLogic dietLogic = new DietLogic(); 

    public CaloriesExerciseVisualization(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception {

        dailyExercise = exerciseLogic.getExerciseByDateRange(userId, startDate, EndDate);
        dailyNutrient = dietLogic.AverageDailyNutrientInfo(startDate, EndDate, userId);

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Energy Expenditure Over Time");

        System.out.println(dailyExercise.size());
        
        
        for (Map.Entry<String, Float> nutrientMap : dailyNutrient.entrySet()) {
            System.out.println(nutrientMap);

            String calorieIntake = nutrientMap.getKey();
            Float startDate2 = nutrientMap.getValue();

            System.out.println(startDate2);

            double calorieIntake2 = Double.parseDouble(calorieIntake);
            double startDate3 = startDate2;


            series1.add(startDate3, calorieIntake2);
        }

        dataset.addSeries(series1);
        

        XYSeries series2 = new XYSeries("Calorie Intake Over Time");

        for (HashMap<String, String> energyMap : dailyExercise) {
            String energyExpenditure = energyMap.get("caloriesBurned");
            String startDate2 = energyMap.get("startDate");

            double energyExpenditure2 = Double.parseDouble(energyExpenditure);
            double startDate3 = Double.parseDouble(startDate2);

            series2.add(startDate3, energyExpenditure2);
        }

        dataset.addSeries(series2);

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
