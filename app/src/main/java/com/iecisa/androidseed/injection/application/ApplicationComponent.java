package com.iecisa.androidseed.injection.application;

import com.iecisa.androidseed.injection.presentation.PresentationComponent;
import com.iecisa.androidseed.injection.presentation.PresentationModule;
import com.iecisa.androidseed.injection.service.ServiceComponent;
import com.iecisa.androidseed.injection.service.ServiceModule;
import com.iecisa.androidseed.injection.usecase.UseCaseComponent;
import com.iecisa.androidseed.injection.usecase.UseCaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkingModule.class})
public interface ApplicationComponent {
    PresentationComponent newPresentationComponent(PresentationModule presentationModule);
    ServiceComponent newServiceComponent(ServiceModule serviceModule);
    UseCaseComponent newUseCaseComponent(UseCaseModule useCaseModule);
}
