package com.lukianbat.urbanist.urbanist_guide.сore.dagger.module

import com.lukianbat.urbanist.urbanist_guide.feature.list.di.ListNetworkModule
import com.lukianbat.urbanist.urbanist_guide.feature.map.presentation.MapActivity
import com.lukianbat.urbanist.urbanist_guide.feature.list.presentation.ListActivity
import com.lukianbat.urbanist.urbanist_guide.feature.map.di.MapNetworkModule
import com.lukianbat.urbanist.urbanist_guide.feature.splash.StartActivity
import com.lukianbat.urbanist.urbanist_guide.сore.dagger.scope.ActivityScope
import com.lukianbat.urbanist.urbanist_guide.сore.network.RetrofitModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        RetrofitModule::class
    ]
)
interface ApplicationModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ListNetworkModule::class])
    fun listActivityInjector(): ListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MapNetworkModule::class])
    fun mapActivityInjector(): MapActivity

}