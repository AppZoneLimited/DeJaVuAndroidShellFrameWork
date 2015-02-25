package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Constant.FlowConstant;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.AuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.Models.PasswordPinModel;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.PasswordPinAuthentication;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.UserPhoneDetails.GeneralPreference;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public class PasswordPinAuth extends Fragment implements AuthenticationListener {
    EditText passwordEditText;
    EditText pinEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_password_pin_auth, container, false);
        passwordEditText = (EditText) rootView.findViewById(R.id.password_field);
        pinEditText = (EditText) rootView.findViewById(R.id.pin_field);
        rootView.findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PasswordPinAuthentication(getActivity(), PasswordPinAuth.this, buildPasswordPinModel());
            }
        });
        return rootView;
    }

    @Override
    public void onAuth() {
        //use successfully login
    }

    @Override
    public void onAuthRejected() {
        new UserDetailsSharePreferences(getActivity()).setPin(pinEditText.getText().toString());
        new UserDetailsSharePreferences(getActivity()).setPassword(passwordEditText.getText().toString());
        if (GeneralPreference.getFlowType(getActivity()) == FlowConstant.BANK_FLOW) {
            new FragmentChanger(getFragmentManager(), new BankAccountNumber());
        } else {
            new FragmentChanger(getFragmentManager(), new DebitCard());

        }
    }

    private ArrayList<PasswordPinModel> buildPasswordPinModel() {
        ArrayList<PasswordPinModel> passwordPinModels = new ArrayList<>();
        PasswordPinModel model = new PasswordPinModel();
        model.setPassword(passwordEditText.getText().toString());
        model.setPin(Integer.parseInt(pinEditText.getText().toString()));
        passwordPinModels.add(model);

        return passwordPinModels;
    }
}