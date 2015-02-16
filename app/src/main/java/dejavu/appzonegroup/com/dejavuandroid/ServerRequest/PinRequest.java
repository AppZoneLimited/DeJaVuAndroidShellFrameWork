package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import dejavu.appzonegroup.com.dejavuandroid.JSONReader.PinRequestJSONReader;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class PinRequest {

    private Context mContext;
    private onPinRequest mPinRequest;

    public interface onPinRequest {
        public void onPinRequested();//server successfully sent pin to the user

        public void onPinRequestDenied();//server reject request to give user pin

        public void onRequestFailed();//internet access failure
    }

    public PinRequest(Context context, onPinRequest pinRequest, String phoneNumber) {
        mContext = context;
        mPinRequest = pinRequest;
        sendNumberToServer(phoneNumber);
    }

    private void sendNumberToServer(String phoneNumber) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                new PinRequestJSONReader(result, mPinRequest);
                // read response from server to make sure that
            }
        }.execute();
    }
}
