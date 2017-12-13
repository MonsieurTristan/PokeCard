package com.android.dev.pokecard.presenters;

import android.content.Context;

import com.android.dev.pokecard.views.viewsinterfaces.MainView;

/**
 * Created by iem on 15/11/2017.
 */

public class MainPresenter {
    private final Context context;
    private final MainView mainView;

    public MainPresenter(Context context, MainView mainView) {
        this.context = context;
        this.mainView = mainView;

    }
}
