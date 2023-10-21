import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * I (joshua) was given this by at 9pm on the day of due date
 * had to implement it into code and do final prep before deliverable 1
 */
public class ExerciseVisualization {

    //Time series object for plotting daily energy expenditure
    private TimeSeries dailyEnergyExpenditureSeries;
    //We will need to use dailyEnergyExpenditureSeries.addData(new Day(date), energyExpenditure); to add data

    //A sorted map to store all the daily energy expenditure data
    private SortedMap<Date, Double> energyExpenditureData;

    public ExerciseVisualization(){
        
        //Initializing the time series
        this.dailyEnergyExpenditureSeries = new TimeSeries("Daily Energy Expenditure");
        //Initializing the sorted map
        this.energyExpenditureData = new TreeMap<>();
    }

    //Method to update the time series with data points that fall within the specified date range
    private void updateSeries(Date startDate, Date endDate) {

        dailyEnergyExpenditureSeries.clear();
        for (Date current : energyExpenditureData.keySet()) {
            if (!current.before(startDate) && !current.after(endDate)) {
                dailyEnergyExpenditureSeries.add(new Day(current), energyExpenditureData.get(current));
            }
        }
    }

     //Method to add a new data point to the map
     public void addData(Date date, double energyExpenditure) {

        energyExpenditureData.put(date, energyExpenditure);
    }

    //Method to plot the daily energy expenditure data for a specified date range
    public void plotExerciseData(Date startDate, Date endDate){

        updateSeries(startDate, endDate); // Filter data based on the date range

        //Creating a dataset for the time series
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(dailyEnergyExpenditureSeries);

        //Creating the chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Exercise Visualization",
                "Date",
                "Energy Expenditure (Calories)",
                dataset,
                false,  // Include legend
                true,   // Include tooltips
                false   // Include URLs
        );

        //Creating a frame to display the chart
        JFrame frame = new JFrame("Exercise Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setLocationRelativeTo(null); //Center the frame
        frame.setVisible(true); //Display the frame
    }

    //For testing - we will replce with data we receive from user
    public static void main(String[] args){

        ExerciseVisualization visualization = new ExerciseVisualization();

        // Adding some sample data
        Date sampleDate1 = new Date();
        visualization.addData(sampleDate1, 2000);
        Date sampleDate2 = new Date(sampleDate1.getTime() + 86400000L); //Next day
        visualization.addData(sampleDate2, 2100);

        // Defining a time period and plotting the data for that period
        Date startDate = sampleDate1;
        Date endDate = new Date(sampleDate2.getTime() + 86400000L);
        visualization.plotExerciseData(startDate, endDate);
    }
}
