package com.android.dev.pokecard.utils;

import android.view.View;

import java.util.List;

/**
 * Created by paulg on 04/04/2018.
 */

public class Collections {

    public static boolean notEmpty(List<?> list) {
        return list != null && list.size() > 0;
    }

    public static boolean notEmpty(Object[] list) {
        return list != null && list.length > 0;
    }

    public static boolean notNull(View... args) {
        for(View v : args)
            if (v == null) return false;
        return true;
    }
}
