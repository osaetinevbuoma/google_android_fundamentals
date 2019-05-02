package com.modnsolutions.webpagesource;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    static String getPageSource(String url) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String pageSourceString = null;

        try {
            Uri builtUri = Uri.parse(url);
            URL websiteURL = new URL(builtUri.toString());

            // Open connection.
            urlConnection = (HttpURLConnection) websiteURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            // Create buffered reader from input source.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) return null;

            pageSourceString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();

            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d(LOG_TAG, pageSourceString);

        return pageSourceString;
    }
}
