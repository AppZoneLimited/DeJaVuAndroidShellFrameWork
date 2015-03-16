package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.FileChooserListener;
import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;

/**
 * Created by CrowdStar on 3/9/2015.
 */
public class DJV_FileChooser extends TextView implements View.OnClickListener {


    static private ArrayList<UI_Model> ui_models;
    FileChooserListener mListener;

    public void setViewAttribute(String attributeDict) {
        ui_models = new AttributeDefiner().AttributeReader(attributeDict);
    }


    public void setDefaultAttribute() {
        setText(getCustomViewAttribute().get(0).getName());
        setId(getCustomViewAttribute().get(0).getId());
        setTextColor(Color.BLACK);
    }


    public ArrayList<UI_Model> getCustomViewAttribute() {
        return ui_models;
    }

    public DJV_FileChooser(Context context) {
        super(context);
        ui_models = new ArrayList<>();
    }
    public DJV_FileChooser(Context context, String attrs, FileChooserListener listener) {
        super(context);
        mListener = listener;
        setViewAttribute(attrs);
        setDefaultAttribute();
        setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        mListener.openFileChooser(v);
    }
}
