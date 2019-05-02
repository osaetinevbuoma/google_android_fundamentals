package com.modnsolutions.webpagesource;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class SourcePageLoader extends AsyncTaskLoader<String> {
    private String mUrl;

    public SourcePageLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getPageSource(mUrl);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
