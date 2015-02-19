package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.CardAuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.DateSetListener;
import dejavu.appzonegroup.com.dejavuandroid.Models.DebitCardModel;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public class DebitCard extends Fragment implements CardAuthenticationListener, DateSetListener {
    TextView datePickerTextView;
    private static int flowType;

    public DebitCard newInstance(int flowType) {
        DebitCard debitCard = new DebitCard();
        this.flowType = flowType;
        return debitCard;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.debit_card_reg, container, false);
        datePickerTextView = (TextView) rootView.findViewById(R.id.data_picker_text);
        datePickerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePickerInstance = new DatePicker().newInstance(DebitCard.this);
                datePickerInstance.show(getFragmentManager(), "Pick expiring date");
            }
        });

        rootView.findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FragmentChanger(getFragmentManager().beginTransaction(), new PhoneRegistration());
            }
        });
        return rootView;
    }


    @Override
    public void onCardAuthenticated() {

    }

    @Override
    public void onInvalidCardDetails() {

    }

    @Override
    public void onRequestFailed() {
        new FragmentChanger(getFragmentManager().beginTransaction(), new PhoneRegistration());
    }

    private ArrayList<DebitCardModel> buildModel(int number, int cvv, String date, int pin) {
        ArrayList<DebitCardModel> debitCardModelArrayListModels = new ArrayList<>();
        DebitCardModel debitCardModel = new DebitCardModel();
        debitCardModel.setCardNumber(number);
        debitCardModel.setCvv(cvv);
        debitCardModel.setExpDate(date);
        debitCardModel.setPin(pin);
        debitCardModelArrayListModels.add(debitCardModel);
        return debitCardModelArrayListModels;

    }

    @Override
    public void onDateSet(String dateString) {
        datePickerTextView.setText(dateString);
    }
}
