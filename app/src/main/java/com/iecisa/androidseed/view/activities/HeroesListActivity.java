package com.iecisa.androidseed.view.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.iecisa.androidseed.domain.SuperHero;
import com.iecisa.androidseed.domain.usecases.FetchHeroesUseCase;
import com.iecisa.androidseed.injection.BaseActivity;
import com.iecisa.androidseed.mvc.viewmvc.ViewMvcFactory;
import com.iecisa.androidseed.mvc.heroes.HeroesListViewMvc;
import com.iecisa.androidseed.view.dialogs.DialogsManager;
import com.iecisa.androidseed.view.dialogs.ServerErrorDialogFragment;

import java.util.List;

import javax.inject.Inject;

public class HeroesListActivity extends BaseActivity implements HeroesListViewMvc.Listener,
        FetchHeroesUseCase.Listener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = HeroesListActivity.class.getSimpleName();

    @Inject
    FetchHeroesUseCase mFetchHeroesUseCase;

    @Inject
    DialogsManager mDialogsManager;

    @Inject
    ViewMvcFactory mViewMvcFactory;

    private HeroesListViewMvc mViewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresentationComponent().inject(this);

        mViewMvc = mViewMvcFactory.newInstance(HeroesListViewMvc.class, null);
        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mViewMvc.bindSwipeRefresh(this);
        mFetchHeroesUseCase.registerListener(this);

        this.onRefresh();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mViewMvc.unregisterListener(this);
        mViewMvc.bindSwipeRefresh(null);
        mFetchHeroesUseCase.unregisterListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewMvc.unbindView();
    }

    @Override
    public void onRefresh() {
        mViewMvc.onViewStartRefreshing();
        mFetchHeroesUseCase.fetchAndNotify();
    }

    @Override
    public void onFetchHeroesOk(List<SuperHero> superHeroes) {
        mViewMvc.bindHeroes(superHeroes, this);
    }

    @Override
    public void onFetchHeroesFailed(String msg) {
        mDialogsManager.showDialogWithId(ServerErrorDialogFragment.newInstance(msg), TAG);
    }

    @Override
    public void onHeroClicked(SuperHero superHero) {
        final Intent intent = new Intent(this, HeroDetailActivity.class);
        intent.putExtra(HeroDetailActivity.HERO_EXTRA, superHero);
        this.startActivity(intent);
    }
}
