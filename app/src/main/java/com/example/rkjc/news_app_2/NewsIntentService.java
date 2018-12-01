package com.example.rkjc.news_app_2;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Intent Service class to help determine the action when the user
 * is asked to sync or not.
 */
public class NewsIntentService extends IntentService {

    public NewsIntentService() {
        super("NewsIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        NewsSyncTask.options(this, action, this.getApplication());
    }
}
