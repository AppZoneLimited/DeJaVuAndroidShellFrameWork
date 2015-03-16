package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Fragment.DatePicker;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.DateSetListener;
import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;

/**
 * Created by CrowdStar on 3/8/2015.
 */
public class DJV_DatePicker extends TextView implements DateSetListener, View.OnClickListener {

    private FragmentManager fragmentManager;

    public DJV_DatePicker(Context context) {
        super(context);
        ui_models = new ArrayList<>();
    }

    static private ArrayList<UI_Model> ui_models;


    public void setViewAttribute(String attributeDict) {
        ui_models = new AttributeDefiner().AttributeReader(attributeDict);
    }


    public void setDefaultAttribute() {
        setText(getCustomViewAttribute().get(0).getName());
        setId(getCustomViewAttribute().get(0).getId());
        setOnClickListener(this);
    }

    public ArrayList<UI_Model> getCustomViewAttribute() {

        return ui_models;
    }

    public DJV_DatePicker(Context context, String attrs, FragmentManager manager) {
        super(context);
        fragmentManager = manager;
        setViewAttribute(attrs);
        setDefaultAttribute();
    }


    @Override
    public void onDateSet(String dateString) {
        setText(dateString);
    }

    @Override
    public void onClick(View v) {
        DatePicker datePicker = new DatePicker();
        datePicker.newInstance(DJV_DatePicker.this).show(fragmentManager, "Choose date of birth");
    }
}
