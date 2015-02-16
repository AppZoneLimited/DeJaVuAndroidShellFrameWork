package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.os.AsyncTask;

import dejavu.appzonegroup.com.dejavuandroid.JSONReader.PinVerificationJsonReader;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class VerifyPin {

    private pinVerificationListener mPinVerificationListener;

    public interface pinVerificationListener {
        public void onPinValid();

        public void onInvalidPin();

        public void onPinVerificationFailed();
    }

    public VerifyPin(String pin, pinVerificationListener pinVerificationListener) {
        mPinVerificationListener = pinVerificationListener;
        sendPin(pin);
    }

    private void sendPin(String pin) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                new PinVerificationJsonReader(result, mPinVerificationListener);
            }
        }.execute();
    }

}
