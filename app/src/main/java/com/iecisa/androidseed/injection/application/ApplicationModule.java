package com.iecisa.androidseed.injection.application;

import android.app.Application;
import android.content.Context;

import com.iecisa.androidseed.api.MarvelApi;
import com.iecisa.androidseed.domain.usecases.FetchHeroesUseCase;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application getApplication() {
        return mApplication;
    }

    @Provides
    Context getAppContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    FetchHeroesUseCase getFetchHeroesUseCase(MarvelApi marvelApi) {
        return new FetchHeroesUseCase(marvelApi);
    }
}
