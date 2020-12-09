package persistence;

import org.json.JSONObject;

// I modelled this interface off of https://github.com/stleary/JSON-java
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
