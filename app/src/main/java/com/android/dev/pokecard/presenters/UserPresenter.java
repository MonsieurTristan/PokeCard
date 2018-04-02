package com.android.dev.pokecard.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.dev.pokecard.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

/**
 * Created by paulg on 11/12/2017.
 */

public class UserPresenter extends AppCompatActivity {
    JSONObject response, profile_pic_data, profile_pic_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("userProfile");
        Log.w("Jsondata", jsondata);
        TextView user_name = (TextView) findViewById(R.id.userName);
        ImageView user_picture = (ImageView) findViewById(R.id.profilePic);
        TextView user_email = (TextView) findViewById(R.id.email);
        TextView user_id = (TextView) findViewById(R.id.userId);

        try {
            response = new JSONObject(jsondata);
            //user_email.setText(response.get("email").toString());
            user_name.setText(response.get("name").toString());
            user_id.setText(response.get("id").toString());
            profile_pic_data = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
            Picasso.with(this).load(profile_pic_url.getString("url"))
                    .into(user_picture);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
