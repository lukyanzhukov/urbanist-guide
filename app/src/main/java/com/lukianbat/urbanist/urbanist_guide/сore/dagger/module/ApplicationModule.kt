package com.lukianbat.urbanist.urbanist_guide.сore.dagger.module

import com.lukianbat.urbanist.urbanist_guide.feature.list.di.PlaceListNetworkModule
import com.lukianbat.urbanist.urbanist_guide.feature.list.presentation.PlaceListActivity
import com.lukianbat.urbanist.urbanist_guide.feature.map.di.MapNetworkModule
import com.lukianbat.urbanist.urbanist_guide.feature.map.presentation.MapsActivity
import com.lukianbat.urbanist.urbanist_guide.feature.map.presentation.ViewModelModule
import com.lukianbat.urbanist.urbanist_guide.feature.start.StartActivity
import com.lukianbat.urbanist.urbanist_guide.сore.dagger.scope.ActivityScope
import com.lukianbat.urbanist.urbanist_guide.сore.data.di.RoomModule
import com.lukianbat.urbanist.urbanist_guide.сore.network.RetrofitModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        RetrofitModule::class,
        PreferenceModule::class,
        ViewModelModule::class,
        RoomModule::class
    ]
)
interface ApplicationModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [PlaceListNetworkModule::class]
    )
    fun PlaceListActivityInjector(): PlaceListActivity

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [MapNetworkModule::class]
    )
    fun mapActivityInjector(): MapsActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun startActivityInjector(): StartActivity

}