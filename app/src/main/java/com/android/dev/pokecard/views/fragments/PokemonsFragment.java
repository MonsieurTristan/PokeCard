package com.android.dev.pokecard.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.dev.pokecard.presenters.PokemonsPresenter;

import java.util.List;

/**
 * Created by iem on 07/11/2017.
 */

public class PokemonsFragment extends Fragment  {
    List listPokemons;
    PokemonsPresenter pokemonPresenter;

    public static PokemonsFragment newInstance() {
        return new PokemonsFragment();
    }



    /*public void setPresenter(PokemonsPresenter pokemonPresenter) {
        this.pokemonPresenter = pokemonPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        //rv.setAdapter(new PokemonRVAdapter(listPokemons));
        return rv;
    }

    @Override
    public void onResume() {
        super.onResume();
        //pokemonPresenter.start();
    }

    @Override
    public void setProgressIndicator(boolean active) {
        /*if (active) {
            mStatisticsTV.setText(getString(R.string.loading));
        } else {
            mStatisticsTV.setText("");
        }
    /*}

    @Override
    public void showStatistics(int numberOfIncompleteTasks, int numberOfCompletedTasks) {
        /*if (numberOfCompletedTasks == 0 && numberOfIncompleteTasks == 0) {
            mStatisticsTV.setText(getResources().getString(R.string.statistics_no_tasks));
        } else {
            String displayString = getResources().getString(R.string.statistics_active_tasks) + " "
                    + numberOfIncompleteTasks + "\n" + getResources().getString(
                    R.string.statistics_completed_tasks) + " " + numberOfCompletedTasks;
            mStatisticsTV.setText(displayString);
        }
    }

    @Override
    public void showLoadingStatisticsError() {
        mStatisticsTV.setText(getResources().getString(R.string.statistics_error));
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }*/
}