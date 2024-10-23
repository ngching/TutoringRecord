package persistence;

import org.json.JSONObject;

// code reference to the sample (JsonSeriallizationDemo)
public interface Writable {

    // EFFECTS: returns data as JSON object
    JSONObject toJson();

}
