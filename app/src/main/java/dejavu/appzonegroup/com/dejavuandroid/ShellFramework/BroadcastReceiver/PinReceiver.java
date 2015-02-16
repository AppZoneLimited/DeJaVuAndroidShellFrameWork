package dejavu.appzonegroup.com.dejavuandroid.ShellFramework.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Objects;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class PinReceiver extends BroadcastReceiver {

    private onPinReceivedListener mPinReceivedListener;

    public interface onPinReceivedListener

    {
        public void onPinReceived(String pin);
    }

    public PinReceiver() {
        super();
    }

    public PinReceiver(onPinReceivedListener pinReceivedListener) {
        mPinReceivedListener = pinReceivedListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle pudsBundle = intent.getExtras();
        Object[] puds = (Object[]) pudsBundle.get("pdus");
        SmsMessage messages = SmsMessage.createFromPdu((byte[]) puds[0]);
        if (messages.getMessageBody().startsWith("key")) {
            abortBroadcast();
            mPinReceivedListener.onPinReceived(messages.getMessageBody());
        } else {
            Toast.makeText(context, messages.getDisplayMessageBody(), Toast.LENGTH_LONG).show();
        }
    }
}
