package com.example.rkjc.news_app_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class NewsFirebaseJobService extends JobService {

    private AsyncTask mFetchNewsTask;

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        mFetchNewsTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = NewsFirebaseJobService.this;
                NewsSyncTask.syncNews(context, ((NewsFirebaseJobService) context).getApplication());
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(jobParameters, false);
            }
        };

        mFetchNewsTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(final JobParameters params) {
        if (mFetchNewsTask != null) {
            mFetchNewsTask.cancel(true);
        }
        return true;
    }
}
