package ui;

import model.ExerciseLog;
import model.Workout;

import javax.swing.*;
import java.awt.*;

// represents menu for when user presses display workouts button
public class DisplayWorkoutsGUI {

    private ExerciseLog exerciseLog;
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JLabel line;


    // EFFECTS: constructs display workouts page
    public DisplayWorkoutsGUI(ExerciseLog exerciseLog) {
        this.exerciseLog = exerciseLog;

        frame = new JFrame();

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1, 2, 2));
        label = new JLabel("Displaying workouts in Exercise Log:");
        line = new JLabel("---------------------------------------------------");
        line.setForeground(new Color(184, 188, 255));
        panel.add(label);
        panel.add(line);

        frame.add(panel, BorderLayout.NORTH);
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setTitle("THE GAINS BOOK");
        frame.pack();
        frame.setVisible(true);

        displayAllWorkouts();

        Icon imgIcon = new ImageIcon("lib/boxer.gif");
        JLabel pic = new JLabel(imgIcon);
        frame.add(pic);
        pic.setVisible(true);

        new WeightChart("Weight Tracker", exerciseLog);
    }

    // MODIFIES: this
    // EFFECTS: prints past workouts from exercise log onto screen
    protected void displayAllWorkouts() {

        for (Workout w : exerciseLog.getWorkouts()) {
            JLabel line = new JLabel("---------------------------------------------------");
            line.setForeground(new Color(182, 134, 212));

            JLabel workoutLabel;
            workoutLabel = new JLabel("You exercised on " + w.getDate() + " doing " + w.getTypeofExercise()
                    + " for " + w.getTime() + " minutes");
            panel.add(workoutLabel);

            JLabel calories;
            if (w.getCaloriesBurned() != 0.0) {
                calories = new JLabel("Calorie burn: " + w.getCaloriesBurned());
            } else {
                calories = new JLabel("You have not calculated calorie burn for this workout yet");
            }
            panel.add(calories);
            panel.add(line);
        }


    }

}
