package model;

// represents exception thrown when user adds workout of date/time already in log
public class DuplicateWorkoutException extends Exception {

    // EFFECTS: constructs exception with warning message
    public DuplicateWorkoutException() {
        super("Warning: already have this workout recorded at this time");
    }
}
