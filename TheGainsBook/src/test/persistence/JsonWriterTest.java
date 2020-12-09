package persistence;

import model.DuplicateWorkoutException;
import model.ExerciseLog;
import model.Workout;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// I modelled this test off of https://github.com/stleary/JSON-java
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ExerciseLog log = new ExerciseLog("my log");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyExerciseLog() {
        try {
            ExerciseLog log = new ExerciseLog("my log");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExerciseLog.json");
            writer.open();
            writer.write(log);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExerciseLog.json");
            log = reader.read();
            assertEquals("my log", log.getName());
            assertEquals(0, log.getWorkouts().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralExerciseLog() {
        try {
            ExerciseLog log = new ExerciseLog("my log");
            try {
                log.addWorkout(new Workout("10/19/2020", 5.0, "swimming", 90,
                        100.0, 60.0));
            } catch (DuplicateWorkoutException e) {
                e.getMessage();
            }
            try {
                log.addWorkout(new Workout("04/04/2000", 7.5, "volleyball", 110,
                        60.0, 65.0));
            } catch (DuplicateWorkoutException e) {
                e.getMessage();
            }
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExerciseLog.json");
            writer.open();
            writer.write(log);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExerciseLog.json");
            log = reader.read();
            assertEquals("my log", log.getName());
            List<Workout> workouts = log.getWorkouts();
            assertEquals(2, workouts.size());
            checkWorkout("10/19/2020", 5.0, "swimming", 90, 100, 60.0, workouts.get(0));
            checkWorkout("04/04/2000", 7.5, "volleyball", 110, 60, 65.0, workouts.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
