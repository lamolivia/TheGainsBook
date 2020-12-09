package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseLogTest {

    ExerciseLog e;
    Workout w1;
    Workout w2;
    Workout w3;

    @BeforeEach
    public void SetUp() {
        e = new ExerciseLog("Your log");
        w1 = new Workout("04/04/2000", 7.5, "Elliptical", 85, 45.0, 88.1);
        w2 = new Workout("08/24/2004", 8.8, "Strength", 90, 120.0, 100.35);
        w3 = new Workout("01/01/1999", 3.5, "Walking", 70, 20.0, 75.0);
        try {
            e.addWorkout(w1);
        } catch (DuplicateWorkoutException e) {
            e.getMessage();
        }
        try {
            e.addWorkout(w2);
        } catch (DuplicateWorkoutException e) {
            e.getMessage();
        }
        try {
            e.addWorkout(w3);
        } catch (DuplicateWorkoutException e) {
            e.getMessage();
        }

    }

    @Test
    public void testGetName() {
        assertEquals("Your log", e.getName());
    }

    @Test
    public void testGetWorkouts() {
        List<Workout> l = e.getWorkouts();
        assertEquals(l.size(), 3);
        assertTrue(l.contains(w1));
        assertTrue(l.contains(w2));
        assertTrue(l.contains(w3));
    }

    @Test
    public void testAddWorkoutNoException() {

        Workout w = new Workout("00/00/2000", 7.5, "Elliptical", 85,
                45.0, 88.1);

        try {
            e.addWorkout(w);
           //expected
        } catch (DuplicateWorkoutException e) {
           fail("should not throw");
        }

        assertEquals(e.getWorkouts().get(3).getDate(), "00/00/2000");
        assertEquals(e.getWorkouts().get(3).getExerciseMET(), 7.5);
        assertEquals(e.getWorkouts().get(3).getTypeofExercise(), "Elliptical");
        assertEquals(e.getWorkouts().get(3).getHeartRate(), 85);
        assertEquals(e.getWorkouts().get(3).getTime(), 45.0);
        assertEquals(e.getWorkouts().get(3).getWeight(), 88.1);

    }

    @Test
    public void testAddWorkoutRepeatWorkoutThrowException() {
        try {
            e.addWorkout(w1);
            fail("workout of this date already exists");
        } catch (DuplicateWorkoutException e) {
            //expected
        }
    }



    @Test
    public void testRemoveWorkout() {

        assertEquals(true, e.removeWorkout("08/24/2004"));
        assertEquals(2, e.getWorkouts().size());
        assertEquals(false, e.removeWorkout("08/24/2004"));

        assertEquals(true, e.removeWorkout("01/01/1999"));
        assertEquals(1, e.getWorkouts().size());
        assertEquals(false, e.removeWorkout("01/01/1999"));

        assertEquals(true, e.removeWorkout("04/04/2000"));
        assertEquals(0, e.getWorkouts().size());
        assertEquals(false, e.removeWorkout("04/04/2000"));
    }

    @Test
    public void testFindWorkout() {
        Workout found = e.findWorkout("08/24/2004");
        assertEquals("08/24/2004", found.getDate());
        Workout empty = e.findWorkout("00/00/0000");
        assertEquals(null, empty);
    }

}
