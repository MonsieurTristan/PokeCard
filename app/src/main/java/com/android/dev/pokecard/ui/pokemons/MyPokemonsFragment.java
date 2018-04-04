package com.android.dev.pokecard.ui.pokemons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.dev.pokecard.BaseFragment;
import com.android.dev.pokecard.R;
import com.android.dev.pokecard.adapter.PokemonsAdapter;
import com.android.dev.pokecard.manager.MyPokemonManager;
import com.android.dev.pokecard.manager.WSManager;
import com.android.dev.pokecard.models.Pokemon;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulg on 28/03/2018.
 */

public class MyPokemonsFragment extends BaseFragment implements PokemonsAdapter.OnItemListener{

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    MyPokemonManager myPokemonManager = MyPokemonManager.getInstance();


    public static MyPokemonsFragment newInstance() {

        return new MyPokemonsFragment();
    }

    @Override
    protected int currentLayout() {
        return R.layout.fragment_my_pokemons;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(currentLayout(), container, false);
        //mProgress = view.findViewById(R.id.progressBar);
        ButterKnife.bind(this, view);

        new Thread(() -> {
            onLoading();

            final List<Pokemon> pokemons = WSManager.getInstance().getPokemonByUserId();
            myPokemonManager.setMypokemons(pokemons);

            getActivity().runOnUiThread(() -> afficherPokemons(pokemons));
        }).start();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void afficherPokemons(List<Pokemon> pokemons) {
        onLoaded();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new PokemonsAdapter(getActivity(), pokemons, this));
        Toast.makeText(getActivity(),"nombre de Pokemon : "+ pokemons.size(),Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClickItem(Pokemon pokemon) {

    }

    protected void onLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    protected void onLoaded() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
