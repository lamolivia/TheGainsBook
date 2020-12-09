//package ui;
//
//import model.ExerciseLog;
//import model.Workout;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//// The Gains Book application
//public class TheGainsBookApp {
//
//    private static final String JSON_STORE = "./data/myLog.json";
//    private ExerciseLog myWorkouts;
//    private Scanner input;
//    private boolean isOn;  // represents if program is still running
//    private int selection; // user selection out of given options
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//    // EFFECTS: runs the application
//    public TheGainsBookApp() {
//        myWorkouts = new ExerciseLog("your log");
//        input = new Scanner(System.in);
//        isOn = true;
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//
//        while (isOn()) {
//            executeRequests();
//        }
//    }
//
//    // EFFECTS: returns true if program still running; false if not
//    public boolean isOn() {
//        return isOn;
//    }
//
//    // EFFECTS: performs action for given user selection
//    private void executeRequests() {
//        try {
//            intro();
//        } catch (InputMismatchException e) {
//            System.out.println("invalid option.\n");
//            input = new Scanner(System.in);
//            return;
//        }
//
//        selections();
//
//        exit();
//    }
//
//
//    // EFFECTS: runs intro text and gives options for actions
//    private void intro() {
//        System.out.println("Welcome to The Gains Book! How can we help you today?");
//        System.out.println("Please select one of the following:");
//        System.out.println("- '1' for Add Workout");
//        System.out.println("- '2' for View Workout");
//        System.out.println("- '3' for View All Past Workouts");
//        System.out.println("- '4' for Calculate Calories Burned");
//        System.out.println("- '5' for Remove Workout");
//        System.out.println("- '6' for Save your log");
//        System.out.println("- '7' for Load your log");
//        selection = input.nextInt();
//    }
//
//    private void selections() {
//        if (selection == 1) {
//            selectionOneAddWorkout();
//        }
//
//        if (selection == 2) {
//            selectionTwoViewWorkout();
//        }
//
//        if (selection == 3) {
//            selectionThreeViewAll();
//        }
//
//        if (selection == 4) {
//            selectionFourCalBurn();
//        }
//
//        if (selection == 5) {
//            selectionFiveRemoveWorkout();
//        }
//
//        if (selection == 6) {
//            selectionSixSaveLog();
//        }
//
//        if (selection == 7) {
//            selectionSevenLoadLog();
//        }
//    }
//
//    // EFFECTS: asks user for workout stats and adds workout to log
//    private void selectionOneAddWorkout() {
//        System.out.println("Please input date of the workout (in form MM/DD/YYYY):");
//        String date = input.next();
//        System.out.println("MET of the exercise:");
//        double met = input.nextDouble();
//        System.out.println("Type of exercise:");
//        String type = input.next();
//        System.out.println("Heart rate at end of workout:");
//        int heartrate = input.nextInt();
//        System.out.println("Length of workout (in min):");
//        double time = input.nextInt();
//        System.out.println("Your current weight (in kg):");
//        double weight = input.nextDouble();
//
//        Workout w = new Workout(date, met, type, heartrate, time, weight);
//        myWorkouts.addWorkout(w);
//
//        System.out.println("Workout added!");
//
//    }
//
//    // EFFECTS: asks user for date of workout, returns workout of given date; otherwise says no workout found
//    private void selectionTwoViewWorkout() {
//        System.out.println("Please select the date of the workout you wish to view (in form MM/DD/YYYY)");
//        String date = input.next();
//        Workout viewed = myWorkouts.findWorkout(date);
//        if (viewed == null) {
//            System.out.println("no workout found on that date");
//        } else {
//            System.out.println("On " + viewed.getDate() + ":");
//            System.out.println("Your exercise MET was " + viewed.getExerciseMET() + " for "
//                    + viewed.getTypeofExercise());
//            System.out.println("You exercised for " + viewed.getTime() + " minutes and had a final heart rate of "
//                    + viewed.getHeartRate());
//            if (viewed.getCaloriesBurned() == 0) {
//                System.out.println("you have not yet calculated calorie burn");
//            } else {
//                System.out.println("Your calorie burn was " + viewed.getCaloriesBurned() + " calories");
//            }
//        }
//    }
//
//    // EFFECTS: prints out list of all input workouts
//    private void selectionThreeViewAll() {
//        if (myWorkouts.getWorkouts().size() == 0) {
//            System.out.println("log currently empty");
//        } else {
//            for (Workout w : myWorkouts.getWorkouts()) {
//                System.out.println("You exercised on " + w.getDate() + " doing activity " + w.getTypeofExercise()
//                        + " for " + w.getTime() + " minutes");
//            }
//        }
//    }
//
//    // EFFECTS: calculates calorie burn for workout with input date; otherwise says workout not found
//    private void selectionFourCalBurn() {
//        System.out.println("Please select the date of the workout to calculate calorie burn for "
//                + "(in format MM/DD/YYYY)");
//        String date = input.next();
//        Workout calWorkout = myWorkouts.findWorkout(date);
//        if (calWorkout == null) {
//            System.out.println("Workout of that date not found");
//        } else {
//            double calories = calWorkout.calorieBurnCalculator();
//            calWorkout.setCaloriesBurned(calories);
//            System.out.println("Calorie burn is calculated to be: "
//                    + calories + ". Great job!");
//            System.out.println(calories);
//        }
//    }
//
//    // EFFECTS: removes workout of given date; otherwise says workout not found
//    private void selectionFiveRemoveWorkout() {
//        System.out.println("Please select the date of the workout you wish to remove (in format MM/DD/YYYY)");
//        String date = input.next();
//        Workout delWorkout = myWorkouts.findWorkout(date);
//        if (delWorkout == null) {
//            System.out.println("Workout of that date not found");
//        } else {
//            myWorkouts.getWorkouts().remove(delWorkout);
//            System.out.println("Workout has been removed!");
//        }
//
//    }
//
//    // EFFECTS: saves the exercise log to file
//    private void selectionSixSaveLog() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(myWorkouts);
//            jsonWriter.close();
//            System.out.println("Saved " + myWorkouts.getName() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads exercise log from file
//    protected void selectionSevenLoadLog() {
//        try {
//            myWorkouts = jsonReader.read();
//            System.out.println("Loaded " + myWorkouts.getName() + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//    // EFFECTS: prints exit text, asks user if they would like to perform another action
//    private void exit() {
//        System.out.println();
//        System.out.println("Perform another action? (Y/N)");
//        String answer = input.next();
//        if (answer.equalsIgnoreCase("N")) {
//            System.out.println("Thanks for using The Gains Book! Stay healthy!");
//            isOn = false;
//        }
//
//        if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
//            System.out.println("Invalid answer, please select 'Y' or 'N'");
//            exit();
//        }
//    }
//
//
//}
