package com.android.dev.pokecard.presenters;

import android.os.AsyncTask;

import com.squareup.picasso.Downloader;

import java.net.URL;

/**
 * Created by paulg on 13/12/2017.
 */

public class MyTask extends android.os.AsyncTask<URL, Integer, Long> {
    protected Long doInBackground(URL... urls) {
        int count = urls.length;
        long totalSize = 0;

        return totalSize;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Long result) {
    }
}