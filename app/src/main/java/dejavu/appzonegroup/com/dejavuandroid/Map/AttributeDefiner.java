package dejavu.appzonegroup.com.dejavuandroid.Map;

import android.util.AttributeSet;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;

/**
 * Created by CrowdStar on 3/16/2015.
 */
public class AttributeDefiner {


    UI_Model ui_model;
    ArrayList<UI_Model> modelArrayList;

    public ArrayList<UI_Model> AttributeReader(String jsonString) {
        ui_model = new UI_Model();
        ui_model.setJSonString(jsonString);
        modelArrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jObject = jsonArray.getJSONObject(0);
            Iterator<?> keys = jObject.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (jObject.get(key) instanceof JSONObject) {

                }
                AttributeMap(ui_model, jObject.get(key).toString(), key);
            }
            modelArrayList.add(ui_model);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return modelArrayList;
    }

    public void AttributeMap(UI_Model ui_model, String value, String key) {
        switch (key.toLowerCase()) {
            case "failureMessage":
                ui_model.setFailureMessage(value);
                break;
            case "name":
                ui_model.setName(value);
                break;
            case "id":
                ui_model.setId(Integer.parseInt(value));
                break;
            case "eventDescription":
                ui_model.setEventDescription(value);
                break;
        }
    }
}
