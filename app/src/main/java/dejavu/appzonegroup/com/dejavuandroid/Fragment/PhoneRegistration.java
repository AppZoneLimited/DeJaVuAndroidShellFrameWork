package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import dejavu.appzonegroup.com.dejavuandroid.Constant.FlowConstant;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.onPinReceivedListener;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.onPinRequest;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.pinVerificationListener;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.PinRequest;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.VerifyPin;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.BroadcastReceiver.PinReceiver;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.UserPhoneDetails.GeneralPreference;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.UserPhoneDetails.UserDetailsFromPhone;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

public class PhoneRegistration extends Fragment implements onPinRequest, onPinReceivedListener, pinVerificationListener {
    private EditText phoneEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.phone_number_fragment, container, false);
        phoneEditText = (EditText) rootView.findViewById(R.id.phone_number_edit_view);
        phoneEditText.setText(new UserDetailsFromPhone(getActivity()).getPhoneNumber());
        rootView.findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PinRequest(getActivity(), PhoneRegistration.this, phoneEditText.getText().toString());
            }
        });
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_generic_flow, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPinRequested() {
        new PinReceiver(PhoneRegistration.this);
    }

    @Override
    public void onPinRequestDenied() {
        // do something
    }

    @Override
    public void onRequestFailed() {
        new VerifyPin("", PhoneRegistration.this);
    }

    @Override
    public void onPinReceived(String pin) {
        //new VerifyPin("", GenericFlow.this);
    }

    @Override
    public void onPinValid() {
        // do something
        //new UserDetailsSharePreferences(GenericFlow.this).setPhoneNumber(phoneEditText.getText().toString());
    }

    @Override
    public void onInvalidPin() {
        // do something
    }

    @Override
    public void onPinVerificationFailed() {
        if (GeneralPreference.getFlowType(getActivity()) == FlowConstant.BANK_FLOW) {
            new FragmentChanger(getFragmentManager(),new PasswordPinAuth());
        } else if (GeneralPreference.getFlowType(getActivity()) == FlowConstant.GENERIC_FLOW) {
            new FragmentChanger(getFragmentManager(),new ProfileDetails());
        } else {
            new ShowMessage(getActivity(), "Wrong flow", Toast.LENGTH_LONG);
        }
    }
}
