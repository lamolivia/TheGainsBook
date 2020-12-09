//package ui;
//
//import javax.swing.*;
//
//import model.ExerciseLog;
//import model.Workout;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.data.category.DefaultCategoryDataset;
//
//// represents weight chart displaying progress of weight change
//public class WeightChart extends JFrame {
//
//    private ExerciseLog exerciseLog;
//    private static final long serialVersionUID = 1L;
//
//    // constructs weight chart
//    public WeightChart(String title, ExerciseLog exerciseLog) {
//        super(title);
//        this.exerciseLog = exerciseLog;
//
//        // Create dataset
//        DefaultCategoryDataset dataset = createDataset();
//
//        // Create chart
//        JFreeChart chart = ChartFactory.createLineChart(
//                "Weight Tracker", // Chart title
//                "Date", // X-Axis Label
//                "Weight (kg)", // Y-Axis Label
//                dataset
//        );
//
//        ChartPanel panel = new ChartPanel(chart);
//        setContentPane(panel);
//        setWeightChart();
//    }
//
//    // EFFECTS: creates x and y axis data from exercise log
//    private DefaultCategoryDataset createDataset() {
//
//        String series1 = "Weight Tracker";
//
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        for (Workout w: exerciseLog.getWorkouts()) {
//
//            dataset.addValue(w.getWeight(), series1, w.getDate());
//        }
//
//        return dataset;
//    }
//
//    // EFFECTS: sets parameters of chart
//    public void setWeightChart() {
//        SwingUtilities.invokeLater(() -> {
//            this.setAlwaysOnTop(true);
//            this.pack();
//            this.setSize(600, 300);
//            this.setVisible(true);
//        });
//    }
//
//}
