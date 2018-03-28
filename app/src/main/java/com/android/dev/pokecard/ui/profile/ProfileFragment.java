package com.android.dev.pokecard.ui.profile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.dev.pokecard.BaseFragment;
import com.android.dev.pokecard.PokeCardApplication;
import com.android.dev.pokecard.R;
import com.android.dev.pokecard.db.PokeCardDatabase;

import butterknife.BindView;

/**
 * Created by paulg on 27/03/2018.
 */

public class ProfileFragment extends BaseFragment {

    String id = "e";

    PokeCardApplication pokeCardApplication;

    @BindView(R.id.userName)
    TextView mUserNameView;

    @BindView(R.id.profilePic)
    ImageView mProfilePicView;

    @BindView(R.id.userId)
    TextView mUserIdView;

    @BindView(R.id.button_fb_disconnect)
    Button mFbDisconnectButton;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    protected int currentLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(currentLayout(), container, false);
        //mProgress = view.findViewById(R.id.progressBar);

        new AsyncTask<Void, String, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                id = pokeCardApplication.get().getmDataBase().userDao().getbyId("42").getIdFacebook();
                //mUserNameView.setText(pokeCardApplication.get().getmDataBase().userDao().getbyId("42").getIdFacebook());
                publishProgress(id);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {

            }
        }.execute();
        mUserNameView.setText(id);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}
