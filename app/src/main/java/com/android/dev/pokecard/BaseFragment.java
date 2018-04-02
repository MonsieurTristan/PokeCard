package com.android.dev.pokecard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by paulg on 27/03/2018.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;
    private ProgressBar mProgress;

    @LayoutRes
    protected abstract int currentLayout();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(currentLayout(), container, false);
        //mUnbinder = ButterKnife.bind(this, view);
        //mProgress = view.findViewById(R.id.progressBar);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected void onLoading() {
        if (mProgress != null) {
            mProgress.setVisibility(View.VISIBLE);
        }
    }

    public void addFragment(int container, Fragment f) {
        getFragmentManager().beginTransaction()
                .add(container, f)
                .commit();
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }
}
