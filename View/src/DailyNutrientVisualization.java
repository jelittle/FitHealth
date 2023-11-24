import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import Controller.DietLogic.DietLogic;
import Controller.DietLogic.IDietLogic;

public class DailyNutrientVisualization extends JPanel {

    HashMap<String, Float> dailyNutrient = new HashMap<>();
    IDietLogic dietLogic = new DietLogic();
    DefaultPieDataset dataset = new DefaultPieDataset();

    public DailyNutrientVisualization(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception {

        dailyNutrient = dietLogic.PercentagesOfNutrients(startDate, EndDate, userId);

        for (Map.Entry<String, Float> entry : dailyNutrient.entrySet()) {

            dataset.setValue(entry.getKey(), entry.getValue());
 
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Daily Nutrient Intake Visualization",
                dataset,         
                true,  
                true,
                false);

        // PiePlot plot = (PiePlot) chart.getPlot();

        // Add the chart to a ChartPanel and then to this JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        this.add(chartPanel);
    }
}
