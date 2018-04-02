package com.android.dev.pokecard.models.facebook;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by paulg on 28/03/2018.
 */

public class Facebook {

    public static final String ID_FACEBOOK = "id";
    public static final String FIRST_NAME = "name";


    public static final String TAG = Facebook.class.getSimpleName();

    public static Bundle getData(JSONObject object, List<String> args, boolean pic) {
        Bundle bundle = new Bundle();

        try {
            String id = object.getString("id");
            bundle.putString("id", id);

            if(pic) {
                URL profile_pic;
                try {
                    profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                    bundle.putString("profile_pic", profile_pic.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            for(String arg : args) {
                if (object.has(arg))
                    bundle.putString(arg, object.getString(arg));
            }

//            prefUtil.saveFacebookUserInfo(object.getString("name"),
//                    object.getString("last_name"),object.getString("email"),
//                    object.getString("gender"), profile_pic.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bundle;
    }

    public static User getUser(JSONObject object) {
        User user = new User();
        try {
            if (object.has(ID_FACEBOOK)) user.id = object.getString(ID_FACEBOOK);
            if (object.has(FIRST_NAME)) user.name = object.getString(FIRST_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
