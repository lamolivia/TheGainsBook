package persistence;

import model.DuplicateWorkoutException;
import model.ExerciseLog;
import model.Workout;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// I modelled this test off of https://github.com/stleary/JSON-java
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ExerciseLog log = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExerciseLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExerciseLog.json");
        try {
            ExerciseLog log = reader.read();
            assertEquals("my log", log.getName());
            assertEquals(0, log.getWorkouts().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExerciseLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExerciseLog.json");
        try {
            ExerciseLog log = reader.read();
            assertEquals("my log", log.getName());
            List<Workout> workouts = log.getWorkouts();
            assertEquals(2, workouts.size());
            checkWorkout("10/19/2020", 5.0, "swimming", 90, 100.0, 60.0, workouts.get(0));
            checkWorkout("04/04/2000", 7.5, "volleyball", 110, 60.0, 65.0, workouts.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}

