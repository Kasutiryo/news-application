package com.example.rkjc.news_app_2;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Helper class to build the URL and make a request to the internet
 * using the URL
 */
public class NetworkUtils {

    private static final String LOG_TAG =
            NetworkUtils.class.getSimpleName();

    private final static String BASE_URL ="https://newsapi.org/v1/articles";
    private final static String PARAM_SOURCE = "source";
    private final static String PARAM_SORT_BY = "sortBy";
    private final static String PARAM_API_KEY = "apiKey";
    private final static String API_KEY = "4a8af76c847f4fa89e4d02ac8b3498ed";


    /**
     * Builds the URL
     * @return URL to News API
     */
    public static URL buildUrl() {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_SOURCE, "ign")
                .appendQueryParameter(PARAM_SORT_BY, "popularity")
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, url.toString());
        return url;
    }

    /**
     * Connects to the network with a given URL
     * @param url URL to a website
     * @return JSON String from the website it called
     * @throws IOException
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }

    }
}
