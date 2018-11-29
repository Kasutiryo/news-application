package com.example.rkjc.news_app_2;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.NotificationCompat.Action;


public class NewsSyncTask {

    private static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    private static final String ACTION_ACCEPT_NOTIFICATION = "accept-notification";

    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 14;
    private static final int ACTION_ACCEPT_PENDING_INTENT_ID = 3;

    private static final String PRIMARY_CHANNEL_ID = "primmary_notification_channel";

    private static final int NOTIFICATION_ID = 0;

    private static final int SYNC_PENDING_INTENT_ID = 7;


    public static void options(Context context, String action, Application application) {
        if (ACTION_ACCEPT_NOTIFICATION.equals(action)){
            new NewsItemRepository(application).insert(null);
            clearAllNotifications(context);
        } else if (ACTION_DISMISS_NOTIFICATION.equals(action)) {
            clearAllNotifications(context);
        }
    }

    public static void clearAllNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    public static void syncNews(Context context, Application application) {

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    PRIMARY_CHANNEL_ID,
                    "Sync Notification",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,PRIMARY_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_sync)
                .setContentTitle("Gaming News Now")
                .setContentText("The application wants to check the news for updates.")
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .addAction(acceptSync(context, application))
                .addAction(ignoreSync(context))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    private static Action ignoreSync(Context context) {
        Intent ignoreSyncIntent = new Intent(context, NewsIntentService.class);
        ignoreSyncIntent.setAction(ACTION_DISMISS_NOTIFICATION);
        PendingIntent ignoreSyncPendingIntent = PendingIntent.getService(context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreSyncIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Action ignoreSycnAction =
                new Action(R.drawable.ic_ignore,
                "Decline Sync",
                ignoreSyncPendingIntent);
        return ignoreSycnAction;
    }

    private static Action acceptSync(Context context, Application application) {
        Intent acceptSyncIntent = new Intent(context, NewsIntentService.class);
        acceptSyncIntent.setAction(ACTION_ACCEPT_NOTIFICATION);
        PendingIntent acceptSyncPendingIntent = PendingIntent.getService(
                context,
                ACTION_ACCEPT_PENDING_INTENT_ID,
                acceptSyncIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Action acceptSyncAction = new Action(R.drawable.ic_update,
                "Accept Sync!",
                acceptSyncPendingIntent);
        return acceptSyncAction;
    }

    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(
                context,
                SYNC_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
