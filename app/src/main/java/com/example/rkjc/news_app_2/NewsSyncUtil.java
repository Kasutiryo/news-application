package com.example.rkjc.news_app_2;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

public class NewsSyncUtil {

    private static final int SYNC_INTERVAL_SECONDS = 10;
    private static final int SYNC_FLEX_SECONDS = SYNC_INTERVAL_SECONDS / 10;
    private static final String SYNC_JOB_TAG = "sync_news_tag";
    private static boolean sInitalized;

    synchronized static void scheduleFireBaseJobDispatcherSync(@NonNull final Context context) {

        if (sInitalized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job syncNewsJobs = dispatcher.newJobBuilder()
                .setService(NewsFirebaseJobService.class)
                .setTag(SYNC_JOB_TAG)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(SYNC_INTERVAL_SECONDS, SYNC_INTERVAL_SECONDS + SYNC_FLEX_SECONDS))
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(syncNewsJobs);

        sInitalized = true;
    }
}
