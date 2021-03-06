package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.DateSetListener;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.ProfileDetailsSubmissionListener;
import dejavu.appzonegroup.com.dejavuandroid.Models.UserDetailsModel;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.ProfileDetailsSubmission;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public class ProfileDetails extends Fragment implements DateSetListener, ProfileDetailsSubmissionListener {
    TextView dateTextView;
    EditText emailEditText;
    Spinner genderSpinner;
    EditText nameEditText;
    EditText otherNamesEditText;
    EditText phoneNumberEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_details, container, false);
        dateTextView = (TextView) rootView.findViewById(R.id.data_picker_text);
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker picker = new DatePicker().newInstance(ProfileDetails.this);
                picker.show(getFragmentManager(), "Date of Birth");
            }
        });


        genderSpinner = (Spinner) rootView.findViewById(R.id.gender_spinner);
        genderSpinner.setAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.gender, android.R.layout.simple_list_item_1));

        emailEditText = (EditText)rootView.findViewById(R.id.email_field);

        nameEditText = (EditText)rootView.findViewById(R.id.name_field);

        otherNamesEditText = (EditText)rootView.findViewById(R.id.other_names_fiel);

        phoneNumberEditText =(EditText)rootView.findViewById(R.id.phone_number_edit_view);

        rootView.findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ProfileDetailsSubmission(getActivity(), ProfileDetails.this, buildProfileDetails());
            }
        });
        return rootView;
    }


    private ArrayList<UserDetailsModel> buildProfileDetails() {
        ArrayList<UserDetailsModel> profileDetails = new ArrayList<>();
        UserDetailsModel model = new UserDetailsModel();
        model.setName(nameEditText.getText().toString());
        model.setDob(dateTextView.getText().toString());
        model.setEmailAddress(emailEditText.getText().toString());
        model.setGender(genderSpinner.getSelectedItemPosition());
        model.setPhoneNumber(new UserDetailsSharePreferences(getActivity()).getUserPhoneNumber());

        profileDetails.add(model);
        return profileDetails;
    }

    @Override
    public void onDateSet(String dateString) {
        dateTextView.setText(dateString);
    }

    @Override
    public void onDetailsSubmitted() {
        //save locally
    }

    @Override
    public void onDetailsSubmissionRejected() {

    }

    @Override
    public void onSubmissionFailed() {
        new FragmentChanger(getFragmentManager(), new PasswordPinAuth());
    }
}
