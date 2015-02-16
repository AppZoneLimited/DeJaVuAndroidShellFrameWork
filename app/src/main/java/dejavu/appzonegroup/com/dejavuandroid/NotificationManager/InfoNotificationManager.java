package dejavu.appzonegroup.com.dejavuandroid.NotificationManager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import dejavu.appzonegroup.com.dejavuandroid.Activities.InfoActivity;
import dejavu.appzonegroup.com.dejavuandroid.Constant.AppConstant;
import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 2/16/2015.
 */
public class InfoNotificationManager {
    NotificationManager mNotificationManager;

    public InfoNotificationManager(Context context, String message, String sender) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .setSummaryText(sender)
                                .bigText(message))
                        .setContentText(message);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, InfoActivity.class), 0);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(AppConstant.INFO, mBuilder.build());
    }
}
