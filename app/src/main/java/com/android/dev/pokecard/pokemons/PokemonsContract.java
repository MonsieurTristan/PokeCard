package com.android.dev.pokecard.pokemons;

import com.android.dev.pokecard.util.BasePresenter;
import com.android.dev.pokecard.util.BaseView;

/**
 * Created by iem on 07/11/2017.
 */

public interface PokemonsContract {

    interface View extends BaseView<Presenter> {

        void setProgressIndicator(boolean active);

        void showStatistics(int numberOfIncompleteTasks, int numberOfCompletedTasks);

        void showLoadingStatisticsError();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

    }
}