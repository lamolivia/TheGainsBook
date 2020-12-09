package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents the stats of a recorded workout
public class Workout implements Writable {

    private static final int MIN_IN_AN_HR = 60;

    private String date;   // in format MM/DD/YYYY
    private Double exerciseMET;
    private String typeofExercise;
    private int heartRate;
    private Double time;    // in minutes
    private Double weight; // in kilograms
    private Double caloriesBurned;

    // EFFECTS: constructs workout with given input statistics
    public Workout(String date, Double exerciseMET, String type, int heartRate, Double time, Double weight) {
        this.date = date;
        this.exerciseMET = exerciseMET;
        this.typeofExercise = type;
        this.heartRate = heartRate;
        this.time = time;
        this.weight = weight;
        caloriesBurned = 0.0;
    }

    // getters
    public String getDate() {
        return date;
    }

    public Double getExerciseMET() {
        return exerciseMET;
    }

    public String getTypeofExercise() {
        return typeofExercise;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public double getTime() {
        return time;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getCaloriesBurned() {
        return caloriesBurned;
    }

    // MODIFIES: this
    // EFFECTS: sets calorie burn to c
    public void setCaloriesBurned(double c) {
        this.caloriesBurned = c;
    }


    // MODIFIES: this
    // EFFECTS: calculates calories burned in a workout and stores the value in caloriesBurned,
    // returning value
    public double calorieBurnCalculator() {
        double minToHours = time / MIN_IN_AN_HR;
        double calsBurned = exerciseMET * weight * minToHours;
        caloriesBurned = calsBurned;
        return calsBurned;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("met", exerciseMET);
        json.put("type", typeofExercise);
        json.put("heart rate", heartRate);
        json.put("time", time);
        json.put("weight", weight);
        json.put("date", date);
        return json;
    }
}
