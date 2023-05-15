/**
 * The AnalyticsScreen class extends JPanel and represents the analytics screen of the application.
 * The screen displays two bar charts of borrowed books, one by hour and the other by day. It also displays
 * information about the most borrowed book, monthly loans, and daily loans.
 */

package comMain.GUI;

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

public class AnalyticsScreen extends JPanel {


    /**
     * Constructs the analytics screen with two bar charts of borrowed books by hour and by day, and information
     * about the most borrowed book, monthly loans, and daily loans.
     */
    public AnalyticsScreen() {

        setLayout(new BorderLayout());

        // Create data for borrowed books graph by hour
        CategoryDataset borrowedByHourData = createBorrowedBooksByHourData();

        // Create chart for borrowed books graph by hour
        JFreeChart borrowedByHourChart = ChartFactory.createBarChart(
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
        CategoryDataset borrowedByDayData = createBorrowedBooksByDayData();

        // Create chart for borrowed books graph by day
        JFreeChart borrowedByDayChart = ChartFactory.createBarChart(
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
        JPanel panel = new JPanel(new GridLayout(1, 2));

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




// Create panel for most borrowed book, monthly loans, and daily loans
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

// Create label for most borrowed book
        JLabel mostBorrowedLabel = new JLabel("Most Borrowed Book: ");
        mostBorrowedLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(mostBorrowedLabel);

// Create label for monthly loans
        JLabel monthlyLoansLabel = new JLabel("Monthly Loans: ");
        monthlyLoansLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(monthlyLoansLabel);

// Create label for daily loans
        JLabel dailyLoansLabel = new JLabel("Daily Loans: ");
        dailyLoansLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(dailyLoansLabel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, infoPanel);

// Add info panel to right side of panel
        add(splitPane);

// Set background color of panel
        panel.setBackground(Color.WHITE);

    }


        // Create sample data for borrowed books graph by hour
    private CategoryDataset createBorrowedBooksByHourData() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(150, "Books", "9");
        dataset.addValue(200, "Books", "10");
        dataset.addValue(400, "Books", "11");
        dataset.addValue(300, "Books", "12");
        dataset.addValue(200, "Books", "13");
        dataset.addValue(150, "Books", "14");
        dataset.addValue(100, "Books", "15");
        dataset.addValue(50, "Books", "16");
        dataset.addValue(20, "Books", "17");
        dataset.addValue(5, "Books", "18");
        return dataset;
    }

    // Create sample data for borrowed books graph by day
    private CategoryDataset createBorrowedBooksByDayData() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(500, "Books", "Monday");
        dataset.addValue(600, "Books", "Tuesday");
        dataset.addValue(700, "Books", "Wednesday");
        dataset.addValue(800, "Books", "Thursday");
        dataset.addValue(900, "Books", "Friday");
        dataset.addValue(700, "Books", "Saturday");
        dataset.addValue(400, "Books", "Sunday");
        return dataset;
    }


}
