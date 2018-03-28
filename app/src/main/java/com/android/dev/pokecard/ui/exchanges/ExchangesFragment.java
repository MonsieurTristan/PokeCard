package com.android.dev.pokecard.ui.exchanges;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.dev.pokecard.BaseFragment;
import com.android.dev.pokecard.R;
import com.android.dev.pokecard.db.PokeCardDatabase;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
