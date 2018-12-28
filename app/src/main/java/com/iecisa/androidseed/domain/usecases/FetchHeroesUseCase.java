package com.iecisa.androidseed.domain.usecases;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.iecisa.androidseed.R;
import com.iecisa.androidseed.api.HeroListWrapper;
import com.iecisa.androidseed.api.MarvelApi;
import com.iecisa.androidseed.domain.Factory;
import com.iecisa.androidseed.domain.SuperHero;
import com.iecisa.androidseed.injection.BaseObservable;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchHeroesUseCase extends BaseObservable<FetchHeroesUseCase.Listener> {

    public interface Listener {
        void onFetchHeroesOk(List<SuperHero> superHeroes);
        void onFetchHeroesFailed(String msg);
    }

    private final MarvelApi mMarvelApi;
    private final WeakReference<Context> contextRef;

    @Nullable
    Call<HeroListWrapper> mCall;

    public FetchHeroesUseCase(MarvelApi marvelApi, Context context) {
        this.mMarvelApi = marvelApi;
        this.contextRef = new WeakReference<>(context);
    }

    public void fetchAndNotify() {
        this.cancelCurrentFetchIfActive();

        mCall = mMarvelApi.getHeroes();
        if (mCall == null) {
            notifyFailed();
            return;
        }

        mCall.enqueue(new Callback<HeroListWrapper>() {
            @Override
            public void onResponse(@NonNull Call<HeroListWrapper> call,
                                   @NonNull Response<HeroListWrapper> response) {
                if (response.isSuccessful()) {
                    notifySucceeded(Factory.superHeroesFromHeroListWrapper(response.body()));
                } else {
                    notifyFailed();
                }
            }

            @Override
            public void onFailure(@NonNull Call<HeroListWrapper> call,
                                  @NonNull Throwable t) {
                notifyFailed();
            }
        });
    }

    private void cancelCurrentFetchIfActive() {
        if (mCall != null && !mCall.isCanceled() && !mCall.isExecuted()) {
            mCall.cancel();
        }
    }

    private void notifySucceeded(List<SuperHero> superHeroes) {
        List<SuperHero> unmodifiableQuestions = Collections.unmodifiableList(superHeroes);
        for (Listener listener : getListeners()) {
            listener.onFetchHeroesOk(unmodifiableQuestions);
        }
    }

    private void notifyFailed() {
        final String reason;
        if (contextRef.get() == null) {
            reason = contextRef.get().getString(R.string.call_failed);
        } else {
            reason = "Connection Failed";
        }
        
        for (Listener listener : getListeners()) {
            listener.onFetchHeroesFailed(reason);
        }
    }
}
