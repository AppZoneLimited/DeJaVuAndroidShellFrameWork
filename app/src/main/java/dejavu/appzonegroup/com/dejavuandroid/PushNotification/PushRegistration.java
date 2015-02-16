package dejavu.appzonegroup.com.dejavuandroid.PushNotification;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

import dejavu.appzonegroup.com.dejavuandroid.Constant.AppConstant;
import dejavu.appzonegroup.com.dejavuandroid.Constant.ClientResponseCode;
import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.GcmSharedPreferences;

/**
 * Created by CrowdStar on 2/16/2015.
 */
public class PushRegistration {
    private GoogleCloudMessaging gcm;
    private Context mContext;
    private GoogleCloudMessagingListener gcmListener;

    public interface GoogleCloudMessagingListener {
        public void onRegistered();

        public void onRegisterationFailed();
    }

    GcmSharedPreferences gcmSharedPreferences;

    public PushRegistration(Context context, GoogleCloudMessagingListener messagingListener) {
        gcmListener = messagingListener;
        mContext = context;
        gcmSharedPreferences = new GcmSharedPreferences(context);
        if (gcmSharedPreferences.getAppPackageVersion() == gcmSharedPreferences.getAppVersion()) {

        } else {
            regId = gcmSharedPreferences.getRegistrationId();
            if (regId.trim().isEmpty()) {
                registerInBackground();
            }
        }
    }

    String regId = "";

    private void registerInBackground() {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                regId = "";
                if (gcm != null) {
                    gcm = GoogleCloudMessaging.getInstance(mContext);
                }
                assert gcm != null;
                try {
                    regId = gcm.register(AppConstant.APP_PROJECT_NUMBER);
                    return sendToBackground(regId);
                } catch (IOException e) {
                    return ClientResponseCode.REG_ID_NOT_RETURENED;
                }
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                if (0 != ServerResponseCodes.SUCCESS) {
                    gcmListener.onRegisterationFailed();
                } else {
                    new GcmSharedPreferences(mContext).setRegistrationId(regId);
                    new GcmSharedPreferences(mContext).setAppVersion(new GcmSharedPreferences(mContext).getAppPackageVersion());
                    gcmListener.onRegistered();
                }
            }
        }.execute();
    }

    public int sendToBackground(String regID) {
        Uri uri = new Uri.Builder()
                .scheme("")
                .authority("")
                .path("")
                .appendQueryParameter("request", regID)
                .build();
        HttpPost regIDHttpPost = new HttpPost(uri.toString());
        HttpClient regIdHttpClient = new DefaultHttpClient();
        ResponseHandler<String> stringResponseHandler = new BasicResponseHandler();
        try {
            int value = Integer.parseInt(regIdHttpClient.execute(regIDHttpPost, stringResponseHandler));
            return value;
        } catch (IOException e) {
            return ServerResponseCodes.NETWORK_FAILURE;
        }
    }
}
