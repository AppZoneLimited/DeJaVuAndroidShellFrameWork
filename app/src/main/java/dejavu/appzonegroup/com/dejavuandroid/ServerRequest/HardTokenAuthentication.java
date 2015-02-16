package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.JSONReader.HardTokenResponseJsonReader;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class HardTokenAuthentication {
    private Context mContext;
    private HardAuthenticationListener mAuthenticationListener;

    public interface HardAuthenticationListener {
        public void onAuth();

        public void onFailedAuth();

        public void onFailedRequest();
    }

    public HardTokenAuthentication(Context context, HardAuthenticationListener listener, String hardKey) {
        mAuthenticationListener = listener;
        mContext = context;
        sendHardToken(hardKey);
    }

    private void sendHardToken(String token) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                new HardTokenResponseJsonReader(s,mAuthenticationListener);
            }
        }.execute();
    }

}
