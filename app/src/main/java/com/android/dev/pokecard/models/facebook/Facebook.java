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

    public static final String GENDER = "gender";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String BIRTHDAY = "birthday";

    public static final String TAG = Facebook.class.getSimpleName();

    public static Bundle getData(JSONObject object, List<String> args, boolean pic) {
        Bundle bundle = new Bundle();

        try {
            String id = object.getString("id");
            bundle.putString("idFacebook", id);

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

//            prefUtil.saveFacebookUserInfo(object.getString("first_name"),
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
            if (object.has(GENDER)) user.gender = object.getString(GENDER);
            if (object.has(FIRST_NAME)) user.first_name = object.getString(FIRST_NAME);
            if (object.has(LAST_NAME)) user.last_name = object.getString(LAST_NAME);
            if (object.has(EMAIL)) user.email = object.getString(EMAIL);
            if (object.has(BIRTHDAY)) user.birthdate = object.getString(BIRTHDAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
