package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.JSONReader.ProfileDetailsResponseJsonReader;
import dejavu.appzonegroup.com.dejavuandroid.Models.UserDetailsModel;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class ProfileDetailsSubmission {

    private Context mContext;
    private ProfileDetailsSubmissionListener mDetailsSubmissionListener;

    public interface ProfileDetailsSubmissionListener {
        public void onDetailsSubmitted();

        public void onDetailsSubmissionRejected();

        public void onSubmissionFailed();
    }

    public ProfileDetailsSubmission(Context context, ProfileDetailsSubmissionListener listener, ArrayList<UserDetailsModel> userDetailsModels) {
        mContext = context;
        mDetailsSubmissionListener = listener;
        submitDetails(userDetailsModels);
    }

    private void submitDetails(ArrayList<UserDetailsModel> userDetailsModels) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                new ProfileDetailsResponseJsonReader(s, mDetailsSubmissionListener);
            }
        }.execute();
    }
}
