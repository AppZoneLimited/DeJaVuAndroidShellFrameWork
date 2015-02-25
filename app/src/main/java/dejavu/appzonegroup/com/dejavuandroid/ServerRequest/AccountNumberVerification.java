package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.AccountNumberVerificationListener;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;

/**
 * Created by CrowdStar on 2/24/2015.
 */
public class AccountNumberVerification extends AsyncTask {

    private Context mContext;
    private AccountNumberVerificationListener mVerificationListener;

    public AccountNumberVerification(AccountNumberVerificationListener mListener, Context context) {
        mVerificationListener = mListener;
        mContext = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        Uri uri = new Uri.Builder()
                .scheme("http")
                .path("")
                .authority("google.com")
                .appendQueryParameter("accountNumber", params[0].toString())
                .appendQueryParameter("phone", new UserDetailsSharePreferences(mContext).getUserPhoneNumber())
                .build();
        HttpPost httpPost = new HttpPost(uri.toString());
        HttpClient httpClient = new DefaultHttpClient();
        ResponseHandler<String> handler = new BasicResponseHandler();
        try {
            return httpClient.execute(httpPost, handler);
        } catch (IOException e) {
            return ServerResponseCodes.UNEXPECTED_ERROR;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        if (Integer.parseInt(o.toString()) == ServerResponseCodes.UNEXPECTED_ERROR) {
            mVerificationListener.onRequestFailed();
        } else if (Integer.parseInt(o.toString()) == ServerResponseCodes.DENY_REQUEST) {
            mVerificationListener.onAccountVerificationDenied();
        } else {
            mVerificationListener.onAccountVerified();
        }
    }
}
