package ui;

import model.ExerciseLog;
import model.Workout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents menu for when user presses calorie burn button
public class CalorieBurnGUI implements ActionListener {

    private SoundPlayer soundPlayer;
    private String sound;
    private ExerciseLog exerciseLog;
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField calorieDate;


    // EFFECTS: constructs menu page for calorie burn calculator
    public CalorieBurnGUI(ExerciseLog exerciseLog) {

        sound = "lib/bloop_x (1).wav";
        soundPlayer = new SoundPlayer(sound);
        this.exerciseLog = exerciseLog;

        frame = new JFrame();

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1, 2, 2));
        label = new JLabel("Calculate calorie burn");
        panel.add(label);

        frame.add(panel, BorderLayout.NORTH);
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setTitle("THE GAINS BOOK");
        frame.pack();
        frame.setVisible(true);

        setCalories();

        JButton enter = new JButton("Calculate!");
        enter.setBackground(Color.GRAY);
        enter.setOpaque(true);
        enter.setActionCommand("Calculate!");
        enter.addActionListener(this);
        panel.add(enter);

        frame.setVisible(true);

    }


    // MODIFIES: this
    // EFFECTS: generates text box for user to date of workout for calorie calculator
    protected void setCalories() {

        JLabel label = new JLabel("Input date of workout (in form \"MM/DD/YYYY at 00:00 AM/PM\"):");
        label.setBounds(10, 20, 80, 20);
        panel.add(label);

        calorieDate = new JTextField(20);
        calorieDate.setBounds(100, 20, 165, 20);
        panel.add(calorieDate);
    }

    // MODIFIES: this
    // EFFECTS: calculates calorie burn and stores amount in workout; tells user calculated amount on menu
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Calculate!")) {

            soundPlayer.playSound(sound);

            Workout w;
            JLabel result;

            String date = calorieDate.getText();

            if (exerciseLog.findWorkout(date) != null) {
                w = exerciseLog.findWorkout(date);
                Double calorieBurn = w.calorieBurnCalculator();

                result = new JLabel("You burned " + calorieBurn + " calories on " + date + "! Great job!");
                panel.add(result);


                Icon imgIcon = new ImageIcon("lib/gains.gif");
                JLabel pic = new JLabel(imgIcon);
                pic.setBounds(668, 45, 10, 10);
                frame.getContentPane().add(pic);
                pic.setVisible(true);

            } else {
                result = new JLabel("Workout not found");
                panel.add(result);
            }

            panel.revalidate();
            panel.repaint();

            calorieDate.setText("");


        }
    }


}
