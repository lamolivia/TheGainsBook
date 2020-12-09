package ui;

import model.ExerciseLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// represents the GUI for the home page
public class MainGUI implements ActionListener {

    private SoundPlayer soundPlayer;
    private String sound;
    private ExerciseLog exerciseLog;
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel label;
    private JButton displayWorkoutsButton;
    private JButton addWorkoutButton;
    private JButton calorieBurnWorkoutButton;
    private JButton saveButton;
    private JButton loadButton;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final String JSON_STORE = "./data/myLog.json";


    // EFFECTS: constructs home page
    public MainGUI() {

        sound = "lib/bloop_x (1).wav";
        soundPlayer = new SoundPlayer(sound);

        exerciseLog = new ExerciseLog("My log");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame();
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1, 2, 2));
        label = new JLabel("Welcome to The Gains Book! Please choose from the following:");
        panel.add(label);

        renderButtons();
        panel.add(displayWorkoutsButton);
        panel.add(addWorkoutButton);
        panel.add(calorieBurnWorkoutButton);
        panel.add(saveButton);
        panel.add(loadButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("THE GAINS BOOK");
        frame.pack();
        frame.setVisible(true);


    }

    // MODIFIES: this
    // EFFECTS: renders buttons for home page
    private void renderButtons() {

        renderDWButton();
        renderAddButton();
        renderCalorieButton();
        renderSaveButton();
        renderLoadButton();

    }

    // EFFECTS: renders display workouts button
    private void renderDWButton() {
        displayWorkoutsButton = new JButton("Display workouts");
        displayWorkoutsButton.setBackground(new Color(255, 183, 178));
        displayWorkoutsButton.setOpaque(true);
        displayWorkoutsButton.setActionCommand("Display workouts");
        displayWorkoutsButton.addActionListener(this);
    }

    // EFFECTS: renders add workout button
    private void renderAddButton() {
        addWorkoutButton = new JButton("Add workout");
        addWorkoutButton.setBackground(new Color(255, 218, 193));
        addWorkoutButton.setOpaque(true);
        addWorkoutButton.setActionCommand("Add workout");
        addWorkoutButton.addActionListener(this);
    }

    // EFFECTS: renders calorie calculator button
    private void renderCalorieButton() {
        calorieBurnWorkoutButton = new JButton("Calculate calorie burn for a workout");
        calorieBurnWorkoutButton.setBackground(new Color(226, 240, 203));
        calorieBurnWorkoutButton.setOpaque(true);
        calorieBurnWorkoutButton.setActionCommand("Calorie burn");
        calorieBurnWorkoutButton.addActionListener(this);
    }

    // EFFECTS: renders save workouts button
    private void renderSaveButton() {
        saveButton = new JButton("Save workouts");
        saveButton.setBackground(new Color(181, 234, 215));
        saveButton.setOpaque(true);
        saveButton.setActionCommand("Save workouts");
        saveButton.addActionListener(this);
    }

    // EFFECTS: renders load workouts button
    private void renderLoadButton() {
        loadButton = new JButton("Load workouts");
        loadButton.setBackground(new Color(199, 206, 234));
        loadButton.setOpaque(true);
        loadButton.setActionCommand("Load workouts");
        loadButton.addActionListener(this);
    }


    // EFFECTS: takes user to page based on which button pressed
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Display workouts")) {
            soundPlayer.playSound(sound);
            new DisplayWorkoutsGUI(exerciseLog);
        }

        if (e.getActionCommand().equals("Add workout")) {
            soundPlayer.playSound(sound);
            new AddWorkoutGUI(exerciseLog);
        }

        if (e.getActionCommand().equals("Calorie burn")) {
            soundPlayer.playSound(sound);
            new CalorieBurnGUI(exerciseLog);
        }

        if (e.getActionCommand().equals("Save workouts")) {
            soundPlayer.playSound(sound);
            saveWorkoutGUI();
        }

        if (e.getActionCommand().equals("Load workouts")) {
            soundPlayer.playSound(sound);
            loadWorkoutGui();
        }

    }

    // EFFECTS: saves exercise log to file
    private void saveWorkoutGUI() {

        try {
            jsonWriter.open();
            jsonWriter.write(exerciseLog);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

        JLabel label = new JLabel("Workouts saved to " + JSON_STORE + "!");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);

        frame.setVisible(true);
    }


    // EFFECTS: loads exercise log from file
    private void loadWorkoutGui() {
        try {
            exerciseLog = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        JLabel label = new JLabel("Workouts loaded from " + JSON_STORE + "!");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);

        frame.setVisible(true);
    }




}
