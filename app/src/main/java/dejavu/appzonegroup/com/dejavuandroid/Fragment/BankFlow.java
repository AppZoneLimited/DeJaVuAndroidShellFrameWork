package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dejavu.appzonegroup.com.dejavuandroid.Constant.FlowConstant;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public class BankFlow extends Fragment implements View.OnClickListener {

    Button softTokenChoiceButton;
    Button hardTokenChoiceButton;
    Button cardTokenChoiceButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bank_flow, container, false);

        softTokenChoiceButton = (Button) rootView.findViewById(R.id.soft_choice);
        softTokenChoiceButton.setOnClickListener(this);

        hardTokenChoiceButton = (Button) rootView.findViewById(R.id.hard_choice);
        hardTokenChoiceButton.setOnClickListener(this);

        cardTokenChoiceButton = (Button) rootView.findViewById(R.id.debit_choice);
        cardTokenChoiceButton.setOnClickListener(this);

        showValidChoice(getArguments());

        return rootView;
    }

    public BankFlow newInstance(boolean soft, boolean hard, boolean card) {
        BankFlow bankFlow = new BankFlow();
        Bundle bundle = new Bundle();
        bundle.putBoolean("soft", soft);
        bundle.putBoolean("hard", hard);
        bundle.putBoolean("card", card);
        bankFlow.setArguments(bundle);
        return bankFlow;
    }

    public void showValidChoice(Bundle bundle) {
        if (bundle.getBoolean("soft")) {
            softTokenChoiceButton.setVisibility(View.VISIBLE);
        }
        if (bundle.getBoolean("hard")) {
            hardTokenChoiceButton.setVisibility(View.VISIBLE);
        }
        if (bundle.getBoolean("card")) {
            cardTokenChoiceButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == softTokenChoiceButton) {
            new FragmentChanger(getFragmentManager().beginTransaction(), new SoftToken());
        } else if (v == hardTokenChoiceButton) {
            new FragmentChanger(getFragmentManager().beginTransaction(), new HardToken());
        } else if (v == cardTokenChoiceButton) {
            new FragmentChanger(getFragmentManager().beginTransaction(), new DebitCard().newInstance(FlowConstant.BANK_FLOW));
        }
    }
}
