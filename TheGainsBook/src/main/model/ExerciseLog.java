package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents workouts recorded by user
public class ExerciseLog implements Writable {

    private String name;
    private List<Workout> workouts;

    // EFFECTS: constructs exercise log with empty workout list
    public ExerciseLog(String name) {

        this.name = name;
        workouts = new ArrayList<>();
    }

    //getters
    public String getName() {
        return name;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    // MODIFIES: this
    // EFFECTS: if workout being added has same date/time as one already in log,
    //          throws DuplicateWorkoutException
    //          otherwise adds workout to list of workouts in log
    public void addWorkout(Workout w) throws DuplicateWorkoutException {

        Boolean notRepeat = true;

        for (Workout workout : workouts) {
            if (workout.getDate().equals(w.getDate())) {
                notRepeat = false;
            }
        }

        if (notRepeat) {
            workouts.add(w);
        } else {
            throw new DuplicateWorkoutException();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes workout with given date if in list and returns true; else returns false
    public boolean removeWorkout(String d) {
        for (Workout w : workouts) {
            if (w.getDate().equals(d)) {
                workouts.remove(w);
                return true;
            }
        }

        return false;
    }

    // EFFECTS: returns workout with given date
    public Workout findWorkout(String d) {
        for (Workout w : workouts) {
            if (w.getDate().equals(d)) {
                return w;
            }
        }
        return null;

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("workouts", workoutsToJson());
        return json;
    }

    // EFFECTS: returns workouts in this exercise log as a JSON array
    private JSONArray workoutsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Workout w : workouts) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }
}
