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

public class CFGVisualization extends JPanel {
    
    HashMap<String, Float> alignmentCFG = new HashMap<>();
    IDietLogic dietLogic = new DietLogic();
    DefaultPieDataset dataset = new DefaultPieDataset();

    public CFGVisualization(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception {

        alignmentCFG = dietLogic.alignmentWithCanadaFoodGuide(startDate, EndDate, userId);

        for (Map.Entry<String, Float> entry : alignmentCFG.entrySet()) {

            dataset.setValue(entry.getKey(), entry.getValue());
 
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Canada Food Guide Visualization",
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
