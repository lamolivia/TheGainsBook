package persistence;

import model.Workout;

import static org.junit.jupiter.api.Assertions.assertEquals;

// I modelled this test off of https://github.com/stleary/JSON-java
public class JsonTest {
    protected void checkWorkout(String date, Double met, String type, int heartrate, double time, double weight, Workout workout) {
        assertEquals(date, workout.getDate());
        assertEquals(met, workout.getExerciseMET());
        assertEquals(type, workout.getTypeofExercise());
        assertEquals(heartrate, workout.getHeartRate());
        assertEquals(time, workout.getTime());
        assertEquals(weight, workout.getWeight());
    }
}

