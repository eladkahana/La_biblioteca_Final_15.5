/**
 * The AnalyticsScreen class extends JPanel and represents the analytics screen of the application.
 * The screen displays two bar charts of borrowed books, one by hour and the other by day. It also displays
 * information about the most borrowed book, monthly loans, and daily loans.
 */

package comMain.GUI;

import com.fasterxml.jackson.core.JsonProcessingException;
import comMain.SwingClient.AnalysticClient;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AnalyticsScreen extends JPanel {

    private  JFreeChart borrowedByDayChart;
    private  JFreeChart borrowedByHourChart;
    private  JLabel mostBorrowedValueLabel;
    private  JLabel monthlyLoansValueLabel;
    private  JLabel dailyLoansValueLabel;
    private CategoryDataset borrowedByHourData;
    private CategoryDataset borrowedByDayData;
    private JPanel panel;
    private JPanel infoPanel;


    /**
     * Constructs the analytics screen with two bar charts of borrowed books by hour and by day, and information
     * about the most borrowed book, monthly loans, and daily loans.
     */
    public AnalyticsScreen() throws JsonProcessingException {



        setLayout(new BorderLayout());

        // Create data for borrowed books graph by hour
         borrowedByHourData = createBorrowedBooksByHourData();

        // Create chart for borrowed books graph by hour
         borrowedByHourChart = ChartFactory.createBarChart(
                "Borrowed Books by Hour", // chart title
                "Hour", // x axis label
                "Number of Books", // y axis label
                borrowedByHourData, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
        );

        // Customize the chart
        CategoryPlot borrowedByHourPlot = (CategoryPlot) borrowedByHourChart.getPlot();
        CategoryAxis borrowedByHourDomainAxis = borrowedByHourPlot.getDomainAxis();
        borrowedByHourDomainAxis.setCategoryMargin(0.25);
        NumberAxis borrowedByHourRangeAxis = (NumberAxis) borrowedByHourPlot.getRangeAxis();
        borrowedByHourRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        borrowedByHourRangeAxis.setUpperMargin(0.10);

        // Create chart panel for borrowed books graph by hour
        ChartPanel borrowedByHourChartPanel = new ChartPanel(borrowedByHourChart);
        borrowedByHourChartPanel.setPreferredSize(new Dimension(500, 270));

        // Create data for borrowed books graph by day
         borrowedByDayData = createBorrowedBooksByDayData();

        // Create chart for borrowed books graph by day
         borrowedByDayChart = ChartFactory.createBarChart(
                "Borrowed Books by Day", // chart title
                "Day", // x axis label
                "Number of Books", // y axis label
                borrowedByDayData, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
        );

        // Customize the chart
        CategoryPlot borrowedByDayPlot = (CategoryPlot) borrowedByDayChart.getPlot();
        CategoryAxis borrowedByDayDomainAxis = borrowedByDayPlot.getDomainAxis();
        borrowedByDayDomainAxis.setCategoryMargin(0.25);
        NumberAxis borrowedByDayRangeAxis = (NumberAxis) borrowedByDayPlot.getRangeAxis();
        borrowedByDayRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        borrowedByDayRangeAxis.setUpperMargin(0.10);

        // Create chart panel for borrowed books graph by day
        ChartPanel borrowedByDayChartPanel = new ChartPanel(borrowedByDayChart);
        borrowedByDayChartPanel.setPreferredSize(new Dimension(500, 270));


        //Create panel for analytics screen
        panel = new JPanel(new GridLayout(1, 2));

// Add borrowed books graph panel to left side of panel



        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

// Add borrowed books by hour graph panel to left side of panel
        JPanel borrowedByHourPanel = new JPanel(new BorderLayout());
        borrowedByHourPanel.add(borrowedByHourChartPanel, BorderLayout.CENTER);
        panel.add(borrowedByHourPanel);

// Add borrowed books by day graph panel to left side of panel
        JPanel borrowedByDayPanel = new JPanel(new BorderLayout());
        borrowedByDayPanel.add(borrowedByDayChartPanel, BorderLayout.CENTER);
        panel.add(borrowedByDayPanel);




        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

// Create label for most borrowed book
        infoPanel.add(Box.createVerticalStrut(100));
        JLabel mostBorrowedTitleLabel = new JLabel("Most Borrowed Book:");
        mostBorrowedTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(mostBorrowedTitleLabel);

         mostBorrowedValueLabel = new JLabel(AnalysticClient.MostReservedBook());
        mostBorrowedValueLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        infoPanel.add(mostBorrowedValueLabel);
        infoPanel.add(Box.createVerticalStrut(100)); // Add spacing

// Create label for monthly loans
        JLabel monthlyLoansTitleLabel = new JLabel("Monthly Loans:");
        monthlyLoansTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(monthlyLoansTitleLabel);

         monthlyLoansValueLabel = new JLabel(AnalysticClient.MonthlyResrvesAmount());
        monthlyLoansValueLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        infoPanel.add(monthlyLoansValueLabel);
        infoPanel.add(Box.createVerticalStrut(100)); // Add spacing

// Create label for daily loans
        JLabel dailyLoansTitleLabel = new JLabel("Daily Loans:");
        dailyLoansTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(dailyLoansTitleLabel);

         dailyLoansValueLabel = new JLabel(AnalysticClient.todayResrvesAmount());
        dailyLoansValueLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        infoPanel.add(dailyLoansValueLabel);

// Create a new JPanel to hold the panel and infoPanel
        JPanel mainPanel = new JPanel(new BorderLayout());

// Set the background color of the main panel
        mainPanel.setBackground(Color.WHITE);

// Add some padding around the panel
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

// Add the panel and infoPanel to the main panel
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.EAST);

// Add the main panel to the content pane of the frame or container
        add(mainPanel);






    }


        // Create sample data for borrowed books graph by hour
    private CategoryDataset createBorrowedBooksByHourData() throws JsonProcessingException {

        List<Object[]> data = AnalysticClient.displayReservesByHours();


        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] rowData : data) {
            Integer value = (Integer) rowData[1];
            String hour = (rowData[0].toString());
            dataset.addValue(value, "Reserves", hour);
        }
        return dataset;
    }

    // Create sample data for borrowed books graph by day
    private CategoryDataset createBorrowedBooksByDayData() throws JsonProcessingException {
        List<Object[]> data = AnalysticClient.displayReservesByDays();


        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] rowData : data) {
            Integer value = (Integer) rowData[1];
            String day = rowData[0].toString();
            dataset.addValue(value, "Reserves", day);
        }
        return dataset;
    }




    public void refersh() throws JsonProcessingException {
        borrowedByHourData = createBorrowedBooksByHourData();
        borrowedByDayData = createBorrowedBooksByDayData();

        // Update the chart panels
        borrowedByHourChart.getCategoryPlot().setDataset(borrowedByHourData);
        borrowedByDayChart.getCategoryPlot().setDataset(borrowedByDayData);

        // Update the labels
        mostBorrowedValueLabel.setText(AnalysticClient.MostReservedBook());
        monthlyLoansValueLabel.setText(AnalysticClient.MonthlyResrvesAmount());
        dailyLoansValueLabel.setText(AnalysticClient.todayResrvesAmount());

        // Repaint the window
        revalidate();
        repaint();

    }


}
