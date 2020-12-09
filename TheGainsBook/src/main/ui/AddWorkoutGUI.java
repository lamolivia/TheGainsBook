package ui;

import model.DuplicateWorkoutException;
import model.ExerciseLog;
import model.Workout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents page user goes to when add workout button pressed
public class AddWorkoutGUI implements ActionListener {

    private SoundPlayer soundPlayer;
    private String sound;
    private String errorSound;
    private ExerciseLog exerciseLog;
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField userDate;
    private JTextField userMET;
    private JTextField userType;
    private JTextField userHR;
    private JTextField userTime;
    private JTextField userWeight;

    // EFFECTS: constructs add workout menu
    public AddWorkoutGUI(ExerciseLog exerciseLog) {

        sound = "lib/bloop_x (1).wav";
        errorSound = "lib/error.wav";
        soundPlayer = new SoundPlayer(sound);
        this.exerciseLog = exerciseLog;

        frame = new JFrame();

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1, 2, 2));
        label = new JLabel("Add workout");
        panel.add(label);


        frame.add(panel, BorderLayout.NORTH);
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setTitle("THE GAINS BOOK");
        frame.pack();
        frame.setVisible(true);

        setAll();

        JButton enter = new JButton("Enter workout:");
        enter.setBackground(new Color(222, 243, 253));
        enter.setOpaque(true);
        enter.setActionCommand("Enter workout");
        enter.addActionListener(this);
        panel.add(enter);

        frame.setVisible(true);
    }

    // EFFECTS: sets the text boxes for all workout information needed from user
    protected void setAll() {
        setDate();
        setMET();
        setHR();
        setType();
        setTime();
        setWeight();
    }

    // MODIFIES: this
    // EFFECTS: generates text field for date
    protected void setDate() {

        JLabel label = new JLabel("Date (in form \"MM/DD/YYYY at 00:00 AM/PM\"):");
        label.setBounds(10, 20, 80, 20);
        panel.add(label);

        userDate = new JTextField(20);
        userDate.setBounds(100, 20, 165, 20);
        panel.add(userDate);
    }

    // MODIFIES: this
    // EFFECTS: generates text field for MET
    protected void setMET() {

        JLabel label = new JLabel("MET:");
        label.setBounds(10, 20, 80, 20);
        panel.add(label);

        userMET = new JTextField(20);
        userMET.setBounds(100, 20, 165, 20);
        panel.add(userMET);
    }

    // MODIFIES: this
    // EFFECTS: generates text field for exercise type
    protected void setType() {
        JLabel label = new JLabel("Type of workout:");
        label.setBounds(10, 20, 80, 20);
        panel.add(label);

        userType = new JTextField(20);
        userType.setBounds(100, 20, 165, 20);
        panel.add(userType);
    }

    // MODIFIES: this
    // EFFECTS: generates text field for heart rate
    protected void setHR() {
        JLabel label = new JLabel("Heart rate:");
        label.setBounds(10, 20, 80, 20);
        panel.add(label);

        userHR = new JTextField(20);
        userHR.setBounds(100, 20, 165, 20);
        panel.add(userHR);
    }

    // MODIFIES: this
    // EFFECTS: generates text field for time spent exercising
    protected void setTime() {
        JLabel label = new JLabel("Time of workout (min):");
        label.setBounds(10, 20, 80, 20);
        panel.add(label);

        userTime = new JTextField(20);
        userTime.setBounds(100, 20, 165, 20);
        panel.add(userTime);
    }

    // MODIFIES: this
    // EFFECTS: generates text field for user's current weight
    protected void setWeight() {
        JLabel label = new JLabel("Your current weight (kg):");
        label.setBounds(10, 20, 80, 20);
        panel.add(label);

        userWeight = new JTextField(20);
        userWeight.setBounds(100, 20, 165, 20);
        panel.add(userWeight);
    }

    // MODIFIES: this
    // EFFECTS: adds workout to exercise log when add workout button pressed;
    //          generates confirmation text on page
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Enter workout")) {

            Boolean noDuplicate = true;

            String date = userDate.getText();
            Double met = Double.parseDouble(userMET.getText());
            String type = userType.getText();
            Integer hr = Integer.parseInt(userHR.getText());
            Double time = Double.parseDouble(userTime.getText());
            Double weight = Double.parseDouble(userWeight.getText());

            Workout w = new Workout(date, met, type, hr, time, weight);
            try {
                exerciseLog.addWorkout(w);
            } catch (DuplicateWorkoutException ex) {
                noDuplicate = false;
            }

            workoutEntered(noDuplicate);

            clearText();
            panel.revalidate();
            panel.repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets the text to be displayed confirming user attempt to add a workout
    //          If workout is duplicate, produces error message
    private void workoutEntered(Boolean noDuplicate) {

        if (noDuplicate) {
            soundPlayer.playSound(sound);
            JLabel label = new JLabel("Workout added!");
            label.setBounds(10, 20, 80, 25);
            panel.add(label);

            Icon imgIcon = new ImageIcon("lib/omg.gif");
            JLabel pic = new JLabel(imgIcon);
            pic.setBounds(50, 45, 10, 10);
            frame.add(pic);
            pic.setVisible(true);

        } else {

            soundPlayer.playSound(errorSound);
            JLabel label = new JLabel("Error: workout already exists at that date and time");
            label.setBounds(10, 20, 80, 25);
            panel.add(label);

        }
    }

    // MODIFIES: this
    // EFFECTS: clears text from text boxes
    private void clearText() {
        userDate.setText("");
        userMET.setText("");
        userType.setText("");
        userHR.setText("");
        userTime.setText("");
        userWeight.setText("");
    }


}
