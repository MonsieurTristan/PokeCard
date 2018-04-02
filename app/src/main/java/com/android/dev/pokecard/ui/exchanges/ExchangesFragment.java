package com.android.dev.pokecard.ui.exchanges;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.dev.pokecard.BaseFragment;
import com.android.dev.pokecard.R;
import com.android.dev.pokecard.db.PokeCardDatabase;
import com.android.dev.pokecard.manager.WSManager;
import com.android.dev.pokecard.models.Pokemon;
import com.android.dev.pokecard.views.activity.MyPokemonsActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by paulg on 28/03/2018.
 */

public class ExchangesFragment extends BaseFragment {

    public static ExchangesFragment newInstance() {
        return new ExchangesFragment();
    }

    @Override
    protected int currentLayout() {
        return R.layout.fragment_exchanges;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(currentLayout(), container, false);
        //mProgress = view.findViewById(R.id.progressBar);
        ButterKnife.bind(this, view);

        initUI(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initUI (View view) {
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> {
            Toast.makeText(getActivity(),"Adding!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MyPokemonsActivity.class);
            startActivity(intent);
        });
    }


}
