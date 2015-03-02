package dejavu.appzonegroup.com.dejavuandroid.PushNotification;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import dejavu.appzonegroup.com.dejavuandroid.Constant.AppConstant;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.UserInfoDBHelper;
import dejavu.appzonegroup.com.dejavuandroid.NotificationManager.InfoNotificationManager;
import dejavu.appzonegroup.com.dejavuandroid.NotificationManager.UpdateNotiManager;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

/**
 * Created by CrowdStar on 2/16/2015.
 */
public class GcmIntentService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * Used to name the worker thread, important only for debugging.
     */
    public GcmIntentService() {
        super("GcmIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        Log.e("here", extras.getString("key"));
        String messageType = gcm.getMessageType(intent);
        if (GoogleCloudMessaging.
                MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            if (extras.getString("key").trim().equalsIgnoreCase(AppConstant.UPDATE)) {
                new UpdateNotiManager(this, extras.getString("message"));
            } else if (extras.getString("key").trim().equalsIgnoreCase(AppConstant.INFO)) {
                new UserInfoDBHelper(this).addNotification(extras.getString("subject"), extras.getString("message"));
                new InfoNotificationManager(this, extras.getString("subject"), extras.getString("message"));
            }
        }
    }
}
