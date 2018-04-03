package com.android.dev.pokecard.ui.exchanges;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.dev.pokecard.BaseFragment;
import com.android.dev.pokecard.R;
import com.android.dev.pokecard.manager.WSManager;
import com.android.dev.pokecard.models.Exchange;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by paulg on 28/03/2018.
 */

public class ExchangesFragment extends BaseFragment implements ExchangeAdapter.OnItemListener{

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    @BindView(R.id.button_other_exchanges)
    Button mOtherExchangesButton;

    @BindView(R.id.button_my_exchanges)
    Button mMyExchangesButton;

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

        showExchanges();

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
            Intent intent = new Intent(getActivity(), MyPokemonsActivity.class);
            startActivity(intent);
        });
    }

    @OnClick(R.id.button_other_exchanges)
    public void onOtherExchangesClick () {
        showExchanges();
    }

    @OnClick(R.id.button_my_exchanges)
    public void onMyExchangesClick () {
        showMyExchanges();
    }

    private void showExchanges () {
        new Thread(() -> {
            final List<Exchange> exchanges = WSManager.getInstance().getExchanges();

            getActivity().runOnUiThread(() -> mRecyclerView.setLayoutManager(
                    new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
            mRecyclerView.setAdapter(new ExchangeAdapter(getActivity(), exchanges, this));
        }).start();
    }

    private void showMyExchanges () {
        new Thread(() -> {
            final List<Exchange> exchanges = WSManager.getInstance().getMyExchanges();

            getActivity().runOnUiThread(() -> mRecyclerView.setLayoutManager(
                    new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
            mRecyclerView.setAdapter(new ExchangeAdapter(getActivity(), exchanges, this));
        }).start();
    }


    @Override
    public void onClickItem(Exchange exchange) {

    }
}
