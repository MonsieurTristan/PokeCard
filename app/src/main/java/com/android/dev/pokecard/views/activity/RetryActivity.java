package com.android.dev.pokecard.views.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;

import com.android.dev.pokecard.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by paulg on 04/04/2018.
 */

public class RetryActivity extends BaseActivity {

    @BindView(R.id.btn_retry)
    Button mRetryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retry);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_retry)
    public void onRetryClick () {
        if(isOnline()) {
            Intent intent = new Intent(RetryActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
