package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutTest {

    Workout w;
    Workout w2;

    @BeforeEach
    public void SetUp() {
        w = new Workout("04/04/2000", 5.0, "Cycling", 80, 60.0, 66.0);
        w2 = new Workout("08/02/2000", 5.0, "Yoga", 60, 30.0, 66.0);
    }

    @Test
    public void testGetDate() {
        assertEquals("04/04/2000", w.getDate());
    }

    @Test
    public void testGetExerciseMET() {
        assertEquals(5.0, w.getExerciseMET());
    }

    @Test
    public void testGetTypeofExercise() {
        assertEquals("Cycling", w.getTypeofExercise());
    }

    @Test
    public void testGetHeartRate() {
        assertEquals(80, w.getHeartRate());
    }

    @Test
    public void testGetTime() {
        assertEquals(60.0, w.getTime());
    }

    @Test
    public void testGetWeight() {
        assertEquals(66.0, w.getWeight());
    }

    @Test
    public void testGetCaloriesBurned() {
        assertEquals(0.0, w.getCaloriesBurned());
    }

    @Test
    public void testSetCaloriesBurned() {
        w.setCaloriesBurned(100.5);
        assertEquals(100.5, w.getCaloriesBurned());
    }

    @Test
    public void testCalorieBurnCalculator() {
        assertEquals(330.0, w.calorieBurnCalculator());
        assertEquals(165.0, w2.calorieBurnCalculator());
    }

}