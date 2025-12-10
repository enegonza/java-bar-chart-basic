package unit8;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class unit8assignment1 {

    public static void main(String[] args) {
    	
    	String csvFilePath ="C:\\Users\\enzuo_8i6f2u6\\OneDrive\\Documents\\Purdue\\IN300\\csv\\Average_Daily_Traffic_Counts.csv";
    	
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	
    	// BufferedReader text files line-by-line.
    	// Try automatically closes the file when done.
    	try (BufferedReader br = Files.newBufferedReader(Paths.get(csvFilePath))) {
    		String line; 
    		line = br.readLine(); // skipping the header, while loop processes every remaining rows 
    	
    		// parsing
    		while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");  /// Column 1 = traffic count 
                // there are at least 2 columns (street and count)
                if (parts.length < 5) {
                	// if not, skip this line
                    continue;
                } 
                
                // extract street name and count text 
                String street = parts[2].trim(); // Column 3 = street name
                String countText = parts[4].trim(); // Column 5 = traffic count/volume 
                
                double count; 
                
                
                try {
                	count = Double.parseDouble(countText);
                } catch (NumberFormatException e) {
                	System.out.println("Skipping invalid number: " + countText); 
                	continue;
                }
                
                dataset.addValue(count, "Traffic Count", street);
                System.out.println("Street: " + street + " | Count: " + count);

    		}
    		
    	} catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    	
        // create the bar chart from the dataset
        JFreeChart barChart = ChartFactory.createBarChart(
                "Average Daily Traffic Counts",  // chart title
                "Street",                        // x-axis label
                "Count",                         // y-axis label
                dataset                          // data
        );
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        
        JFrame frame = new JFrame("Unit 8 - Java Bar Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}

