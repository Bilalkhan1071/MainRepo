package persistance;

import org.json.JSONObject;

public interface Writable {
    //Source: Sample Application

    //EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
