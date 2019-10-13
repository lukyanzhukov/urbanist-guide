package com.lukianbat.urbanist.guide.сore.dagger.module.feature

import com.lukianbat.urbanist.guide.feature.map.domain.usecases.dagger.GetRouteUseCaseModule
import com.lukianbat.urbanist.guide.feature.map.presentation.MapsActivity
import com.lukianbat.urbanist.guide.сore.dagger.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MapsModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [GetRouteUseCaseModule::class])
    fun MapsActivityInjector(): MapsActivity

}