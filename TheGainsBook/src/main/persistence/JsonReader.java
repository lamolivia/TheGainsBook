package persistence;

import model.DuplicateWorkoutException;
import model.ExerciseLog;
import model.Workout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// I modelled this class off of https://github.com/stleary/JSON-java
// Represents a reader that reads exercise log from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads exercise log from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ExerciseLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExerciseLog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses exercise log from JSON object and returns it
    private ExerciseLog parseExerciseLog(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ExerciseLog log = new ExerciseLog(name);
        addWorkouts(log, jsonObject);
        return log;
    }

    // MODIFIES: exercise log
    // EFFECTS: parses workouts from JSON object and adds them to exercise log
    private void addWorkouts(ExerciseLog log, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("workouts");
        for (Object json : jsonArray) {
            JSONObject nextWorkout = (JSONObject) json;
            addWorkout(log, nextWorkout);
        }
    }

    // MODIFIES: exercise log
    // EFFECTS: parses workout from JSON object and adds it to exercise log
    private void addWorkout(ExerciseLog log, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        Double met = jsonObject.getDouble("met");
        String type = jsonObject.getString("type");
        int heartrate = jsonObject.getInt("heart rate");
        Double time = jsonObject.getDouble("time");
        Double weight = jsonObject.getDouble("weight");
        Workout w = new Workout(date, met, type, heartrate, time, weight);
        try {
            log.addWorkout(w);
        } catch (DuplicateWorkoutException e) {
            e.getMessage(); //this should never happen because GUI inhibits adding duplicates
        }
    }
}
