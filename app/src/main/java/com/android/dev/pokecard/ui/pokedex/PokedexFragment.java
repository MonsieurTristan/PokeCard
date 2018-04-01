package com.android.dev.pokecard.ui.pokedex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.dev.pokecard.BaseFragment;
import com.android.dev.pokecard.R;

import butterknife.ButterKnife;

/**
 * Created by paulg on 01/04/2018.
 */

public class PokedexFragment extends BaseFragment {
    @Override
    protected int currentLayout() {
        return R.layout.fragment_pokedex;
    }

    public static PokedexFragment newInstance() {
        return new PokedexFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(currentLayout(), container, false);
        //mProgress = view.findViewById(R.id.progressBar);
        ButterKnife.bind(this, view);

        return view;
    }
}
