package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.JSONReader.SoftTokenResponseJsonReader;


/**
 * Created by CrowdStar on 2/12/2015.
 */
public class SoftTokenAuthentication {

    private Context mContext;
    private SoftAuthenticationListener mAuthenticationListener;

    public interface SoftAuthenticationListener {
        public void onAuth();

        public void onFailedAuth();

        public void onFailedRequest();
    }

    public SoftTokenAuthentication(Context context, SoftAuthenticationListener listener, String hardKey) {
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
                new SoftTokenResponseJsonReader(s, mAuthenticationListener);
            }
        }.execute();
    }
}
