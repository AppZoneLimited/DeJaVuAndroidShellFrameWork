package dejavu.appzonegroup.com.dejavuandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.FileChooserListener;
import dejavu.appzonegroup.com.dejavuandroid.Map.UI_Type;
import dejavu.appzonegroup.com.dejavuandroid.Map.ViewMap;
import dejavu.appzonegroup.com.dejavuandroid.Map.viewControl;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_Button;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_DatePicker;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_EditText;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_FileChooser;

/**
 * Created by CrowdStar on 3/6/2015.
 */
public class UiControlTrier extends Fragment implements FileChooserListener {

    View rootView;
    UiControlTrier uiControlTrier;
    static DJV_DatePicker djv_datePicker;
    LinearLayout rootLayout;
    private ShowMessage showMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.ui_control, container, false);
        rootLayout = (LinearLayout) rootView.findViewById(R.id.root_linear_vertical);
        String controlAttribute = getArguments().getString("view");

        rootLayout.addView(viewControl.getViewById(ViewMap.getViewMap(getActivity(), controlAttribute, UiControlTrier.this), UI_Type.DJV_FileUpload));
        rootLayout.addView(viewControl.getViewById(ViewMap.getViewMap(getActivity(), controlAttribute), UI_Type.DJV_SingleLineField));
        rootLayout.addView(viewControl.getViewById(ViewMap.getViewMap(getActivity(), controlAttribute, getFragmentManager()), UI_Type.DJV_DateField));
        rootLayout.addView(viewControl.getViewById(ViewMap.getViewMap(getActivity(), controlAttribute), UI_Type.DJV_Button));

        Button button = new Button(getActivity());
        button.setText("Read values");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShowMessage(getActivity(), readOutPut(), Toast.LENGTH_LONG);
            }
        });
        rootLayout.addView(button);
        return rootView;
    }

    public UiControlTrier newInstance(String controlsAttribute) {
        uiControlTrier = new UiControlTrier();
        Bundle attributeBundle = new Bundle();
        attributeBundle.putString("view", controlsAttribute);
        uiControlTrier.setArguments(attributeBundle);
        return uiControlTrier;
    }

    public String readOutPut() {
        String output = "";
        for (int viewIndex = 0; viewIndex < rootLayout.getChildCount() - 1; viewIndex++) {
            output += viewInstance(rootLayout.getChildAt(viewIndex)) + ",";
        }
        output = output.substring(0, output.length() - 1);
        return output;
    }

    public String viewInstance(View view) {
        String jsonString = "";
        String value = "";
        if (view instanceof DJV_Button) {
            jsonString = ((DJV_Button) view).getCustomViewAttribute().get(0).getJSonString();
            value = " \"value\" : " + "" + ((DJV_Button) view).getText().toString() + ",";
        } else if (view instanceof DJV_DatePicker) {
            jsonString = ((DJV_DatePicker) view).getCustomViewAttribute().get(0).getJSonString();
            value = " \"value\" : " + "" + ((DJV_DatePicker) view).getText().toString() + ",";
        } else if (view instanceof DJV_EditText) {
            jsonString = ((DJV_EditText) view).getCustomViewAttribute().get(0).getJSonString();
            value = " \"value\" : " + "" + ((DJV_EditText) view).getText().toString() + ",";
        } else if (view instanceof DJV_FileChooser) {
            jsonString = ((DJV_FileChooser) view).getCustomViewAttribute().get(0).getJSonString();
            value = " \"value\" : " + "" + ((DJV_FileChooser) view).getText().toString() + ",";
        }
        return insertValue(jsonString, value);
    }

    public String insertValue(String string, String value) {
        string = string.substring(0, 2) + value + string.substring(2);
        return string;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ((TextView) manipView).setText(data.getData().getPath());
    }

    View manipView;

    @Override
    public void openFileChooser(View view) {
        manipView = view;
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("audio/*");
        chooseFile.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(chooseFile, "Choose a file"), 2);
    }
}
