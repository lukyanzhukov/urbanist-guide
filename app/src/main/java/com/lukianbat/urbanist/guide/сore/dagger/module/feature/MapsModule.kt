package com.lukianbat.urbanist.guide.сore.dagger.module.feature

import com.lukianbat.urbanist.guide.feature.map.di.MapNetworkModule
import com.lukianbat.urbanist.guide.feature.map.presentation.MapsActivity
import com.lukianbat.urbanist.guide.сore.dagger.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MapsModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MapNetworkModule::class])
    fun MapsActivityInjector(): MapsActivity

}