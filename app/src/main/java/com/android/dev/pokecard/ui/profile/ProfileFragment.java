package com.android.dev.pokecard.ui.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.android.dev.pokecard.BaseFragment;
import com.android.dev.pokecard.R;

/**
 * Created by paulg on 27/03/2018.
 */

public class ProfileFragment extends BaseFragment {

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    protected int currentLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}
