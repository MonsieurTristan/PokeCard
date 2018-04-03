package com.android.dev.pokecard.ui.profile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dev.pokecard.BaseFragment;
import com.android.dev.pokecard.PokeCardApplication;
import com.android.dev.pokecard.R;
import com.android.dev.pokecard.models.facebook.User;
import com.android.dev.pokecard.views.activity.MainActivity;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by paulg on 27/03/2018.
 */

public class ProfileFragment extends BaseFragment {

    private User mUser;

    @BindView(R.id.userProfileName)
    TextView mUserNameView;

    @BindView(R.id.img_profile)
    ImageView mProfilView;

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
        ButterKnife.bind(this, view);

        getUserInformations();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void getUserInformations () {
        new AsyncTask<Void, String, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                mUser = PokeCardApplication.get().getAppUser();
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                initUI();
            }
        }.execute();
    }

    private void initUI () {

        mUserNameView.setText(mUser.getName());

        Picasso.with(getActivity().getApplicationContext()).load("https://graph.facebook.com/" + mUser.getId() + "/picture?type=large")
                .into(mProfilView);

    }

    @OnClick(R.id.button_fb_disconnect)
    public void onFbDisconnectClick() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

}
