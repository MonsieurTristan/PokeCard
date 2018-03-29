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
import android.widget.Toast;

import com.android.dev.pokecard.BaseFragment;
import com.android.dev.pokecard.PokeCardApplication;
import com.android.dev.pokecard.R;
import com.android.dev.pokecard.models.facebook.User;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by paulg on 27/03/2018.
 */

public class ProfileFragment extends BaseFragment {

    String id = "e";

    private User mUser;

    PokeCardApplication pokeCardApplication;

    @BindView(R.id.userProfileName)
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
        ButterKnife.bind(this, view);
        getUserInformations();

        //mUserNameView.setText(id);
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
                mUser = pokeCardApplication.get().getmDataBase().userDao().getUser();
                id = pokeCardApplication.get().getmDataBase().userDao().getUser().getIdFacebook();
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                Toast toast = Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT); toast.show();
                initUI();

            }
        }.execute();
    }

    private void initUI () {
        mUserNameView.setText(mUser.getFirst_name());

        /*Picasso.with(getActivity()).load(mUser.getPic_url())
                .into(mProfilePicView);*/
    }

}
