package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

/**
 * Created by CrowdStar on 3/5/2015.
 */
public class DJV_Button extends Button implements View.OnClickListener {

    static private ArrayList<UI_Model> ui_models;


    public void setViewAttribute(String attributeDict) {
        ui_models = new AttributeDefiner().AttributeReader( attributeDict);
    }


    public void setDefaultAttribute() {
        setText(getCustomViewAttribute().get(0).getName());
        setId(getCustomViewAttribute().get(0).getId());
    }

    public ArrayList<UI_Model> getCustomViewAttribute() {
        return ui_models;
    }

    public DJV_Button(Context context) {
        super(context);
        ui_models = new ArrayList<>();
    }


    public DJV_Button(Context context, String attribute) {
        super(context);
        setViewAttribute(attribute);
        setDefaultAttribute();
        setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        new ShowMessage(getContext(), getCustomViewAttribute().get(0).getName(), Toast.LENGTH_LONG);
    }
}
